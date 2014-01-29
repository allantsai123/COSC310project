import java.util.*;

public final class Parser {
    public static ParsedInput parseUserMessage(String userMessage) {
        ParsedInputType type = ParsedInputType.DontUnderstand;
        HashMap<String, String> inputs = new HashMap<>();
        TokenCollection tokenCollection = new TokenCollection();

        String userMsgLower = userMessage.toLowerCase().trim();
        if (userMsgLower.compareTo("exit") == 0) System.exit(0);
        if (userMsgLower.isEmpty()) {
            type = ParsedInputType.None;
        } else {
            // Create the token collection
            tokenCollection.parse(userMessage);

            // In order, check for
            parseGreetingOrFarewell(userMsgLower, type, inputs, tokenCollection);
            parsePleaseComeBack(userMsgLower, type, inputs, tokenCollection);
            parseDestination(userMsgLower, type, inputs, tokenCollection);
            parseWeather(userMsgLower, type, inputs, tokenCollection);

            // parse(tokenCollection, type, inputs);
            // parse(tokenCollection, type, inputs);
        }

        return new ParsedInput(type, inputs, tokenCollection);
    }

    private static void parse(TokenCollection tokens, ParsedInputType type, HashMap<String, String> inputs) {

    }

    private static void parseGreetingOrFarewell(String userMessage, ParsedInputType type, HashMap<String, String> inputs, TokenCollection tokens) {
        // Check for greetings and farewells
        if (StringUtils.containsAnyIgnoreCase(userMessage, Responses.greetings)) {
            type = ParsedInputType.Greeting;
        } else if (StringUtils.containsAnyIgnoreCase(userMessage, Responses.farewells)) {
            type = ParsedInputType.Farewell;
        }

        // Check for user telling their name
        if (userMessage.contains("im dave")) {
            inputs.put("username", "Dave");
            type = ParsedInputType.Greeting;
        }
    }

    private static void parsePleaseComeBack(String userMessage, ParsedInputType type, HashMap<String, String> inputs, TokenCollection tokens) {
        if (userMessage.contains("please help")) {
            type = ParsedInputType.PleaseComeBack;
        }
    }

    private static void parseDestination(String userMessage, ParsedInputType type, HashMap<String, String> inputs, TokenCollection tokens) {
        if (userMessage.contains("go to cuba")) {
            type = ParsedInputType.SetDestination;
            inputs.put("destination", "cuba");
        }
    }

    private static void parseWeather(String userMessage, ParsedInputType type, HashMap<String, String> inputs, TokenCollection tokens) {
        if (userMessage.contains("weather")) {
            inputs.put("date", "null");
            type = ParsedInputType.CheckWeather;
        }
    }
}