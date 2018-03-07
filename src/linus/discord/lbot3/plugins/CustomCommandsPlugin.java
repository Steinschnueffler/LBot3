package linus.discord.lbot3.plugins;

import java.util.Optional;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class CustomCommandsPlugin extends Plugin{

	public CustomCommandsPlugin() {
		super(
			"!customCommands",
			"Prints all setted custom commands to the same channel"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		Optional.ofNullable(evt.bot.getCustomCommands(evt.guild.getId()))
			.filter(e -> !e.isEmpty())
			.ifPresentOrElse(e -> {
				StringBuilder sb = new StringBuilder();
				e.entrySet().forEach(f -> sb
						.append(f.getKey())
						.append(": ")
						.append(f.getValue())
						.append(System.lineSeparator())
					);
				PluginUtils.print(sb.toString(), evt.channel);
			}, () -> {	
				PluginUtils.print("No custom commands yet!", evt.channel);
			});
	}
	
}
