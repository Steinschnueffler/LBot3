package linus.discord.lbot3.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public class ResourceLoader {
	private ResourceLoader() {}
	
	private static final Path ROOT_FOLDER = Paths.get("./res/");
	
	private static final Path SERVER_FOLDER = ROOT_FOLDER.resolve("servers/");
	private static final Path USER_FOLDER = ROOT_FOLDER.resolve("users/");
	private static final Path SPECIFIC_FOLDER = ROOT_FOLDER.resolve("specific/");
	
	private static final Path SLAP_IMAGE_FOLDER = SPECIFIC_FOLDER.resolve("slapImages/");
	private static final ArrayList<Path> SLAPS = new ArrayList<>();
	
	private static final String DEFAULT_SLAP = "defaultSlap.png";
	private static final SecureRandom rand = new SecureRandom();
	
	private static final String CUSTOM_COMMAND_FILE = "customCommands.txt";
	private static final String CUSTOM_COMMAND_SEPERATOR = " = ";

	private static final Path SPECIFIC_COMMAND_FILE = SPECIFIC_FOLDER.resolve("SpecificCommands.txt");
	private static final String SPECIFIC_COMMAND_DEFAULT = "defaultSpecificCommands.txt";
	
	static {
		try {
			Files.createDirectories(SERVER_FOLDER);
			Files.createDirectories(USER_FOLDER);
			Files.createDirectories(SPECIFIC_FOLDER);
			
			Files.createDirectories(SLAP_IMAGE_FOLDER);
			for(Path p : Files.newDirectoryStream(SLAP_IMAGE_FOLDER))
				SLAPS.add(p);	
			
			if(Files.notExists(SPECIFIC_COMMAND_FILE))
				Files.createFile(SPECIFIC_COMMAND_FILE);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Path getServerFolder(String resource) throws IOException {
		Path p = SERVER_FOLDER.resolve(resource);
		return Files.createDirectories(p);
	}
	
	private static Path getCustomCommandFile(String resource) throws IOException {
		Path p = getServerFolder(resource).resolve(CUSTOM_COMMAND_FILE);
		if(Files.notExists(p))
			Files.createFile(p);
		return p;
	}
	
	private static Map<String, String> loadCommands(Path p) throws IOException{
		return loadCommands(Files.newBufferedReader(p));
	}
	
	private static Map<String, String> loadCommands(InputStream in) throws IOException{
		return loadCommands(new BufferedReader(new InputStreamReader(in)));
	}
	
	private static Map<String, String> loadCommands(BufferedReader reader) throws IOException {
		Map<String, String> map = new HashMap<>();
		String line;
		while((line = reader.readLine()) != null) {
			int split = line.indexOf(CUSTOM_COMMAND_SEPERATOR);
			if(split < 0)
				continue;
			String key = line.substring(0, split);
			String value = line.substring(split + CUSTOM_COMMAND_SEPERATOR.length());
			map.put(key, value);
		}
		reader.close();
		return map;
	}
	
	private static void saveCommands(Path p, Map<String, String> map) throws IOException{
		BufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		map.forEach((key, value) -> {
			try {
				writer.write(key);
				writer.write(CUSTOM_COMMAND_SEPERATOR);
				writer.write(value);
				writer.write(System.lineSeparator());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		writer.flush();
		writer.close();
	}
	
	public static Pair<String, InputStream> getRandomSlap(){
		Path p = SLAPS.get(rand.nextInt(SLAPS.size()));
		try {
			return new Pair<String, InputStream>(p.getFileName().toString(), Files.newInputStream(p));
		} catch (IOException e) {
			e.printStackTrace();
			InputStream stream = ResourceLoader.class.getResourceAsStream(DEFAULT_SLAP);
			return new Pair<String, InputStream>(DEFAULT_SLAP, stream);
		}
	}
		
	public static Map<String, String> loadCustomCommands(String resource){
		try {
			return loadCommands(getCustomCommandFile(resource));
		} catch (IOException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}
	
	public static void saveCustomCommands(Map<String, String> map, String resource) {
		new Thread(() -> {
			try {
				saveCommands(getCustomCommandFile(resource), map);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public static void saveCustomCommands(Map<String, Map<String, String>> map) {
		new Thread(() -> {
			map.forEach((key, value) -> {
				try {
					saveCommands(getCustomCommandFile(key), value);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}).start();
	}
		
	public static Map<String, String> loadSpecificCommands(){
		try {
			InputStream defStream = ResourceLoader.class.getResourceAsStream(SPECIFIC_COMMAND_DEFAULT);
			Map<String, String> defMap = loadCommands(defStream);
			defMap.putAll(loadCommands(SPECIFIC_COMMAND_FILE));
			return defMap;
		} catch (IOException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}

	public static void saveSpecificCommands(Map<String, String> map) {
		try {
			saveCommands(SPECIFIC_COMMAND_FILE, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
