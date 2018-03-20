package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class AsciiPlugin extends Plugin{

	public AsciiPlugin() {
		super(
			"!ascii",
			"Converst binary to ASCII"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		try {
			String str = PluginUtils.toAscii(evt.content);
			PluginUtils.print(str, evt.channel);
		}catch(NumberFormatException e) {
			PluginUtils.print("Invalid input: ", evt.channel);
		}
	}

}
