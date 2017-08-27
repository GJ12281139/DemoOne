package Hcluster;

import Subjects.LocationSubject;

/**
 * 存放样本点，包括样本点的经度、纬度、计数、所属的类簇
 */
public class DataPoint {
    private String dataPointName; // 样本点名
    private String lng; // 用户位置经度
    private String lat; // 用户位置纬度
    private String count; // 计数
    private Cluster cluster; // 样本点所属类簇

    public String getDataPointName() {
        return dataPointName;
    }

    public void setDataPointName(String dataPointName) {
        this.dataPointName = dataPointName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
