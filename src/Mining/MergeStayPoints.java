package Mining;

import Subjects.LocationSubject;

import java.util.LinkedList;
import java.util.List;

public class MergeStayPoints {

    public static List<LocationSubject> MergeStayPoints(int index){
        String TopPointsFile = "D:\\10000More400UsersData\\CountPcen0.09\\" + String.valueOf(index) + ".txt";
        String ThrPointsFile = "D:\\10000More400UsersData\\StayPointsOfUserAfterChange\\" + String.valueOf(index)+ ".txt";
        List<LocationSubject> TopPoints = new LinkedList<>();
        List<LocationSubject> ThrPoints = new LinkedList<>();


        for( int i = 0; i< TopPoints.size(); i++){

        }
        return TopPoints;
    }
    public static void main(String args[]){
        MergeStayPoints(1);
    }
}
