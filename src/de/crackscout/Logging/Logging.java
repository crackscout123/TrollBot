package de.crackscout.Logging;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import de.crackscout.utils.TimeHandler;

public class Logging {

	public void setup() {

	  	Logger logger = Logger.getLogger("MyLog");  
	    FileHandler fh;  
	    
	    try {  

	        // This block configure the logger with handler and formatter  
	    	String filepath = Utils.getUniqueFileName("tmp/logs/TrollBot/", TimeHandler.sdf3.format(new Date())+".log").toString();
	        fh = new FileHandler(filepath);  
	        fh.setFormatter(new MyFormatter());  
	        logger.addHandler(fh);

	        // the following statement is used to log any messages  
	        logger.info("Begin of the log (" + TimeHandler.sdf1.format(new Date()) + ")");  
	        logger.setUseParentHandlers(false);

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }   
	}
}


/** 
 *
 * @author Joel Rzepka - crackscout.de
 *
 * @date 22.03.2023 - 23:53:49
 *
 */