
public final class FuzzyMatching {
    // Public wrapper for getLevenshteinDistance.
    // Allows us to pass in string but internally use the same char array when recursing
    public static int EditDistance(String s, String t) {
        return LevenshteinDistance(s.toCharArray(), t.toCharArray());
    }

    // Dynamic programming implementation of Levenshtein distance
    // Copied almost directly from Wikipedia (http://en.wikipedia.org/wiki/Levenshtein_distance)
    private static int LevenshteinDistance(char[] s, char[] t) {
        int m = s.length;
        int n = t.length;

        // for all i and j, d[i,j] will hold the Levenshtein distance between
        // the first i characters of s and the first j characters of t;
        int[][] d = new int[m + 1][n + 1];

        // source prefixes can be transformed into empty string by dropping all characters
        for (int i = 1; i <= m; i++) {
            d[i][0] = i;
        }

        // target prefixes can be reached from empty source prefix by inserting every characters
        for (int j = 1; j <= n; j++) {
            d[0][j] = j;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (s[i - 1] == t[j - 1]) {
                    d[i][j] = d[i - 1][j - 1];    // match
                } else {
                    d[i][j] = getMinimum(
                            d[i - 1][j] + 1,      // deletion
                            d[i][j - 1] + 1,      // insertion
                            d[i - 1][j - 1] + 1); // substitution
                }
            }
        }

        return d[m][n];
    }

    public static FuzzySubstringResults Substring(String s, String t) {
        return Substring(s.toCharArray(), t.toCharArray());
    }

    // Almost the same as the LevenshteinDistance function
    // Except we don't care how many characters we have to skip to start matching
    // and we don't care how many characters come after a successful match
    public static FuzzySubstringResults Substring(char[] s, char[] t) {
        int m = s.length;
        int n = t.length;

        // Base cases
        if (m == 0) return new FuzzySubstringResults(Integer.MAX_VALUE, -1, 0);
        if (n == 0) return new FuzzySubstringResults(m, 0, 1);

        int[][] d = new int[m + 1][n + 1];
        // leave the top row all zeros, don't care how many characters to skip

        for (int j = 1; j <= n; j++) {
            d[0][j] = j;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (s[i - 1] == t[j - 1]) {
                    d[i][j] = d[i - 1][j - 1];    // characters match
                } else {
                    d[i][j] = getMinimum(
                            d[i - 1][j] + 1,      // deletion
                            d[i][j - 1] + 1,      // insertion
                            d[i - 1][j - 1] + 1); // substitution
                }
            }
        }

        // Check for the minimum in the bottom row
        // We don't care how many characters come after a match
        int min = Integer.MAX_VALUE;
        int indexOfMin = 0;

        for (int i = 1; i <= m; i++) {
            // s1 += Integer.toString(d[i][n - 1]) + ",";
            // s2 += Integer.toString(i) + ",";
            if (d[i][n - 1] <= min) {
                min = d[i][n - 1];
                indexOfMin = i;
            }
        }

        return new FuzzySubstringResults(min, indexOfMin, getSimilarity(m,n,min));
    }

    // Gets a 'similarity index' for two fuzzy matched strings
    // higher values indicate higher similarity
    private static double getSimilarity(int sourceLen, int matchLen, int distance) {
        // TODO make sure this formula actually works
        return (double)(sourceLen + matchLen + 1) / (double)(distance + 1);
    }

    // Gets the right index of the source string where the best match of substring is found
    // Returns -1 if errors are over the allowed amount
    public static int IndexOfEndOfMatch(String source, String substring, double errorAllowed) {
        int smallerLength = Math.min(source.length(), substring.length());
        int allowedDistance = (int) Math.round(errorAllowed * (double) smallerLength);

       // IORW.writeLine("len " + smallerLength);
       // IORW.writeLine("allowed " + allowedDistance);

        FuzzySubstringResults results = Substring(source, substring);

        //IORW.writeLine("dist " + results.levenshteinDistance);
       // IORW.writeLine("pos " + results.indexOfEndOfMatch);

        if (results.levenshteinDistance <= allowedDistance) {
            return results.indexOfEndOfMatch;
        } else {
            return -1;
        }
    }

    public static boolean Contains(String source, String substring, double errorAllowed ) {
        if (IndexOfEndOfMatch(source, substring, errorAllowed) > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static int getMinimum(int... values) {
        int min = values[0];

        for (int i = 1; i < values.length; i++) {
            if (values[i] < min) min = values[i];
        }

        return min;
    }
}