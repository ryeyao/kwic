// -*- Java -*-
/*
 * <copyright>
 * 
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 * 
 * </copyright>
 * 
 * <file>
 * 
 *  Name:    KWIC.java
 * 
 *  Purpose: The Master Control class
 * 
 *  Created: 05 Nov 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    The Master Control class
 * </file>
*/

package kwic.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * $Log$
*/

/**
 *  The KWIC system implemented by means of event-based architecture consists of the
 *  following modules:
 *  <ul>
 *  <li>Master Control module, which is responsible for controling all other modules
 *  in the system
 *  <li>Input module, which is responsible for reading the input file, parsing it
 *  and adding lines to Original Line Storage module
 *  <li>Original Line Storage module, which stores original lines
 *  <li>Circular Shifter module, which produces all circular shifts from original lines
 *  <li>Shifted Line Storage module, which stores circular shifts 
 *  <li>Alphabetizer module, which sorts shifted lines
 *  <li>Output module, which prints out the sorted shifts in a nice format.
 *  </ul>
 *  The system works as follows. Input module reads the input file, parses it
 *  and adds lines to Original Line Storage module. Any change (e.g. a new line 
 *  was added) to Original Line Storage module causes an event (message) to be
 *  sent to all modules which declare interest in tracking these changes. In 
 *  the current system, Circular Shifter module declares such interest. Thus, 
 *  upon receiving a notification event stating that the new line was added to
 *  Original Line Storage module, Circular Shifter module processes the line and
 *  creates circular shifts for that line. The newly created circular shifts are
 *  stored in Shifted Line Storage module. Similarly to changes in Original Line
 *  Storage module, any change (e.g. a new circular shift was added) to Shifted 
 *  Line Storage module causes an event (message) to be sent to all modules which
 *  declare interest in tracking these changes. Obviously, in the current system 
 *  Alphabetizer module declares such interest. Thus, upon receiving a notification
 *  event stating that the new circular shift was added to Shifted Line Storage module, 
 *  Alphabetizer module sorts this circular shift. The sorted shifts are kept in
 *  Shifted Line Storage as well. Once when Input module has finished with parsing
 *  procedure, which implies that all circular shifts have been created and sorted
 *  (because of notification mechanism) Outpur module prints out the sorted shifts.
 *  In the current implementation an object of the KWIC class creates and initializes
 *  all other objects from the event-based KWIC implementation to achieve the desired 
 *  functionality. Thus, KWIC instance creates the following instances of other classes:
 *  <ul>
 *  <li>An instance of LineStorageWrapper class that holds the original lines
 *  <li>An instance of LineStorageWrapper class that holds the circular shifts
 *  <li>An instance of Input class which parses the input file and adds lines
 *  to the line storage for original lines
 *  <li>An instance of CircularShift class which declares its interest in
 *  changes to the line storage for original lines. Thus, whenever it receives a
 *  message that a new line has been added it produces circular shifts for that line
 *  and stores them into the line storage for circular shifts
 *  <li>An instance of Alphabetizer class which declares its interest in
 *  changes to the line storage for circular shifts. Thus, whenever it receives a
 *  message that a new circular shift has been added it sorts the line storage for
 *  circular shifts
 *  <li>An instance of Output class which prints all shifts from the line 
 *  storage for circular shifts.
 *  Further, the KWIC class provides also the main method which checks the command line
 *  arguments.
 *  @author  dhelic
 *  @version $Id$
*/

public class KWIC{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Parses the data, makes shifts and sorts them. At the end prints the
 * sorted shifts.
 * @param file name of the input file
 * @return void
 * @throws IOException 
 */

  public void execute(String file) throws IOException{

        // initialize all variables
    
        // storage for original lines
    LineStorageWrapper lines = new LineStorageWrapper();

        // storage for circular shifts
    LineStorageWrapper shifts = new LineStorageWrapper();

    Map<String, String> wordsIndex = new HashMap<String, String>();
        // input reader
    Input input = new Input();
    
    // New added===========================
    WordsIndex windex = new WordsIndex(wordsIndex);
    lines.addObserver(windex);
    // ====================================

        // circular shifter
    CircularShifter shifter = new CircularShifter(shifts);
        // declare interest in tracking changes
    lines.addObserver(shifter);

        // alphabetizer
    Alphabetizer alphabetizer = new Alphabetizer();
        // declare interest in tracking changes
    shifts.addObserver(alphabetizer);

        // line printer
    Output output = new Output();
    while (true) {
		System.out.println("Add, Delete, Print, Index, Quit: ");
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		switch (Character.toLowerCase(stdin.readLine().charAt(0))) {
		case 'a':
			input.parse(file, lines);
			break;
		case 'd':
			String lineToDel = stdin.readLine();
			int lineIndex = lines.findLine(lineToDel);
			if (lineIndex < 0) {
				System.out.println("No lines are deleted");
				break;
			}
			lines.deleteLine(lineIndex);
		case 'i':
			output.print(windex);
			break;
		case 'p':
			// print sorted shifts
		    output.print(shifts);
			break;
		case 'q':
			return;
		}
	}
        
  }

//----------------------------------------------------------------------
/**
 * Main function checks the command line arguments. The program expects 
 * exactly one command line argument specifying the name of the file 
 * that contains the data. If the program has not been started with 
 * proper command line arguments, main function exits
 * with an error message. Otherwise, a KWIC instance is created and program
 * control is passed to it.
 * @param args command line arguments
 * @return void
 * @throws IOException 
 */

  public static void main(String[] args) throws IOException{
//    if(args.length != 1){
//      System.err.println("KWIC Usage: java kwic.ms.KWIC file_name");
//      System.exit(1);
//    }

    KWIC kwic = new KWIC();
//    kwic.execute(args[0]);
    kwic.execute("");
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
