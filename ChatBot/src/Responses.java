import java.util.*;

public final class Responses {
   private static java.util.Random rand = new java.util.Random();

   public static List<String> greetings = Arrays.asList("Good <TimeOfDay>, I'm Travel Bot. How can I help?", "Hi, how can I help?", "Hello. Can I help you plan a trip?","Hey", "Greetings", "Welcome to our travel center. How can I help?");
   public static List<String> farewells = Arrays.asList("Good <TimeOfDay>", "Bye", "Goodbye", "Farewell", "See ya", "See you later", "Take care", "Ciao","Thanks for stopping by.");
   public static List<String> sorrybusys = Arrays.asList("I've moved on to helping someone else.", "Sorry, I'm busy now.", "I'm busy, try again later");
   
   public static List<String> locations = Arrays.asList("cuba");
   public static List<String> cities = Arrays.asList("Havana", "Santiago de Cuba","Santa Lucia", "Varadero");
   
   public static List<String> activities = Arrays.asList("relax on the beach", "swim", "drink", "tan", "visit the museum of Che Guevara",
		   "see some of the historical landmarks", "play beach volleyball", "explore the local wildlife areas", "swim with dolphins");
   
   public static List<String> lang = Arrays.asList("Well the national language is Spanish.", "Spanish is the native language, although some people do speak English",
		   "People in Cuba speak Spanish.");
   
   public static List<String> transport = Arrays.asList("Well people in Havana and Santiago de Cuba use local buses. There are also government owned taxis.", "If you want to get between cities coach bus is the way to go.", 
		   "We could help set you up with a rental car if you'd like.", "You should be able to walk to most places within the cities. Otherwise coach buses are a good choice.");
   
   
   public static List<String> niceAccom = Arrays.asList("");
   public static List<String> medAccom = Arrays.asList("We have a nice hotel in Havana with a pool and swim up bar. Only $105 per night.", "I suggest Hotel Deauville, a great view of the city with a rooftop pool!", 
		   "Hotel Tulipan is one of our highest rated hotels. Right in the heart of Havana and only $95 a night.", "In Santa Lucia we have a fantastic beachfront hotel. Just steps outside your room, and only $100 a night.");
   
   public static List<String> cheapAccom = Arrays.asList("Our top discount hotels Hotel Plaza and Hotel Tropicoco for $55 a night.", 
		   "We've partnered with some great places. You might like Hotel Tropicoco, only $50, a night!","One of our best reviewed hotels is Club Amigo Caracol in Santa Lucia. It should fit your budget.");
   
   public static List<String> genAccom = Arrays.asList("We offer a wide variety of accomodations. Do you have a budget?", 
		   "We offer lots of different hotels. Do you have a price in mind?","Were you interested in a family resort, or one of our luxury offerings?");
   
   public static List<String> searching = Arrays.asList("Well let me find out.", "Calculating. ", "Let me look that up.", "Determining what's best for you.", "Searching...");
   
   
   public static List<String> simpleYes = Arrays.asList("Yes.", "Of course, let me look at some options.", "Sure! We can do that.", "I think that can be done.");

   public static List<String> simpleNo = Arrays.asList("No.", "That's not possible", "Sorry, I can't help with that.");

   public static List<String> dontKnow = Arrays.asList("I do not know the answer to that.");

   public static String getRandomResponse(List<String> responses) {
      return responses.get(rand.nextInt(responses.size()));
   }
}