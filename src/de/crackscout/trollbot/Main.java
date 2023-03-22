package de.crackscout.trollbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import de.crackscout.Collectors.AfkCollector;
import de.crackscout.Collectors.OnlineCollector;
import de.crackscout.Logging.Logging;
import de.crackscout.events.TS3Events;
import de.crackscout.utils.Debug;



public class Main {
	
	  public static TS3Config config = new TS3Config();
	  public static TS3Query query = new TS3Query(config);
	  public static TS3Api api = new TS3Api(query);
	  public static boolean bypass = false;
	  public static ArrayList<String> ops = new ArrayList<>();
	  
	  public static boolean MySQL = false;
	  public static HashMap<String, Boolean> whitelist = new HashMap<>();
	  public static ArrayList<Integer> clients = new ArrayList<>();

	  private static Thread collectorProzess, onlineCollector;

	  
	  
	  public static void main(String[] args) {
		  
		  	// setup my logger
		  	Logging logg = new Logging();
		  	logg.setup();
		  
			config.setDebugLevel(Level.ALL);
			
			config.setHost("crackscout.de");
			
		    query.connect();
		    
		    api.login("serveradmin", "*********");
		    api.selectVirtualServerById(1);
		    api.setNickname("Türsteher <dev>");
		   
		    api.registerEvent(TS3EventType.SERVER);
		    
		    
		    Debug.info("Bot starting...");
		    
		    
		    TS3Events.load();
		    //AntiAFK.load();
		    
		    collectorProzess = new Thread(new AfkCollector(api));
		    collectorProzess.start();
		    
		    onlineCollector = new Thread(new OnlineCollector(api));
		    onlineCollector.start();
		    
		    Debug.info("Bot started.");
		    
		    
		    // add all online Clients to this.clients
		    for(Client client : Main.api.getClients()) {
		    	clients.add(client.getId());
		    	Debug.info("added: " + client.getNickname());
		    } 

		    // Fetch start arguments
		    // java -jar teamspeakbot.jar arg0 arg1 arg2
		    for(int i = 0; i < args.length; i++) {
		        if(args[i].contains("-help")) {
		        	System.out.println("=====HELP ARGUMENT TRIGGERD====");
		        }
		    }
	  }

}


/**
 * @author Joel Rzepka - crackscout123.de
 *
 * @date 24.05.2022 - 18:34:27
 *
 */