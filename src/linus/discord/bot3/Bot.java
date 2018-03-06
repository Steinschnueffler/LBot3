package linus.discord.bot3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.security.auth.login.LoginException;

import linus.discord.bot3.events.MessageReceivedEvt;
import linus.discord.bot3.plugins.Plugin;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Bot {
		
	private JDABuilder builder;
	private JDA jda;
	
	private List<Plugin> plugins = new ArrayList<>();
	
	public Bot(String token) {
		builder = new JDABuilder(AccountType.BOT)
			.addEventListener(new ListenerWrapper(this))
			.setToken(token);	
	}
	
	public List<Plugin> getPlugins(){
		return plugins;
	}
	
	public void setPlugins(List<Plugin> plugins) {
		this.plugins = Objects.requireNonNull(plugins);
	}
	
	public void setPlugins(Plugin... plugins) {
		setPlugins(Arrays.asList(plugins));
	}
	
	public void start() {
		if(jda != null)
			throw new IllegalStateException("Bot already started");
		try {
			jda = builder.buildAsync();
		} catch (LoginException e) {
			throw new RuntimeException("Couldn't log in", e);
		}
	}
	
	public void stop() {
		if(jda == null)
			throw new IllegalStateException("Bot must be started first");
		jda.shutdown();
	}
	
	private class ListenerWrapper extends ListenerAdapter{
		
		private final Bot bot;
		
		public ListenerWrapper(Bot bot) {
			this.bot = bot;
		}
				
		@Override
		public void onMessageReceived(MessageReceivedEvent event) {
			MessageReceivedEvt evt = new MessageReceivedEvt(event, bot);
			for(Plugin p : plugins)
				if(p.onMessageReceived(evt))
					return;
		}
		
		@Override
		public void onGuildMemberJoin(GuildMemberJoinEvent event) {
			for(Plugin p : plugins)
				if(p.onGuildMemberJoin(event))
					return;
		}
		
		@Override
		public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
			for(Plugin p : plugins)
				if(p.onGuildMemberLeave(event))
					return;
		}
		
		@Override
		public void onShutdown(ShutdownEvent event) {
			for(Plugin p : plugins)
				if(p.onShutdown(event))
					return;
		}
	}
	
}
