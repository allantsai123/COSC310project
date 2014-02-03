import java.util.*;

public class TravelAgent {
    private ResponseMaker responseMaker = new ResponseMaker();

    // Agent state
    private ArrayList<ParsedInput> previousInputs = new ArrayList<>();
    public HashMap<String, String> savedInputs = new HashMap<>();
    private boolean userHasGreeted = false;
    private boolean userHasSaidFarewell = false;

    public ResponseMaker getResponseMaker() {
        return responseMaker;
    }

    public void resetState() {
        // TODO reset state
    }

    public String getResponse(ParsedInput parsedInput) {
        String response = "";

        // TravelBot doesn't have time for annoying users ;) (unless they're sorry)
        if (userHasSaidFarewell && (parsedInput.getType() != ParsedInputType.PleaseComeBack)) {
            return Responses.getRandomResponse(Responses.sorrybusys);
        }

        // Save all user entered variables
        savedInputs.putAll(parsedInput.inputs);

        // Check which kind of question or statement the user inputted
        switch (parsedInput.getType()) {
            case TooLong:
                response = "Sorry, your message is too long. I don't have time to read that.";
                break;

            case Greeting:
                response = greeting(parsedInput);
                break;

            case Farewell:
                response = farewell(parsedInput);
                break;

            case PleaseComeBack:
                response = pleaseComeBack(parsedInput);
                break;

            case Activity:
                response = responseMaker.getActivities();
                break;

            case ListCities:
                response = responseMaker.getCities();
                break;

            // How the user wants to get to destination
            case TravelMethod:
               // String transport = parsedInput.getField("travel method");
                response = responseMaker.getTravelMethod(savedInputs.get("travel method"));
                break;

            case Distance:
                response = responseMaker.getDistances(savedInputs.get("city"),savedInputs.get("city2"));
                break;


            case GetAround:
            	response = responseMaker.getAround();
                break;

            case Accomodations:
                response = responseMaker.getGenAccomodation();
                break;

            case Budget:
                int amount = Integer.valueOf(savedInputs.get("budget"));
                response = responseMaker.getBudgetAccom(amount);
                break;

            case Language:
                response = responseMaker.getLanguages();
                break;

            case SetDestination:
                //destination = parsedInput.getField("destination");
                response = responseMaker.getDestinationInfo(savedInputs.get("destination"), savedInputs.get("city"));
                break;

            case CheckWeather:
                response = responseMaker.getWeather(savedInputs.get("destination"));
                break;
                
            case SimpleYes:
            	response = responseMaker.getSimpleYes();
            	break;
            	
            case SimpleNo:
            	response = responseMaker.getSimpleNo();
            	break;
            
            case DontUnderstand:
                response = "Sorry, I don't understand what you said.";
                break;

            case None:
            default:
                response = "...";
                break;

            case Debug_Reset:
                resetState();
                response = "State reset.";
                break;
        }

        // Save valid inputs to memory
        if (parsedInput.getType().isWellFormed()) {
            previousInputs.add(parsedInput);
        }

        return response;
    }

    private String greeting(ParsedInput parsedInput) {
        // Either say hi or get snappy if greetings are repeated
        if (userHasGreeted) {
            return responseMaker.getGreetingRepeat();
        } else {
            userHasGreeted = true;
            return responseMaker.getGreeting(savedInputs.get("username"));
        }
    }

    private String farewell(ParsedInput parsedInput) {
        userHasSaidFarewell = true;
        return responseMaker.getFarewell(savedInputs.get("username"));
    }
    private String pleaseComeBack(ParsedInput parsedInput) {
        userHasSaidFarewell = false;
        return responseMaker.getImBack();
    }
}