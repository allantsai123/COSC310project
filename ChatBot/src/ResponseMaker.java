import java.util.*;

public final class ResponseMaker {
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

   public String getDestinationInfo(String location) {
      // TODO getDestinationInfo

      if (StringUtils.isNullOrEmpty(location)) {
         return "Sorry I don't know where you are asking about.";
      }

      return location + " sounds very nice!";
   }

   public String getWeather(String location, String date) {
      // TODO getWeather

      if (StringUtils.isNullOrEmpty(location) || StringUtils.isNullOrEmpty(date)) {
         return "I need to know a place and a date to help you with that.";
      }

      return "It's hot in " + location;
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