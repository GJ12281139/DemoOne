package dataset;

import preprocess.ReadTxt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

import static Tools.GetXRow.GetXRow;

/**
 * Created by guojie on 2017/6/20.
 */
public class GetCity {
    // Write txt files.
    public static void SaveIndexs(FileOutputStream fop, String content) throws IOException {
        fop.write(content.getBytes());
        String str = "";
        str += "\r\n";
        fop.write(str.getBytes("utf-8"));
    }
    public static void main(String[] args) throws IOException {
        ReadTxt in = new ReadTxt();
        File file,file2,file3,file4;
        file = new File("D:/10000More400/GetCity/ThreeCities.txt");
        file2 = new File("D:/10000More400/GetCity/BeijingAndXinjiang.txt");
        file3 = new File("D:/10000More400/GetCity/XinjiangAndOthers.txt");
        file4 = new File("D:/10000More400/GetCity/BeijingAndOthers.txt");
        FileOutputStream fop = new FileOutputStream(file);
        FileOutputStream fop2 = new FileOutputStream(file2);
        FileOutputStream fop3 = new FileOutputStream(file3);
        FileOutputStream fop4 = new FileOutputStream(file4);
        int CitySum[] = new int[3];
        // 计数
        int ThreeCity = 0;
        int TwoCity = 0;
        int ConBeiJingAndXinjiang = 0;
        int ConBeiJingAndQita = 0;
        int ConXinjiangAndQita = 0;

        for (int index = 1; index <= 6000; index++) {
            String filename = "D:\\10000More400\\UsersOrigFiles\\" + String.valueOf(index) + ".txt";
            String Aline = GetXRow(filename, 1);
            String temp[] = Aline.split(" ");
            int num = Integer.valueOf(temp[1]); //用户记录行数
            String a = GetXRow(filename, num + 4);
            CitySum[Integer.valueOf(a)]++;
            // 记录在多个城市活跃的人
            String ci = GetXRow(filename,num + 3);
            String[] cit = ci.split(" ");

            // ********* （1）3个城市生活的人 *********
            if(Integer.valueOf(cit[0]) != 0 & Integer.valueOf(cit[1]) != 0 & Integer.valueOf(cit[2]) !=0) {
                ThreeCity++;
                System.out.println(index);
                SaveIndexs(fop,String.valueOf(index));
            }
            // ********* （2）2个城市（北京和新疆）生活的人 *********
            else if (Integer.valueOf(cit[0]) !=0 & Integer.valueOf(cit[1]) != 0) {
                TwoCity++;
                ConBeiJingAndXinjiang++;
                SaveIndexs(fop2,String.valueOf(index));
            }
            // ********* （3）2个城市（新疆和其他）生活的人 *********
            else if (Integer.valueOf(cit[0]) != 0 & Integer.valueOf(cit[2]) !=0){
                TwoCity++;
                ConXinjiangAndQita ++;
                SaveIndexs(fop3,String.valueOf(index));
            }
            // ********* （4）2个城市（北京和其他）生活的人 *********
            else if ( Integer.valueOf(cit[1]) != 0 & Integer.valueOf(cit[2]) !=0){
                TwoCity++;
                ConBeiJingAndQita ++;
                SaveIndexs(fop4,String.valueOf(index));
            }
        }
        // *********  输出模块 *********
        System.out.println("总体城市分布");
        for(int i=0;i<3;i++) {
            System.out.println(CitySum[i]);
        }
        System.out.println("3个城市生活的人数" + ThreeCity);
        System.out.println("2个城市生活的人数" + TwoCity);
        System.out.println("包含北京和其他人数:" + ConBeiJingAndQita);
        System.out.println("包含北京和新疆人数：" + ConBeiJingAndXinjiang);
        System.out.println("包含新疆和其他人数：" + ConXinjiangAndQita);
    }
}
