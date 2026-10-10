import java.util.HashSet;
import java.util.Set;

public class Problem4 {

    // Approach 1: Hash Set Lookup (Optimal)
    public static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> visited = new HashSet<>();
        for (int value : nums) {
            int needed = target - value;
            if (visited.contains(needed)) {
                return true;
            }
            visited.add(value);
        }
        return false;
    }

    // Approach 2: Two-Pointer Method after Sorting (Alternative)
    public static boolean hasPairWithSumTwoPointer(int[] nums, int target) {
        int[] sortedNums = nums.clone();
        java.util.Arrays.sort(sortedNums);
        
        int left = 0;
        int right = sortedNums.length - 1;

        while (left < right) {
            int sum = sortedNums[left] + sortedNums[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}