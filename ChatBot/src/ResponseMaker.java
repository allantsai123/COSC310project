/**
 * Created by Manny on 26/01/14.
 */

import java.util.*;

public class ResponseMaker {
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
      } else if (!Responses.locations.contains(location)){
    	  return "Sorry, we don't do trips to " + location;
      }

      return location + " is very nice! Where would you like to go in " + location;
   }
   
   public String getLanguages(){
	   return Responses.getRandomResponse(Responses.lang);
   }
   
   public String getDistances(String city1, String city2){
	   // Something with the API to add to 
	   String response = Responses.getRandomResponse(Responses.distance);
	   return response;
	   
   }
   
   public String getDontKnow(ParsedInputType type){
	   
	   if(type == ParsedInputType.DontUnderstand){
		   return Responses.getRandomResponse(Responses.dontKnow);
	   }
	   return "I really don't know.";
   }

   public String getWeather(String location, String date) {
      // TODO getWeather

      if (StringHelper.isNullOrEmpty(location) || StringHelper.isNullOrEmpty(date)) {
         return "I need to know a place and a date to help you with that.";
      }

      return "It's hot in " + location;
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

   public String getGenAccomodation() {
	
	   return null;
   }

   public String getBudgetAccom(int type){ 
	   return null;
   }
}