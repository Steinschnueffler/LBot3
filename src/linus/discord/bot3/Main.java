package linus.discord.bot3;

import linus.discord.bot3.plugins.*;

public class Main {

	public static void main(String[] args){
		Bot bot = new Bot("NDIwMTc4Nzc1Nzc5NTczNzYx.DX65gQ.LhL7h57M9wCbM5-KFaBhvkdi9Lw");
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
				new SlapPlugin(),
				new SavePlugin(),
				new ShutdownPlugin()
			);
		bot.start();
	}

}
