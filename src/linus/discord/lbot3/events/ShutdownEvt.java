package linus.discord.lbot3.events;

import java.time.OffsetDateTime;

import linus.discord.lbot3.Bot;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.requests.CloseCode;

public class ShutdownEvt extends BotEvt{

	public final ShutdownEvent event;
	public final CloseCode code;
	public final OffsetDateTime time;
	public final long response;
	
	public ShutdownEvt(Bot b, ShutdownEvent event) {
		super(b);
		this.event = event;
		this.code = event.getCloseCode();
		this.time = event.getShutdownTime();
		this.response = event.getResponseNumber();
	}

}
