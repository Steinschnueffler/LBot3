package linus.discord.lbot3.plugins;

import linus.discord.lbot3.Plugin;
import linus.discord.lbot3.events.MessageReceivedEvt;

public class MatchPlugin extends Plugin{

	public MatchPlugin() {
		super(
			"!match",
			"Matches to arguments"
		);
	}
		
	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		if(evt.contentCmd.length != 2) {
			PluginUtils.print("You must specify exact two Arguments", evt.channel);
			return;
		}
		String first = evt.contentCmd[0];
		String second = evt.contentCmd[1];
		int procent = (first.hashCode() ^ second.hashCode()) % 101;
		
		String str = new StringBuilder()
			.append(">").append(first).append(System.lineSeparator())
			.append(">").append(second).append(System.lineSeparator())
			.append(System.lineSeparator())
			.append(getProgress(procent)).append(" ").append(procent).append("%")
			.toString();
		PluginUtils.print(str, evt.channel);
	}
	
	private static final String getProgress(int procent) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < procent; i++)
			sb.append('|');
		return sb.toString();
	}

}
