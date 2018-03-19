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
		String what = PluginUtils.normalize(evt.content);
		PluginUtils.print(what, evt.channel);
	}
	
}
