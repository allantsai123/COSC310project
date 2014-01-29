import java.util.*;

public final class TokenCollection {
    private String originalInput;
    private String lowercaseInput;
    private String strippedInput;
    List<Token> tokens = new ArrayList<>();
    List<String> words = new ArrayList<>();
    List<String> numbers = new ArrayList<>();

    public String getStrippedInput() {
        return strippedInput;
    }

    public String getOriginalInput() {
        return originalInput;
    }

    public String getLowercaseInput() {
        return lowercaseInput;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void parse(String originalInput) {
        this.originalInput = originalInput;
        lowercaseInput = originalInput.toLowerCase();
        strippedInput = stripPunctuationAndSquashWhitespace(lowercaseInput);

        tokens = tokenize(strippedInput);
        words = parseWords(strippedInput);
        numbers = parseNumbers(strippedInput);
    }

    private static String stripPunctuationAndSquashWhitespace(String input) {
        // Removes most punctuation, leaves $ - + ' , . ?
        String stripped = RegexPatterns.mostPunctuation.matcher(input).replaceAll(" ");

        // Squashes multiple whitespace into a single space
        String squashed = RegexPatterns.whitespace.matcher(input).replaceAll(" ");

        return squashed;
    }

    private static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();

        for (String fullToken : input.split(RegexPatterns.whitespace.pattern())) {
            tokens.add(new Token(fullToken));
        }

        return tokens;
    }

    private static List<String> parseWords(String input) {
        return RegexPatterns.getAllMatches(RegexPatterns.words, input);
    }

    private static List<String> parseNumbers(String input) {
        return RegexPatterns.getAllMatches(RegexPatterns.anyNum, input);
    }
}