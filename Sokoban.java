
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

import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.util.*;

public class Sokoban {

	/**
	 * Prompts the user for a value by displaying prompt. Note: This method should
	 * not add a new line to the output of prompt.
	 *
	 * After prompting the user, the method will consume an entire line of input
	 * while reading an int. If the value read is between min and max (inclusive),
	 * that value is returned. Otherwise, "Invalid value." terminated by a new line
	 * is output to the console and the user is prompted again.
	 *
	 * @param sc     The Scanner instance to read from System.in.
	 * @param prompt The name of the value for which the user is prompted.
	 * @param min    The minimum acceptable int value (inclusive).
	 * @param max    The maximum acceptable int value (inclusive).
	 * @return Returns the value read from the user.
	 */
	public static int promptInt(Scanner sc, String prompt, int min, int max) {
		prompt = prompt.trim(); // gets rid of leading and trailing spaces

		String s = prompt; // sets new string s to trimmed prompt
		Scanner scnr = new Scanner(s); // creates new scanner with s

		if (s.length() == 0) { // checks if scnr(s) is empty
			return -1;
		}

		if (scnr.hasNextInt()) // check if scnr(s) is an int
		{
			int userprompt = Integer.parseInt(prompt);
			return checkLevel(userprompt, Config.LEVELS, Config.GOALS);

		} else {
			return -1; // invalid value read
		}
	}

	/**
	 * Prompts the user for a char value by displaying prompt. Note: This method
	 * should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input and
	 * return the first non-whitespace character converted to lower case.
	 *
	 * @param sc     The Scanner instance to read from System.in
	 * @param prompt The user prompt.
	 * @return Returns the first non-whitespace character (in lower case) read from
	 *         the user. If there are no non-whitespace characters read, the null
	 *         character is returned.
	 */
	public static char promptChar(Scanner sc, String prompt) {
		char userchartrimmed = ' '; // sets char to ' '
		do {
			System.out.print(prompt); // prints out "Play Again y/n
			String userchar = "";

			userchar = sc.nextLine(); // records user input

			userchar = userchar.toLowerCase(); // sets input to lowercase
			userchar = userchar.trim(); // trims leading and trailing spaces
			userchartrimmed = userchar.charAt(0); // first char in string

		} while (userchartrimmed != 'y' && userchartrimmed != 'n');
		// repeats while entry is invalid

		return userchartrimmed; // return result y or n
	}

	/**
	 * Prompts the user for a string value by displaying prompt. Note: This method
	 * should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input,
	 * remove any leading and trailing whitespace, and return the input converted to
	 * lower case.
	 *
	 * @param sc     The Scanner instance to read from System.in
	 * @param prompt The user prompt.
	 * @return Returns the string entered by the user, converted to lower case with
	 *         leading and trailing whitespace removed.
	 */
	public static String promptString(Scanner sc, String prompt) {

		System.out.print(prompt); // prompts user
		String input = "";
		if (sc.hasNextLine()) { // if sc has value return as trimmed & 
			//lowercase
			input = sc.nextLine();
			input = input.trim();
			input = input.toLowerCase();
//			if (input.equals("\n")) {
//
//			}
		}

		return input;
	}

	/**
	 * Initializes the game board to a given level. You can assume that the level at
	 * lvl has been successfully verified by the checkLevel method and that pos is
	 * an array of length 2.
	 *
	 * 1 - The game board should be created row-by-row. a - For each row, copy the
	 * values from the corresponding row in the 2-d array contained at index lvl in
	 * levels. b - When the worker is located, it's position should be recorded in
	 * the pos parameter. 2 - For each goal described in the array at index lvl of
	 * goals, convert the character at the goal coordinate to: -
	 * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if it
	 * contains a box - Config.GOAL_CHAR otherwise
	 *
	 * @param lvl    The index of the level to load.
	 * @param levels The array containing the levels.
	 * @param goals  The parallel array to levels, containing the goals for the
	 *               levels.
	 * @param pos    The starting pos of the worker. A length 2 array, where index 0
	 *               is the row and index 1 is the column.
	 * @return A two dimension array representing the initial configuration for the
	 *         given level.
	 */
	public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
		int boardLength1 = levels[lvl].length;

		char[][] initConfig = new char[levels[lvl].length][];
		for (int i = 0; i < levels[lvl].length; i++) {
			// loops through rows
			int cols = levels[lvl][i].length;
			initConfig[i] = levels[lvl][i].clone();

			for (int j = 0; j < levels[lvl][i].length; j++) {
				// loops through columns
				// if its a worker char save position
				if (levels[lvl][i][j] == Config.WORKER_CHAR) {
					pos[0] = i;
					pos[1] = j;
				}
				initConfig[i][j] = levels[lvl][i][j];
			}
		}

		for (int i = 0; i < goals[lvl].length; i += 2) {
			// loops through goals
			if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] 
					== Config.WORKER_CHAR) {
				initConfig[goals[lvl][i]][goals[lvl][i + 1]] 
						= Config.WORK_GOAL_CHAR;
				// if its a worker, make it a worker goal
			} else if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] 
					== Config.BOX_CHAR) { // problem line
				initConfig[goals[lvl][i]][goals[lvl][i + 1]] 
						= Config.BOX_GOAL_CHAR;
				// if its a box make it a box goal
			} else {
				initConfig[goals[lvl][i]][goals[lvl][i + 1]] 
						= Config.GOAL_CHAR;
				// all other char are made to goal char
			}
		}
		return initConfig;
		// return the initialized board
	}

	/**
	 * Prints out the game board.
	 *
	 * 1 - Since the game board does not contain the outer walls, print out a
	 * sequence of Config.WALL_CHAR with a length equal to that of the first row of
	 * board, plus the outer wall to the left and the right. 2 - For each row in
	 * board, print out a Config.WALL_CHAR, followed by the contents of the row,
	 * followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence of
	 * Config.WALL_CHAR with a length equal to that of the last row of board, plus
	 * the outer wall to the left and the right.
	 *
	 * Note: each row printed out should be terminated by a new line.
	 *
	 * @param board The board to print.
	 */
	public static void printBoard(char[][] board) {
		for (int i = 0; i < board[0].length + 2; i++) {
			System.out.print(Config.WALL_CHAR);
			// prints top row of wall characters
		}
		System.out.println("");
		for (int i = 0; i < board.length; i++) {
			System.out.print(Config.WALL_CHAR);// prints left wall
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]); // prints row by row
			}
			System.out.println(Config.WALL_CHAR); // prints right wall
		}
		for (int i = 0; i < board[board.length - 1].length + 2; i++) {
			System.out.print(Config.WALL_CHAR); // prints bottom row of wall
		}
		System.out.println("");
	}

	/**
	 * Runs a given level through some basic sanity checks.
	 *
	 * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl is
	 * a valid index in levels, that the 2-d array at index lvl exists and that it
	 * contains at least 1 row. 3 - lvl is a valid index in goals, the 1-d array at
	 * index lvl exists and that it contains an even number of cells. 4 - the number
	 * of boxes is more than 0. 5 - the number of boxes equals the number of goals.
	 * 6 - the coordinate of each goal is valid for the given lvl and does not
	 * correspond to a wall cell. 7 - the number of workers is exactly 1. 8 - check
	 * for duplicate goals.
	 *
	 * @param lvl    The index of the level to load.
	 * @param levels The array containing the levels.
	 * @param goals  The parallel array to levels, containg the goals for the
	 *               levels.
	 * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test 2
	 *         fails: -1 - Test 3 fails: -2 - Test 4 fails: -3 - Test 5 fails: -4 -
	 *         Test 6 fails: -5 - Test 7 fails: -6 - Test 8 fails: -7
	 *
	 */
	public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {

		// Test 1
		// lvl >= 0
		if (lvl < 0) {
			System.out.println("Error loading level!");
			System.out.println("Level " + lvl + " must be 0 or greater!");
			return 0;
		}

		// Test 2
		// lvl is a valid index in levels,
		// that the 2-d array at index lvl exists and that
		// it contains at least 1 row.
		if (lvl > (levels.length - 1)) {
			System.out.println("Error loading level!");
			System.out.println("Error with Config.LEVELS");
			return -1;
		}

		// Test 3
		// lvl is a valid index in goals, the 1-d array at index 
		//lvl exists and that it
		// contains an even number of cells.
		if ((lvl > (goals.length - 1)) || (goals[lvl].length % 2 != 0)) {
			System.out.println("Error loading level!");
			System.out.println("Error with Config.GOALS");
			return -2;
		}

		// Test 4
		// the number of boxes is more than 0.
		// loop through levels and count boxes, if boxes > 0
		int count = 0;

		for (int i = 0; i < levels[lvl].length; i++) { 
			// loops through levels

			for (int j = 0; j < levels[lvl][i].length; j++) { 
				// loops through indexes in levels
				if ((levels[lvl][i][j]) == '=') { // check if its a box
					count++; // add box to count
				}
			}

		}

		if (count <= 0) { // if boxes is <= 0 the test fails
			System.out.println("Error loading level!");
			System.out.println("Level " + lvl + 
					" does not contain any boxes.");
			return -3;
		}

		// Test 5
		// the number of boxes equals the number of goals.
		int boxes = 0;
		for (int i = 0; i < levels[lvl].length; i++) { 
			// loops through levels
			for (int j = 0; j < levels[lvl][i].length; j++) { 
				// loops through indexes in levels
				if ((levels[lvl][i][j]) == '=') { 
					// counts # of boxes
					boxes++;
				}
			}

		}

		int goals1 = 0;
		goals1 = goals1 + goals[lvl].length / 2; // counts goals

		if (boxes != goals1) { // checks if # of goals != # of boxes
			System.out.println("Error loading level!");
			System.out.println("Level " + lvl + 
					" does not have the same number of boxes as goals.");
			return -4;
		}

		// Test 6
		// the coordinate of each goal is valid for the given lvl and does not
		// correspond to a wall cell.
		for (int i = 0; i < goals[lvl].length; i++) {
			if (i % 2 == 0) {
				if ((goals[lvl][i]) >= levels[lvl].length) {
					System.out.println("Error loading level!");
					System.out.println("Level " + lvl + 
							" has a goal location that is a wall.");
					return -5;
				}
			} else {
				if (goals[lvl][i] >= levels[lvl][goals[lvl][i - 1]].length) {
					System.out.println("Error loading level!");
					System.out.println("Level " + lvl + 
							" has a goal location that is a wall.");
					return -5;
				}
			}
		}
		for (int i = 0; i < goals[lvl].length; i += 2) {
			if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == '#') {
				System.out.println("Error loading level!");
				System.out.println("Level " + lvl + 
						" has a goal location that is a wall.");
				return -5;
			}
		}

		// Test 7
		// the number of workers is exactly 1.
		int workers = 0;
		for (int i = 0; i < levels[lvl].length; i++) // loops through levels
		{
			for (int j = 0; j < levels[lvl][i].length; j++) 
				// loops through indexes of levels
			{
				if ((levels[lvl][i][j]) == '@') { // checks if it is a worker
					workers++; // adds to worker count
				}
			}
		}
		if (workers != 1) { // checks if # of workers = 1
			System.out.println("Error loading level!");
			System.out.println("Level " + lvl + 
					" has 0 or more than 1 worker(s).");
			return -6;
		}

		// Test 8
		// check for duplicate goals.
		for (int i = 0; i < goals[lvl].length - 1; i += 2) { 
			// loops through goals
			for (int j = i + 2; j < goals[lvl].length - 1; j += 2) { 
				// loops through indexes of goal by pairs
				if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] 
						== goals[lvl][j + 1]) { 
					// checks if goal is the
					System.out.println("Error loading level!");
					System.out.println("Level " + lvl + 
							" contains duplicate goals.");
					return -7;
				}
			}
		}

		return 1; // is a valid level

	}

	/**
	 * This method builds an int array with 2 cells, representing a movement vector,
	 * based on the String parameter.
	 *
	 * The rules to create the length 2 int array are as follows: - The 1st
	 * character of the String represents the direction. - The remaining characters
	 * (if there are any) are interpreted as integer and represent the magnitude or
	 * the number of steps to take.
	 *
	 * The cell at index 0 represents movement in the rows. Hence, a negative value
	 * represents moving up the rows and a positive value represents moving down the
	 * rows.
	 *
	 * The cell at index 1 represents movement in the columns. Hence, a negative
	 * value represents moving left in the columns and a positive value represents
	 * moving right in the columns.
	 *
	 * If the first character of moveStr does not match on of Config.UP_CHAR,
	 * Config.DOWN_CHAR, Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an
	 * array with 0 in both cells.
	 *
	 * If there are no characters after the first character of moveStr or the
	 * characters cannot be interpreted as an int, set the magnitude of the movement
	 * to 1.
	 *
	 * Hint: Use Scanner to parse the magnitude.
	 *
	 * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would
	 * represent moving up by one character. - If the parameter moveStr is "65": An
	 * array {0, 5} would represent moving right by 5 characters.
	 *
	 * @param moveStr The string to parse.
	 * @return The calculated movement vector as a 2 cell int array.
	 */
	public static int[] calcDelta(String moveStr) {
		int[] noMove = { 0, 0 };
		int[] delta = new int[2];
		int magnitude;
		char direction;
		
		if (moveStr.length() > 1) {
			direction = moveStr.charAt(0);
			magnitude = Integer.parseInt(
					moveStr.substring(1, moveStr.length()));
			// if more than one int is entered then set second int to magnitude
			// and first int to direction
		} else if (moveStr.length() == 1) {
			direction = moveStr.charAt(0);
			magnitude = 1;
			// If there are no characters after the first character of moveStr
			// or the characters cannot be interpreted as an int, set the
			// magnitude of the movement to 1.
		} else {
			direction = '0';
			magnitude = 0;
			// if moveStr.length < 1 set direction and magnitude to 0
		}

		// up and left are negative
		// down and right are positive
		if (direction == Config.UP_CHAR) {
			delta[0] = (-1 * magnitude);
			delta[1] = 0;
		} else if (direction == Config.DOWN_CHAR) {
			delta[0] = (1 * magnitude);
			delta[1] = 0;
		} else if (direction == Config.LEFT_CHAR) {
			delta[0] = 0;
			delta[1] = (-1 * magnitude);
		} else if (direction == Config.RIGHT_CHAR) {
			delta[0] = 0;
			delta[1] = (1 * magnitude);
		} else {
			return noMove;

	// If the first character of moveStr does not match on of Config.UP_CHAR,
	// Config.DOWN_CHAR, Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an
	// array with 0 in both cells.
		}

		return delta; // returns calculated delta
	}

	/**
	 * This method checks that moving from one position to another position is a
	 * valid move.
	 *
	 * To validate the move, the method should (in order) check: 1 - that pos is
	 * valid. 2 - that the character at pos in board is in the valid array. 3 - that
	 * the delta is valid. 4 - that the new position is valid and not a wall
	 * character. 5 - that the new position is not a box character For what makes
	 * each test invalid, see the return details below.
	 *
	 * @param board The current board.
	 * @param pos   The position to move from. A length 2 array, where index 0 is
	 *              the row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @param valid A character array containing the valid characters for the cell
	 *              at pos.
	 * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2,
	 *         or not on the board. -2 : if the character at pos is not valid (not
	 *         in the valid array). -3 : if delta is null or not length 2. -4 : if
	 *         the new position is off the board or a wall character -5 : if the new
	 *         position is a box character
	 */
	public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
		boolean returnValue = true;
		Boolean pos1 = false;
		Boolean valid1 = false;
		Boolean delta1 = false;

		if (pos == null) { // if current position is invalid
			returnValue = false;
			return -1;
		}
		if (pos.length != 2) {
			// if position does not have direction and magnitude
			returnValue = false;
			return -1;
		}

		// check that the character at pos in board is in the valid array
		for (int j = 0; j < board.length; j++) {
			for (int k = 0; k < board[j].length; k++) {
				if (pos[0] == j && pos[1] == k) {
					pos1 = true;
				}
			}
		}
		if (pos1 == false) {
			returnValue = false;
			return -1;
		}

		// check that the delta is valid
		for (int i = 0; i < valid.length; i++) {
			if (valid[i] == board[pos[0]][pos[1]]) {
				valid1 = true;
			}
		}
		if (valid1 == false) {
			returnValue = false;
			return -2;
		}

		// if delta is empty
		if (delta == null) {
			returnValue = false;
			return -3;
		}
		// if delta does not have direction and magnitude
		if (delta.length != 2) {
			returnValue = false;
			return -3;
		}

		// check that the new position is valid and not a wall char
		for (int h = 0; h < board.length; h++) {
			for (int g = 0; g < board[h].length; g++) {
				if (((pos[0] + delta[0]) == h) 
						&& ((pos[1] + delta[1]) == g)) {
					if (board[h][g] != Config.WALL_CHAR) {
						returnValue = false;
						delta1 = true;
					}
				}
			}
		}

		if (delta1 == false) {
			returnValue = false;
			return -4;
		}

		// check that the new position is not a box character
		if (board[pos[0] + delta[0]][pos[1] + delta[1]] == Config.BOX_CHAR
				|| board[pos[0] + delta[0]][pos[1] + delta[1]] 
						== Config.BOX_GOAL_CHAR) {
			returnValue = false;
			return -5;
		}

		// if returnValue shows that all tests passed, return 1
		if (returnValue) {
			return 1;
		}

		return 1;
	}

	/**
	 * Changes a character on the board to one of two characters (opt1 or opt2),
	 * depending on the value of the cell.
	 *
	 * Check the cell at position pos. If the character is val, change it to opt1.
	 * Otherwise, change it to opt2.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param val   The value to check for in the board.
	 * @param opt1  The character to change to if the value is val.
	 * @param opt2  The character to change to if the value is not val.
	 */
	public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
		for (int i = 0; i < pos.length - 1; i += 2) // loops through positions
		{
			if (board[pos[i]][pos[i + 1]] == val)// check if board[pos] is val
			{
				board[pos[i]][pos[i + 1]] = opt1;// if so change to opt1
			} else {
				board[pos[i]][pos[i + 1]] = opt2;// if not change to opt2
			}
		}
	}

	/**
	 * Moves a box on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid. Recall
	 * that there are 2 characters that can represent a box.
	 *
	 * Step 2: Use your togglePos method to correctly change the character at the
	 * new position to the appropriate box character.
	 *
	 * ---Step 3: Again use your togglePos method to correctly change the character
	 * at pos to the the appropriate character without a box.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return The return value of checkDelta if less than 1. Otherwise 1.
	 */
	public static int shiftBox(char[][] board, int[] pos, int[] delta) {
		char[] valid = new char[] { Config.BOX_CHAR, Config.BOX_GOAL_CHAR };
		// two valid characters that can represent a box

		int result = checkDelta(board, pos, delta, valid);
		if (result < 1) { // failed checkDelta, delta is invalid
			return result;
		}

		int[] newPos = new int[] { pos[0] + delta[0], pos[1] + delta[1] };
		// if delta is valid, make new pos

		togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, 
				Config.BOX_CHAR);
		// Use your togglePos method to correctly change the character at the
		// new position to the appropriate box character.

		togglePos(board, pos, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR, 
				Config.EMPTY_CHAR);
		// Again use your togglePos method to correctly change the character
		// at pos to the the appropriate character without a box.

		return 1;
	}

	/**
	 * Processes a move of the worker step-by-step.
	 *
	 * Go through the delta step-by-step, calling doMove for each step. That is, if
	 * the delta is {0, -3}, your method should call doMove three times with an
	 * argument of {0, -1} for the delta parameter of doMove. Or, if the delta is
	 * {6, 0}, it would call the doMove six times with an argument of {1, 0} for the
	 * delta parameter of the doMove method.
	 *
	 * During the processing of the move, if ever a call to doMove returns a value
	 * less than 1, your method should stop processing and return that value.
	 *
	 * Note: You can assume that one of the cells of delta will be 0.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return If both of the cells of delta are 0, return 0. If the call to doMove
	 *         returns a value less than 1, return that value. Otherwise, return 1.
	 */
	public static int processMove(char[][] board, int[] pos, int[] delta) {
		if (delta[0] == 0 && delta[1] == 0) { // no move to be processed
			return 0;
		}

		if (delta[0] != 0) { // move up or down
			if (delta[0] < 0) { // if negative its moving up
				int[] changeDelta = { -1, 0 };
				for (int i = delta[0]; i < 0; ++i) {
					int returnMove = doMove(board, pos, changeDelta);
					if (returnMove < 1) {
						return returnMove;
					}
				}
			} else if (delta[0] > 0) { // if positive its moving down
				int[] changeDelta = { 1, 0 };
				for (int i = 1; i <= delta[0]; ++i) {
					int returnMove = doMove(board, pos, changeDelta);
					if (returnMove < 1) {
						return returnMove;
					}
				}
			}
		}
		if (delta[1] != 0) { // move left or right
			if (delta[1] < 0) { // if negative its moving left
				int[] changeDelta = { 0, -1 };
				for (int i = delta[1]; i < 0; ++i) {
					int returnMove = doMove(board, pos, changeDelta);
					if (returnMove < 1) {
						return returnMove;
					}
				}
			} else if (delta[1] > 0) { // if pos its moving right
				int[] changeDelta = { 0, 1 };
				for (int i = 1; i <= delta[1]; ++i) {
					int returnMove = doMove(board, pos, changeDelta);
					if (returnMove < 1) {
						return returnMove;
					}
				}
			}
		}
		return 1;
	}

	/**
	 * Moves the worker on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid. Recall
	 * that there are 2 characters that can represent the worker.
	 *
	 * Step 2: If checkDelta returns -5, use your shiftBox method to move the box by
	 * delta before moving the worker.
	 *
	 * Step 3: Use your togglePos method to correctly change the character at the
	 * new position to the appropriate worker character.
	 *
	 * ---Step 4: Again use your togglePos method to correctly change the character
	 * at pos to the the appropriate character without a worker.
	 *
	 * ---Step 5: Update the position of the worker in pos.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return If checkDelta returns a value less than 1 that is not -5, return that
	 *         value. If checkDelta returns -5 and shiftBox returns a value less
	 *         than 0, return 0. Otherwise, return 1.
	 */
	public static int doMove(char[][] board, int[] pos, int[] delta) {
		char[] valid = new char[] { Config.WORKER_CHAR, 
				Config.WORK_GOAL_CHAR, };
		// there are 2 valid char that represent a worker

		int result = checkDelta(board, pos, delta, valid);
		int shift = 1;

		if (result < 1 && result != -5) {
			// prospective pos is valid and not a box
			return result;
		}

		if (result == -5) { // prospective pos is a box
			shift = shiftBox(board, new int[] 
					{ pos[0] + delta[0], pos[1] + delta[1] }, delta);
			// move the box by delta before moving the worker.
			if (shift < 0) {
				return 0;
			}
		}

		int[] newPos = new int[] { pos[0] + delta[0], pos[1] + delta[1] };

		togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, 
				Config.WORKER_CHAR);
		// Use your togglePos method to correctly change the character at the
		// new position to the appropriate worker character.

		togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, 
				Config.EMPTY_CHAR);
		// Again use your togglePos method to correctly change the character
		// at pos to the the appropriate character without a worker.

		pos[0] = (pos[0] + delta[0]); // updates pos
		pos[1] = (pos[1] + delta[1]); // updates pos

		return 1;
	}

	/**
	 * Checks all the cells in board and ensures that there are no goals that are
	 * not covered by boxes.
	 *
	 * @param board The current board.
	 * @return true if all the goals are covered by boxes. Otherwise, false.
	 */
	public static boolean checkWin(char[][] board) {
		boolean winner = false;
		int flag = 0;
		for (int i = 0; i < board.length; i++) // loops through board
		{
			for (int j = 0; j < board[i].length; j++)
			// loops through indexes of board
			{
				if (board[i][j] == Config.GOAL_CHAR 
						|| board[i][j] == Config.WORK_GOAL_CHAR)
					// check if its a goal
				{
					winner = false; // it goal doesn't have box, test fails
					return winner;
				}
			}
		}

		winner = true; // if all goals have box then they have won the game
		return winner;
	}

	/**
	 * This is the main method for the Sokoban game. It consists of the main game
	 * and play again loops with calls to the various supporting methods. The
	 * details of the main method for each milestone can be found in the BP1 -
	 * Sokoban write-up on the CS 200 webpage:
	 * https://cs200-www.cs.wisc.edu/wp/programs/
	 *
	 * For all milestones, you will need to create a Scanner object to read from
	 * System.in that you will pass to the helper methods.
	 *
	 * For milestone 3, you will need to create a Random object using Config.SEED as
	 * the seed. This object should be create at the beginning of the method,
	 * outside of any loops.
	 *
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		/*
		 * Milestone 1, an algorithm for the main method is: Print out “Welcome to
		 * Sokoban!” Begin the play again loop ● Call promptInt for the “Choose a level
		 * between 0 and maxLvl: ”, where maxLvl is the maximum index in Config.LEVELS.
		 * The minimum value should be 0 and the maximum value should be maxLvl. ● Check
		 * the level selected by the user using your checkLevel method. ● If the level
		 * is valid, print out “Sokoban Level lvl”, where lvl is the level selected by
		 * the user. ● Prompt the user with “Play again? (y/n) “, using the promptChar
		 * method. Exit the loop if the value of promptChar is not a ‘y’ Print out
		 * “Thanks for playing!”
		 */
		System.out.println("Welcome to Sokoban!"); // starts game
		int[] pos = new int[2];

		int[] deltaRes = new int[2]; // calcDelta result
		int processRes; // processMove() result
		int counter = 1; // counts # of moves to win

		String prompt = ": ";

		Scanner scnr = new Scanner(System.in);

		int maxLvl = Config.LEVELS.length - 1;
		// the maximum index in Config.LEVELS

		String userLevel = "";
		String returnedInput = "p";

		char promptcharresult = 'y'; // sets char to yes
		int result = 0;
		boolean flag = false;
		Random randnum = new Random(); // random level if they enter -1
		randnum.setSeed(Config.SEED); // sets seed so tests do the same thing

		int randLvl = 0;
		do { // begin play again loop
			do {
				System.out.print("Choose a level between 0 and " + 
						maxLvl + ": ");
				// prompts user

				if (scnr.hasNextLine()) {// makes sure user entered something
					userLevel = scnr.nextLine(); // gets level
					if (userLevel.length() == 0) {
						System.out.println("Invalid value.");
						continue;
					}
				} else {
					userLevel = "-2";
				}

				if (userLevel.charAt(0) == '-' && userLevel.charAt(1) == '1') {
					// checks if user wants a random level
					userLevel = "" + randnum.nextInt(maxLvl + 1);
				}

				result = promptInt(scnr, userLevel, 0, maxLvl);
				// calls promptInt

				if (result != 1) { // checks if level entered is valid
					System.out.println("Invalid value.");
				} else {

					Scanner scnr2 = new Scanner(userLevel);
					int level = scnr2.nextInt();
					char[][] board = initBoard(level, Config.LEVELS, 
							Config.GOALS, pos);
					System.out.println("Sokoban Level " + userLevel);
					while (checkWin(board) == false) {
						printBoard(board);

						returnedInput = promptString(scnr, prompt);

						if (returnedInput.length() == 0) {
							returnedInput = "p";
							continue;
						}
						if (returnedInput.charAt(0) == Config.QUIT_CHAR) {
							counter = 1;
							break;
						}

						if (returnedInput.length() > 0
							&& Character.isDigit(returnedInput.charAt(0))) {

							deltaRes = calcDelta(returnedInput);
							if (deltaRes[0] != 0 && deltaRes[1] != 0) {
								continue;
							} else {
								processRes = processMove(board, pos, deltaRes);
								if (processRes == 1) {
									counter++;
								}
							}
						}

					}
					if (checkWin(board)) {
						System.out.println("Congratulations! You won in " + 
								counter + " moves!");
						printBoard(board);
						counter = 1;
					}

					returnedInput = "p";
					if (flag) {
						break;
					}
				}
			} while (result != 1); // repeat while level is invalid
			calcDelta(returnedInput);

			String prompt2 = ("Play again? (y/n) ");
			// prompts user to play again

			promptcharresult = promptChar(scnr, prompt2);
			// checks if input is valid

		} while (promptcharresult == 'y');
		// repeats while user wants to keep playing

		System.out.println("Thanks for playing!"); // ends game
	}
}
