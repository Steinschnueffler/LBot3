package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;

public class HelpPlugin extends Plugin{

	public HelpPlugin() {
		super(
			"!help",
			"prints all commands with their description"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		StringBuilder sb = new StringBuilder();
		for(Plugin p : evt.bot.getPlugins())
			sb
				.append(p.NAME)
				.append(": ")
				.append(p.DESCRIPTION)
				.append(System.lineSeparator());
		PluginUtils.print(sb.toString(), evt.channel);
	}
	
}
