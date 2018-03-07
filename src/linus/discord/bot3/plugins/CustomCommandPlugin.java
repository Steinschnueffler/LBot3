package linus.discord.bot3.plugins;

import java.util.Optional;

import linus.discord.bot3.Plugin;
import linus.discord.bot3.events.MessageReceivedEvt;

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
		Optional.ofNullable(event.bot.getCustomCommands(event.guild.getId()))
			.filter(e -> e.containsKey(event.command))
			.ifPresent(e -> {
				PluginUtils.print(e.get(event.command), event.channel);
				res[0] = true;
			});
		return res[0];
	}
	
}
