package ml.tema4;

import java.util.ArrayList;

import ml.tema4.ga.Chromosome;
import ml.tema4.ga.CrossoverHandler;
import ml.tema4.ga.GeneticAlgorithm;
import ml.tema4.ga.MutationHandler;
import ml.tema4.ga.SelectionHandler;

public class SlidePuzzleGA extends GeneticAlgorithm {

	public SlidePuzzleGA(SelectionHandler selectionH, MutationHandler mutationH, CrossoverHandler crossoverH) {
		super(selectionH, mutationH, crossoverH);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ArrayList<Chromosome> generate(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean stopCondition() {
		// TODO Auto-generated method stub
		return false;
	}

}
