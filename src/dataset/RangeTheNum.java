package dataset;

import preprocess.ReadTxt;

import java.io.IOException;
import static Tools.GetMax.getMax;
import static Tools.GetMax.getMaxIndex;
import static Tools.GetXRow.GetXRow;

public class RangeTheNum {

    public int[] GetRanges(int indexNum) throws IOException {
        int[] con = new int[indexNum];
        for(int index = 1; index <= indexNum; index++){
            ReadTxt in = new ReadTxt();
            String filename = "D:\\10000More400\\UsersOrigFiles\\" + String.valueOf(index) + ".txt";
            String Aline = GetXRow(filename, 1);
            String temp[] = Aline.split("\\s+");
            int num = Integer.valueOf(temp[1]); //用户记录行数
            con[index - 1] = num;
        }
        return con;
    }
    public static void main(String[] args) throws IOException {
        RangeTheNum in = new RangeTheNum();
        int indexNum = 10;
        int resNum = 1000;
        int[] con = in.GetRanges(indexNum);
        int[] res = new int[resNum];
        for(int i = 0;i < indexNum; i++){
            System.out.println("Con:" + con[i]);
            res[(int) Math.floor(con[i] / 100)] ++;
        }
        System.out.println( "Max:" + getMax(con));
        System.out.println( "Max Person:" + getMaxIndex(con));
        for (int i = 0; i < 100; i++)
            System.out.println(res[i]);
    }
}
