import java.util.*;

public final class ParsedInput {
   private ParsedInputType type;
   private HashMap<String, String> inputs;

   public ParsedInputType getType() {
      return type;
   }

   public HashMap<String, String> getInputs() {
      return inputs;
   }

   public ParsedInput(ParsedInputType type, HashMap<String, String> inputs) {
      this.type = type;
      this.inputs = inputs;
   }

   public String getField(String fieldName) {
      return inputs.get(fieldName);
   }
}