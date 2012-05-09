/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4.ga;

/**
 * The Interface CrossoverHandler.
 */
public interface CrossoverHandler {

	/**
	 * Crossover.
	 *
	 * @param chrom1 the chrom1
	 * @param chrom2 the chrom2
	 */
	public void crossover(Chromosome chrom1, Chromosome chrom2);
}
