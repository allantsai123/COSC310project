import java.util.*;

public final class Parser {
    public static ParsedInput parseUserMessage(String userMessage) {
        ParsedInput parsedInput = new ParsedInput();

        String userMsgLower = userMessage.toLowerCase().trim();
        if (userMsgLower.compareTo("exit") == 0) System.exit(0);
        if (userMsgLower.isEmpty()) {
            parsedInput.type = ParsedInputType.None;
        } else {
            // Create the token collection
            parsedInput.tokenCollection.parse(userMessage);

            // In order, check for
            parseGreetingOrFarewell(parsedInput);
            parsePleaseComeBack(parsedInput);
            parseDestination(parsedInput);
            parseWeather(parsedInput);

            // parse(parsedInput);
            // parse(parsedInput);
        }

        return parsedInput;
    }

    private static void parse(ParsedInput parsedInput) {

    }

    private static void parseGreetingOrFarewell(ParsedInput parsedInput) {
        // Check for greetings and farewells
        if (StringUtils.containsAnyIgnoreCase(parsedInput.getOrigMsg(), Responses.greetings)) {
            parsedInput.type = ParsedInputType.Greeting;
        } else if (StringUtils.containsAnyIgnoreCase(parsedInput.getOrigMsg(), Responses.farewells)) {
            parsedInput.type = ParsedInputType.Farewell;
        }

        // Check for user telling their name
        if (parsedInput.getOrigMsg().contains("im dave")) {
            parsedInput.setField("username", "Dave");
            parsedInput.type = ParsedInputType.Greeting;
        }
    }

    private static void parsePleaseComeBack(ParsedInput parsedInput) {
        if (parsedInput.getOrigMsg().contains("please help")) {
            parsedInput.type = ParsedInputType.PleaseComeBack;
        }
    }

    private static void parseDestination(ParsedInput parsedInput) {
        if (parsedInput.getOrigMsg().contains("go to cuba")) {
            parsedInput.type = ParsedInputType.SetDestination;
            parsedInput.setField("destination", "cuba");
        }
    }

    private static void parseWeather(ParsedInput parsedInput) {
        if (parsedInput.getOrigMsg().contains("weather")) {
            parsedInput.setField("date", "null");
            parsedInput.type = ParsedInputType.CheckWeather;
        }
    }
}