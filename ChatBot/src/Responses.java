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
   public static List<String> lang = Arrays.asList("Well the national language is Spanish.", "Spanish is the native language, although some people do speak English", "People from Cuba speak Spanish.");
   
   public static List<String> transport = Arrays.asList("Sure! We can do that.", "Of course, let me look at some options.");
   public static List<String> distance = Arrays.asList("Well let me find out. It is ", "Calculating. ", "Let me look that up");
   
   public static List<String> niceAccom = Arrays.asList("");
   public static List<String> medAccom = Arrays.asList("");
   public static List<String> cheapAccom = Arrays.asList("");
   public static List<String> genAccom = Arrays.asList("We offer a wide variety of accomodations. Do you have a budget?", "We offer lots of different hotels. Do you have a price in mind?",
		   "");
   
   
   public static List<String> simpleYes = Arrays.asList("Yes.", "Of course.", "Sure!", "I think that can be done.");
   public static List<String> simpleNo = Arrays.asList("No.", "That's not possible", "Sorry, I can't help with that.");
   public static List<String> dontKnow = Arrays.asList("I do not know the answer to that.");
   


   public static String getRandomResponse(List<String> responses) {
       return responses.get(rand.nextInt(responses.size()));
   }

    
}

