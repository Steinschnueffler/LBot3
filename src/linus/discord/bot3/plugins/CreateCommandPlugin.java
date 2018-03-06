package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;

public class CreateCommandPlugin extends Plugin{
	
	public CreateCommandPlugin() {
		super(
			"!createCommand",
			"Creates a new command with the given content"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		if(evt.contentCmd.length == 0) {
			PluginUtils.print("You have to specify the name and the content of the command", evt.channel);
			return;
		}
		String name = evt.contentCmd[0];
		if(name.isEmpty()) {
			PluginUtils.print("The command must have a content!", evt.channel);
			return;
		}
		
		String content = evt.content.substring(name.length());
		
		String id = evt.guild.getId();
		Commands.getCustom(id).put(name, content);
		
		PluginUtils.print("Command \"" + name + "\" succesfull created", evt.channel);
	}
	
}
