/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4.ga;

import java.util.ArrayList;

/**
 * The Class GeneticAlgorithm.
 */
public abstract class GeneticAlgorithm {

	/** The selection handler. */
	private SelectionHandler selectionH;
	
	/** The mutation handler. */
	private MutationHandler mutationH;
	
	/** The crossover handler. */
	private CrossoverHandler crossoverH;
	
	/**
	 * Generates 'count' random chromosomes.
	 *
	 * @param count the count
	 * @return the array list of chromosomes
	 */
	protected abstract ArrayList<Chromosome> generate(int count);
	
	/**
	 * Checks if the stop condition is achieved.
	 *
	 * @return true, if the algorithm should stop.
	 */
	protected abstract boolean stopCondition();
	
	/**
	 * Instantiates a new genetic algorithm.
	 *
	 * @param selectionH the selection handler
	 * @param mutationH the mutation handler
	 * @param crossoverH the crossover handler
	 * @param generator the generator
	 */
	public GeneticAlgorithm(SelectionHandler selectionH, MutationHandler mutationH,
			CrossoverHandler crossoverH) {
		super();
		this.selectionH = selectionH;
		this.mutationH = mutationH;
		this.crossoverH = crossoverH;
	}
	
	/**
	 * Runs the genetic algorithm.
	 *
	 * @return true, if successful
	 */
	public boolean run()
	{
		return true;
	}

}
