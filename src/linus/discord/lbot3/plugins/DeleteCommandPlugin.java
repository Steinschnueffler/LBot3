package linus.discord.lbot3.plugins;

import java.util.Optional;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class DeleteCommandPlugin extends Plugin{
	
	public DeleteCommandPlugin() {
		super(
			"!deleteCommand",
			"Deletes a custom command out of heap and file storage"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		String name = evt.content;
		String id = evt.guild.getId();
		Optional.ofNullable(evt.bot.getCustomCommands(id))
			.filter(e -> e.containsKey(name))
			.ifPresentOrElse(e -> {
				e.remove(name);
				PluginUtils.print("Command " + name + " deleted.", evt.channel);
			}, () -> {
				PluginUtils.print("Command not found: " + name, evt.channel);
			});
	}

}
