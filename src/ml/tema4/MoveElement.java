/*
 * Machine Learning - Genetic Algorithms
 * Slide Puzzle
 *
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ml.tema4;

import java.util.ArrayList;
import java.util.Arrays;

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
	private static ArrayList<MoveElement> allMoves = null;

	/**
	 * Gets all the possible moves. Singleton style.
	 * 
	 * @return the all possible moves
	 */
	public static ArrayList<MoveElement> getAllPossibleMoves() {
		if (allMoves == null) {
			allMoves = new ArrayList<MoveElement>();
			allMoves.add(new MoveElement(new Step[] { Step.Left }));
			allMoves.add(new MoveElement(new Step[] { Step.Right }));
			allMoves.add(new MoveElement(new Step[] { Step.Up }));
			allMoves.add(new MoveElement(new Step[] { Step.Down }));
			allMoves.add(new MoveElement(new Step[] { Step.Left, Step.Up, Step.Right }));
			allMoves.add(new MoveElement(new Step[] { Step.Right, Step.Up, Step.Left }));
			allMoves.add(new MoveElement(new Step[] { Step.Up, Step.Left, Step.Down }));
			allMoves.add(new MoveElement(new Step[] { Step.Right, Step.Up, Step.Up, Step.Left, Step.Down }));
			allMoves.add(new MoveElement(new Step[] { Step.Down, Step.Left, Step.Left, Step.Up, Step.Right }));
			allMoves.add(new MoveElement(new Step[] { Step.Down, Step.Right, Step.Right, Step.Up, Step.Left }));
			allMoves.add(new MoveElement(new Step[] { Step.Up, Step.Right, Step.Right, Step.Down, Step.Left,
					Step.Up, Step.Left, Step.Down }));
			allMoves.add(new MoveElement(new Step[] { Step.Up, Step.Up, Step.Left, Step.Down, Step.Right,
					Step.Down, Step.Left, Step.Up, Step.Up, Step.Right, Step.Down }));
			allMoves.add(new MoveElement(new Step[] { Step.Left, Step.Up, Step.Right, Step.Right, Step.Down,
					Step.Left, Step.Up, Step.Left, Step.Down }));
			allMoves.add(new MoveElement(new Step[] { Step.Up, Step.Right, Step.Down, Step.Right, Step.Right,
					Step.Up, Step.Left, Step.Left, Step.Left, Step.Down, Step.Right, Step.Right, Step.Up,
					Step.Right }));
			allMoves.add(new MoveElement(new Step[] { Step.Up, Step.Right, Step.Down, Step.Right, Step.Right,
					Step.Up, Step.Left, Step.Left, Step.Left, Step.Down, Step.Right, Step.Right, Step.Up,
					Step.Right, Step.Down }));
			allMoves.add(new MoveElement(new Step[] { Step.Left, Step.Left, Step.Up, Step.Right, Step.Down,
					Step.Right, Step.Right, Step.Up, Step.Left, Step.Left, Step.Left, Step.Down, Step.Right,
					Step.Right, Step.Up, Step.Right, Step.Down }));
			allMoves.add(new MoveElement(new Step[] { Step.Up, Step.Left, Step.Down, Step.Left, Step.Left,
					Step.Up, Step.Right, Step.Down, Step.Right, Step.Up, Step.Left, Step.Left, Step.Down,
					Step.Right, Step.Right, Step.Up, Step.Right, Step.Down }));
		}

		return allMoves;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MoveElem [" + Arrays.toString(steps) + "]";
	}
}
