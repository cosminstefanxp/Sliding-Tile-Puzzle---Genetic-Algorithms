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
import ml.tema4.ga.MutationHandler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class SlideMutationHandler implements MutationHandler {

	public float MUTATION_RATE;

	private static Logger log = Logger.getLogger(SlideMutationHandler.class);

	private static final Random rand = new Random();

	public SlideMutationHandler(float mutationRate) {
		MUTATION_RATE = mutationRate;
	}

	/* (non-Javadoc)
	 * 
	 * @see ml.tema4.ga.MutationHandler#mutate(ml.tema4.ga.Chromosome) */
	@Override
	public void mutate(Chromosome chrom) {
		// Type checking
		if (!(chrom instanceof SlideChromosome)) {
			log.error("Mutation of illegal chromosome: " + chrom);
			return;
		}
		SlideChromosome slideChrom = (SlideChromosome) chrom;
		if (log.isEnabledFor(Level.DEBUG))
			log.debug("Mutating: " + slideChrom);

		// Mutating?
		if (rand.nextFloat() > MUTATION_RATE) {
			log.debug("No mutation.");
			return;
		}

		// Choosing one of the three mutation types: ADDing, REMOVing or EDITing a move element.
		int pos;
		ArrayList<MoveElement> possibleElements = MoveElement.getAllPossibleMoves();
		switch (rand.nextInt(3)) {
		// Inserting new move element
		case 0:
			log.debug("Inserting new move element...");
			// Element picking
			MoveElement el = possibleElements.get(rand.nextInt(possibleElements.size()));

			// Insert element
			pos = rand.nextInt(slideChrom.moves.size());
			slideChrom.moves.add(pos, el);
			break;
		// Removing an element
		case 1:
			log.debug("Removing move element...");
			pos = rand.nextInt(slideChrom.moves.size());
			slideChrom.moves.remove(pos);
			break;
		// Modifying an element
		case 2:
			log.debug("Modifying move element...");
			// Element picking
			MoveElement el2 = possibleElements.get(rand.nextInt(possibleElements.size()));

			// Edit the element
			pos = rand.nextInt(slideChrom.moves.size());
			slideChrom.moves.set(pos, el2);
			break;
		}
		if (log.isEnabledFor(Level.DEBUG))
			log.debug("Mutated successfully in " + slideChrom);

	}

}
