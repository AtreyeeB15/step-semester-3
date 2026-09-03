public class P4 {
    public static char findFirstNonRepeatingChar(String text) {
        int[] freq = new int[256];

        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        for (int i = 0; i < text.length(); i++) {
            if (freq[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '\0';
    }

    public static void processString(String text) {
        char result = findFirstNonRepeatingChar(text);
        if (result != '\0') {
            System.out.println("\"" + text + "\" -> First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("\"" + text + "\" -> No Non-Repeating Character Found");
        }
    }

    public static void main(String[] args) {
        processString("swiss");
        processString("aabbcc");
    }
}