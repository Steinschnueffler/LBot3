package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;
import net.dv8tion.jda.core.events.ShutdownEvent;

public class SavePlugin extends Plugin {

	public SavePlugin() {
		super(
			"!save",
			"Saves all resources."
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		PluginUtils.saveResources();
		PluginUtils.print("All resources are being written...", evt.channel);
	}
	
	@Override
	public boolean onShutdown(ShutdownEvent event) {
		PluginUtils.saveResources();
		return false;
	}
}
