import java.io.*;
import java.util.*;

public final class Program {
   private static Scanner reader = new Scanner(System.in);
   private static TravelAgent agent = new TravelAgent();
   private static String botName = "Travel Bot";

   public static void main(String[] args) {
      try {
         writeFromBot(agent.getResponseMaker().getGreeting(null) + ". How can I help you?");
         readParsePrintLoop();
      } catch (Exception e) {
         System.out.println("\nAn unhandled exception occurred.\n" + e.getMessage() + "\n" + getStackTrace(e));
         System.exit(1);
      }
   }

   private static String getStackTrace(Exception e) {
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      return sw.toString();
   }

   public static void readParsePrintLoop() {
      String userInput = "";
      String response = "";

      while (true) {
         // Read from console
         userInput = reader.nextLine();

         // Parse input
         ParsedInput parsedInput = Parser.parseUserMessage(userInput);

         // Send parsed messaged to agent
         response = agent.getResponse(parsedInput);

         // Display response to user
         writeFromBot(response);
      }
   }

   private static void writeFromBot(String message) {
      // We want to display the username if it's available
      String user = "User";
      if (!StringHelper.isNullOrEmpty(agent.getUsername())) user = agent.getUsername();

      // Write out our response with header & footer
      System.out.println("\n" + botName + ":\n" + message + "\n\n" + user + ":");
   }
}