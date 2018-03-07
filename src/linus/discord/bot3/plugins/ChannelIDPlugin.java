package linus.discord.bot3.plugins;

import linus.discord.bot3.Plugin;
import linus.discord.bot3.events.MessageReceivedEvt;

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
