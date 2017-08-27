package Subjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gj on 2017/4/17.
 */
public class LocationSubject {
    private String index; // 用户id
    private String lng; // 用户位置经度
    private String lat; // 用户位置纬度
    private String count; //
    private String time; // 用户位置时间戳
    private Date dateTime; //标准时间格式
    private String ArriveTime;
    private String LeaveTime;

    public void setIndex(String index){
        this.index = index;
    }
    public String getIndex(){
        return index;
    }
    public void setlng(String lng){
        this.lng = lng;
    }
    public String getLng(){
        return lng;
    }
    public void setlat(String lat){
        this.lat = lat;
    }
    public String getlat(){
        return lat;
    }
    public void setCount(String count){
        this.count = count;
    }
    public String getCount(){
        return count;
    }
    public void settime(String time){
        this.time = time;
    }
    public String gettime(){
        return time;
    }
    public void setDateTime(String time) throws ParseException {
        //DateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
        // MM 是月份，mm是分，HH是24小时制，hh是12小时制
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        this.dateTime = sdf.parse(time);
    }
    public Date getDateTime(){
        return dateTime;
    }
    public void setArriveTime(String ArriveTime){
        this.ArriveTime  = ArriveTime;
    }
    public String getArriveTime(){
        return ArriveTime;
    }
    public void setLeaveTime(String LeaveTime){
        this.LeaveTime  = LeaveTime;
    }
    public String getLeaveTime(){
        return LeaveTime;
    }
}



