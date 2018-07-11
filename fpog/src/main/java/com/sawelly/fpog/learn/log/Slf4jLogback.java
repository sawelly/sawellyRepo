package com.sawelly.learn.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogback {
	 static Logger logger = LoggerFactory.getLogger(Slf4jLogback.class);
	public static void main(String[] args) {
		 for (int i = 0; i < 100000; i++) {
			             logger.trace("trace message " + i);
			              logger.debug("debug message " + i);
			             logger.info("info message " + i);
			             logger.warn("warn message " + i);
			             logger.error("error message " + i);            
			        }
			         System.out.println("Hello World! 2");

	}

}
