package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class BinaryPlugin extends Plugin{

	public BinaryPlugin() {
		super(
			"!binary",
			"Prints the content as a binary format"
			);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		String binary = PluginUtils.toBinary(evt.content);
		PluginUtils.print(binary, evt.channel);
	}

}
