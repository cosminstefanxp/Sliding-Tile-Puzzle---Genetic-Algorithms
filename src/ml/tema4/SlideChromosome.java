/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ml.tema4.ga.Chromosome;

/**
 * The Class SlideChromosome.
 */
public class SlideChromosome extends Chromosome {
	
	/** The Constant MIN_CHROM_SIZE. */
	public static final int MIN_CHROM_SIZE = 1;

	/** The Constant MAX_CHROM_SIZE. */
	public static final int MAX_CHROM_SIZE = 25;

	/** The moves. */
	public ArrayList<MoveElement> moves;

	/** The board. */
	public Board board;
	
	/** The log. */
	private static Logger log = Logger.getLogger(SlideChromosome.class);

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.Chromosome#updateFitness() */
	@Override
	public void updateFitness() {

		if(log.isInfoEnabled())
			log.info("Updating fitness for chromosome: "+this);
		Board newBoard = board.getCopy();
		
		// Do the moves
		if(log.isDebugEnabled())
			log.debug("Initial board: "+newBoard);
		for (MoveElement move : moves)
			newBoard.doMoveElement(move);
		if(log.isDebugEnabled())
			log.debug("Final board: "+newBoard);

		/* Evaluate the board using 
		 * - first misplaced element 
		 * - blank element 
		 * - count of misplaced elements 
		 */
		int misplaced = 0, firstMisplacedPos = -1, firstMisplaced = -1, blankPos=0;
		
		for (int i = 0; i < Board.BOARD_SIZE; i++)
			for (int j = 0; j < Board.BOARD_SIZE; j++) {
				
				int val = i * Board.BOARD_SIZE + j + 1;
				
				//Find the blank position
				if (newBoard.board[i][j] == 0)
				{
					blankPos = val;
					// If the blank position is not on it's final spot, it may be on the place of
					// the first misplaced tile
					if (firstMisplaced == -1 && val != Board.BOARD_SIZE * Board.BOARD_SIZE)
						firstMisplaced = val;
					continue;
				}
				//If the value here is not corresponding
				if (newBoard.board[i][j] != val) {
					//if(newBoard.board[i][j] != 0)
						misplaced++;
					if (firstMisplaced == -1)
						firstMisplaced = val;
				}
				if (newBoard.board[i][j] == firstMisplaced)
					firstMisplacedPos = val;
			}
		log.debug("Computed values: (misplaced " + misplaced + "), (firstMisplaced " + firstMisplaced
				+ "), (firstMisplacedPos " + firstMisplacedPos + "), (blank " + blankPos + ")");

		// Compute the fitness
		// Use count of misplaced elements
		this.fitness = 36 * misplaced;
		
		// Use the Manhattan distance of the first misplaced element 
		if (firstMisplaced != -1) {
			int manhattanDiff = 0;
			manhattanDiff += Math.abs((firstMisplaced - 1) / Board.BOARD_SIZE - (firstMisplacedPos - 1)
					/ Board.BOARD_SIZE);
			manhattanDiff += Math.abs((firstMisplaced - 1) % Board.BOARD_SIZE - (firstMisplacedPos - 1)
					% Board.BOARD_SIZE);
			fitness += 18 * manhattanDiff;
			log.debug("First Misplaced Manhattan: "+manhattanDiff);
		}
		
		// Use the Manhattan distance of the blank element to the first misplaced element
		if(firstMisplaced!=-1)
		{
			int manhattanDiff = 0;
			manhattanDiff += Math.abs((firstMisplacedPos - 1) / Board.BOARD_SIZE - (blankPos - 1)
					/ Board.BOARD_SIZE);
			manhattanDiff += Math.abs((firstMisplacedPos - 1) % Board.BOARD_SIZE - (blankPos - 1)
					% Board.BOARD_SIZE);
			fitness += manhattanDiff;
			log.debug("First Misplaced to Blank Manhattan: "+manhattanDiff);
		}
		
		log.info("Final fitness: "+this.fitness);
	}

	/**
	 * Instantiates a new slide chromosome.
	 */
	public SlideChromosome() {
		super();
		this.moves = new ArrayList<MoveElement>();
		this.board = null;
	}

	/**
	 * Instantiates a new slide chromosome.
	 * 
	 * @param board the board
	 */
	public SlideChromosome(Board board) {
		super();
		this.board = board;
		this.moves = new ArrayList<MoveElement>();
	}

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.Chromosome#getCopy() */
	@Override
	public Chromosome getCopy() {

		SlideChromosome chrom = new SlideChromosome(this.board);
		for (MoveElement el : this.moves)
			chrom.moves.add(el);

		return chrom;
	}

	/* (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return "SlideChrom [" + moves + "]";
	}

}
