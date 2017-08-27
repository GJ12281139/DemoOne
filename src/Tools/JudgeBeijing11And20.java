package Tools;

public class JudgeBeijing11And20 {
    /**
     * Divide Beijng into 11 * 20 blocks, and count how much blocks User_X has appeared.
     *
     * @param lng
     * @param lat
     * @return Count：How may blocks are the user have been in?
     */
    public static int JudgeBeijing11And20(String lng, String lat) {
        int count = 0;
        outterLoop:
        for (double i = 39.70; i < 40.1; i = i + 0.04)
            for (double j = 116.0; j < 116.6; j = j + 0.03) {
                if (Double.valueOf(lng) >= j & Double.valueOf(lng) < j + 0.03
                        & Double.valueOf(lat) >= i & Double.valueOf(lat) < i + 0.04) {
                    break outterLoop;
                } else
                    count++;
            }
        return count;
    }
}
