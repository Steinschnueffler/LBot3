package linus.discord.lbot3;

import java.io.IOException;
import java.io.InputStream;

import linus.discord.lbot3.plugins.ChannelIDPlugin;
import linus.discord.lbot3.plugins.ClearPlugin;
import linus.discord.lbot3.plugins.CreateCommandPlugin;
import linus.discord.lbot3.plugins.CustomCommandPlugin;
import linus.discord.lbot3.plugins.CustomCommandsPlugin;
import linus.discord.lbot3.plugins.DecidePlugin;
import linus.discord.lbot3.plugins.DeleteCommandPlugin;
import linus.discord.lbot3.plugins.HelpPlugin;
import linus.discord.lbot3.plugins.MatchPlugin;
import linus.discord.lbot3.plugins.OnlinePlugin;
import linus.discord.lbot3.plugins.PrintPlugin;
import linus.discord.lbot3.plugins.SavePlugin;
import linus.discord.lbot3.plugins.ServerIDPlugin;
import linus.discord.lbot3.plugins.ShutdownPlugin;
import linus.discord.lbot3.plugins.SlapPlugin;
import linus.discord.lbot3.plugins.SpeakPlugin;
import linus.discord.lbot3.plugins.SpecificCommandPlugin;
import linus.discord.lbot3.plugins.SpecificCommandsPlugin;
import linus.discord.lbot3.plugins.StartPlugin;
import linus.discord.lbot3.plugins.UserIDPlugin;

public class LBot3 extends Bot{

	public LBot3(String token) {
		super(token);
		setPlugins(
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
				new SavePlugin(),
				new ShutdownPlugin()
				
			);
	}
	
	private static String loadAuth() {
		InputStream in = LBot3.class.getResourceAsStream("auth.txt");
		try {
			return new String(in.readAllBytes());
		} catch (IOException e) {
			throw new SecurityException("Couldn't load auth", e);
		}
	}
	
	public static void main(String[] args) {
		new LBot3(loadAuth()).start();
	}

}
