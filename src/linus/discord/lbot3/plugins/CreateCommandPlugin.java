package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;
import linus.discord.lbot3.resource.ResourceLoader;

public class CreateCommandPlugin extends Plugin{
	
	public CreateCommandPlugin() {
		super(
			"!createCommand",
			"Creates a new command with the given content"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		if(evt.contentCmd.length < 2) {
			PluginUtils.print("You have to specify the name and the content of the command", evt.channel);
			return;
		}
		String name = evt.contentCmd[0];
		
		String content = evt.content.substring(name.length());
		
		String id = evt.guild.getId();
		evt.bot.getCustomCommands(id, () -> ResourceLoader.loadCustomCommands(id))
			.put(name, content);
		
		PluginUtils.print("Command \"" + name + "\" succesfull created", evt.channel);
	}
	
}
