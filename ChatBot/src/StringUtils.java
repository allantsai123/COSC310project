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

    public static String toTitleCase(String input) {
        if (input.length() == 0) return "";

        String[] words = input.split(Regex.whitespace.pattern());
        String titleCased = "";

        // First word
        titleCased += words[0].substring(0, 1).toUpperCase() + words[0].substring(1);

        for (int i = 1; i < words.length; i++) {
            titleCased += " " + words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }

        return titleCased;
    }
}