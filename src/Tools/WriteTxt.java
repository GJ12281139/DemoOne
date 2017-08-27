package Tools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by guojie on 2017/7/3.
 */
public class WriteTxt {

    /**
     * 写入filename 文件 末尾 content内容
     *
     * @param filename
     * @param content
     * @throws IOException
     */
    public static void WriteTxt(String filename, String content) throws IOException {
        RandomAccessFile randomFile = new RandomAccessFile(filename, "rw"); // 打开一个随机访问文件流，按读写方式
        long fileLength = randomFile.length(); // 将写文件指针移到文件尾。
        randomFile.seek(fileLength);
        randomFile.writeBytes(content + "\r\n");
        randomFile.close();
    }

    public static void WriteTxt(String filename, String content,int fileLength) throws IOException {
        new File(filename).delete();
        RandomAccessFile randomFile = new RandomAccessFile(filename, "rw"); // 打开一个随机访问文件流，按读写方式
        randomFile.seek(fileLength);
        randomFile.writeBytes(content + "\r\n");
        randomFile.close();
    }
}