/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import java.util.ArrayList;

/**
 * The Class MoveElement that contains possible step combinations, including macros.
 */
public class MoveElement {

	/** The steps. */
	public Step[] steps;

	/**
	 * Instantiates a new move element.
	 *
	 * @param moves the moves
	 */
	public MoveElement(Step[] moves) {
		super();
		this.steps = moves;
	}
	
	/** All possible moves. */
	private static ArrayList<MoveElement> allMoves=null;
	
	/**
	 * Gets all the possible moves. Singleton style.
	 *
	 * @return the all possible moves
	 */
	public static ArrayList<MoveElement> getAllPossibleMoves(){
		if(allMoves==null)
		{
			allMoves=new ArrayList<MoveElement>();
			allMoves.add(new MoveElement(new Step[] {Step.Left}));
			allMoves.add(new MoveElement(new Step[] {Step.Right}));
			allMoves.add(new MoveElement(new Step[] {Step.Up}));
			allMoves.add(new MoveElement(new Step[] {Step.Down}));
		}
		
		return allMoves;
	}
}
