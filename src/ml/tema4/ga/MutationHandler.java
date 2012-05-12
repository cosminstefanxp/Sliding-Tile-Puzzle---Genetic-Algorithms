/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4.ga;

/**
 * The Interface MutationHandler.
 */
public interface MutationHandler {

	/**
	 * Mutate.
	 *
	 * @param chrom the chrom
	 */
	public void mutate(Chromosome chrom);
	
}
