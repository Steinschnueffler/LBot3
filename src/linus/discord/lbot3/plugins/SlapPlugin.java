package linus.discord.lbot3.plugins;

import java.io.InputStream;

import javafx.util.Pair;
import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;
import linus.discord.lbot3.resource.ResourceLoader;

public class SlapPlugin extends Plugin{

	public SlapPlugin() {
		super(
			"!slap",
			"Slaps the given content with a random gif"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		Pair<String, InputStream> pair = ResourceLoader.getRandomSlap();
		String msg = 
				evt.member.getEffectiveName() + 
				" slaps: " + 
				(evt.content.isEmpty() ? "you all" : evt.content);
		PluginUtils.sendFile(pair.getValue(), pair.getKey(), msg, evt.channel);
	}
	
}
