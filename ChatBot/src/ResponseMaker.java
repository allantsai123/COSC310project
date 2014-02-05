import java.util.*;

public final class ResponseMaker {
    LocationFactory lf = new LocationFactory();
    Location l; // = new Location(); // we need a constructor somewhere. In the parser when the destination is received? Then pass to ResponseMaker for use later?

    public ResponseMaker(){}
    
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

    public String getYoureWelcome() {
        return Responses.getRandomResponse(Responses.youreWelcome);
    }

    public String getCities() {
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

    public String getTravelMethod(String travelMethod, String location) {
        if (travelMethod == "car" || travelMethod == "drive") {
            return "You can if you want to.";
        } else if (travelMethod == "boat" || travelMethod == "cruise"){
        	String response = Responses.getRandomResponse(Responses.searching) + "\n";
    		response += Responses.getRandomResponse(Responses.boatResponses, "<Dest>",location);
        	return response;
    	} else if (travelMethod == "fly" || travelMethod == "flight" || travelMethod == "plane"){
    		String response = Responses.getRandomResponse(Responses.searching) + "\n";
    		response += Responses.getRandomResponse(Responses.flightResponses, "<Dest>",location);
    		return response;
    	}
        
    	return "Sorry, we don't book trips by " + travelMethod;
    }

    public String getGenAccomodation() {
        return Responses.getRandomResponse(Responses.genAccom);
    }

    public String getBudgetAccom(int amount, String location) {
        String response = "Searching for the best accomodations that match you budget. " + "\n";

        if (amount >= 130) {
            response += " " + Responses.getRandomResponse(Responses.niceAccom, "<Dest>",location);
        } else if (amount > 90) {
            response += " " + Responses.getRandomResponse(Responses.medAccom, "<Dest>",location);
        } else {
            response += " " + Responses.getRandomResponse(Responses.cheapAccom, "<Dest>",location);
        }
        
        return response;
    }
    
    public String getLocalFood(){
    	String response = Responses.getRandomResponse(Responses.searching) + "\n";
    	if(l.getPlaces("food") == "I could not find any places for food"){
    		response += "";
    	} else {
    		response += l.getPlaces("food");
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
    	//System.out.println(l.getPlaces("lodging"));
        return Responses.getRandomResponse(Responses.niceDest, "<Dest>", destination);
    }
    
    public String getTravelCost(String methodOfTravel){
    	if(methodOfTravel == ""){
    		return l.estimateTravelCost();
    	} else {
    		return l.estimateTravelCost(methodOfTravel);
    	}
    }

    public String getLanguages(String dest) {
        return Responses.getRandomResponse(Responses.lang, "<Dest>", dest);
    }

    public String getDistances(String city, String city2) {
        String response = Responses.getRandomResponse(Responses.searching) + "\n";
        l = new Location(city,city2);
        
        response = "The distance between " + city + " and " + city2 + " is " + l.distanceFromOrigin + " kilometers.";
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
        
        Random r = new Random();
        int temp = r.nextInt(10);
        temp = temp+20;
        
        l.setDestination(destination);
        lf.build(l);
        return "It is currently " + l.tempInCelcius + " degrees C in " + l.destination;
    }

    public String getActivities() {
        return "While you're there, you could " + Responses.getRandomResponse(Responses.activities) +
                ", or even " + Responses.getRandomResponse(Responses.activities);
    }

    private String substituteParameters(String paramText) {
        paramText = paramText.replace("<TimeOfDay>", getTimeOfDay());
        checkAllParamsSubbed(paramText);
        return paramText;
    }
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