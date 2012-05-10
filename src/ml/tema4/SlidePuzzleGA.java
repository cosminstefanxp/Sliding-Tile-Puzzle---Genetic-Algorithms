/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import java.util.ArrayList;
import java.util.Random;

import ml.tema4.ga.Chromosome;
import ml.tema4.ga.CrossoverHandler;
import ml.tema4.ga.GeneticAlgorithm;
import ml.tema4.ga.MutationHandler;
import ml.tema4.ga.SelectionHandler;

/**
 * The Class SlidePuzzleGA.
 */
public class SlidePuzzleGA extends GeneticAlgorithm {

	/** The Constant MIN_CHROM_SIZE. */
	private static final int MIN_CHROM_SIZE = 4;

	/** The Constant MAX_CHROM_SIZE. */
	private static final int MAX_CHROM_SIZE = 25;

	/** The Constant MAX_ITERATION_COUNT. */
	private static final int MAX_GENERATION_COUNT = 2000;

	/** The board. */
	Board board;

	/** The rand. */
	Random rand = new Random();

	/** The generation. */
	protected int generation = 0;

	/**
	 * Instantiates a new slide puzzle genetic algorithm.
	 * 
	 * @param selectionH the selection handler
	 * @param mutationH the mutation handler
	 * @param crossoverH the crossover handler
	 */
	public SlidePuzzleGA(SelectionHandler selectionH, MutationHandler mutationH, CrossoverHandler crossoverH,
			Board board) {
		super(selectionH, mutationH, crossoverH);
		this.board=board;
	}

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.GeneticAlgorithm#generate(int) */
	@Override
	protected ArrayList<Chromosome> generate(int count) {
		ArrayList<Chromosome> chromos = new ArrayList<Chromosome>();
		log.warn("Generating a new population of size " + count);

		for (int i = 0; i < count; i++) {
			// Create a new chromosome of variable size
			SlideChromosome chrom = new SlideChromosome(board);
			int size = rand.nextInt(MAX_CHROM_SIZE - MIN_CHROM_SIZE);
			ArrayList<MoveElement> possibleMoves = MoveElement.getAllPossibleMoves();

			for (int j = 0; j < size + MIN_CHROM_SIZE; j++)
				chrom.moves.add(possibleMoves.get(rand.nextInt(possibleMoves.size())));
			log.debug("Generated Chromosome: " + chrom);

			// Add the chromosome to the list
			chromos.add(chrom);
		}
		log.info("Generation of population successful.");
		return chromos;
	}

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.GeneticAlgorithm#stopCondition() */
	@Override
	protected Status stopCondition() {
		// If the maximum generation count was reached
		if (generation > MAX_GENERATION_COUNT)
			return Status.TIMEOUT;

		// If any of the chromosomes is a solution (i.e. the fitness equals 0)
		for (Chromosome chrom : population)
			if (chrom.getFitness() == 0)
				return Status.SUCCESSFULL;

		return Status.PROCESSING;
	}

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.GeneticAlgorithm#beginGenerationCallback() */
	@Override
	protected void beginGenerationCallback() {
		generation++;
		log.warn("New generation starting: " + generation);
	}

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.GeneticAlgorithm#endGenerationCallback() */
	@Override
	protected void endGenerationCallback() {

	}

}
