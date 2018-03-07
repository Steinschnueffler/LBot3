package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;
import linus.discord.lbot3.events.StartEvt;

public class StartPlugin extends Plugin{

	public StartPlugin() {
		super(
			"!!start",
			"Is executed when the bots starts. Shouldn't be executed via command"
		);
	}

	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		PluginUtils.loadResources(evt.bot);
		PluginUtils.print("restarted system, latest resource might be deleted.", evt.channel);
	}
	
	@Override
	public boolean onStart(StartEvt event) {
		PluginUtils.loadResources(event.bot);
		return false;
	}
}
