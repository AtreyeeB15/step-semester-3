package mypackage;

public class AccessChecker {
    
    public static String classifyAccess(String fieldModifier, String accessorContext){
        switch(fieldModifier){
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ACCESS GRANTED" : "ACCESS DENIED";
            case "default":
                return accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE")? "ACCESS GRANTED" : "ACCESS DENIED";
            case "protected":
                switch (accessorContext) {
                    case "SAME_CLASS":
                    case "SAME_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                        return "ALLOWED";
                    case "DIFFERENT_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                    default:
                        return "DENIED";
                }
            case "public":
                return "ACCESS GRANTED";
            default:
                return "ACCESS DENIED";
    }
}
public static String summarizeBatch(String[][] attempts){

    int allowedCount=0;
    int deniedCount=0;
   for(String[] attempt:attempts){
    String result = classifyAccess(attempt[0], attempt[1]);
            if (result.equals("ALLOWED")) {
                allowedCount++;
            } else {
                deniedCount++;
            }
        }

        return "Allowed: " + allowedCount + " | Denied: " + deniedCount;
    }
}

   
