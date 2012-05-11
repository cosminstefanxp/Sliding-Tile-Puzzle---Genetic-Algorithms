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
import ml.tema4.ga.SelectionHandler;

import org.apache.log4j.Logger;

public class RouletteWheelSelectionHandler implements SelectionHandler {

	public float SELECTION_RATIO;

	private static Logger log = Logger.getLogger(RouletteWheelSelectionHandler.class);

	private static final Random rand = new Random();

	public RouletteWheelSelectionHandler(float selectionRate) {
		SELECTION_RATIO = selectionRate;
	}

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.SelectionHandler#select(java.util.ArrayList) */
	@Override
	public ArrayList<Chromosome> select(ArrayList<Chromosome> population) {
		ArrayList<Chromosome> newPop = new ArrayList<Chromosome>();
		log.info("Selecting population...");

		// Find maximum
		float maxValue = -1;
		for (Chromosome chrom : population)
			if (chrom.getFitness() > maxValue)
				maxValue = chrom.getFitness();
		log.info("Max value: " + maxValue);

		// Prepare sums array
		float fitnesses[] = new float[population.size()];
		for (int i = 0; i < population.size(); i++) {
			if (i == 0)
				fitnesses[i] = 1 - population.get(i).getFitness() / maxValue;
			else
				fitnesses[i] = fitnesses[i - 1] + 1 - population.get(i).getFitness() / maxValue;
		}
		log.debug("Fitnesses: " + fitnesses);

		// Select a percent of the population
		for (int i = 0; i < population.size() * SELECTION_RATIO; i++) {
			// Pick a position
			float nr = rand.nextFloat();
			int pos = 0;

			// Search for the number
			for (int j = 0; j < population.size(); j++) {
				if (j == 0 && nr < fitnesses[0])
					pos = j;
				else if (j > 0 && nr > fitnesses[j - 1] && nr < fitnesses[j])
					pos = j;
			}
			// Add the chromosome to the selection, if not already there
			if (!newPop.contains(population.get(pos)))
				newPop.add(population.get(pos));
			else
				i--;
		}
		if(log.isDebugEnabled())
			log.debug("Selected population:" + newPop);

		return newPop;
	}

}
