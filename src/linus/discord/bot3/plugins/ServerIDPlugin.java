package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;

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
