public class Problem2 {
    public static class SummaryResult {
        public int totalItems;
        public int maxRow;
        public int maxCol;

        public SummaryResult(int totalItems, int maxRow, int maxCol) {
            this.totalItems = totalItems;
            this.maxRow = maxRow;
            this.maxCol = maxCol;
        }
    }

    public static SummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new SummaryResult(0, 0, 0);
        }

        int totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int currentItems = grid[r][c];
                totalItems += currentItems;

                if (currentItems > maxItems) {
                    maxItems = currentItems;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new SummaryResult(totalItems, maxRow, maxCol);
    }
}