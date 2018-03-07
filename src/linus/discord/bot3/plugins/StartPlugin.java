package linus.discord.bot3.plugins;

import linus.discord.bot3.Plugin;
import linus.discord.bot3.events.MessageReceivedEvt;
import linus.discord.bot3.events.StartEvt;

public class StartPlugin extends Plugin{

	public StartPlugin() {
		super(
			"!!start",
			"Is executed when the bots starts. Shouldn't be executed via command"
		);
	}

	@Override
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {
		// TODO Auto-generated method stub
		super.onMessageWithNameReceived(evt);
	}
	
	@Override
	public boolean onStart(StartEvt event) {
		// TODO Auto-generated method stub
		return super.onStart(event);
	}
}
