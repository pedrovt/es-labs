package org.pedro;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * FirstTests - LogTests <br>
 *
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 14, 2020
 */
public class LogTests {

    private static Logger logger = LogManager.getLogger(LogTests.class);

    public static void main(String[] args) {
        logger.debug("New Debug log message");
        logger.info("New Info log message");
        logger.error("New Error log message");
        logger.warn("New warn log message");
        logger.trace("New trace log message");
    }
}
