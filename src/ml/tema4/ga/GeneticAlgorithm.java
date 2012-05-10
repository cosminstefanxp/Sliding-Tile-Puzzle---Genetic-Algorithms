/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4.ga;

import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * The Class GeneticAlgorithm.
 */
public abstract class GeneticAlgorithm {

	/** The Constant POPULATION_SIZE. */
	public static final int POPULATION_SIZE = 100;

	/**
	 * The Status of the Genetic Algorithm.
	 */
	public enum Status {
		/** PROCESSING. */
		PROCESSING,
		/** TIMEOUT. */
		TIMEOUT,
		/** SUCCESSFULL. */
		SUCCESSFULL
	};

	/** The selection handler. */
	private SelectionHandler selectionH;

	/** The mutation handler. */
	private MutationHandler mutationH;

	/** The crossover handler. */
	private CrossoverHandler crossoverH;

	/** The Constant log. */
	protected static final Logger log = Logger.getLogger(GeneticAlgorithm.class);

	/** The population. */
	protected ArrayList<Chromosome> population;

	/**
	 * Generates 'count' random chromosomes.
	 * 
	 * @param count the count
	 * @return the array list of chromosomes
	 */
	protected abstract ArrayList<Chromosome> generate(int count);

	/**
	 * Checks if the stop condition is achieved. The fitnesses for the chromosomes should be
	 * up-to-date before calling this method. If {@link PROCESSING} is returned, the algorithm is
	 * not finished and the stop condition was not met.
	 * 
	 * @return the status
	 */
	protected abstract Status stopCondition();

	/**
	 * This method is called at the beginning of each generation.
	 */
	protected abstract void beginGenerationCallback();

	/**
	 * This method is called at the end of each generation.
	 */
	protected abstract void endGenerationCallback();

	/**
	 * Instantiates a new genetic algorithm.
	 * 
	 * @param selectionH the selection handler
	 * @param mutationH the mutation handler
	 * @param crossoverH the crossover handler
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
	public Status run() {
		this.population = generate(POPULATION_SIZE);
		
		for(int i=0;i<10;i++)
		{
			crossoverH.crossover(population.get(i), population.get(i+10));
		}
		
		return Status.SUCCESSFULL;
	}

}
