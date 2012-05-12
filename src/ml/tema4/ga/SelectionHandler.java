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
 * The Interface SelectionHandler.
 */
public interface SelectionHandler {

	/**
	 * Select.
	 *
	 * @param chromos the chromos
	 * @return the array list
	 */
	public ArrayList<Chromosome> select(ArrayList<Chromosome> chromos);
}
