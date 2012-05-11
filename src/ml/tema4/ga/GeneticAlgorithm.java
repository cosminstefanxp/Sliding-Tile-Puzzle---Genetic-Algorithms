/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4.ga;

import java.util.ArrayList;
import java.util.Random;

import ml.tema4.MoveElement;
import ml.tema4.SlideChromosome;

import org.apache.log4j.Level;
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

		// Generate initial population
		this.population = generate(POPULATION_SIZE);
		// Compute initial fitnesses
		for (Chromosome chrom : population)
			chrom.updateFitness();

		float lastBestFitness = -1;
		Status status = null;
		// int generations
		while ((status = stopCondition()) == Status.PROCESSING) {
			this.beginGenerationCallback();

			// Select subpopulation
			ArrayList<Chromosome> subPop = selectionH.select(population);

			// Create the new population
			ArrayList<Chromosome> newPop = new ArrayList<Chromosome>();

			// Compute best fitness
			bestFitness = Float.MAX_VALUE;
			Chromosome bestChrom = null;
			for (Chromosome chrom : population) {
				if (bestFitness > chrom.getFitness()) {
					bestFitness = chrom.getFitness();
					bestChrom = chrom;
				}
			}

			// Introduce best chromosome in new population - elitism
			newPop.add(bestChrom);
			// Introduce a mutant of the first chromosome, if it's not there (if it mutated) to help
			// with local minimums
			bestChrom = bestChrom.getCopy();
			mutationH.mutate(bestChrom);
			if (!newPop.contains(bestChrom))
				;
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

			// if (bestFitness == 91.0f) {
			// SlideChromosome chrRes = (SlideChromosome) bestChrom;
			// Logger.getRootLogger().setLevel(Level.DEBUG);
			// // for(MoveElement move:chrRes.moves)
			// // chrRes.board.doMoveElement(move);
			// log.warn(chrRes.board);
			// chrRes.updateFitness();
			// Logger.getRootLogger().setLevel(Level.WARN);
			//
			// }

			// Update the population
			population = newPop;
			for (Chromosome chrom : population)
				chrom.updateFitness();
			this.endGenerationCallback();
		}

		return status;
	}
}
