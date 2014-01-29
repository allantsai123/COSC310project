import java.util.*;

public final class ParsedInput {
    public ParsedInputType type = ParsedInputType.DontUnderstand;
    public HashMap<String, String> inputs = new HashMap<>();
    public TokenCollection tokenCollection = new TokenCollection();

    public ParsedInputType getType() {
        return type;
    }

//    public HashMap<String, String> getInputs() {
//        return inputs;
//    }

    // TODO remove me when no longer used
    public String getOrigMsg() {
        return tokenCollection.getOriginalInput();
    }
//
//    public TokenCollection getTokenCollection() {
//        return tokenCollection;
//    }

//    public ParsedInput(ParsedInputType type, HashMap<String, String> inputs, TokenCollection tokens) {
//        this.type = type;
//        this.inputs = inputs;
//        this.tokenCollection = tokenCollection;
//    }

    public String setField(String fieldName, String value) {
        return inputs.put(fieldName, value);
    }

    public String getField(String fieldName) {
        return inputs.get(fieldName);
    }
}