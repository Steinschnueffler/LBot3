package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class UserIDPlugin extends Plugin{

	public UserIDPlugin() {
		super(
			"!userID",
			"Prints the member id of the given member. If no member is given, it'll be the ID of the author"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		String content = evt.content.trim();
		if(!content.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			evt.guild.getMembersByEffectiveName(content.substring(1), true).forEach(e -> sb
					.append(e.getUser().getId())
					.append(System.lineSeparator())
				);
			String str = sb.toString();
			PluginUtils.print(str.isEmpty() ? "User not found: " +content : str, evt.channel);
		}else {
			PluginUtils.print(evt.user.getId(), evt.channel);
		}
	}
	
}
