package Tools;

import java.io.*;

public class GetXRow {

    /**
     * 获得txt文件中特定行的内容
     * @param txtPath
     * @param lineNum
     * @return
     * @throws IOException
     */
    public static String GetXRow(String txtPath, int lineNum) throws IOException {
        String line=null;
        File txtFile = new File(txtPath);
        InputStream in = new FileInputStream(txtFile);
        InputStreamReader read = new InputStreamReader(in, "utf-8");
        BufferedReader reader = new BufferedReader(read);
        int i =0;
        while(i<lineNum){
            line = reader.readLine();
            i++;
        }
        read.close();
        return line;
    }
}