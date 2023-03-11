package de.crackscout.utils;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import de.crackscout.trollbot.Main;



public class Utils {

	public static int projektleitung_channel = 26, support_channel = 27, mitglied_channel = 28, kurs_channel = 29;
	public static ArrayList<String> admin = new ArrayList<>();
	
	public static String getNickname(int clientId) {
		return Main.api.getClientInfo(clientId).getNickname().toString();
	}
	
	public static boolean isOp(Client client) {
		admin.add("authkey="); //cracky
		admin.add("authkey="); //Tizi
		
		if(admin.contains(client.getUniqueIdentifier())) {
			System.out.println("Access granted: " + client.getUniqueIdentifier());
			return true;
			
		} else {
			System.out.println("No auth: " + client.getUniqueIdentifier());
			return false;
		}
	}
		
	
	public static Client getClientByID(Integer id) {
		return Main.api.getClientByNameExact(getUserByID(id), false);
	}
	
	public static Client t;
	public static String getUserByID(Integer id) {
		  for(Client c1 : Main.api.getClients()) {
  			  if (c1.getId() == id) {
  				   t = c1;
  	  		  }
  			  return t.getNickname();
			  } 
		  return "";
	}
	
	public static Client clientByUID(String uuid) {
		return Main.api.getClientByUId(uuid);
	}
	
	public static String getIP() {
		String ip = "";
		try(final DatagramSocket socket = new DatagramSocket()){
			  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			  ip = socket.getLocalAddress().getHostAddress();
			} catch (UnknownHostException | SocketException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ip;
	}
}


/**
* @author JOE_
*
* @created 21.01.2019 (13:54:42)
*/
