package de.crackscout.Collectors;

import java.util.List;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import de.crackscout.trollbot.Main;

public class OnlineCollector implements Runnable {


    private final TS3Api api;
    private int sleep = 60*1000; //sleep between collections in seconds x 1000
	public static List<Client> activClients = Main.api.getClients();

    public OnlineCollector(TS3Api api) {
        this.api = api;
    }
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("Encountered an interrupted exception while sleeping, shutting down collection service... \n Dumping error:");
				e.printStackTrace();
				return;
			}
			
			List<Client> clients = api.getClients();
			System.out.println("Trying to move and evaluate clients...");
			for (Client client : clients) {
				activClients.add(client);
			}
		}
		
	}

}


/** 
 *
 * @author Joel Rzepka - crackscout.de
 *
 * @date 11.03.2023 - 14:42:22
 *
 */