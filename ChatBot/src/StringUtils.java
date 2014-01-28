import java.util.*;

public final class StringUtils {
    public static boolean isNullOrEmpty(String input) {
        if (input == null) return true;
        if (input.isEmpty()) return true;
        return false;
    }

    // Checks if a string contains any of the items of a list
    public static boolean containsAnyIgnoreCase(String input, List<String> values) {
        boolean containsValue = false;

        for (int i = 0; i < values.size(); i++) {
            if (input.toLowerCase().contains(values.get(i).toLowerCase())) {
                containsValue = true;
            }
        }

        return containsValue;
    }
}