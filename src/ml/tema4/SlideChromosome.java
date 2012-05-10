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
		this.moves=new ArrayList<MoveElement>();
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
		this.moves=new ArrayList<MoveElement>();
	}

	/* (non-Javadoc)
	 * @see ml.tema4.ga.Chromosome#getCopy()
	 */
	@Override
	public Chromosome getCopy() {
		
		SlideChromosome chrom=new SlideChromosome(this.board);
		for(MoveElement el:this.moves)
			chrom.moves.add(el);
				
		return chrom;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SlideChrom [" + moves + "]";
	}

}
