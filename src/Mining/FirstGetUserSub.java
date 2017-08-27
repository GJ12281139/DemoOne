package Mining;

import Subjects.LocationSubject;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import static Tools.GetXRow.GetXRow;

public class FirstGetUserSub {

    public List<LocationSubject> GetUserSub( int index ) throws IOException, ParseException {
        String file = "D:\\10000More400UsersData\\10000OrigTxtData\\"+ String.valueOf(index) + ".txt";
        int num = Integer.valueOf(GetXRow(file, 1).split(" ")[1]);
        List<LocationSubject> UserSub = new LinkedList<>();

        for(int i = 2; i<= num+1; i++){
            LocationSubject LocTmp = new LocationSubject();
            String[] Temp = GetXRow(file,i).split(" ");
            LocTmp.setIndex(String.valueOf(index));
            LocTmp.setlng(Temp[0]);
            LocTmp.setlat(Temp[1]);
            LocTmp.setCount(Temp[2]);
            LocTmp.settime(Temp[5]);
            LocTmp.setDateTime(Temp[5]);
            UserSub.add(LocTmp);

        }
        return UserSub;
    }

    public static void main(String args[]) throws IOException, ParseException {
        FirstGetUserSub in = new FirstGetUserSub();
        in.GetUserSub(10000);
    }
}
