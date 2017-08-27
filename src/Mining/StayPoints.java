package Mining;

import Subjects.LocationSubject;
import preprocess.StayPoint;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import static Tools.WriteTxt.WriteTxt;

public class StayPoints {
    private static double distThreh = 200;  // 200 meters
    private static double timeThreh = 1800; // more than 30 minutes

    public List<LocationSubject> StayPoints_Detection( List<LocationSubject> P) throws ParseException {
        List<LocationSubject> Res = new LinkedList<>();

        int i =0;
        while( i< P.size()-1 ){
            List<LocationSubject> Sav = new LinkedList<>();
            LocationSubject tmp = new LocationSubject();
            tmp.setlng(P.get(i).getLng());
            tmp.setlat(P.get(i).getlat());
            tmp.settime(P.get(i).gettime());
            tmp.setCount(P.get(i).getCount());
            Sav.add(tmp);
            for(int j = i+1;j < P .size(); j++ ) {
                    LocationSubject tmp2 = new LocationSubject();
                    double dist = Tools.Distance.getDistance(Double.valueOf(P.get(i).getLng()),
                            Double.valueOf(P.get(i).getlat()),
                            Double.valueOf(P.get(j).getLng()),
                            Double.valueOf(P.get(j).getlat()));
                    System.out.println("Dist" + dist);
                    double interval = Tools.CalTimeInterval.getInterval(P.get(j), P.get(i));
                    System.out.println("Interval:" + interval);

                    if (dist < distThreh && interval < timeThreh) {
                        tmp2.setlng(P.get(j).getLng());
                        tmp2.setlat(P.get(j).getlat());
                        tmp2.settime(P.get(j).gettime());
                        tmp2.setCount(P.get(j).getCount());
                        Sav.add(tmp2);
                        if(j == P.size()-1){
                            i = j+1;
                            break;
                        }
                    }else{
                        i = j;
                        break;
                    }
            }
            if(Sav.size()>=2 ){
                LocationSubject Restmp = new LocationSubject();
                Restmp = Tools.ComputMeanCoord.getMeanCoord(Sav);
                Res.add(Restmp);
            }

        }
        return Res;
    }

    public static void main(String args[]) throws IOException, ParseException {
        FirstGetUserSub in = new FirstGetUserSub();
        List<LocationSubject> UserSub = new LinkedList<>();
        for(int index =1; index<=10000; index++) {
            UserSub = in.GetUserSub(index);
            StayPoints im = new StayPoints();
            List<LocationSubject> Res = im.StayPoints_Detection(UserSub); // All the Stay Points Of X User

            //********************** 写文件模块 *********************
            String WriteFile = "D:\\10000More400UsersData\\StayPointsOfUserAfterChange\\" + String.valueOf(index)+ ".txt";
            WriteTxt(WriteFile, String.valueOf(index)+" "+ Res.size());
            for (int i = 0; i < Res.size(); i++) {
                System.out.println(Res.get(i).getLng());
                System.out.println(Res.get(i).getlat());
                System.out.println(Res.get(i).getArriveTime());
                System.out.println(Res.get(i).getLeaveTime());
                System.out.println(Res.get(i).getCount());

                WriteTxt(WriteFile, Res.get(i).getLng() + " " + Res.get(i).getlat() + " " + Res.get(i).getCount() +
                        " " + Res.get(i).getArriveTime() + " "+ Res.get(i).getLeaveTime());
            }
        }

    }
}
