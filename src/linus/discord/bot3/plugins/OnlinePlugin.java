package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;
import net.dv8tion.jda.core.OnlineStatus;

public class OnlinePlugin extends Plugin{

	public OnlinePlugin() {
		super(
			"!online",
			"List all online members of the channel"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		StringBuilder sb = new StringBuilder();
		evt.guild.getMembers().stream()
				.filter(e -> e.getOnlineStatus() != OnlineStatus.OFFLINE)
				.forEach(e -> sb
						.append(e.getUser().getName())
						.append(" (")
						.append(e.getEffectiveName())
						.append("),")
						.append(System.lineSeparator())
					);
		String str = sb.toString();
		PluginUtils.print(str.isEmpty() ? "No members online!" : str, evt.channel);
	}
	
}
