import java.util.HashMap;
import java.util.Map;

public class Problem3 {
    public static class PopularResult {
        public String item;
        public int count;

        public PopularResult(String item, int count) {
            this.item = item;
            this.count = count;
        }
    }

    public static PopularResult mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();
        for (String order : orders) {
            counts.put(order, counts.getOrDefault(order, 0) + 1);
        }

        int maxCount = 0;
        String mostPopularItem = "";

        for (String order : orders) {
            int currentCount = counts.get(order);
            if (currentCount > maxCount) {
                maxCount = currentCount;
                mostPopularItem = order;
            }
        }

        return new PopularResult(mostPopularItem, maxCount);
    }
}