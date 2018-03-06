package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.requests.CloseCode;

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
	
	@Override
	public boolean onShutdown(ShutdownEvent event) {
		if(event.getCloseCode() != CloseCode.GRACEFUL_CLOSE)
			System.err.println("Application error: " + event.getCloseCode());
		return false;
	}

}
