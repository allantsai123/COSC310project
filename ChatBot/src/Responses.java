import java.util.*;

public class Responses {
   private static java.util.Random rand = new java.util.Random();

   public static List<String> greetings = Arrays.asList("Good <TimeOfDay>, I'm Travel Bot. How can I help?", "Hi, how can I help?", "Hello. Can I help you plan a trip?",
		   "Hey", "Greetings", "Welcome to our travel center. How can I help?");
   public static List<String> farewells = Arrays.asList("Good <TimeOfDay>", "Bye", "Goodbye", "Farewell", "See ya", "See you later", "Take care", "Ciao",
		   "Thanks for stopping by.");
   public static List<String> sorrybusys = Arrays.asList("I've moved on to helping someone else.", "Sorry, I'm busy now.", "I'm busy, try again later");
   public static List<String> locations = Arrays.asList("Cuba");
   public static List<String> cities = Arrays.asList("Havana", "Santiago de Cuba","Santa Lucia", "Varadero");
   public static List<String> activities = Arrays.asList("relax on the beach", "swim", "drink", "tan", "visit the museum of Che Guevara",
		   "see some of the historical landmarks", "stop by Guantanimo");
   public static List<String> lang = Arrays.asList("english","spanish","french");
   
   public static List<String> simpleYes = Arrays.asList("Yes.");
   public static List<String> simpleNo = Arrays.asList("No.");
   public static List<String> dontKnow = Arrays.asList("I do not know the answer to that.");


   public static String getRandomResponse(List<String> responses) {
       return responses.get(rand.nextInt(responses.size()));
   }

    
}

