package Tools;

public class Distance {
    private final static double PI = 3.14159265358979323; // 圆周率
    private final static double R = 6371229; // 地球的半径

    public static double getDistance(double lng1, double lat1, double lng2,double lat2){
        double x, y, distance;
        x = (lng2 - lng1) * PI * R * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);
        return distance;
    }

    public static void main(String args[]){
        System.out.println(getDistance(116.372,39.948, 116.348,39.952));
    }
}
