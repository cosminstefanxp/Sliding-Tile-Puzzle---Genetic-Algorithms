/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Main {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(Main.class);

	/**
	 * Configure logger.
	 */
	private static void configureLogger() {
		PatternLayout patternLayout = new PatternLayout("%-3r [%-5p] %c - %m%n");
		ConsoleAppender appender = new ConsoleAppender(patternLayout);
		Logger.getRootLogger().addAppender(appender);
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}

	public static void main(String args[]) {
		configureLogger();

		log.warn("Starting up...");

		Board board = new Board();
		board.initRandom();

		// Testing
		// board.doStep(Step.Up);
		// log.info(board);
		// board.doStep(Step.Left);
		// log.info(board);
		// board.doStep(Step.Down);
		// log.info(board);
		// board.doStep(Step.Right);
		// log.info(board);
		// board.doMoveElement(MoveElement.getAllPossibleMoves().get(13));
		// log.info(board);
	}
}