package preprocess;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import static Tools.GetXRow.GetXRow;
import static Tools.WriteTxt.WriteTxt;

public class StayPoint {


    public void GetStayPoints(int index) throws IOException {
        System.out.println(index);
        // *********** 定义变量部分 ********************
        ReadTxt in = new ReadTxt();
        String filename = "D:\\10000More400\\UpdatUsersFiles\\" + String.valueOf(index) + ".txt";
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
            //  System.out.println("User" + index + "Information" + word + " " + points.get(word) + " ");
            keys[SubN++] = word;
            values[SubN2++] = points.get(word);
        }
        if(points.size()>=3) {
            int Point1 = Tools.GetMax.getMaxIndex(values);
            String P1 = keys[Point1];
            values[Point1] = 0;
            Point1 = Tools.GetMax.getMaxIndex(values);
            String P2 = keys[Point1];
            values[Point1] = 0;
            Point1 = Tools.GetMax.getMaxIndex(values);
            String P3 = keys[Point1];
//                System.out.println("P1:" + P1 ); // Cnt 最大的三个坐标
//                System.out.println("P2:" + P2 ); // Cnt 最大的三个坐标
//                System.out.println("P3：" + P3); // Cnt 最大的三个坐标

//                //********************** 写文件模块 *********************
//                String WriteFile = "D:\\10000More400\\UsersResults\\" + String.valueOf(index) + ".txt";
//                Tools.WriteTxt.WriteTxt(WriteFile, "********* 4. Points *********");
//                WriteTxt(WriteFile, "********* (1) Cnt Max Three points *********");
            String WriteFile = "D:\\10000More400\\TBHG\\" + String.valueOf(index) + ".txt";
            FileOutputStream fop = null;
            File file;
            file = new File("D:\\10000More400\\TBHG\\" + String.valueOf(index) + ".txt");
            fop = new FileOutputStream(file);
            // (1) txt文件第一行: 用户索引 记录数目
            fop.write(String.valueOf(index).getBytes()); // Write index 用户索引
            fop.write(" ".getBytes("utf-8"));
            fop.write(String.valueOf(3).getBytes()); // num 记录数目
            // 换行
            String str = "";
            str += "\r\n";
            fop.write(str.getBytes("utf-8"));

            WriteTxt(WriteFile,P1);
            WriteTxt(WriteFile,P2);
            WriteTxt(WriteFile,P3);
        }
    }
    public static void main(String[] args) throws IOException {

        StayPoint im = new StayPoint();
        for (int index = 10000; index <= 10000; index++) {
            im.GetStayPoints(index);
        }
    }
}
