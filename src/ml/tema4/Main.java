/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import ml.tema4.ga.CrossoverHandler;
import ml.tema4.ga.GeneticAlgorithm;
import ml.tema4.ga.GeneticAlgorithm.Status;
import ml.tema4.ga.MutationHandler;
import ml.tema4.ga.SelectionHandler;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Main {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(Main.class);
	
	/** The finished generations count. */
	public static int finishedGenerationsCount;

	/**
	 * Configure logger.
	 */
	private static void configureLogger() {
		PatternLayout patternLayout = new PatternLayout("%-3r [%-5p] %c - %m%n");
		ConsoleAppender appender = new ConsoleAppender(patternLayout);
		Logger.getRootLogger().addAppender(appender);
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	/**
	 * Plots the number of runs that finish in under 1500 generations according to the environment reset ratio.
	 */
	private static void plot2()
	{
		Config.PRINTING=false;
		Config.MAX_GENERATION_COUNT=500;
		
		Board board = new Board();
		board.initRandom();
		System.out.println(board);
		
		MutationHandler mh=new SlideMutationHandler(Config.MUTATION_RATIO);
		CrossoverHandler ch=new SlideCrossoverHandler(Config.CROSSOVER_RATIO);
		SelectionHandler sh=new RouletteWheelSelectionHandler(Config.SELECTION_RATIO);

		int TEST_VALS_COUNT=10;
		int xVals[]=new int[TEST_VALS_COUNT];
		int yVals[]=new int[TEST_VALS_COUNT];
		int size=0;
		for(int i=0;i<TEST_VALS_COUNT;i++)
		{
			System.out.println("Test #"+i);
			size+=50;
			Config.ENVIRONMENT_RESET_GENERATION_COUNT=size;
			int totalGenerations=0;
			for(int count=0;count<40;count++)
			{
				System.out.println("\tCount #"+count);
				GeneticAlgorithm algorithm=new SlidePuzzleGA(sh, mh, ch, board.getCopy());
				Status status=algorithm.run();		
				if(status==Status.SUCCESSFULL)
					totalGenerations++;
			}
			xVals[i]=size;			
			yVals[i]=totalGenerations;
		}
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter("octave_plot2"));
			out.write("x="+Arrays.toString(xVals)+";\n");
			System.out.println("x="+Arrays.toString(xVals)+";\n");
			out.write("y="+Arrays.toString(yVals)+";\n");
			System.out.println("y="+Arrays.toString(yVals)+";\n");
			out.write("plot(x,y);");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Plots the number of generations in which the algorithm is finished according to the population size.
	 */
	private static void plot1()
	{
		Config.PRINTING=false;
		Board board = new Board();
		board.initRandom();
		System.out.println(board);
		
		MutationHandler mh=new SlideMutationHandler(Config.MUTATION_RATIO);
		CrossoverHandler ch=new SlideCrossoverHandler(Config.CROSSOVER_RATIO);
		SelectionHandler sh=new RouletteWheelSelectionHandler(Config.SELECTION_RATIO);

		int TEST_VALS_COUNT=20;
		int xVals[]=new int[TEST_VALS_COUNT];
		int yVals[]=new int[TEST_VALS_COUNT];
		int size=5;
		for(int i=0;i<TEST_VALS_COUNT;i++)
		{
			System.out.println("Test #"+i);
			size+=5;
			int totalGenerations=0;
			for(int count=0;count<20;count++)
			{
				System.out.println("\tCount #"+count);
				GeneticAlgorithm algorithm=new SlidePuzzleGA(sh, mh, ch, board.getCopy());
				algorithm.run();		
				totalGenerations+=finishedGenerationsCount;
			}
			xVals[i]=size;
			
			yVals[i]=totalGenerations/20;
		}
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter("octave_plot1"));
			out.write("x="+Arrays.toString(xVals)+";\n");
			System.out.println("x="+Arrays.toString(xVals)+";\n");
			out.write("y="+Arrays.toString(yVals)+";\n");
			System.out.println("y="+Arrays.toString(yVals)+";\n");
			out.write("plot(x,y);");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		configureLogger();

		log.warn("Starting up...");
//		plot1();
//		plot2();
		Board board = new Board();
		board.initRandom();
		
		MutationHandler mh=new SlideMutationHandler(Config.MUTATION_RATIO);
		CrossoverHandler ch=new SlideCrossoverHandler(Config.CROSSOVER_RATIO);
		SelectionHandler sh=new RouletteWheelSelectionHandler(Config.SELECTION_RATIO);
		GeneticAlgorithm algorithm=new SlidePuzzleGA(sh, mh, ch, board);
		Status status=algorithm.run();
		
		
		log.warn("Algorithm finished with status: "+status);
		
		
			

	}
}
