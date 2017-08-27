package preprocess;

import Subjects.LocationSubject;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static Tools.GetXRow.GetXRow;

public class NoiseFiltering {

    public int ComputeSpeedReturnCount( List<String> lngs, List<String> lats, List<String> Times) throws ParseException, IOException {
        double TempDis = 0;
        double timeDis1 = 0;
        double timeDis2 = 0;
        int hour1, minu1, sec1;
        int hour2, minu2, sec2;
        double[] sumTemp = new double[lngs.size()];
        double sum = 0;
        int count =1;

        if (lngs.size() != 1) {
            for (int j = 0; j < lngs.size() - 1; j++) {
                TempDis = Math.sqrt(Math.pow(111700 * Math.abs(Double.valueOf(lngs.get(j + 1)) - Double.valueOf(lngs.get(j))), 2) +
                        Math.pow(111700 * Math.abs(Math.cos(Double.valueOf(lats.get(j + 1))) - Math.cos(Double.valueOf(lats.get(j)))), 2));
                LocationSubject is = new LocationSubject();
                LocationSubject is2 = new LocationSubject();
                is.setDateTime(Times.get(j));
                is2.setDateTime(Times.get(j + 1));
                Calendar calendar = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                calendar.setTime(is.getDateTime());
                calendar2.setTime(is2.getDateTime());
                minu1 = calendar.get(Calendar.MINUTE);
                minu2 = calendar2.get(Calendar.MINUTE);
                hour1 = calendar.get(Calendar.HOUR_OF_DAY);
                hour2 = calendar2.get(Calendar.HOUR_OF_DAY);
                sec1 = calendar.get(Calendar.SECOND);
                sec2 = calendar2.get(Calendar.SECOND);
                timeDis1 = (hour1 * 60 * 60 + minu1 * 60 + sec1);
                timeDis2 = (hour2 * 60 * 60 + minu2 * 60 + sec2 + 1);
                sumTemp[j] = TempDis / Math.abs(timeDis2 - timeDis1);
            }
        }
//        for(int k =0;k<lngs.size();k++){
//            sum +=sumTemp[k];
//            if(sumTemp[k] != 0 )
//                count++;
//        }
//        return sum/count;
        for(int k =0;k<lngs.size();k++){
            sum += sumTemp[k];
            if(sumTemp[k] > 340)
                count++;
        }
        return count;
    }

    public void NoiseFilter(int index) throws IOException, ParseException {
        ReadTxt in = new ReadTxt();
        System.out.println( index );
        NoiseFiltering im = new NoiseFiltering();
        String filename = "D:\\10000More400\\UpdatUsersFiles\\" + String.valueOf(index) + ".txt";
        String Aline = GetXRow(filename, 1);
        String temp[] = Aline.split(" ");
        int num = 0;
        int count =0;
        num = Integer.valueOf(temp[1]); //用户记录行数
        List<String> lngs = new ArrayList<String>();
        List<String> lats = new ArrayList<String>();
        List<String> Times = new ArrayList<String>();
        for(int i = 2; i<= num; i++){
            String[] tmp = GetXRow(filename, i).split(" ");
            lngs.add(tmp[0]);
            lats.add(tmp[1]);
            Times.add(tmp[5]);
        }
        count = im.ComputeSpeedReturnCount(lngs,lats,Times);
        System.out.println( count );
    }

    /**
     * Heuristic anomaly detection
     * Threshold : the Speed of the sound 340 m/s.
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException, ParseException {
        NoiseFiltering im = new NoiseFiltering();
        for (int index = 1; index <= 10; index++) {
            im.NoiseFilter(index);
        }
    }
}
