package mypackage;

import java.util.LinkedHashMap;
import java.util.Map;

public class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";

            case "protected":
                if ("SAME_CLASS".equals(accessorContext) ||
                    "SAME_PACKAGE".equals(accessorContext) ||
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        Map<String, int[]> counts = new LinkedHashMap<>();
        counts.put("private", new int[]{0, 0});
        counts.put("default", new int[]{0, 0});
        counts.put("protected", new int[]{0, 0});
        counts.put("public", new int[]{0, 0});

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String modifier = attempt[0];
                    String context = attempt[1];

                    if (counts.containsKey(modifier)) {
                        String result = classifyAccess(modifier, context);
                        if ("ALLOWED".equals(result)) {
                            counts.get(modifier)[0]++;
                        } else {
                            counts.get(modifier)[1]++;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, int[]> entry : counts.entrySet()) {
            if (i > 0) {
                sb.append(" | ");
            }
            int allowed = entry.getValue()[0];
            int denied = entry.getValue()[1];
            sb.append(entry.getKey()).append(": ").append(allowed).append(" allowed / ").append(denied).append(" denied");
            i++;
        }

        return sb.toString();
    }

    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts == null) {
            return "None Denied";
        }

        for (int index = 0; index < attempts.length; index++) {
            String[] attempt = attempts[index];
            if (attempt != null && attempt.length >= 2) {
                String modifier = attempt[0];
                String context = attempt[1];

                String result = classifyAccess(modifier, context);
                if ("DENIED".equals(result)) {
                    return modifier + " via " + context + " (attempt #" + (index + 1) + ")";
                }
            }
        }

        return "None Denied";
    }
}