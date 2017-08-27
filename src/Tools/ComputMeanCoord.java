package Tools;

import Hcluster.DataPoint;
import Mining.FirstGetUserSub;
import Subjects.LocationSubject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class ComputMeanCoord {

    public static LocationSubject getMeanCoord(List<LocationSubject> P){
        LocationSubject temp = new LocationSubject();
        double MeanLng = 0;
        double MeanLat = 0;
        double TmpLng = 0;
        double TmpLat = 0;
        String pattern = "#0.000";
        DecimalFormat formatter = new DecimalFormat();
        formatter.applyPattern(pattern);
        int count = 0;
        for (int i = 0;i< P.size(); i++){
            TmpLng += Double.valueOf(P.get(i).getLng()) * Integer.valueOf(P.get(i).getCount()) ;
            TmpLat += Double.valueOf(P.get(i).getlat()) * Integer.valueOf(P.get(i).getCount());
            count += Integer.valueOf(P.get(i).getCount());
        }
        MeanLng = TmpLng/ count;
        MeanLat = TmpLat/ count;
        temp.setlng(String.valueOf(formatter.format(MeanLng)));
        temp.setlat(String.valueOf(formatter.format(MeanLat)));
        temp.setArriveTime(P.get(0).gettime());
        temp.setLeaveTime(P.get(P.size()-1).gettime());
        temp.setCount(String.valueOf(count));
        return temp;
    }
    public static LocationSubject getMeanCoord_Cluster( List<DataPoint> P){
        LocationSubject temp = new LocationSubject();
        double MeanLng = 0;
        double MeanLat = 0;
        double TmpLng = 0;
        double TmpLat = 0;
        String pattern = "#0.000";
        DecimalFormat formatter = new DecimalFormat();
        formatter.applyPattern(pattern);
        int count = 0;
        for (int i = 0;i< P.size(); i++){
            TmpLng += Double.valueOf(P.get(i).getLng()) * Integer.valueOf(P.get(i).getCount()) ;
            TmpLat += Double.valueOf(P.get(i).getLat()) * Integer.valueOf(P.get(i).getCount());
            count += Integer.valueOf(P.get(i).getCount());
        }
        MeanLng = TmpLng/ count;
        MeanLat = TmpLat/ count;
        temp.setlng(String.valueOf(formatter.format(MeanLng)));
        temp.setlat(String.valueOf(formatter.format(MeanLat)));
        temp.setCount(String.valueOf(count));
        return temp;
    }

    public static void main(String args[]) throws IOException, ParseException {
        FirstGetUserSub in = new FirstGetUserSub();
        List<LocationSubject> P = in.GetUserSub(10000);
        System.out.println(getMeanCoord(P).getCount());
        System.out.println(getMeanCoord(P).getLng());
        System.out.println(getMeanCoord(P).getlat());

    }
}
