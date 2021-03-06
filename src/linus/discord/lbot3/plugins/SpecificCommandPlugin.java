package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class SpecificCommandPlugin extends Plugin{
	
	public SpecificCommandPlugin() {
		super(
			"<specific message>",
			"Prints a specific message to specific inputs"
		);
	}
	
	@Override
	public boolean onMessageReceived(MessageReceivedEvt event) {
		if(event.bot.getSpecificCommands().containsKey(event.display)) {
			PluginUtils.print(event.bot.getSpecificCommands().get(event.display), event.channel);
			return true;
		}else {
			return false;
		}
	}
	
}
