package linus.discord.lbot3.plugins;

import java.io.InputStream;
import java.util.ArrayList;

import linus.discord.lbot3.Bot;
import linus.discord.lbot3.resource.ResourceLoader;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;

public class PluginUtils {
	private PluginUtils() {};
	
	public static void output(CharSequence what, boolean tts, MessageChannel channel) {
		channel.sendMessage(what).tts(tts).queue();
	}
	
	public static void print(String what, MessageChannel channel) {
		output(what, false, channel);
	}
	
	public static void speak(String what, MessageChannel channel) {
		output(what, true, channel);
	}
	
	public static void sendFile(InputStream stream, String fileName, String message, MessageChannel channel) {
		channel.sendFile(stream, fileName, new MessageBuilder(message).build()).queue();
	}
	
	public static void saveResources(Bot b) {
		ResourceLoader.saveCustomCommands(b.getAllCustomCommands());
	}
	
	public static void loadResources(Bot b) {
		b.getSpecificCommands().putAll(ResourceLoader.loadSpecificCommands());
	}
	
	public static void loadCustomCommands(Bot b, String id) {
		b.getAllCustomCommands().replace(id, ResourceLoader.loadCustomCommands(id));
	}
	
	public static String toBinary(String str) {
		StringBuilder sb = new StringBuilder();
		for(byte b : str.getBytes())
			sb.append(toBinary(b));
		return sb.toString();
	}
	
	
	public static String toBinary(byte b) {
		String str = Integer.toBinaryString(b);
		int missing = 8 - str.length();
		return buildString("0", missing).concat(str);
	}
	
	private static String buildString(String s, int count) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < count; i++)
			sb.append(s);
		return sb.toString();
	}
	
	public static String toAscii(String str) throws NumberFormatException{
		StringBuilder sb = new StringBuilder();
		for(String s : split(str, 8))
			sb.append(toBinary(s));
		return sb.toString();
	}
	
	private static String[] split(String str, int count) {
		ArrayList<String> list = new ArrayList<>();
		int pos = 0;
		while(pos + count < str.length()) {
			list.add(str.substring(pos, pos + count));
			pos += count;
		}
		return list.toArray(new String[list.size()]);
	}
	
	public static char toByte(String binary) throws NumberFormatException{
		byte b = (byte) Integer.parseInt(binary, 2);
		return (char) b;
	}
	
}
