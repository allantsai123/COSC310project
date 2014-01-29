public final class Token {
    private String fullToken;

    public Token(String fullToken) {
        this.fullToken = fullToken;
    }

    public String getFullToken() {
        return fullToken;
    }

    public String extractWord() {
        return RegexPatterns.getFirstMatch(RegexPatterns.words, fullToken);
    }

    public double extractNumber() {
        return Double.parseDouble(RegexPatterns.getFirstMatch(RegexPatterns.anyNum, fullToken));
    }
}