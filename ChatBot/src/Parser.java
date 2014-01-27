import java.util.*;

public final class Parser {


   public static ParsedInput parseUserMessage(String userMessage) {
      ParsedInputType type = ParsedInputType.DontUnderstand;
      HashMap<String, String> inputs = new HashMap<String, String>();

      String userMsgLower = userMessage.toLowerCase();

      // TODO proper tokenizer to check for words, not substring search
      // TODO lots of work in here

      // Check for greetings and farewells
      if (StringHelper.containsAnyIgnoreCase(userMessage, Responses.greetings)) {
         type = ParsedInputType.Greeting;
      } else if (StringHelper.containsAnyIgnoreCase(userMessage, Responses.farewells)) {
         type = ParsedInputType.Farewell;
      }

      // Check for user telling their name
      if (userMsgLower.contains("im dave")) {
         inputs.put("username", "Dave");
         type = ParsedInputType.Greeting;
      }

      // Check for user entering destination
      if (userMsgLower.contains("go to cuba")) {
         type = ParsedInputType.SetDestination;
         inputs.put("destination", "cuba");
      }

      // Check for user asking for weather info
      if (userMsgLower.contains("weather")) {
         inputs.put("date", "null");
         type = ParsedInputType.CheckWeather;
      }

      // User said bye but now wants to talk
      if (userMsgLower.contains("please help")) {
         type = ParsedInputType.PleaseComeBack;
      }

      return new ParsedInput(type, inputs);
   }

//   private static String extractValueUntilSymbol(String input, String keyphrase, List<String> symbols) {
//      int start = input.indexOf(keyphrase);
//      int startOfValue = start + keyphrase.length();
//
//      if (start >= 0 && input.length() > startOfValue) {
//        // String name = StringHelper.extractTextUntilEndOfSentence(input, startOfValue);
//
//      }
//   }


}