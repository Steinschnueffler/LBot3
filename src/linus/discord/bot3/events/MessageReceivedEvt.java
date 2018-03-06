package linus.discord.bot3.events;

import linus.discord.bot3.Bot;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageReceivedEvt extends BotEvt{
	
	public final MessageReceivedEvent event;
	
	public final Message message;
	public final User user;
	public final Member member;
	public final MessageChannel channel;
	public final Guild guild;
	
	public final String raw;
	public final String stripped;
	public final String display;
	
	public final String command;
	public final String content;
	public final String[] contentCmd;
		
	public MessageReceivedEvt(MessageReceivedEvent evt, Bot b) {
		super(b);
		
		event = evt;
		
		message = event.getMessage();
		user = event.getAuthor();
		member = event.getMember();
		channel = event.getChannel();
		guild = event.getGuild();
		
		raw = message.getContentRaw();
		stripped = message.getContentStripped();
		display = message.getContentDisplay();
		
		command = EventUtils.getCommand(display);
		content = EventUtils.getContent(display);
		contentCmd = EventUtils.getContentCmd(content);
	}
	
}
