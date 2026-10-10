public class Problem1 {
    public static class Result {
        public int rowIndex;
        public int total;

        public Result(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }
    }

    public static Result findTopper(int[][] marks) {
        int bestStudentIndex = 0;
        int maxTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestStudentIndex = i;
            }
        }

        return new Result(bestStudentIndex, maxTotal);
    }
}