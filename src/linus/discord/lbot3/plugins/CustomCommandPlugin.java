package linus.discord.lbot3.plugins;

import java.util.Optional;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;
import linus.discord.lbot3.resource.ResourceLoader;

public class CustomCommandPlugin extends Plugin{

	public CustomCommandPlugin() {
		super(
			"<custom command>",
			"prints the custom command content to the same channel"
		);
	}
	
	@Override
	public boolean onMessageReceived(MessageReceivedEvt event) {
		boolean[] res = new boolean[1];
		Optional.ofNullable(event.bot.getCustomCommands(event.guild.getId(), () -> ResourceLoader.loadCustomCommands(event.guild.getId())))
			.filter(e -> e.containsKey(event.command))
			.ifPresent(e -> {
				PluginUtils.print(e.get(event.command), event.channel);
				res[0] = true;
			});
		return res[0];
	}
	
}
