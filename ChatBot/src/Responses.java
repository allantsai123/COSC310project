import java.util.*;

public class Responses {
   private static java.util.Random rand = new java.util.Random();

   public static List<String> greetings = Arrays.asList("Good <TimeOfDay>", "Hi", "Hello", "Hey", "Greetings", "Howdy", "Welcome");
   public static List<String> farewells = Arrays.asList("Good <TimeOfDay>", "Bye", "Goodbye", "Farewell", "See ya", "See you later", "Take care", "Ciao");
   public static List<String> sorrybusys = Arrays.asList("I've moved on to helping someone else.", "Sorry, I'm busy now.", "I'm busy, try again later");

   // Keep building lists
   // Build a keywords class with similar structure
   // Finish script

   public static String getRandomResponse(List<String> responses) {
      return responses.get(rand.nextInt(responses.size()));
   }
}