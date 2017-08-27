package Mining;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import static Tools.GetXRow.GetXRow;
import static Tools.WriteTxt.WriteTxt;

/**
 * Created by guojie on 2017/7/3.
 */
public class StayPointsOne {

    public static void main(String[] args) throws IOException {
        for (int index = 1; index <= 10000; index++) {
            System.out.println(index);
            // *********** 定义变量部分 ********************
            String filename = "D:\\10000More400\\UsersOrigFiles\\" + String.valueOf(index) + ".txt";
            String WriteFile = "D:\\10000More400UsersData\\CountPcen0.09\\" + String.valueOf(index) + ".txt";
            String Aline = GetXRow(filename, 1);
            String tmp[] = Aline.split(" ");
            int num = Integer.valueOf(tmp[1]); //用户记录行数
            HashMap<String, Integer> points = new HashMap<String, Integer>();

            for (int lineNum = 2; lineNum <= num + 1; lineNum++) {
                String temp = GetXRow(filename, lineNum);
                String temp7[] = temp.split(" ");
                String LngLat = temp7[0] + " "+temp7[1];
                String LngLatCount = temp7[2];
                int count = 1;
                if (points.containsKey(LngLat)) {//判断刚输入的单词是否已经存在
                    count = points.get(LngLat) + Integer.valueOf(LngLatCount);//如果已经存在，新的个数就在已有的个数上加访问次数
                }
                points.put(LngLat, count);//插入新的数据
            }
            // 遍历
            String[] keys = new String[points.size()];
            int[] values = new int[points.size()];
            int SubN  = 0;
            int SubN2  = 0;
            Iterator iterator = points.keySet().iterator();
            while (iterator.hasNext()) {
                String word = (String) iterator.next();
                System.out.println("User" + index + "Information" + word + " " + points.get(word) + " ");
                keys[SubN++] = word;
                values[SubN2++] = points.get(word);
            }
            int n = 0;
            for(int i =0; i< values.length;i++){
                int count = values[i];
                if( count >= 0.09 * num ){
                    n++;
                    System.out.println(keys[i]);
                    System.out.println(count);
                    WriteTxt(WriteFile, keys[i] + " "+String.valueOf(count) );
                }
            }
            WriteTxt(WriteFile, String.valueOf(index)  + " "+String.valueOf(n) );
        }
    }
}
