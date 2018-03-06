package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;

public class ClearPlugin extends Plugin{

	public ClearPlugin() {
		super(
			"!clear",
			"Clears the channel"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		evt.channel.getHistory().getRetrievedHistory().forEach(e -> e.delete());
		PluginUtils.print("Cleared Channel!", evt.channel);
	}

}
