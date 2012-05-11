/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4.ga;

/**
 * The Class Chromosome.
 */
public abstract class Chromosome {

	/** The fitness. */
	protected float fitness;
	
	/**
	 * Updates the fitness of the chromosome.
	 */
	public abstract void updateFitness();
	
	/**
	 * Gets a cop of the chromosome.
	 *
	 * @return the copy
	 */
	public abstract Chromosome getCopy();

	/**
	 * Gets the fitness.
	 *
	 * @return the fitness
	 */
	public float getFitness() {
		return fitness;
	}
	
	
}
