package ml.tema4;

import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import ml.tema4.ga.Chromosome;
import ml.tema4.ga.CrossoverHandler;

public class SimpleCrossoverHandler implements CrossoverHandler {
	public float CROSSOVER_RATE;

	private static Logger log = Logger.getLogger(SlideMutationHandler.class);

	private static final Random rand = new Random();

	public SimpleCrossoverHandler(float crossoverRate) {
		CROSSOVER_RATE = crossoverRate;
	}
	@Override
	public void crossover(Chromosome chrom1, Chromosome chrom2) {
		// Type checking
		if (!(chrom1 instanceof SlideChromosome)) {
			log.error("Mutation of illegal chromosome: " + chrom1);
			return;
		}
		SlideChromosome slideChrom1 = (SlideChromosome) chrom1;
		// Type checking
		if (!(chrom2 instanceof SlideChromosome)) {
			log.error("Mutation of illegal chromosome: " + chrom2);
			return;
		}
		SlideChromosome slideChrom2 = (SlideChromosome) chrom2;
		
		// Performing crossover?
		if (rand.nextFloat() > CROSSOVER_RATE) {
			log.debug("No crossover.");
			return;
		}
		
		if (log.isEnabledFor(Level.DEBUG))
			log.debug("Performing crossover between: \n\t" + slideChrom1 + " and \n\t"+slideChrom2);
		
		//Set a crossover position
		int maxSize;
		if(slideChrom1.moves.size()>slideChrom2.moves.size())
			maxSize=slideChrom2.moves.size();
		else
			maxSize=slideChrom1.moves.size();
		int pos=rand.nextInt(maxSize);

		//Do the crossover
		for(int i=0;i<pos;i++)
		{
			MoveElement aux=slideChrom2.moves.get(i);
			slideChrom2.moves.set(i, slideChrom1.moves.get(i));
			slideChrom1.moves.set(i, aux);
		}
		if (log.isEnabledFor(Level.DEBUG))
			log.debug("Crossover successfull with results: \n\t" + slideChrom1 + " and \n\t"+slideChrom2);
		

	}

}
