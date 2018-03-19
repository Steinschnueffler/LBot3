package linus.discord.lbot3.plugins;

import java.io.InputStream;

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
}
