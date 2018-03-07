package linus.discord.lbot3.events;

public class EventUtils {
	private EventUtils() {}
	
	public static String getCommand(String all) {
		int index = all.indexOf(' ');
		return index < 0 ? all : all.substring(0, index).trim();
	}
	
	public static String getContent(String all) {
		int index = all.indexOf(' ');
		return index < 0 ? "" : all.substring(index + 1);
	}
	
	public static String[] getContentCmd(String content) {
		return content.split(" ");
	}
}
