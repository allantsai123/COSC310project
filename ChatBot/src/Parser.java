import java.util.*;

public final class Parser {
    private static final int MAX_SUPPORTED_MESSAGE_SIZE = 500;

    public static ParsedInput parseUserMessage(String userMessage) {
        ParsedInput parsedInput = new ParsedInput();

        String userMsgLower = userMessage.toLowerCase().trim();
        if (userMsgLower.compareTo("exit") == 0) System.exit(0);
        if (userMsgLower.isEmpty()) {
            parsedInput.type = ParsedInputType.None;
        } else if (userMsgLower.length() > MAX_SUPPORTED_MESSAGE_SIZE) {
            parsedInput.type = ParsedInputType.TooLong;
        } else {
            // Create the token collection
            parsedInput.tokenCollection.parse(userMessage);

            // In order, check for
            parseGreetingOrFarewell(parsedInput);
            parsePleaseComeBack(parsedInput);
            parseDestination(parsedInput);
            parseWeather(parsedInput);
            parseCity(parsedInput);

            // parse(parsedInput);
            // parse(parsedInput);
        }

        return parsedInput;
    }

    private static void parse(ParsedInput parsedInput) {

    }

    private static void parseGreetingOrFarewell(ParsedInput parsedInput) {
        // Check for greetings and farewells

      if (parsedInput.containsAnyPhrase(ParserDictionary.greet)) {
          parsedInput.type = ParsedInputType.Greeting;
      } else if (parsedInput.containsAnyPhrase(ParserDictionary.leave)) {
          parsedInput.type = ParsedInputType.Farewell;
      }

        // Check for user telling their name
//        if (parsedInput.getOrigMsg().contains("im dave")) {
//            parsedInput.setField("username", "Dave");
//            parsedInput.type = ParsedInputType.Greeting;
//        }
    }

    private static void parsePleaseComeBack(ParsedInput parsedInput) {
        if (parsedInput.containsAnyPhrase(ParserDictionary.comeBack)) {
            parsedInput.type = ParsedInputType.PleaseComeBack;
        }
    }

    private static void parseDestination(ParsedInput parsedInput) {
        String match = parsedInput.getMatchingPhrase(ParserDictionary.dest);

        if (!match.isEmpty()) {
            parsedInput.type = ParsedInputType.SetDestination;
            parsedInput.setField("destination", match);
        }
    }

    private static void parseWeather(ParsedInput parsedInput) {
        if (parsedInput.containsAnyPhrase(ParserDictionary.weather)) {
            parsedInput.type = ParsedInputType.CheckWeather;
        }
    }

    private static void parseCity(ParsedInput parsedInput) {
        String match = parsedInput.getMatchingPhrase(ParserDictionary.city);

        // TODO add city to destination so we get ex: Havana, Cuba

        if (!match.isEmpty()) {
            parsedInput.type = ParsedInputType.City;
            parsedInput.setField("city", match);
        }
    }
}