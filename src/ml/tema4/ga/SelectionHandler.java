/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4.ga;

import java.util.ArrayList;

public interface SelectionHandler {

	public ArrayList<Chromosome> select(ArrayList<Chromosome> chromos);
}
