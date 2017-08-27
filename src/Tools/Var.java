package Tools;

/**
 * Created by guojie on 2017/7/4.
 */
public class Var {

    public static double GetVar(double[] array){
        double ave = 0;
        for (int i = 0; i < array.length; i++)
            ave += array[i];
        ave /= array.length;

        double sum = 0;
        for (double anArray : array) sum += (anArray - ave) * (anArray - ave);
        sum /= array.length;
        return sum;
    }
    public static double GetVar(int[] array){
        double ave = 0;
        for (int anArray : array) ave += anArray;
        ave /= array.length;

        double sum = 0;
        for (int anArray : array) sum += (anArray - ave) * (anArray - ave);
        sum /= array.length;
        return sum;
    }

    public static double GetEntro(double[] array){
        double sum = 0;
        for (double anArray : array) sum += anArray;
        double entro = 0;
        for (double anArray : array) {
            double p = 1.0 * anArray / sum;
            if (p > 0)
                entro += -(p * Math.log(p) / Math.log(2));
        }
        return entro;
    }
    public static double GetEntro(int[] array){
        double sum = 0;
        for (int anArray : array) sum += anArray;
        double entro = 0;
        for (int anArray : array) {
            double p = 1.0 * anArray / sum;
            if (p > 0)
                entro += -(p * Math.log(p) / Math.log(2));
        }
        return entro;
    }



    public static void main(String[] args) {
        double[] array = { 1, 2, 3, 4, 5, 6 };
        double ave = 0;
        for (double anArray : array) ave += anArray;
        ave /= array.length;

        double sum = 0;
        for (double anArray : array) sum += (anArray - ave) * (anArray - ave);
        sum /= array.length;

        System.out.println(sum);
    }


}
