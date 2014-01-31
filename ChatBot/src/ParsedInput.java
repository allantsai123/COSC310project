import java.util.*;

public final class ParsedInput {
    private static final double FUZZY_ERROR_RATE = 0.09; // percentage

    public ParsedInputType type = ParsedInputType.DontUnderstand;
    public HashMap<String, String> inputs = new HashMap<>();
    public TokenCollection tokenCollection = new TokenCollection();

    public ParsedInputType getType() {
        return type;
    }

    public String setField(String fieldName, String value) {
        return inputs.put(fieldName, value);
    }

    public String getField(String fieldName) {
        return inputs.get(fieldName);
    }

    private int getAllowedDistance(String source, String match) {
        int smallerLength = Math.min(source.length(), match.length());
        return (int) Math.floor(FUZZY_ERROR_RATE * (double) smallerLength);
    }

    public boolean containsAnyPhrase(List<String> phrases) {
        // Pad with spaces to help avoid in string searches
        String userInputPadded = " " + tokenCollection.getStrippedInput() + " ";
        double bestSimilarity = 0;
        String bestPhrase = "";
        int allowedDistance = 0;

        for (String phrase : phrases) {
            // Pad with spaces to help avoid in string searches
            String phrasePadded = " " + phrase + " ";

            // Get maximum allowed edit distance based on FUZZY_ERROR_RATE
            allowedDistance = getAllowedDistance(userInputPadded, phrasePadded);

            // Run the fuzzy substring matcher
            FuzzySubstringResults result = FuzzyMatching.Substring(userInputPadded, phrasePadded);

            IORW.writeLine("DEBUG -- dist:" + result.levenshteinDistance + " with: '" + phrase + "' at " + result.indexOfEndOfMatch + " rank " + result.similarity);

            if (result.levenshteinDistance <= allowedDistance) { // Input contains a close-enough recognized phrase
                if (result.similarity > bestSimilarity) {
                    // Keep track of the highest similarity phrase
                    bestSimilarity = result.similarity;
                    bestPhrase = phrase;
                }
            }
        }

        if (bestSimilarity > 0) {
            IORW.writeLine("DEBUG -- match found with: " + bestPhrase);
            return true;
        } else {
            return false;
        }
    }
}