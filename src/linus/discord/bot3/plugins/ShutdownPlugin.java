package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;

public class ShutdownPlugin extends Plugin{

	public ShutdownPlugin() {
		super(
			"!shutdown",
			"Exits this application"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		PluginUtils.print("Shutting down... Bye!", evt.channel);
		evt.bot.stop();
	}

}
