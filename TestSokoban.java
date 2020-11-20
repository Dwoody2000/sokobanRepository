//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Sokoban
// Files:           Sokoban.java , TestSokoban.java
// Course:          CS 200 Fall 2018
//
// Author:          David Woodson
// Email:           dwoodson2@wisc.edu
// Lecturer's Name: Jim Williams
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Sarah Worthington
// Partner Email:   smworthingto@wisc.edu
// Lecturer's Name: Jim Williams
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __X_ Write-up states that pair programming is allowed for this assignment.
//   __X_ We have both read and understand the course Pair Programming Policy.
//   __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:         Zach Einspanier and Anshul Pakala
//						Zach and Anshul are in their own group. Our groups 
//						worked together mostly to understand the instructions of
//						the assignment, specifically for the printBoard and 
//						CalcDelta methods.
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This file contains testing methods for the Sokoban project. These methods are intended to
 * provide an example of a way to incrementally test your code, and to provide example method calls
* for the Sokoban methods
*
* Toward these objectives, the expectation is that part of the grade for the Sokoban project is
 * to write some tests and write header comments summarizing the tests that have been written.
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
*/

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban class as
 * they are developed. These methods are all private as they are only intended
 * for use within this class.
 *
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestSokoban {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 *
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		// Milestone 1
		// testCheckLevel();
		// Milestone 2
		// testInitBoard();
		// testCheckWin();
		// testCalcDelta();
		// testCheckDelta();
		// Milestone 3
		// testTogglePos();
		// testShiftBox();
		// testDoMove();
		// testProcessMove();
		
	}

	/**
	 * Tests that checkLevel() runs a given level through some basic sanity checks.
	 */
	private static void testCheckLevel() {
		int numTests = 4;
		int passed = numTests;
		int res;

		// Test 1
		// lvl >= 0
		if ((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) { 
			// lvl < 0 so it should fail
			System.out.println("FAILED: Sokoban.checkLevel Test 1. Expected 0,"
					+ " but value returned " + res);
			passed--;
		}

		// Test 2
		// lvl is a valid index in levels, that the 2-d array 
		//at index lvl exists and
		// that
		// it contains at least 1 row.

		else if ((res = Sokoban.checkLevel(3, Config.LEVELS, Config.GOALS)) 
				!= -1) { // 3 is an invalid index so
						// test should fail
			System.out.println("FAILED: Sokoban.checkLevel Test 2. "
					+ "Expected -1, but value returned " + res);
			passed--;
		}

		// Test 3
		// 3 lvl is a valid index in goals, the 1-d array at index 
		//lvl exists and that
		// it
		// contains an even number of cells.
		else if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) 
				== -2) { // 1 is an invalid index in
						// goals so test should fail
			System.out.println("FAILED: Sokoban.checkLevel Test 3. "
					+ "Expected -2, but value returned " + res);
			passed--;
		}

		// Test 4
		// the number of boxes is more than 0.
		else if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) 
				== -3) { // number of boxes is less than
						// 0 so test should fail
			System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected "
					+ "-3, but value returned " + res);
			passed--;
		}

		System.out.println("testCheckLevel: Passed " + passed + " of " 
				+ numTests + " tests.");
	}

	/**
	 * Returns true if the arrays are the same size and have the same contents.
	 */
	private static boolean compBoards(char[][] a, char[][] b) {
		if (a == null || b == null) // if a and b are empty it is false
			return false;
		if (a.length != b.length) 
			// if a and b aren't equal length it is false
			return false;
		for (int i = 0; i < a.length; i++) { // loops through a
			if (!Arrays.equals(a[i], b[i])) { 
				// checks if every index for a equals every index for b
				return false;
			}
		}
		return true;

	}

	/**
	 * Checks that InitBoard correctly initializes the game board to a given level. 
	 * You can assume that the level at lvl has been successfully verified by the 
	 * checkLevel method and that pos is an array of length 2.
	 */
	private static void testInitBoard() {
		int numTests = 2;
		int passed = numTests;

		// Test 1
		int[] pTest1 = new int[2];
		char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, 
				Config.GOALS, pTest1);
		if (!Arrays.equals(pTest1, new int[] { 4, 4 })) {
			System.out.println(
					"FAILED: Sokoban.initBoard Test 1. Expected initial "
					+ "position: {4, 4} , but value after call "
							+ Arrays.toString(pTest1));
			passed--;
		}
		//board to compare result to
		char[][] bCompTest1 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if (!compBoards(bTest1, bCompTest1)) {
			System.out.println("FAILED: Sokoban.initBoard Test 1. "
					+ "Board not as expected!");
			System.out.println("Generated:");
			Sokoban.printBoard(bTest1);
			System.out.println("Expected:");
			Sokoban.printBoard(bCompTest1);
			passed--;
		}
		// End of Test 1
		
		// Test 2
		int[] pTest2 = new int[2];
		char[][] bTest2 = Sokoban.initBoard(0, Config.LEVELS, 
				Config.GOALS, pTest2);
		if (!Arrays.equals(pTest2, new int[] { 4, 4 })) {
			System.out.println(
					"FAILED: Sokoban.initBoard Test 2. Expected initial "
					+ "position: {4, 4} , but value after call "
							+ Arrays.toString(pTest2));
			passed--;
		}
		//board to compare result to
		char[][] bCompTest2 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if (!compBoards(bTest2, bCompTest2)) {
			System.out.println("FAILED: Sokoban.initBoard Test 2. "
					+ "Board not as expected!");
			System.out.println("Generated:");
			Sokoban.printBoard(bTest2);
			System.out.println("Expected:");
			Sokoban.printBoard(bCompTest2);
			passed--;
		}
		// End of Test 2

		System.out.println("testInitBoard: Passed " + passed + " of " 
				+ numTests + " tests.");
	}

	/**
	 * Checks that checkWin returns the correct result by checking all the cells in board 
	 * and ensures that there are no goals that are not covered by boxes.
	 */
	private static void testCheckWin() {
		int numTests = 1;
		int passed = numTests;

		char[][] test = new char[][] {
				{ Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };

		boolean result = Sokoban.checkWin(test);
		if (!result) { //if user fails / check win returns false
			System.out
					.println("FAILED: Sokoban.checkWin Test 1. Expected initial"
							+ " position: false , but value after call "
							+ Arrays.toString(test));
			passed--;
		} else {

			System.out.println("All Tests Passsed: Sokoban.checkWin");
		}
	}

	
	/**
	 * Checks that CalcDelta builds an int array with 2 cells, representing a movement vector,
	 * based on the String parameter.
	 */
	private static void testCalcDelta() {
		int numTests = 1;
		int passed = numTests;

		// TEST 1
		String moveStr = "81";
		//should move up 1
		int[] testResult = Sokoban.calcDelta(moveStr);
		if (!(Arrays.equals(testResult, new int[] { -1, 0 }))) {
			System.out.println("FAILED: Sokoban.calcDelta Test 1. Expected: "
					+ "{-1, 0} , but value after call "
					+ Arrays.toString(testResult));
			passed--;
		} else {
			System.out.println("PASSED for Test 1 of testCalcDelta");
			System.out.println(Arrays.toString(testResult));
			System.out.println(Arrays.toString(new int[] { -1, 0 }));

		}

		// TEST 2
		// Failed: Sokoban.calcDelta Test 1. Expected movement vector 
		//{-400,0}, but
		// returned [0, 0]
		String moveStr2 = "8400";
		int[] testResult2 = Sokoban.calcDelta(moveStr2);
		if (!(Arrays.equals(testResult2, new int[] { -400, 0 }))) {
			System.out.println("FAILED: Sokoban.calcDelta Test 2. "
					+ "Expected: {-400, 0} , but value after call "
					+ Arrays.toString(testResult2));
			passed--;
		} else {
			System.out.println("PASSED for Test 2 of testCalcDelta");
			System.out.println(Arrays.toString(testResult2));
			System.out.println(Arrays.toString(new int[] { -400, 0 }));

		}

	}

	/**
	 * Checks that CheckDelta checks that moving from one position to another position is a
	 * valid move.
	 */
	private static void testCheckDelta() {
		int numTests = 1;
		int passed = numTests;
		int res;

		// Test 1
		int[] pos = { 4 };
		int[] delta = { 2, 2 };
		char[] valid = { Config.GOAL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, 
				Config.WALL_CHAR, Config.WORKER_CHAR,
				Config.BOX_GOAL_CHAR, Config.WORK_GOAL_CHAR };
		char[][] test1 = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if ((res = Sokoban.checkDelta(test1, pos, delta, valid)) != -1) {
			System.out.println("FAILED: Sokoban.checkDelta Test 1. Expected "
					+ "-1, but value returned " + res);
			passed--;
		}

		System.out.println("testCheckDelta: Passed " + passed + " of " 
				+ numTests + " tests.");

	}

	/**
	 * Tests that TogglePos changes a character on the board to one of two characters (opt1 or opt2),
	 * depending on the value of the cell.
	 */
	private static void testTogglePos() {
		int[] pos = new int[] { 2, 3 };
		Sokoban.togglePos(Config.LEVELS[0], pos, Config.EMPTY_CHAR, 
				Config.BOX_CHAR, Config.EMPTY_CHAR);

		if (Config.LEVELS[0][2][3] != Config.BOX_CHAR) {
			System.out.println("Test Failed");
		} else {
			System.out.println("All Test Passed");
		}
	}

	/**
	 * Checks that ShiftBox moves a box on the board.
	 */
	private static void testShiftBox() {
		int[] pos = new int[] { 4, 3 };
		int[] delta = new int[] { 0, -1 };
		int shiftRes = (Sokoban.shiftBox(Config.LEVELS[0], pos, delta));
		if ((Config.LEVELS[0][4][2] == Config.BOX_CHAR) && (shiftRes == 1)) {
			System.out.println("All Test Passed");
		} else {
			System.out.println("Test Failed");
		}
	}
	/**
	 * Tests that DoMove moves the worker on the board.
	 */
	private static void testDoMove() {
		int[] pos = new int[] { 4, 4 };
		int[] delta = new int[] { 0, -1 };
		Sokoban.doMove(Config.LEVELS[0], pos, delta);
		if (Config.LEVELS[0][4][3] != Config.WORKER_CHAR) {
			System.out.println("Test Failed");
		} else {
			System.out.println("All Test Passed");
		}
	}

	/**
	 * Tests that ProcessMove processes a move of the worker step-by-step.
	 */
	private static void testProcessMove() {
		char[] validChar = new char[] { Config.WORKER_CHAR, 
				Config.WORK_GOAL_CHAR, Config.EMPTY_CHAR };
		Sokoban.printBoard(Config.LEVELS[0]);
		int[] pos = new int[] { 4, 4 };
		int[] delta = new int[] { 0, -3 };
		Sokoban.processMove(Config.LEVELS[0], pos, delta);
		if (Config.LEVELS[0][4][1] == Config.WORKER_CHAR) {
			System.out.println("All Test Passed");
		} else {
			System.out.println("Test Failed");
		}
	}

}