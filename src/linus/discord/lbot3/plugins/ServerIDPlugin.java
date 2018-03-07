package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class ServerIDPlugin extends Plugin{

	public ServerIDPlugin() {
		super(
			"!serverID",
			"Prints the id of this server"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		String str = evt.guild.getId();
		PluginUtils.print(str, evt.channel);
	} 
	
}
