
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

        //IORW.writeLine(matrixToString(d));

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

        //IORW.writeLine(matrixToString(d));

        // Check for the minimum in the bottom row
        // We don't care how many characters come after a match
        int min = Integer.MAX_VALUE;
        int indexOfMin = 0;
        for (int i = 0; i <= m; i++) {
            if (d[i][n] <= min) {
                min = d[i][n];
                indexOfMin = i;
            }
        }

        return new FuzzySubstringResults(min, indexOfMin, getSimilarity(m, n, min));
    }

    // Gets a 'similarity index' for two fuzzy matched strings
    // higher values indicate higher similarity
    private static double getSimilarity(int sourceLen, int matchLen, int distance) {
        // TODO make sure this formula actually works
        return (double) (matchLen) / (double) (distance + 1);
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

    public static boolean Contains(String source, String substring, double errorAllowed) {
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

    public static int numDigits(int x) {
        if (x == 0) {
            return 1;
        } else if (x > 0) {
            return 1 + (int)Math.floor(Math.log10(Math.abs(x)));
        } else { // x < 0
            return 2 + (int)Math.floor(Math.log10(Math.abs(x)));
        }
    }

    public static String matrixToStringMatlab(int[][] M) {
        return matrixToString(M, "[", "]", "[", "]; ", ' ', " ");
    }

    public static String matrixToString(int[][] M) {
        return matrixToString(M, "", "", "", "\r\n", ' ', "  ");
    }

    public static String matrixToString(int[][] M, String prefix, String suffix, String rowPrefix, String rowSuffix, char padChar, String colDelim) {
        int m = M.length; // # rows
        int n = M[0].length; // # cols

        StringBuilder str = new StringBuilder(m * n * 2);
        int[][] padding = new int[m][n];
        int maxDigits = 0;
        int tmpDigits = 0;

        str.append(prefix);

        // First pass to check for how much padding the cols will need
        for (int i = 0; i < M.length; i++) { // Rows
            for (int j = 0; j < M[i].length; j++) { // Col
                tmpDigits = numDigits(M[i][j]);
                padding[i][j] = tmpDigits;
                if (tmpDigits > maxDigits) maxDigits = tmpDigits;
            }
        }

        for (int i = 0; i < M.length; i++) { // Rows
            str.append(rowPrefix);

            // Add first element of col
            str.append(pad(maxDigits - padding[i][0], padChar));
            str.append(M[i][0]);

            for (int j = 1; j < M[i].length; j++) { // Col
                str.append(colDelim);
                str.append(pad(maxDigits - padding[i][0], padChar));
                str.append(M[i][j]);
            }
            str.append(rowSuffix);
        }

        str.append(suffix);

        return str.toString();
    }

    // Returns a string repeating padding, length times
    public static String pad(int length, char padding) {
        return new String(new char[length]).replace('\0', padding); // Admittedly slightly hacky
    }
}