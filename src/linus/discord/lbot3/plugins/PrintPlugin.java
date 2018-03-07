package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class PrintPlugin extends Plugin{
	
	public PrintPlugin() {
		super(
			"!print",
			"prints the given content to the same channel"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		PluginUtils.print(evt.content, evt.channel);
	}
	
}
