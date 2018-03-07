package linus.discord.bot3.plugins;

import java.util.Optional;

import linus.discord.bot3.Plugin;
import linus.discord.bot3.events.MessageReceivedEvt;

public class SpecificCommandsPlugin extends Plugin{

	public SpecificCommandsPlugin() {
		super(
			"!specificCommands",
			"Prints all specific commands"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		Optional.ofNullable(evt.bot.getSpecificCommands())
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
			PluginUtils.print("No specific commands!", evt.channel);
		});
	}

}
