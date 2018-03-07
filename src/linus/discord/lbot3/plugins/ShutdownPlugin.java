package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;
import linus.discord.lbot3.events.ShutdownEvt;
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
	public boolean onShutdown(ShutdownEvt event) {
		if(event.code != CloseCode.GRACEFUL_CLOSE)
			System.err.println("Application error: " + event.code);
		return false;
	}

}
