import java.util.*;

public class TravelAgent {
   private ResponseMaker responseMaker = new ResponseMaker();

   // Agent state
   private ArrayList<ParsedInput> previousInputs = new ArrayList<ParsedInput>();
   private String username;
   private String destination;
   private boolean userHasGreeted = false;
   private boolean userHasSaidFarewell = false;

   public ResponseMaker getResponseMaker() {
      return responseMaker;
   }

   // Reset state
   public void reset() {
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
         case Greeting:
            response = greeting(parsedInput);
            break;

         case Farewell:
            response = farewell(parsedInput);
            break;

         case PleaseComeBack:
            response =  pleaseComeBack(parsedInput);
            break;
            
         case Activity:
        	 response = responseMaker.getActivities();
        	 break;
         
         case City:
        	 response = responseMaker.getCities();
        	 break;
        	 
         case TravelMethod:
        	 String transport = parsedInput.getField("travel method");	// Did I get how this works right?
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

         case Debug_Reset:
            reset();
            response = "State reset.";
            break;

         case DontUnderstand:
            response = "Sorry, I don't understand what you said.";
            break;
            
         

         case None:
         default:
            response = "...";
      }

      // Save valid inputs to memory
      if (parsedInput.getType() != ParsedInputType.DontUnderstand && parsedInput.getType() != ParsedInputType.None) {
         previousInputs.add(parsedInput);
      }

      return response;
   }

   private String greeting(ParsedInput parsedInput) {
      // Set username if given
      String name = parsedInput.getField("username");
      if (!StringHelper.isNullOrEmpty(name)) this.username = name;

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
      String date = parsedInput.getField("date");
      String destination = parsedInput.getField("destination");

      if (StringHelper.isNullOrEmpty(destination)) {
         // If user does not supply location, use a previously set one
         destination = this.destination;
      } else {
         // Save this location if user has not set one
         if (StringHelper.isNullOrEmpty(this.destination)) this.destination = destination;
      }

      return responseMaker.getWeather(destination, date);
   }

   public String getUsername() {
      return username;
   }

   public String getDestination() {
      return destination;
   }
}