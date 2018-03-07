package linus.discord.lbot3;

import linus.discord.lbot3.events.MessageReceivedEvt;
import linus.discord.lbot3.events.ShutdownEvt;
import linus.discord.lbot3.events.StartEvt;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;

public abstract class Plugin{

	public final String NAME;
	public final String DESCRIPTION;
	
	public Plugin(String name, String description) {
		this.NAME = name;
		this.DESCRIPTION = description;
	}
	
	public Plugin() {
		this("unknown plugin name", "unknown plugin description");
	}
	
	public boolean onMessageReceived(MessageReceivedEvt event) {
		if(event.command.equalsIgnoreCase(NAME)) {
			onMessageWithNameReceived(event);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean onGuildMemberJoin(GuildMemberJoinEvent event) {
		return false;
	}
	
	public boolean onGuildMemberLeave(GuildMemberLeaveEvent event) {
		return false;
	}
	
	public boolean onShutdown(ShutdownEvt event) {
		return false;
	}
	
	public boolean onStart(StartEvt event) {
		return false;
	}
	
	protected void onMessageWithNameReceived(MessageReceivedEvt evt) {}
	
}
