package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class SpamPlugin extends Plugin{

	public SpamPlugin() {
		super(
			"!spam",
			"Spams the given content the given number of times"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		new Thread(() -> { 
			if(evt.contentCmd.length < 2) {
				PluginUtils.print("You must specify at least a number of iterations and a content!", evt.channel);
				return;
			}
			
			int howOften;
			try {
				howOften = Integer.parseInt(evt.contentCmd[0]);
			}catch(NumberFormatException e) {
				PluginUtils.print(evt.contentCmd[0] + " isnt a valid number", evt.channel);
				return;
			}
			
			String content = evt.content.substring(evt.contentCmd[0].length() + 1);
			
			for(int i = 0; i < howOften; i++)
				PluginUtils.print(content, evt.channel);
			
		}).start();
	}
}
