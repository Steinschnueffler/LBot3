package linus.discord.bot3.plugins;

import java.util.Map;

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
		Map<String, String> map = Commands.getCustom(event.guild.getId());
		if(map.containsKey(event.display)) {
			PluginUtils.print(map.get(event.display), event.channel);
			return true;
		}
		return false;
	}
	
}
