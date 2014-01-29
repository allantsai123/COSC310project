import java.util.*;

public final class ParsedInput {
    private ParsedInputType type;
    private HashMap<String, String> inputs;
    TokenCollection tokenCollection;

    public ParsedInputType getType() {
        return type;
    }

    public HashMap<String, String> getInputs() {
        return inputs;
    }

    public TokenCollection getTokenCollection() {
        return tokenCollection;
    }

    public ParsedInput(ParsedInputType type, HashMap<String, String> inputs, TokenCollection tokens) {
        this.type = type;
        this.inputs = inputs;
        this.tokenCollection = tokenCollection;
    }

    public String getField(String fieldName) {
        return inputs.get(fieldName);
    }
}