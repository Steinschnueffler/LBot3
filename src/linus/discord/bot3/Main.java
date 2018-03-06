package linus.discord.bot3;

import java.io.IOException;
import java.io.InputStream;

import linus.discord.bot3.plugins.*;

public class Main {

	public static void main(String[] args){
		Bot bot = new Bot(loadAuth());
		bot.setPlugins(
				new HelpPlugin(),
				new PrintPlugin(),
				new SpeakPlugin(),
				new SpecificCommandPlugin(),
				new SpecificCommandsPlugin(),
				new CreateCommandPlugin(),
				new CustomCommandPlugin(),
				new CustomCommandsPlugin(),
				new OnlinePlugin(),
				new ServerIDPlugin(),
				new ChannelIDPlugin(),
				new UserIDPlugin(),
				new ClearPlugin(),
				new MatchPlugin(),
				new OrPlugin(),
				new SlapPlugin(),
				new SavePlugin(),
				new ShutdownPlugin()
			);
		bot.start();
	}
	
	private static String loadAuth() {
		InputStream in = Main.class.getResourceAsStream("auth.txt");
		try {
			return new String(in.readAllBytes());
		} catch (IOException e) {
			throw new SecurityException("Couldn't load auth", e);
		}
	}

}
