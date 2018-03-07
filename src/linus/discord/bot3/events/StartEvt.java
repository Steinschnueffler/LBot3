package linus.discord.bot3.events;

import linus.discord.bot3.Bot;
import net.dv8tion.jda.core.events.ReadyEvent;

public class StartEvt extends BotEvt{

	public final ReadyEvent event;
	public final long response;
	
	public StartEvt(Bot b, ReadyEvent event) {
		super(b);
		this.event = event;
		this.response = event.getResponseNumber();
	}

}
