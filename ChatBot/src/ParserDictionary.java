import java.util.*;

// Not sure if this would actually help with searching the sentence, would be better
// for building sentences from scratch. Substring searches may be easier/quicker than
// trying to go through all the different lists.

public class ParserDictionary {
	public static List<String> greet = Arrays.asList("Good <TimeOfDay>", "Hi", "Hello", "Hey", "Greetings", "Howdy", "Welcome");
	public static List<String> leave = Arrays.asList("Good <TimeOfDay>", "Bye", "Goodbye", "Farewell", "See ya", "See you later", "Take care", "Ciao","Thanks");
	public static List<String> dest = Arrays.asList("Cuba");
	public static List<String> city = Arrays.asList("Havana", "Santiago de Cuba","Santa Lucia", "Varadero");
	public static List<String> bookInfo = Arrays.asList("passport", "reservation","book", "booking");
	public static List<String> lang = Arrays.asList("english","spanish","french");
	public static List<String> weather = Arrays.asList("weather","temperature","rain","sun","warm","cool","time of year");
	public static List<String> seasons = Arrays.asList("sping","summer","fall","winter");
	public static List<String> distance = Arrays.asList("far","long");
	public static List<String> questions = Arrays.asList("when","how","why","what","where","do","?","can");	
	public static List<String> syn = Arrays.asList("i","you","we","they","i'm","your","my","mine","there","it","their");	
	public static List<String> verbs = Arrays.asList("get","go", "go to", "want","are","is","like","speak","spoken","travel","cancel","drive","fly","sail");
	public static List<String> travel = Arrays.asList("car","plane","cruise","bus");
	
}
