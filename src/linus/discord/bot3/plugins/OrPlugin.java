package linus.discord.bot3.plugins;

import java.util.Arrays;

import linus.discord.bot3.events.MessageReceivedEvt;

public class OrPlugin extends Plugin{

	public OrPlugin() {
		super(
			"!or",
			"Decides between the given arguments"
		);
	}
	
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		int hashCode = Arrays.hashCode(evt.contentCmd);
		if(hashCode < 0)
			hashCode *= -1;
		int length = evt.contentCmd.length;
		int index = hashCode % length;
		String what = evt.contentCmd[index];
		PluginUtils.print(what, evt.channel);
	}

}
