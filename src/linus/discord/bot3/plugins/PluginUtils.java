package linus.discord.bot3.plugins;

import java.io.InputStream;

import linus.discord.bot3.resource.ResourceLoader;
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
	
	public static void saveResources() {
		ResourceLoader.saveCustomCommands(Commands.getAllCustoms());
		ResourceLoader.saveSpecificCommands(Commands.getSpecific());
	}
}
