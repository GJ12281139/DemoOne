package Tools;

import preprocess.ReadTxt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.RandomAccess;
import static Tools.GetXRow.GetXRow;
public class WriteXRow {
    /**
     * Java的RandomAccessFile提供对文件的读写功能，与普通的输入输出流不一样的是RamdomAccessFile可以任意的访问文件的任何地方。
     * 这就是“Random”的意义所在。
     * @param file
     * @param XRow
     * @param Sum
     * @throws IOException
     */
    public static void WriteXRow(String file, int XRow,String Sum,String Row,String index) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        int curRow = 0;
        while(raf.readLine() !=null ){
            if(curRow == XRow){
                break;
            }
            curRow++;
        }
        raf.write( Sum.getBytes());
  //      raf.write(Sum.getBytes("utf-8"));
//        raf.write("\r\n".getBytes());
        raf.close();
    }

    public static void main(String args[]) throws IOException {
        for(int index = 1; index <= 10000; index++){
            ReadTxt in = new ReadTxt();
            String filename = "D:\\10000More400\\UpdatUsersFiles\\" + String.valueOf(index) + ".txt";
            String Aline = GetXRow(filename, 1);
            String temp[] = Aline.split("\\s+");
            int num = Integer.valueOf(temp[1]); //用户记录行数
            int sum = 0;
            for(int i = 2; i<=num+1; i++){
                sum += Integer.valueOf( GetXRow( filename,i).split("\\s+")[2]);
            }
            System.out.println(sum);
            WriteXRow(filename, -1, String.valueOf(sum), String.valueOf(num), String.valueOf(index));
        }
    }
}
