package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.requests.restaction.pagination.MessagePaginationAction;

public class ClearPlugin extends Plugin{

	public ClearPlugin() {
		super(
			"!clear",
			"Clears the channel"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		new Thread(() -> {
			int counter = 0;
			if(!evt.content.isEmpty()) {
				try {
					int number = Integer.parseInt(evt.content);
					MessagePaginationAction action = evt.channel.getIterableHistory();
					for(Message m : action) {
						if(counter < number) {
							m.delete().queue();
							counter++;
						}else
							break;
					}
				}catch(NumberFormatException e) {
					PluginUtils.print(evt.content + " is not a valid number", evt.channel);
					return;
				}
			}else {
				MessagePaginationAction action = evt.channel.getIterableHistory();
				for(Message m : action) {
					m.delete().queue();
					counter++;
				}
			}
			PluginUtils.print("Cleared " + counter + " messages", evt.channel);
		}).start();
	}

}
