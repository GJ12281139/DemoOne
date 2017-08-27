package preprocess;

import Subjects.LocationSubject;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;

/**
 * cmd type C:\Person6300.txt 可查看从Hbase导出用户数据文件
 * 6300名用户20151208到20151214一星期的数据，按照不同经纬度数目：
 * 100-200：3600名；200-300：1200名；300-400：600名：400-500：400名；
 * 500-600：300名；600-700：200名
 * 原始数据格式：
 *         1	20151208083457	{"coor":"gcj02","lng":"117.696","is_currentpos":"1","lat":"28.308","country":"中国"}	20151208
 */

public class ReadTxt {
    private static String path = "C://Person6300.txt";
    private static String path_GetLngLat = "D://testReadTxt.txt";
    private List<String> content = new LinkedList<String>();
    private static String lng_rex = "lng\":\".*?\"";
    private static String lat_rex = "lat\":\".*?\"";
//    private static String time_rex = "\\u0009.*?(\"coor\")"; //对没有coor的条目会出错
    private static String time_rex = "\\u0009.*?\\{\"";

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
        while((temp = br.readLine())!= null){
            Matcher m = index_r.matcher(temp);
            if(m.find()){
                content.add(temp);
            }
        }
        br.close();
        return content;
    }
    /**
     * 得到某个用户的所有数据
     * @param index 用户的id
     *
     * */
    public List<String> getData(String index, String path) throws IOException {
        File file = new File(path);
        // 定位单个用户，用index\t提取
        String index_rex = "^"+ index+"\u0009";
        Pattern index_r = Pattern.compile(index_rex);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        // temp保存一条用户记录
        String temp = null;
        while ((temp = br.readLine()) != null) {
            Matcher m =index_r.matcher(temp);
            if(m.find()){
                content.add(temp);
            }
        }
        br.close();
        return content;
    }

    /**
     * 获取某用户index 的位置记录次数
     * @param index
     * @return
     * @throws IOException
     */
    public int getUserContentSize(int index) throws IOException {
        int num = getData(index).size();
        return num;
    }

    /**
     * getLng(int index)  取出某用户的记录中位置记录 - 经度 longtitude
     * getLng(int index)  取出某用户的记录中位置记录 - 纬度 latitude
     * getTimestamp(int index) 取出某用户的记录中位置记录 - 时间戳 timestamp
     * @param content
     * @param Rex
     * @param begin
     * @param end
     * @return
     */
    private List<String> getRex(List<String> content, String Rex, int begin, int end){
        List<String> cons = new LinkedList<String>();
        Pattern lng_pattern = Pattern.compile(Rex);
        for(String con:content){
            Matcher lng_m = lng_pattern.matcher(con);
            if(lng_m.find()){
                cons.add(lng_m.group(0).substring(begin,lng_m.group(0).length()-end));
            }
            else{
                cons.add("00000000000000");
                System.out.println("缺失部分数据信息。");
            }
        }
        return cons;
    }
    public List<String> getLng(List<String> content) throws IOException {
        List<String> lngs = getRex(content, lng_rex, 6, 1);
        return lngs;
    }
    public List<String> getLat(List<String> content) throws IOException {
        List<String> lats = getRex(content, lat_rex, 6, 1);
        return lats;
    }
    public List<String> getTimestamp(List<String> content) throws IOException {
        List<String> Timestamps = getRex(content, time_rex, 1, 3);
        return Timestamps;
    }
//    public List<String> getDate(List<String> content){
//        List<String> Dates = getRex(content, date_rex,1,1);
//        return Dates;
//    }

    /**
     * Txt格式：每行都是“经度 纬度”
     * @return
     */
    public List<String> GetLngsOrLatsFromTxt(int i) throws IOException {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        reader = new FileReader(path_GetLngLat);
        bufferedReader = new BufferedReader(reader);
        String line = null;
        String[] tmp = null;
        List<String> lngs = new ArrayList<String>();
        while(true){
            line = bufferedReader.readLine();
            if(line ==null)
                break;
            tmp = line.split("\\s+");
            lngs.add(tmp[i]);
        }
        bufferedReader.close();
        reader.close();
        return lngs;
    }

    /**
     * 取出数组中的最大值
     */

    public int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    //获取最大值的下标
    public int getMaxIndex(int[] arr) {
        int maxIndex = 0;   //获取到的最大值的角标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) throws IOException, ParseException {
        ReadTxt in = new ReadTxt();
        List<String> lngs = in.GetLngsOrLatsFromTxt(0);
        List<String> lats = in.GetLngsOrLatsFromTxt(1);


    }
}
