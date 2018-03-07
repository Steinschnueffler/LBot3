package linus.discord.bot3.plugins;

import linus.discord.bot3.Plugin;
import linus.discord.bot3.events.MessageReceivedEvt;

public class SpeakPlugin extends Plugin{

	public SpeakPlugin() {
		super(
			"!speak",
			"prints and speaks the given content to the same channel"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		PluginUtils.speak(evt.content, evt.channel);
	}
	
}
