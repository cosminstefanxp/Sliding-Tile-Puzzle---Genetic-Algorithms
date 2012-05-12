/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

/**
 * The Class Config.
 */
public class Config {

	/** The BOARD WIDTH. */
	public static int BOARD_WIDTH = 3;

	/** The BOARD HEIGHT. */
	public static int BOARD_HEIGHT = BOARD_WIDTH;

	/** The MAXIMUM NUMBER OF GENERATIONS. */
	public static int MAX_GENERATION_COUNT = 1000000;

	/** The POPULATION SIZE. */
	public static int POPULATION_SIZE = 100;

	/** The number of generations after a population that has a constant best fitness is reseted. */
	public static int ENVIRONMENT_RESET_GENERATION_COUNT = 200;

	/** The MUTATION RATIO. */
	public static float MUTATION_RATIO = 0.5f;

	/** The CROSSOVER RATIO. */
	public static float CROSSOVER_RATIO = 0.4f;

	/** The SELECTION RATIO. */
	public static float SELECTION_RATIO = 0.5f;

	/** The Constant SORTING_ENABLED. */
	public static boolean SORTING_ENABLED = true;

	/** The Constant ELITISM_RATION. */
	public static float ELITISM_RATIO = 0.01f;

	public static boolean PRINTING = true;

}
