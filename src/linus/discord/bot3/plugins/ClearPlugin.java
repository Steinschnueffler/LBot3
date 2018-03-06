package linus.discord.bot3.plugins;

import linus.discord.bot3.events.MessageReceivedEvt;

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
			final int[] counter = new int[1];
			evt.channel.getIterableHistory().forEach(e -> {
				e.delete().queue();
				counter[0]++;
			});
			PluginUtils.print("Cleared " + counter[0] + " Messages!", evt.channel);
		}).start();
		PluginUtils.print("Clear process thread started...", evt.channel);
	}

}
