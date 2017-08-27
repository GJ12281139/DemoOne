package Hcluster;

import Tools.GetXRow;
import Tools.WriteTxt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HCluster {
    private static double  ThreDis= 500;

    public static void main(String[] args) throws IOException {

        HCluster hc = new HCluster();

        // 使用链表存放样本点
        ArrayList<DataPoint> dp = new ArrayList<DataPoint>();

        // 读入样本文件
        for(int index = 1;index<= 20; index++) {
            dp = hc.readData(index);

        /*
         * freq代表了聚类的终止条件，判断还有没有距离小于freq的两个类簇，若有则合并后继续迭代，否则终止迭代
         */
            List<Cluster> clusters = hc.startCluster(dp, ThreDis);

            // 输出聚类的结果，两个类簇中间使用----隔开
            System.out.println(index);
            System.out.println(clusters.size());

//        for (Cluster cl : clusters) {
//            List<DataPoint> tempDps = cl.getDataPoints();
//            for (DataPoint tempdp : tempDps) {
//                System.out.println(tempdp.getCount());
//                System.out.println(tempdp.getLng());
//                System.out.println(tempdp.getLat());
//            }
//            System.out.println("----");
//        }
            String filename = "D:\\10000More400UsersData\\Res\\" + String.valueOf(index) + ".txt";
            WriteTxt.WriteTxt(filename, String.valueOf(index) + " " + String.valueOf(clusters.size()) , 0);
            for (int i = 0; i < clusters.size(); i++) {
                WriteTxt.WriteTxt(filename, clusters.get(i).getCorlng() + " " + clusters.get(i).getCorlat() + " " + clusters.get(i).getCount());
            }
        }
    }

    // 聚类的主方法
    private List<Cluster> startCluster(ArrayList<DataPoint> dp, double freq) {

        // 声明cluster类，存放类名和类簇中含有的样本
        List<Cluster> finalClusters = new ArrayList<Cluster>();
        // 初始化类簇，开始时认为每一个样本都是一个类簇并将初始化类簇赋值给最终类簇
        List<Cluster> originalClusters = initialCluster(dp);
        finalClusters = originalClusters;
        // flag为判断标志
        boolean flag = true;
        int it = 1;
        while (flag) {
            System.out.println("第" + it + "次迭代");
            double max = 100000;
            // mergeIndexA和mergeIndexB表示每一次迭代聚类最小的两个类簇，也就是每一次迭代要合并的两个类簇
            int mergeIndexA = 0;
            int mergeIndexB = 0;
            /*
             * 迭代开始，分别去计算每个类簇之间的距离，将距离小的类簇合并
             */
            for (int i = 0; i < finalClusters.size() - 1; i++) {
                for (int j = i + 1; j < finalClusters.size(); j++) {
                    // 得到两个类簇
                    Cluster clusterA = finalClusters.get(i);
                    Cluster clusterB = finalClusters.get(j);

                    double tempDis = 0;
                    tempDis = Tools.Distance.getDistance(Double.valueOf(clusterA.getCorlng()),Double.valueOf(clusterA.getCorlat()),
                            Double.valueOf(clusterB.getCorlng()),Double.valueOf(clusterB.getCorlat()));

                    if (tempDis <= max) {
                        max = tempDis;
                        mergeIndexA = i;
                        mergeIndexB = j;
                    }
                }
            }

            if (max > freq) { //  应该是大于
                flag = false;
            } else {
                finalClusters = mergeCluster(finalClusters, mergeIndexA, mergeIndexB);
            }
            it++;
        }
        return finalClusters;
    }

    private List<Cluster> mergeCluster(List<Cluster> finalClusters, int mergeIndexA, int mergeIndexB) {
        if (mergeIndexA != mergeIndexB) {
            // 将cluster[mergeIndexB]中的DataPoint加入到 cluster[mergeIndexA]
            Cluster clusterA = finalClusters.get(mergeIndexA);
            Cluster clusterB = finalClusters.get(mergeIndexB);

            List<DataPoint> dpA = clusterA.getDataPoints();
            List<DataPoint> dpB = clusterB.getDataPoints();

            for (DataPoint dp : dpB) {
                DataPoint tempDp = new DataPoint();
                tempDp.setDataPointName(dp.getDataPointName());
                tempDp.setLat(dp.getLat());
                tempDp.setLng(dp.getLng());
                tempDp.setCount(dp.getCount());
                tempDp.setCluster(clusterA);
                dpA.add(tempDp);
            }
            clusterA.setDataPoints(dpA);
            clusterA.setCorlng(Tools.ComputMeanCoord.getMeanCoord_Cluster(dpA).getLng());
            clusterA.setCorlat(Tools.ComputMeanCoord.getMeanCoord_Cluster(dpA).getlat());
            clusterA.setCount(Tools.ComputMeanCoord.getMeanCoord_Cluster(dpA).getCount());
            finalClusters.remove(mergeIndexB);
        }
        return finalClusters;
    }

    // 初始化类簇
    private List<Cluster> initialCluster(ArrayList<DataPoint> dpoints) {
        // 声明存放初始化类簇的链表
        List<Cluster> originalClusters = new ArrayList<Cluster>();

        for (int i = 0; i < dpoints.size(); i++) {
            // 得到每一个样本点
            DataPoint tempDataPoint = dpoints.get(i);
            // 声明一个临时的用于存放样本点的链表
            List<DataPoint> tempDataPoints = new ArrayList<DataPoint>();
            // 链表中加入刚才得到的样本点
            tempDataPoints.add(tempDataPoint);
            // 声明一个类簇，并且将给类簇设定名字、增加样本点
            Cluster tempCluster = new Cluster();
            tempCluster.setClusterName("Cluster " + String.valueOf(i));
            tempCluster.setDataPoints(tempDataPoints);
            tempCluster.setCorlng(tempDataPoint.getLng());
            tempCluster.setCorlat(tempDataPoint.getLat());
            tempCluster.setCount(tempDataPoint.getCount());

            // 将样本点的类簇设置为tempCluster
            tempDataPoint.setCluster(tempCluster);
            // 将新的类簇加入到初始化类簇链表中
            originalClusters.add(tempCluster);
        }

        return originalClusters;
    }



    private ArrayList<DataPoint> readData(int index) throws IOException {

        ArrayList<DataPoint> dp = new ArrayList<DataPoint>();
        String TopPointsFile = "D:\\10000More400UsersData\\CountPcen0.09\\" + String.valueOf(index) + ".txt";
        String ThrPointsFile = "D:\\10000More400UsersData\\StayPointsOfUserAfterChange\\" + String.valueOf(index) + ".txt";

        FileInputStream fis1 = null;
        InputStreamReader isr1 = null;
        BufferedReader br1 = null;
        fis1 = new FileInputStream(TopPointsFile);
        isr1 = new InputStreamReader(fis1, "utf-8");
        br1 = new BufferedReader(isr1);

        // Save TopPoints:
        String LastLine = Tools.ReadLastLine.readLastLine(TopPointsFile);
        int num = Integer.valueOf(LastLine.split("\\s+")[1]);
        for( int i = 1; i<=num; i++){
            // 临时存放
            DataPoint tmp = new DataPoint();
            tmp.setLng(Tools.GetXRow.GetXRow(TopPointsFile, i).split(" ")[0]);
            tmp.setLat(Tools.GetXRow.GetXRow(TopPointsFile, i).split(" ")[1]);
            tmp.setCount(Tools.GetXRow.GetXRow(TopPointsFile, i).split(" ")[2]);
            dp.add(tmp);
        }

        // Save ThrPoints:
        String Aline = GetXRow.GetXRow(ThrPointsFile, 1);
        String tmp[] = Aline.split(" ");
        int num2 = Integer.valueOf(tmp[1]); //用户记录行数
        for (int lineNum = 2; lineNum <= num2 + 1; lineNum++) {
            DataPoint tmp2 = new DataPoint();
            tmp2.setLng(Tools.GetXRow.GetXRow(ThrPointsFile, lineNum).split(" ")[0]);
            tmp2.setLat(Tools.GetXRow.GetXRow(ThrPointsFile, lineNum).split(" ")[1]);
            tmp2.setCount(Tools.GetXRow.GetXRow(ThrPointsFile, lineNum).split(" ")[2]);
            dp.add(tmp2);
        }

        System.out.println("加载数据完毕，数据大小为：" + dp.size());
        return dp;
    }
}