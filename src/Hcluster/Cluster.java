package Hcluster;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于存放类名和该类下的样本
 */
public class Cluster {
    private List<DataPoint> dataPoints = new ArrayList<DataPoint>(); // 类簇中的样本点
    private String clusterName;
    String corlng;
    String corlat;
    String count;

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
    public String getCorlng(){
        return corlng;
    }
    public  void setCorlng(String corlng){
        this.corlng = corlng;
    }

    public String getCorlat(){
        return corlat;
    }
    public  void setCorlat(String corlat){
        this.corlat = corlat;
    }

    public String getCount(){
        return count;
    }
    public  void setCount(String count){
        this.count = count;
    }
}
