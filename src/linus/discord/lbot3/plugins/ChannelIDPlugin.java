package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class ChannelIDPlugin extends Plugin{

	 public ChannelIDPlugin() {
		super(
			"!channelID",
			"Prints the channelID of the channel"
		);
	 }
	 
	 @Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		String str = evt.channel.getId();
		PluginUtils.print(str, evt.channel);
	}
	
}
