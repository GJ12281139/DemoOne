package Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guojie on 2017/6/27.
 * Tools.GetMax
 */
public class GetMax {
    //获取最大值的下标
    public static int getMaxIndex(int[] arr) {
        int maxIndex = 0;   //获取到的最大值的角标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static int getMaxIndex(double[] arr) {
        int maxIndex = 0;   //获取到的最大值的角标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * 取出数组中的最大值
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    public static double getMax(double[] arr) {
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    //获取最大值的下标
    public static int getMaxIndexUpdate(int[] arr) {
        int maxIndex = 0;   //获取到的最大值的角标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        if(arr[maxIndex] ==0)
            maxIndex = -1;
        return maxIndex;
    }

    //  对数组进行排序可以使用ArraySort方法，保存源数组下标值可以存入map中
    public static void GetSortedSub(String[] arr){
        HashMap map = new HashMap();
        int[] SortedArr = new int[arr.length];
        for(int i =0; i<arr.length; i++){
            map.put(arr[i],i); // 将值和下标存入map
        }
        List list = new ArrayList();
        Arrays.sort(arr); //升序排列
        for(int i =0; i<arr.length; i++){
            SortedArr[i] = Integer.parseInt((map.get(arr[i]).toString()));
        //    System.out.println(map.get(arr[i]));
        }
    }
    public static int[] GetSortedSub(int[] arr){
        HashMap map = new HashMap();
        int[] SortedArr = new int[arr.length];
        for(int i =0; i<arr.length; i++){
            map.put(arr[i],i); // 将值和下标存入map
        }
        Arrays.sort(arr); //升序排列
        for(int i =0; i<arr.length; i++){
            SortedArr[i] = Integer.parseInt((map.get(arr[i]).toString()));
         //   System.out.println(SortedArr[i]);
        }
        return SortedArr;
    }
    public static void main(String[] args){
        int[] a = {4,11,3,4,2,99,0};
        GetSortedSub(a);

        int[] SortedSub = new int[7];
        SortedSub = GetMax.GetSortedSub(a);
        for(int i =0;i<7;i++)
            System.out.println("test"+SortedSub[i]);
    }
}
