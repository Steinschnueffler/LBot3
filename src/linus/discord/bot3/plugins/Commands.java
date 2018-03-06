package linus.discord.bot3.plugins;

import java.util.HashMap;
import java.util.Map;

import linus.discord.bot3.resource.ResourceLoader;

class Commands {

	private static final Map<String, Map<String, String>> custom = new HashMap<>();
	private static final Map<String, String> specific = ResourceLoader.loadSpecificCommands();
		
	public static Map<String, String> getCustom(String id){
		if(!custom.containsKey(id))
			custom.put(id, ResourceLoader.loadCustomCommands(id));
		return custom.get(id);
	}
	
	public static Map<String, Map<String, String>> getAllCustoms(){
		return custom;
	}
	
	public static Map<String, String> getSpecific(){
		return specific;
	}
}
