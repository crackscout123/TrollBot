package de.crackscout123.events;




import java.nio.charset.Charset;
import java.util.Random;

import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDeletedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelPasswordChangedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.PrivilegeKeyUsedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ServerEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import de.crackscout123.trollbot.Main;
import de.crackscout123.utils.Debug;
import de.crackscout123.utils.Utils;


public class TS3Events{
	
	public static int SUPPORTCHANNELID;
	public static int SERVERGROUPID;
	public static Client t;
	public static void load(){
	  
    Main.api.registerAllEvents();
    Main.api.addTS3Listeners(new TS3Listener[] { new TS3Listener(){
    
     public void onTextMessage(TextMessageEvent e) {
    	 Client sender = Main.api.getClientByUId(e.getInvokerUniqueId());
		  String input = e.getMessage();
		  String [] output = input.split(" ");
		  System.out.println(output);
		  //!trollmove
    	  if(e.getMessage().startsWith("!trollmove") && (e.getTargetMode() != TextMessageTargetMode.SERVER)) {
	    	 if(!Utils.isOp(sender)) {
	    		 System.out.println("Missing auth!");
	    		 return;
	    	 }
    		  System.out.println(e.getInvokerName()+": !trollmove "+output[1] +" " + output[2]);
    		  System.out.println("UID:" + sender.getId());
    		  int id = Integer.parseInt(output[1]);
	  		  for(Client c1 : Main.api.getClients()) {
	  			  if (c1.getId() == id) {
	  				   t = c1;
	  	  		  }

  			  } 	
	  		  if(isInteger(output[2])) {
	  			int foo;
	  			try {
	  			   foo = Integer.parseInt(output[2]);
	  			}
	  			catch (NumberFormatException ex)
	  			{
	  			   foo = 1;
	  			}
		  		if(foo > 100) {
		  			  foo = 0;
		  			  Main.api.pokeClient(sender.getId(), "Value are limited by 100");
		  			  System.out.println("Value are limited by 100");
		  		}
	  			System.out.println("Trollmove wird ausgeführt: " + output[1]);
	  			int home = t.getChannelId();
	  			for(int i1 =0; i1 < foo; i1++) {
	  				if(!Main.clients.contains(t.getId())) {
	  					Debug.err("target disconected.");
	  					return;
	  				}
					  Main.api.moveClient(t.getId(), 51);
					  Main.api.moveClient(t.getId(), 52);
	    		  }
	  	  		  Main.api.moveClient(t.getId(), home);
	  	  		  System.out.println("Trollmove wurde ausgeführt! - Moved client: " +t.getNickname());
	  		  }
	  		//!trollpoke
    	  }else if(e.getMessage().startsWith("!trollpoke") && (e.getTargetMode() != TextMessageTargetMode.SERVER)) {
 			  if(!Utils.isOp(sender)) {
				  System.out.println("Missing auth!");
				  return;
			  }
    		  System.out.println(e.getInvokerName()+": !trollpoke "+output[1] +" " + output[2] + " " + output[3]);
    		  int id = Integer.parseInt(output[1]);
	  		  for(Client c1 : Main.api.getClients()) {
	  			  if (c1.getId() == id) {
	  				   t = c1;
	  	  		  }

  			  } 				  
	  		  if(isInteger(output[2])) {
	  			int foo;
	  			try {
	  			   foo = Integer.parseInt(output[2]);
	  			}
	  			catch (NumberFormatException ex)
	  			{
	  			   foo = 1;
	  			}
		  		if(foo > 100) {
		  			  foo = 0;
		  			  Main.api.pokeClient(sender.getId(), "Value are limited by 100");
		  			  System.out.println("Value are limited by 100");
		  		}
	  			System.out.println("Trollpoke wird ausgeführt: " + output[1]);
	  	  		  for(int i1 =0; i1 < foo; i1++) {
		  				if(!Main.clients.contains(t.getId())) {
		  					Debug.err("target disconected.");
		  					return;
		  				}
	  	  			  if(output[3] != "") {
	  	  				  Main.api.pokeClient(t.getId(), output[3].replace("_", " "));
	  	  			  } else {
	  	  			    byte[] array = new byte[7]; // length is bounded by 7
		  	  		    new Random().nextBytes(array);
		  	  		    String generatedString = new String(array, Charset.forName("UTF-8"));
	  	  				Main.api.pokeClient(t.getId(), generatedString);
	  	  			  }
	    		  }
	  	  		  System.out.println("Trollpoke wurde ausgeführt! - Poked client: " +t.getNickname());
	  		  }
	  		  
	  		  //!list
    	  }else if(e.getMessage().startsWith("!list") && (e.getTargetMode() != TextMessageTargetMode.SERVER)) {
    		  if(!Utils.isOp(sender)) {
				  System.out.println("Missing auth!");
				  return;
			  }
    		  System.out.println(e.getInvokerName()+": !list ");
    		  Main.api.sendPrivateMessage(sender.getId(), "============| CLIENT <-> ID |============");
    		  for(Client c1 : Main.api.getClients()) {
    			  if(c1.getNickname() != "serveradmin") {
    				  Main.api.sendPrivateMessage(sender.getId(), c1.getNickname() +" <-> " + c1.getId());
    			  }
  			  } 		  		  
    		  Main.api.sendPrivateMessage(sender.getId(), "====================================");
    		  
    		  
    		  
    		  //!kick
    	  }else if(e.getMessage().startsWith("!kick") && (e.getTargetMode() != TextMessageTargetMode.SERVER)) {
    		  if(!Utils.isOp(sender)) {
				  System.out.println("Missing auth!");
				  return;
			  }
    		  System.out.println(e.getInvokerName()+": !kick "+output[1] +" " + output[2]);
    		  int id = Integer.parseInt(output[1]);
	  		  for(Client c1 : Main.api.getClients()) {
	  			  if (c1.getId() == id) {
	  				   t = c1;
	  	  		  }
  			  } 
	  		  Main.api.kickClientFromServer(output[2].replace("_", " "), id);

  	  		  System.out.println("Kick wurde ausgeführt! - Kicked client: " +t.getNickname());
	  		  
    	  }else if(e.getMessage().startsWith("!help") && (e.getTargetMode() != TextMessageTargetMode.SERVER)) {
    		  if(!Utils.isOp(sender)) {
				  System.out.println("Missing auth!");
				  return;
			  }
    		  System.out.println(e.getInvokerName()+": !help");
    		  Main.api.sendPrivateMessage(sender.getId(), "============| BOT HELP |============");

    		  Main.api.sendPrivateMessage(sender.getId(), "| !list 										List player id's & names. ");
    		  Main.api.sendPrivateMessage(sender.getId(), "| !trollmove <id> <amount>				Move a user constantly. 	max. 100 ");
    		  Main.api.sendPrivateMessage(sender.getId(), "| !trollpoke <id> <amount> <msg>		Poke a user with a custom message.		max. 100 ");
    		  Main.api.sendPrivateMessage(sender.getId(), "| !kick <id> <reason>					Kick a player anonymously");
    		  
    		  Main.api.sendPrivateMessage(sender.getId(), "============| BOT HELP |============");
    	  }
    	  
      }
     
     

     public void onClientLeave(ClientLeaveEvent e) {
    	 removeClient(e.getClientId());
     }
     
     void removeClient(Integer id) {
	   	 Main.clients.remove(id);
	   	 Debug.info("removed: " + id);
     }

     public void onClientJoin(ClientJoinEvent e) {
	   	 Main.clients.add(e.getClientId());
	   	 Debug.info("added: " + e.getClientId()); 
     }    
     
     public boolean isInteger(String s) {
    	    try { 
    	        Integer.parseInt(s); 
    	    } catch(NumberFormatException e) { 
    	        return false; 
    	    } catch(NullPointerException e) {
    	        return false;
    	    }
    	    // only got here if we didn't return false
    	    return true;
    	}
      
      public void onServerEdit(ServerEditedEvent event) {}
      
      public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent event) {}
      /*
      public void onClientMoved(ClientMovedEvent e) {
    	  //Projektleitungs Warteraum
    	  
    	  if(e.getTargetChannelId() == utils.projektleitung_channel) {
    		  int i1 = 0;
    		  for(Client c : Main.api.getClients()) {
    			  for(int i =0; i < c.getServerGroups().length; i++) {
    				  if(c.getServerGroups()[i] == utils.projektleitung_rank) {
    					  i1++;
    					  Main.api.sendPrivateMessage(c.getId(), "[color=blue]"+ utils.getNickname(e.getClientId()) +" wartet im Channel 'Warteraum für Projektleitung'");
     					  System.out.println(utils.getNickname(e.getClientId()) +" wartet im Channel 'Warteraum für Projektleitung'");
    				  }
    			  }
    			  
    		  }
    		  
    		  Main.api.sendPrivateMessage(e.getClientId(), "[color=red]Es wurden [B]" + i1 + "[/B] Teammitglieder benachrichtigt!");
    		  
    		 // Support Warteraum
    	  }else if(e.getTargetChannelId() == utils.support_channel) {
    		  int i1 = 0;
    		  for(Client c : Main.api.getClients()) {
    			  for(int i =0; i < c.getServerGroups().length; i++) {
    				  if((c.getServerGroups()[i] == utils.support_rank) || (c.getServerGroups()[i] == utils.projektleitung_rank)) {
    					  i1++;
    					  Main.api.sendPrivateMessage(c.getId(), "[color=blue]"+ utils.getNickname(e.getClientId()) +" wartet im Channel 'Warteraum auf Support'");
    					  System.out.println(utils.getNickname(e.getClientId()) +" wartet im Channel 'Warteraum auf Support'");
    				  }
    			  }
    			  
    		  }
    		  Main.api.sendPrivateMessage(e.getClientId(), "[color=red]Es wurden [B]" + i1 + "[/B] Teammitglieder benachrichtigt!");
    		  
     		 // Kurs Warteraum
     	  }else if(e.getTargetChannelId() == utils.kurs_channel) {
     		  int i1 = 0;
     		  for(Client c : Main.api.getClients()) {
     			  for(int i =0; i < c.getServerGroups().length; i++) {
     				  if((c.getServerGroups()[i] == utils.kurs_rank) || (c.getServerGroups()[i] == utils.projektleitung_rank)) {
     					  i1++;
     					  Main.api.sendPrivateMessage(c.getId(), "[color=blue]"+ utils.getNickname(e.getClientId()) +" wartet im Channel 'Warteraum Kurs Anmeldung'");
     					  System.out.println(utils.getNickname(e.getClientId()) +" wartet im Channel 'Warteraum Kurs Anmeldung'");
     				  }
     			  }
     			  
     		  }
     		  Main.api.sendPrivateMessage(e.getClientId(), "[color=red]Es wurden [B]" + i1 + "[/B] Teammitglieder benachrichtigt!");

     		 // Werkstatt Warteraum
     	  }else if(e.getTargetChannelId() == utils.mitglied_channel) {
     		  int i1 = 0;
     		  for(Client c : Main.api.getClients()) {
     			  for(int i =0; i < c.getServerGroups().length; i++) {
     				  if((c.getServerGroups()[i] == utils.support_rank) || (c.getServerGroups()[i] == utils.projektleitung_rank)) {
     					  i1++;
     					  Main.api.sendPrivateMessage(c.getId(), "[color=blue]"+ utils.getNickname(e.getClientId()) +" wartet im Channel 'Werkstatt Mitglied werden'");
     					  System.out.println(utils.getNickname(e.getClientId()) +" wartet im Channel 'Warteraum für 'Werkstatt Mitglied werden'");
     				  }
     			  }
     			  
     		  }
     		  Main.api.sendPrivateMessage(e.getClientId(), "[color=red]Es wurden [B]" + i1 + "[/B] Teammitglieder benachrichtigt!");
     		  
     		  //
     	  }
    	  
      }
      */
      
      public void onChannelPasswordChanged(ChannelPasswordChangedEvent event) {}
      
      public void onChannelMoved(ChannelMovedEvent event) {}
      
      public void onChannelEdit(ChannelEditedEvent event) {}
      
      public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent event) {}
      
      public void onChannelDeleted(ChannelDeletedEvent event) {}
      
      public void onChannelCreate(ChannelCreateEvent event) {}

	
	public void onClientMoved(ClientMovedEvent e) {
		if(e.getTargetChannelId() == 7) {
  		  for(Client c : Main.api.getClients()) {
  			  for(int i =0; i < c.getServerGroups().length; i++) {
  				  if(c.getServerGroups()[i] == 9 || c.getServerGroups()[i] == 18) {
  					  Main.api.pokeClient(c.getId(), "[color=red]"+ Utils.getNickname(e.getClientId()) +"[/color] wartet im Channel 'Move'.");
  					  //Main.api.sendPrivateMessage(c.getId(), "[color=red]"+ utils.getNickname(e.getClientId()) +"[/color] wartet im Channel 'Move'.");
   					  System.out.println(Utils.getNickname(e.getClientId()) +" wartet im Channel 'Move'.");
  				  }
  			  }
  			  
  		  }
		}
	}
      
    } });
  }


}