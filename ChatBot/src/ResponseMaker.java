import java.util.*;

public final class ResponseMaker {
    LocationFactory lf = new LocationFactory();
    Location l; // = new Location(); // we need a constructor somewhere. In the parser when the destination is received? Then pass to ResponseMaker for use later?

    public ResponseMaker(){
    	
    }
    
    public ResponseMaker(Location l){
    	super();
    	this.l = l;
    }
    
    public String getGreeting(String username) {
        if (StringUtils.isNullOrEmpty(username)) {
            return substituteParameters(Responses.getRandomResponse(Responses.greetings));
        } else {
            return substituteParameters(Responses.getRandomResponse(Responses.greetings)) + " " + username;
        }
    }

    public String getGreetingRepeat() {
        return "Yes, we've said our greetings. Is there something I can help with?";
    }

    public String getFarewell(String username) {
        if (StringUtils.isNullOrEmpty(username)) {
            return substituteParameters(Responses.getRandomResponse(Responses.farewells));
        } else {
            return substituteParameters(Responses.getRandomResponse(Responses.farewells)) + " " + username;
        }
    }

    public String getImBack() {
        return "Okay, I'm back. What can I help with?";
    }

    public String getCities(String place) {
        String cities = "Well, the biggest are ";

        for (String s : Responses.cities) {
            cities = cities + ", " + s;
        }
        cities += ".";
        return cities;
    }
    
    public String getKeywordPlaces(String keyword){
    	return l.getPlaces(keyword);
    }
    
    public String getAround(){
    	return Responses.getRandomResponse(Responses.transport);
    }

    public String getTravelMethod(String travelMethod) {
        if (travelMethod == "car" || travelMethod == "drive") {
            return "You can't drive to Cuba";
        } else if (travelMethod == "boat" || travelMethod == "cruise"){
        	String response = Responses.searching + "\n";
    		response += Responses.getRandomResponse(Responses.boatResponses);
        	return response;
    	} else if (travelMethod == "fly" || travelMethod == "flight" || travelMethod == "plane"){
    		String response = Responses.searching + "\n";
    		response += Responses.getRandomResponse(Responses.flightResponses);
    		return response;
    	}
        
    	return "Sorry, we don't book trips by " + travelMethod;
    }

    public String getGenAccomodation() {
        return Responses.getRandomResponse(Responses.genAccom);
    }

    public String getBudgetAccom(int amount) {
        String response = "Searching for the best accomodations that match you budget. " + "\n";

        if (amount >= 130) {
            response += " " + Responses.getRandomResponse(Responses.niceAccom);
        } else if (amount > 90) {
            response += " " + Responses.getRandomResponse(Responses.medAccom);
        } else {
            response += " " + Responses.getRandomResponse(Responses.cheapAccom);
        }
        return response;
    }

    public String getDestinationInfo(String location, String city) {
        String destination = "";

        if (StringUtils.isNullOrEmpty(location) && StringUtils.isNullOrEmpty(city)) {
            return "Sorry you need to say where you want to go!";
        } else if (!StringUtils.isNullOrEmpty(location) && StringUtils.isNullOrEmpty(city)) {
            destination = location;
            return Responses.getRandomResponse(Responses.niceDest, "<Dest>", location) + " Where would you like to go in " + location + "?";
        } else if (StringUtils.isNullOrEmpty(location) && !StringUtils.isNullOrEmpty(city)) {
            destination = city;
        } else {
            destination = city + ", " + location;
        }
        
        l = new Location(destination);
        
        return Responses.getRandomResponse(Responses.niceDest, "<Dest>", location);
    }
    
    public String getTravelCost(String methodOfTravel){
    	if(methodOfTravel == ""){
    		return l.estimateTravelCost();
    	} else {
    		return l.estimateTravelCost(methodOfTravel);
    	}
    }

    public String getLanguages() {
        return Responses.getRandomResponse(Responses.lang);
    }

    public String getDistances(String city){   //1, String city2) {
        String response = Responses.getRandomResponse(Responses.searching);

        // Modifies origin to set user origin/destination
        // l.setOrigin(city);
        l.setDestination(city);
        //lf.locationMaker(city2);
        // lf.setTransportMethod(parsedInput.getField("transport"); Have a method like this so we can change how to determine distances
       
        // Get distance between two cities and return.
        response = "The distance between " + l.origin + " and " + city + " is " + l.distanceFromOrigin;
        return response;
    }

    public String getDontKnow(ParsedInputType type) {
        if (type == ParsedInputType.DontUnderstand) {
            return Responses.getRandomResponse(Responses.dontKnow);
        }

        return "I really don't know...";
    }

    public String getWeather(String destination) {
    	assert destination != null;
    	
        if (StringUtils.isNullOrEmpty(destination)) {
            return "I need to know a place to help you with that.";
        }
        
        l = new Location(destination);
        l.setDestination(destination);
        //lf.locationMaker(location);
        return "It is currently " + l.tempInCelcius + " degrees C in " + destination;
    }

    public String getActivities() {
        return "While you're there, you could " + Responses.getRandomResponse(Responses.activities) +
                ", or even " + Responses.getRandomResponse(Responses.activities);
    }

    // TODO fix me
    private String substituteParameters(String paramText) {
        // Substitute all dynamic values that don't depend on agent state
        paramText = paramText.replace("<TimeOfDay>", getTimeOfDay());
        //  paramText = paramText.replace("<Username>", agent.getUsername());

        // Make sure that all parameters were found
        checkAllParamsSubbed(paramText);

        return paramText;
    }

    // TODO test me
    private void checkAllParamsSubbed(String paramText) {
        int start = paramText.indexOf('<');
        int end = paramText.indexOf('>', start);

        if (start > 0 && end > start) {
            throw new RuntimeException("Failed to expand response parameter '" + paramText.substring(start, end) + "'");
        }
    }

    public String getTimeOfDay() {
        Calendar now = Calendar.getInstance();

        if ((now.get(Calendar.HOUR_OF_DAY) <= 5) || (now.get(Calendar.HOUR_OF_DAY) > 22)) {
            return "night";     // 10pm - 5am
        } else if ((now.get(Calendar.HOUR_OF_DAY) >= 5) || (now.get(Calendar.HOUR_OF_DAY) < 12)) {
            return "morning";   // 5am  - 12pm
        } else if ((now.get(Calendar.HOUR_OF_DAY) >= 12) || (now.get(Calendar.HOUR_OF_DAY) < 17)) {
            return "afternoon"; // 12pm - 5pm
        } else { //if ((now.get(Calendar.HOUR_OF_DAY) >= 17) || (now.get(Calendar.HOUR_OF_DAY) < 22)) {
            return "evening";   // 5pm  - 10pm
        }
    }

	public String getSimpleNo() {
		return Responses.getRandomResponse(Responses.simpleNo);
	}

	public String getSimpleYes() {
		return Responses.getRandomResponse(Responses.simpleYes);
		}

}