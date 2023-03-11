package de.crackscout.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import de.crackscout.trollbot.Main;
import de.crackscout.utils.Debug;

public class AntiAFK {
	
	//abandoned function moved into utils.AfkCollector.java !!!!!!
	
		// Initialize variables
		public static Client sender;
		public static Client target;
		


	public static void load() {

			
		Integer afk_channel = 65;
		Integer afkMoveTime = 5;

		Boolean poke = false;
		Boolean enabled = true;
		
		String afkAlertMsg = "Du wurdest verschoben weil du zulang abwesend warst.";
		
		List<Integer> afk_groups = new ArrayList<Integer>();

		// numbers = ignored ranks
		String[] numbers = {"0"};
		for (String string : numbers) {
			afk_groups.add(Integer.parseInt(string));
		}
		

		for(Client c : Main.api.getClients()) {
			System.out.println(c.getIdleTime());
		}
	
		
		if(enabled) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// Scheduler checking idle time
					for(Client c : Main.api.getClients()) {
						if(c.getIdleTime() > afkMoveTime*60*1000 && c.getChannelId() != afk_channel) {
							if((c.isInputMuted() || c.isOutputMuted())) {
								for(int i = 0; i < c.getServerGroups().length; i++) {
									if(!(c.getServerGroups()[i] == afk_groups.get(i))) {
									//if(!(sys.afk_groups.contains(c.getServerGroups()[i]))) {-
										Main.api.moveClient(c.getId(), afk_channel);
										Debug.info("MOVING " + c.getNickname() + "(" + c.getId() + ") INTO AFK CHANNEL");
										if(poke) {
											Main.api.pokeClient(c.getId(), afkAlertMsg);
										} else {
											Main.api.sendPrivateMessage(c.getId(), afkAlertMsg);									
										}
									}
								}
							}
						}
					}
				}
			}, 1000, 5*1000);
		}
	}
}


	/**
	 * @author Joel Rzepka - crackscout123.de
	 *
	 * @date 19.10.2021 - 21:47:59
	 *
	 */



/** 
 *
 * @author Joel Rzepka - crackscout.de
 *
 * @date 03.03.2023 - 02:00:48
 *
 */