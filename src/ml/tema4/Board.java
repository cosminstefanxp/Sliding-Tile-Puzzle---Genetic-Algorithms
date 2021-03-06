/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import java.util.BitSet;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * The Class Board.
 */
public class Board {

	/** The Constant BOARD_SIZE. */
	public static final int BOARD_WIDTH = Config.BOARD_WIDTH;
	
	/** The Constant BOARD_HEIGHT. */
	public static final int BOARD_HEIGHT = Config.BOARD_HEIGHT;

	/** The Constant BOARD_MAX_ELEMENT. */
	public static final int BOARD_MAX_ELEMENT = BOARD_HEIGHT * BOARD_WIDTH;

	/** The Constant rand. */
	public static final Random rand = new Random();

	/** The board. */
	public int[][] board;

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(Board.class);

	/**
	 * Instantiates a new board.
	 */
	public Board() {
		super();
		board = new int[BOARD_HEIGHT][BOARD_WIDTH];
	}

	/**
	 * Inits a random board.
	 */
	public void initRandom() {
		log.info("Initializing random board...");

		BitSet used = new BitSet(BOARD_MAX_ELEMENT);

		for (int pos = 0; pos < BOARD_MAX_ELEMENT - 1; pos++) {
			int l = pos / BOARD_WIDTH;
			int c = pos % BOARD_WIDTH;

			// Get a free number
			int val = rand.nextInt(BOARD_MAX_ELEMENT - 1) + 1;
			while (used.get(val))
				val = rand.nextInt(BOARD_MAX_ELEMENT - 1) + 1;

			// Set the number to the board
			used.set(val);
			board[l][c] = val;

		}

		board[BOARD_HEIGHT - 1][BOARD_WIDTH - 1] = 0;
		log.debug("Generated board: \n" + this.toString());

		if (!isValid()) {
			log.info("Generated board not valid. Generating again...");
			initRandom();
		}
	}

	/**
	 * Checks if is valid.
	 * 
	 * @return true, if is valid
	 */
	public boolean isValid() {
		int inversions = 0;

		for (int i = 0; i < BOARD_HEIGHT; i++)
			for (int j = 0; j < BOARD_WIDTH; j++) {
				// Check end of line
				for (int j1 = j + 1; j1 < BOARD_WIDTH; j1++)
					if (board[i][j] > board[i][j1] && board[i][j1] != 0)
						inversions++;

				// Check next lines
				for (int i1 = i + 1; i1 < BOARD_HEIGHT; i1++)
					for (int j1 = 0; j1 < BOARD_WIDTH; j1++)
						if (board[i][j] > board[i1][j1] && board[i1][j1] != 0)
							inversions++;
			}

		log.info("Checking validity... Number of inversions: " + inversions);
		if (inversions % 2 == 1)
			return false;

		return true;
	}

	/* (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("\n");
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++)
				if (board[i][j] > 0)
					buf.append(String.format("%2d  ", board[i][j]));
				else
					buf.append("    ");
			buf.append("\n");
		}
		return buf.toString();
	}

	/**
	 * Do a step in one direction.
	 * 
	 * @param step the step
	 */
	public void doStep(Step step) {

		// Find the empty tile
		int xPos = -1, yPos = -1;
		boolean done = false;
		for (xPos = 0; xPos < BOARD_HEIGHT; xPos++) {
			for (yPos = 0; yPos < BOARD_WIDTH; yPos++)
				if (board[xPos][yPos] == 0) {
					done = true;
					break;
				}
			if (done)
				break;
		}
		assert (xPos > 0 && yPos > 0);

		// Perform the step
		switch (step) {
		case Up:
			if (xPos > 0) {
				board[xPos][yPos] = board[xPos - 1][yPos];
				board[xPos - 1][yPos] = 0;
			}
			break;
		case Down:
			if (xPos < BOARD_HEIGHT - 1) {
				board[xPos][yPos] = board[xPos + 1][yPos];
				board[xPos + 1][yPos] = 0;
			}
			break;
		case Left:
			if (yPos > 0) {
				board[xPos][yPos] = board[xPos][yPos - 1];
				board[xPos][yPos - 1] = 0;
			}
			break;
		case Right:
			if (yPos < BOARD_WIDTH - 1) {
				board[xPos][yPos] = board[xPos][yPos + 1];
				board[xPos][yPos + 1] = 0;
			}
			break;
		default:
			log.error("Illegal step: " + step);
			break;
		}
	}

	/**
	 * Do a move element, consisting of potentially more steps.
	 * 
	 * @param move the move
	 */
	public void doMoveElement(MoveElement move) {
		for (Step step : move.steps) {
			doStep(step);
		}
	}

	/**
	 * Gets a copy of the board.
	 *
	 * @return the copy
	 */
	public Board getCopy()
	{
		Board board=new Board();
		
		for(int i=0;i<BOARD_HEIGHT;i++)
			for(int j=0;j<BOARD_WIDTH;j++)
				board.board[i][j]=this.board[i][j];
		
		return board;
		
	}
}
