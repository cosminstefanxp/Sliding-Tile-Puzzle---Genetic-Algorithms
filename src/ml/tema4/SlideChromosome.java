/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import java.util.ArrayList;

import ml.tema4.ga.Chromosome;

/**
 * The Class SlideChromosome.
 */
public class SlideChromosome extends Chromosome {

	/** The moves. */
	public ArrayList<MoveElement> moves;

	/** The board. */
	public Board board;

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.Chromosome#updateFitness() */
	@Override
	public void updateFitness() {

		Board newBoard = board.getCopy();

		// Do the moves
		for (MoveElement move : moves)
			newBoard.doMoveElement(move);

		/* Evaluate the board using 
		 * - first misplaced element 
		 * - blank element 
		 * - count of misplaced elements 
		 */
		int misplaced = 0, firstMisplacedPos = 0, firstMisplaced = -1, blankPos=0;
		
		for (int i = 0; i < Board.BOARD_SIZE; i++)
			for (int j = 0; j < Board.BOARD_SIZE; j++) {
				
				int val = i * Board.BOARD_SIZE + j + 1;
				
				if (newBoard.board[i][j] == 0)
				{
					blankPos = val;
					continue;
				}
				if (newBoard.board[i][j] != val) {
					misplaced++;
					if (firstMisplaced == -1)
						firstMisplaced = val;
				}
				if (newBoard.board[i][j] == firstMisplaced)
					firstMisplacedPos = val;
			}

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
		}
		
		// Use the Manhattan distance of the blank element to the first misplaced element
		if(firstMisplaced!=-1)
		{
			int manhattanDiff = 0;
			manhattanDiff += Math.abs((firstMisplaced - 1) / Board.BOARD_SIZE - (blankPos - 1)
					/ Board.BOARD_SIZE);
			manhattanDiff += Math.abs((firstMisplaced - 1) % Board.BOARD_SIZE - (blankPos - 1)
					% Board.BOARD_SIZE);
			fitness += manhattanDiff;
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
