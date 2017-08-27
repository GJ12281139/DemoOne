package dataset;

import Subjects.LocationSubject;

import java.io.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static Tools.JudgeBeijing11And20.JudgeBeijing11And20;

public class GetDataToTxts {

    private final static String path = "C://UsersMillWithoutDesc_info.txt"; // Raw Data
    private List<String> content = new LinkedList<>();

    /**
     * 获得txt文件中某个编号为index的用户的所有数据
     * @param Index 用户id  int
     * @return
     * @throws IOException
     */
    public List<String> getData(int Index) throws IOException {
        File file = new File(path);
        String index = String.valueOf(Index);
        //     String index_rex = index+"\u0009"; // index\t //但是前面会匹配到不正确的
        String index_rex = "^"+ index+ "\u0009";
        Pattern index_r = Pattern.compile(index_rex);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String temp;
        int cou = 0;
        //      while((temp = br.readLine())!= null && cou< 3562667 ){
        while((temp = br.readLine())!= null && cou< 3562667 ){
            cou++;
            Matcher m = index_r.matcher(temp);
            if(m.find()){
                content.add(temp);
            }
        }
        br.close();
        return content;
    }


    /**
     * 创建编号为index用户的txt文件，存储的内容为:
     * 第一行：编号 位置信息数
     * 第二行：经度、纬度、该条位置记录数、所属日期、该条记录的年、月、日、小时、分钟、星期几
     * @param index
     * @throws IOException
     * @throws ParseException
     */
    public void GetUserTxt(int index) throws IOException, ParseException {
        GetDataToTxts in = new GetDataToTxts();
        List<String> content = in.getData(index);   // "content" is all the information about User_X
        LocationSubject loc = new LocationSubject();    // use subject to store one slice of information
        String Index = String.valueOf(index);
        loc.setIndex(Index);

        // Write txt file
        FileOutputStream fop = null;
        File file;
        file = new File("D:\\10000More400\\UpdatUsersFiles\\" + Index + ".txt");
        fop = new FileOutputStream(file);
        // (1) txt文件第一行: 用户索引 记录数目
        fop.write(Index.getBytes()); // Write index 用户索引
        fop.write(" ".getBytes("utf-8"));
        fop.write(String.valueOf(content.size()).getBytes()); // num 记录数目
        // 换行
        String str = "";
        str += "\r\n";
        fop.write(str.getBytes("utf-8"));

        Calendar calendar = Calendar.getInstance();
        int hour,week;
        String[] tmp = null;
        // txt 文件第 i 行:
        for (int i = 0; i < content.size(); i++) {
            tmp = content.get(i).split("\\s+");
            loc.setDateTime(tmp[1]);
            calendar.setTime(loc.getDateTime());
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            week = calendar.get(Calendar.DAY_OF_WEEK);

            // （2）用户文件第二行开始逐行写入：
            // 经度，纬度，该条位置记录数，所属日期，timestamp，星期几，小时
            // 经度
            fop.write(tmp[2].getBytes());
            fop.write(" ".getBytes("utf-8"));
            // 纬度
            fop.write(tmp[3].getBytes());
            fop.write(" ".getBytes("utf-8"));
            // 该条位置记录数
            fop.write(tmp[4].getBytes());
            fop.write(" ".getBytes("utf-8"));
            // 只考虑北京的情况，划分地理位置11*20 的标号
            fop.write(String.valueOf(JudgeBeijing11And20(tmp[2],tmp[3])).getBytes("utf-8"));
            fop.write(" ".getBytes("utf-8"));
            // 所属日期
            fop.write(tmp[5].getBytes());
            fop.write(" ".getBytes("utf-8"));
            // 该条记录的timestamp
            fop.write(tmp[1].getBytes());
            fop.write(" ".getBytes("utf-8"));
            // 记录该记录是星期几
            fop.write(String.valueOf(week).getBytes());
            fop.write(" ".getBytes());
            // 该条记录的小时，用来区分白天夜晚
            fop.write(String.valueOf(hour).getBytes());
            fop.write(" ".getBytes("utf-8"));
            // 换行
            str = "";
            str += "\r\n";
            fop.write(str.getBytes("utf-8"));
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        GetDataToTxts in = new GetDataToTxts();
        for(int i = 1; i<= 1; i++){
            in.GetUserTxt(i);
            System.out.println(i);
        }
    }
}
