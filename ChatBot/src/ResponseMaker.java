/**
 * Created by Manny on 26/01/14.
 */

import java.util.*;

public class ResponseMaker {
	
   LocationFactory lf = new LocationFactory();	
   Location l = new Location();
   
   public String getGreeting(String username) {
      if (StringHelper.isNullOrEmpty(username)) {
         return substituteParameters(Responses.getRandomResponse(Responses.greetings));
      } else {
         return substituteParameters(Responses.getRandomResponse(Responses.greetings)) + " " + username;
      }
   }

   public String getGreetingRepeat() {
      return "Yes, we've said our greetings. Is there something I can help with?";
   }

   public String getFarewell(String username) {
      if (StringHelper.isNullOrEmpty(username)) {
         return substituteParameters(Responses.getRandomResponse(Responses.farewells));
      } else {
         return substituteParameters(Responses.getRandomResponse(Responses.farewells)) + " " + username;
      }
   }

   public String getImBack() {
      return "Okay, I'm back. What can I help with?";
   }
   
   public String getCities(){   
   	   	String cities = "Well, the biggest are ";
   	   
   	   	for(String s : Responses.cities){
   	   		cities = cities + ", " + s;
   	   	}
   	   		cities += ".";
   	   	return cities;
   }

   public String getGenAccomodation() {
	
	   return Responses.getRandomResponse(Responses.genAccom);
   }

   public String getBudgetAccom(int amount){ 
	
	   String response = "Searching for the best accomodations that match you budget. " + "\n";
	   
	   if(amount >= 130){
		   response += " " + Responses.getRandomResponse(Responses.niceAccom);
	   } else if(amount > 90){
		   response += " " + Responses.getRandomResponse(Responses.medAccom);
	   } else {
		   response += " " + Responses.getRandomResponse(Responses.cheapAccom);
	   }
	   return response;
   }
   
   public String getTransport(String transport){
	   if(transport != "car" && transport != "drive"){
		   return Responses.getRandomResponse(Responses.transport);
	   } else if (transport == "car" || transport == "drive") {
		   return "You can't drive to Cuba";
	   }
	   return "Sorry, we don't book trips by " + transport;
   }

   public String getDestinationInfo(String location) {
      // TODO getDestinationInfo

      if (StringHelper.isNullOrEmpty(location)) {
         return "Sorry you need to say where you want to go!";
      } else if (!Responses.locations.contains(location.toLowerCase())){
    	  return "Sorry, we don't do trips to " + location;
      }

      return location + " is very nice! Where would you like to go in " + location;
   }
   
   public String getLanguages(){
	   return Responses.getRandomResponse(Responses.lang);
   }
   
   public String getDistances(String city1, String city2){
	   String response = Responses.getRandomResponse(Responses.searching);
	   
	   // Modifies origin to set user origin/destination
	   l.setOrigin(city1);
	   lf.locationMaker(city2);
	   // Get distance between two cities and return.
	   response = "The distance between " + city1 + " and " + city2 + " is " + l.distanceFromOrigin;
	   return response;
	   // TODO add calculated distances.
	   
   }
   
   public String getDontKnow(ParsedInputType type){
	   
	   if(type == ParsedInputType.DontUnderstand){
		   return Responses.getRandomResponse(Responses.dontKnow);
	   }
	   return "I really don't know.";
   }

   public String getWeather(String location) {
      // TODO getWeather
      if (StringHelper.isNullOrEmpty(location)) {
         return "I need to know a place to help you with that.";
      }
      l.setOrigin(location);
      lf.locationMaker(location);
      return "It is currently " + l.tempInCelcius + " degrees C in " + location;
   }
   
   public String getActivities(){
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
}