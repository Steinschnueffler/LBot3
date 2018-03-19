package linus.discord.lbot3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import javax.security.auth.login.LoginException;

import linus.discord.lbot3.events.*;
import linus.discord.lbot3.plugins.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Bot{

	private JDABuilder builder;
	private JDA jda;
	
	private List<Plugin> plugins = new ArrayList<>(Arrays.asList(
			new StartPlugin(),
			new HelpPlugin(),
			new PrintPlugin(),
			new SpeakPlugin(),
			new SpecificCommandPlugin(),
			new SpecificCommandsPlugin(),
			new CreateCommandPlugin(),
			new CustomCommandPlugin(),
			new DeleteCommandPlugin(),
			new CustomCommandsPlugin(),
			new OnlinePlugin(),
			new ServerIDPlugin(),
			new ChannelIDPlugin(),
			new UserIDPlugin(),
			new ClearPlugin(),
			new MatchPlugin(),
			new DecidePlugin(),
			new SlapPlugin(),
			new SpamPlugin(),
			new SavePlugin(),
			new ShutdownPlugin()
		));
	
	private final Map<String, Map<String, String>> customCommands = new HashMap<>();
	private final Map<String, String> specificCommands = new HashMap<>();
	
	public Bot(String token) {
		builder = new JDABuilder(AccountType.BOT)
			.addEventListener(new ListenerWrapper(this))
			.setToken(token);
	}
	
	public Map<String, String> getSpecificCommands(){
		return specificCommands;
	}
	
	public Map<String, String> getCustomCommands(String id, Supplier<Map<String, String>> def){
		if(!customCommands.containsKey(id))
			customCommands.put(id, def.get());
		
		return customCommands.get(id);
	}
	
	public Map<String, String> getCustomCommands(String id){
		return customCommands.get(id);
	}
	
	public Map<String, Map<String, String>> getAllCustomCommands(){
		return customCommands;
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
			if(event.getAuthor().isBot())
				return;
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
			ShutdownEvt evt = new ShutdownEvt(bot, event);
			for(Plugin p : plugins)
				if(p.onShutdown(evt))
					return;
		}
		
		@Override
		public void onReady(ReadyEvent event) {
			StartEvt evt = new StartEvt(bot, event);
			for(Plugin p : plugins)
				if(p.onStart(evt))
					return;
		}
		
		
	}
	
	private static String loadAuth() {
		InputStream in = Bot.class.getResourceAsStream("auth.txt");
		try {
			return new String(in.readAllBytes());
		} catch (IOException e) {
			throw new SecurityException("Couldn't load auth", e);
		}
	}
	
	public static void main(String[] args) {
		new Bot(loadAuth()).start();
	}

}
