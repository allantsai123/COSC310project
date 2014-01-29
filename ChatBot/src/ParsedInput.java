import java.util.*;

public final class ParsedInput {
    public ParsedInputType type = ParsedInputType.DontUnderstand;
    public HashMap<String, String> inputs = new HashMap<>();
    public TokenCollection tokenCollection = new TokenCollection();

    public ParsedInputType getType() {
        return type;
    }

    // TODO remove me when no longer used
    public String getOrigMsg() {
        return tokenCollection.getOriginalInput();
    }

    public String setField(String fieldName, String value) {
        return inputs.put(fieldName, value);
    }

    public String getField(String fieldName) {
        return inputs.get(fieldName);
    }
}