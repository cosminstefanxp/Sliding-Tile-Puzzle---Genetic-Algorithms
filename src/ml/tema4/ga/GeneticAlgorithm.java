/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4.ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ml.tema4.Config;

import org.apache.log4j.Logger;

/**
 * The Class GeneticAlgorithm.
 */
public abstract class GeneticAlgorithm {

	/** The Constant POPULATION_SIZE. */
	public static final int POPULATION_SIZE = Config.POPULATION_SIZE;

	/** The number of generations after a population that has a constant best fitness is reseted. */
	public static final int ENVIRONMENT_RESET_GENERATION_COUNT = Config.ENVIRONMENT_RESET_GENERATION_COUNT;

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

	/** The best fitness found so far. */
	protected float bestFitness = Float.MAX_VALUE;

	/** The Constant rand. */
	private static final Random rand = new Random();

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
	protected abstract Status updateStatus();

	/**
	 * This method is called at the beginning of each generation.
	 */
	protected abstract void beginGenerationCallback();

	/**
	 * This method is called at the end of each generation.
	 */
	protected abstract void endGenerationCallback();

	/**
	 * This method is called when a population has a constant best fitness for a long period. Then
	 * the environment is reseted.
	 * 
	 * @param bestChromosome the best chromosome
	 */
	protected abstract void resetEnvironment(Chromosome bestChromosome);
	
	/**
	 * This method is called at the end of the algorithm.
	 */
	protected abstract void algorithmCompleteCallBack(Status status);

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

		// Generate initial population
		this.population = generate(POPULATION_SIZE);
		// Compute initial fitnesses
		for (Chromosome chrom : population)
			chrom.updateFitness();

		float lastBestFitness = -1;
		int generationsConstFitness = 0;
		Status status = null;
		Chromosome bestChrom = null;
		while ((status = updateStatus()) == Status.PROCESSING) {
			this.beginGenerationCallback();

			// If the best fitness is constant for a long period of time, reset the environment
			if (generationsConstFitness > ENVIRONMENT_RESET_GENERATION_COUNT) {
				resetEnvironment(bestChrom);
				generationsConstFitness = 0;
			}

			// Select subpopulation
			ArrayList<Chromosome> subPop = selectionH.select(population);

			// Create the new population
			ArrayList<Chromosome> newPop = new ArrayList<Chromosome>();

			if (Config.SORTING_ENABLED) {
				// Sort the chromosomes and get the best
				Collections.sort(population);
				bestFitness = population.get(0).getFitness();
				bestChrom = population.get(0);
			
			} else {
				// Compute best fitness
				bestFitness = Float.MAX_VALUE;
				for (Chromosome chrom : population) {
					if (bestFitness > chrom.getFitness()) {
						bestFitness = chrom.getFitness();
						bestChrom = chrom;
					}
				}
			}
			// Update fitness computations
			if (bestFitness == lastBestFitness)
				generationsConstFitness++;
			else
				generationsConstFitness = 0;
			lastBestFitness = bestFitness;

			// Introduce best chromosome(s) in new population - elitism
			if(!Config.SORTING_ENABLED)
				newPop.add(bestChrom);
			else
				for (int i = 0; i < POPULATION_SIZE * Config.ELITISM_RATIO; i++)
					newPop.add(population.get(i));
			
			// Introduce a mutant of the first chromosome, if it's not there (if it mutated) to help
			// with local minimums
			bestChrom = bestChrom.getCopy();
			mutationH.mutate(bestChrom);
			if (!newPop.contains(bestChrom))
				newPop.add(bestChrom);

			// Add new chromosomes
			while (newPop.size() < POPULATION_SIZE) {
				Chromosome chrom1, chrom2;
				chrom1 = subPop.get(rand.nextInt(subPop.size()));
				chrom2 = subPop.get(rand.nextInt(subPop.size()));

				chrom1 = chrom1.getCopy();
				chrom2 = chrom2.getCopy();

				crossoverH.crossover(chrom1, chrom2);

				mutationH.mutate(chrom1);
				mutationH.mutate(chrom2);

				if (!newPop.contains(chrom1))
					newPop.add(chrom1);
				if (!newPop.contains(chrom2) && newPop.size() < POPULATION_SIZE)
					newPop.add(chrom2);
			}

			// Update the population
			population = newPop;
			for (Chromosome chrom : population)
				chrom.updateFitness();

			this.endGenerationCallback();
		}
		
		algorithmCompleteCallBack(status);

		return status;
	}
}
