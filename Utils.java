import java.util.Arrays;

public class Utils {
    public static int[][] search(String[] src, String[] filter){
        int[] tmp = new int[src.length];
        Arrays.fill(tmp, -1);
        int[] res1 = new int[src.length];
        int[] res2 = new int[src.length];
        int index = 0;
        int start = 0;
        int temp = 0;
        for(int i = 0; i < filter.length; i++){
            start = 0;

            for(int j = 0; j < src.length; j++){
                if(src[j].equals(filter[i])){
                    tmp[j] = i;
                }
            }

        }
        for (int i = 0; i < src.length; i++) {
            if(tmp[i] >= 0){
                res1[index] = i;
                res2[index] = tmp[i];
                index++;
            }
        }
        if(index == 0)
            return null;
        res1 = Arrays.copyOf(res1, index);
        res2 = Arrays.copyOf(res2, index);
        int[][] res = new int[][]{ res1, res2};
        return res;
    }
}
