//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Sokoban
// Files:           a list of all source files used by that program
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
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class MyLevels 
{

	//Using your LEVELS and GOALS constants, create 3 new valid Sokoban (pass the checkLevel checks) 
	//levels that are (in order) at least 3 by 3 with 1 goal, 6 by 6 with 2 goals and 9 by 9 with
	//3 goals. Since the arrays stored in LEVELS can be ragged, the minimum number of columns
	//means that at least one row should contain that number of columns. At the minimum, the 9 by 9
	//should be a ragged array
	
	//public static int checkLevel(int lvl, char[][][] levels, int[][] goals)
		
	public static final char EMPTY_CHAR = ' '; // Empty character
    public static final char BOX_CHAR = '='; // Box character
    public static final char WALL_CHAR   = '#'; // Wall character
    public static final char WORKER_CHAR  = '@'; // Worker character
    public static final char GOAL_CHAR  = '.'; // Goal character
    public static final char BOX_GOAL_CHAR  = '*'; // Box on a goal character
    public static final char WORK_GOAL_CHAR  = '+'; // Worker on a goal character

    
    
    //In the MyLevels class, define a LEVELS and a GOALS constant similar to Config.java. To
    //populate the LEVELS array, you will use the constants defined in Config.java.
    
	public static final char[][][] LEVELS = {
			 {
				//{'#', '#', '#', '#', '#'},
		        //{'#', ' ', ' ', ' ', '#'},
		        //{'#', ' ', '=', ' ', '#'},
		        //{'#', ' ', ' ', '@', '#'},
		        //{'#', '#', '#', '#', '#'}
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR}
				 
			 },
			 {
				//{'#', '#', '#', '#', '#', '#', '#', '#',},
			    //{'#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			    //{'#', ' ', '=', ' ', ' ', '=', ' ', '#'},
			    //{'#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
				//{'#', ' ', ' ', ' ', '@', ' ', ' ', '#'},
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			    //{'#', '#', '#', '#', '#', '#', '#', '#'}
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},       
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
		         {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR}
			 },
			 {
				//{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, 
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'}, 
				//{'#', ' ', '=', ' ', ' ', '=', ' ', ' ', ' ', ' ', '#'},
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'}, 
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'}, 
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'}, 
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'}, 
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'}, 
				//{'#', ' ', '=', ' ', '@', ' ', ' ', ' ', ' ', ' ', '#'},
				//{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'}, 
				//{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}  
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				 {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR}
			 }
			 
	 
	 };


	 //Position of the goals for each level. Every pair of values represents the row and column of 
	 //a goal. This is a parallel array to LEVELS.
	 public static final int[][] GOALS = { {1,1}, {3, 3, 3, 6}, {5, 1, 4, 4, 6, 8}};
	 //goals are coordinates of goals
	
}
