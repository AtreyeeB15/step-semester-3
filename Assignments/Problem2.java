public class Problem2 {
    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        int m = counterA.length;
        int n = counterB.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }

        while (i < m) {
            merged[k++] = counterA[i++];
        }

        while (j < n) {
            merged[k++] = counterB[j++];
        }

        return merged;
    }
}