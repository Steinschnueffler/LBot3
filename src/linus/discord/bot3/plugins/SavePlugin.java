package linus.discord.bot3.plugins;

import linus.discord.bot3.Plugin;
import linus.discord.bot3.events.MessageReceivedEvt;
import linus.discord.bot3.events.ShutdownEvt;

public class SavePlugin extends Plugin {

	public SavePlugin() {
		super(
			"!save",
			"Saves all resources."
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		PluginUtils.saveResources(evt.bot);
		PluginUtils.print("All resources are being written...", evt.channel);
	}
	
	@Override
	public boolean onShutdown(ShutdownEvt event) {
		PluginUtils.saveResources(event.bot);
		return false;
	}
}
