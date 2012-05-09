/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import java.util.LinkedList;

import ml.tema4.ga.Chromosome;

/**
 * The Class SlideChromosome.
 */
public class SlideChromosome extends Chromosome {

	/** The moves. */
	public LinkedList<MoveElement> moves;
	
	/** The board. */
	public Board board;

	/* (non-Javadoc)
	 * @see ml.tema4.ga.Chromosome#updateFitness()
	 */
	@Override
	public void updateFitness() {
		// TODO Auto-generated method stub
	}

	/**
	 * Instantiates a new slide chromosome.
	 */
	public SlideChromosome() {
		super();
		this.moves=new LinkedList<MoveElement>();
		this.board=null;
	}

	/**
	 * Instantiates a new slide chromosome.
	 *
	 * @param board the board
	 */
	public SlideChromosome(Board board) {
		super();
		this.board = board;
		this.moves=new LinkedList<MoveElement>();
	}

	/* (non-Javadoc)
	 * @see ml.tema4.ga.Chromosome#getCopy()
	 */
	@Override
	public Chromosome getCopy() {
		
		SlideChromosome chrom=new SlideChromosome(this.board);
		for(MoveElement el:this.moves)
			chrom.moves.push(el);
				
		return chrom;
	}

}
