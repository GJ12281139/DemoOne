package dataset;

import java.io.*;

public class GetAllLngLatToTxt {

    private static String pathRead = "C://UsersMillWithoutDesc_info.txt"; // Raw Data
    private static String pathWrite = "D://2000UsersData//AllLngLat.txt"; // Raw Data

    private void GetAllData() throws IOException {
        File file = new File(pathRead);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        File filew = new File(pathWrite);
        FileOutputStream fop = new FileOutputStream(filew);
        int line = 0;
        String temp;

     //   while( (temp = br.readLine())!=null && line < 3562667 ){
          while( (temp = br.readLine())!=null && line <= 719727 ){ // 2000 Users
            line++;
            String[] temps =  temp.split("\\s+");
            fop.write(temps[2].getBytes("utf-8"));
            fop.write(" ".getBytes("utf-8"));
            fop.write(temps[3].getBytes("utf-8"));
            fop.write(" ".getBytes("utf-8"));
            fop.write(temps[4].getBytes("utf-8"));
            // 换行
            String str = "";
            str += "\r\n";
            fop.write(str.getBytes("utf-8"));
        }
        fop.close();
        br.close();
    }

    public static void main(String args[]) throws IOException {
        GetAllLngLatToTxt in = new GetAllLngLatToTxt();
        in.GetAllData();
    }
}
