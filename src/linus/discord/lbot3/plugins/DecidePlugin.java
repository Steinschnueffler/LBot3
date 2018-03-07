package linus.discord.lbot3.plugins;

import java.util.Arrays;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class DecidePlugin extends Plugin{

	public DecidePlugin() {
		super(
			"!decide",
			"Decides between the given arguments"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		int hashCode = Arrays.hashCode(evt.contentCmd);
		if(hashCode < 0)
			hashCode *= -1;
		
		int min = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0; i < evt.contentCmd.length; i++) {
			int hash = evt.contentCmd[i].hashCode() ^ hashCode;
			if(hash < min) {
				min = hash;
				index = i;
			}
		}
		
		PluginUtils.print(evt.contentCmd[index], evt.channel);
	}

}
