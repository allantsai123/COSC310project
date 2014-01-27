import java.util.*;

/*	Dictionary method meant to hold the "understanding" of the AI. Mapping of Q and A's
 	
 */

public class DictionaryMap {
	
	private HashMap<ParsedInputType, List<String>> map;
	
	public DictionaryMap(){
		
		map = new HashMap<>();
		this.buildDictionary();
				
	}
	
	public String getResponse(ParsedInputType type){
		
		if(type == ParsedInputType.CheckWeather){
			List<String> resp = map.get(type);
		}
		
		return null;
	}
	
	public HashMap buildDictionary(){		
		
		for(ParsedInputType e : ParsedInputType.values()){

			switch (e) {
				case Greeting: map.put(e, Responses.greetings);
							   break;
				
			}
			
		}
		
		
		return map;
	}
}