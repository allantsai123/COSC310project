import java.util.*;

public class TravelAgent {
    private ResponseMaker responseMaker = new ResponseMaker();

    // Agent state
    private ArrayList<ParsedInput> previousInputs = new ArrayList<>();
    private String username;
    private String destination;
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

            case City:
                response = responseMaker.getCities();
                break;

            case TravelMethod:
                String transport = parsedInput.getField("travel method");
                response = responseMaker.getTransport(transport);
                break;

            case Accomodations:
                response = responseMaker.getGenAccomodation();
                break;

            case Budget:
                int amount = Integer.valueOf(parsedInput.getField("budget"));
                response = responseMaker.getBudgetAccom(amount);
                break;

            case Language:
                response = responseMaker.getLanguages();
                break;

            case SetDestination:
                destination = parsedInput.getField("destination");
                response = responseMaker.getDestinationInfo(destination);
                break;

            case CheckWeather:
                response = checkWeather(parsedInput);
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
        // Set username if given
        String name = parsedInput.getField("username");
        if (!StringUtils.isNullOrEmpty(name)) this.username = name;

        // Either say hi or get snappy if greetings are repeated
        if (userHasGreeted) {
            return responseMaker.getGreetingRepeat();
        } else {
            userHasGreeted = true;
            return responseMaker.getGreeting(username);
        }
    }

    private String farewell(ParsedInput parsedInput) {
        userHasSaidFarewell = true;
        return responseMaker.getFarewell(username);
    }
    private String pleaseComeBack(ParsedInput parsedInput) {
        userHasSaidFarewell = false;
        return responseMaker.getImBack();
    }

    private String checkWeather(ParsedInput parsedInput) {
        String destination = parsedInput.getField("destination");

        if (StringUtils.isNullOrEmpty(destination)) {
            // If user does not supply location, use a previously set one
            destination = this.destination;
        } else {
            // Save this location if user has not set one
            if (StringUtils.isNullOrEmpty(this.destination)) this.destination = destination;
        }
        return responseMaker.getWeather(destination);
    }

    public String getUsername() {
        return username;
    }

    public String getDestination() {
        return destination;
    }
}