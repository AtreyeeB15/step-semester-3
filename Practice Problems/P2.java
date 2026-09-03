public class P2 {
    public static boolean isPalindromeIterative(String text) {
        int left = 0, right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) return true;
        if (text.charAt(0) != text.charAt(text.length() - 1)) return false;
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        return new String(original).equals(new String(reversed));
    }

    public static void checkPalindrome(String text) {
        String iter = isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome";
        String recur = isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome";
        String arr = isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome";

        System.out.println("\"" + text + "\" -> Iterative: " + iter + " | Recursive: " + recur + " | Array Reversal: " + arr);
    }

    public static void main(String[] args) {
        checkPalindrome("madam");
        checkPalindrome("hello");
    }
}