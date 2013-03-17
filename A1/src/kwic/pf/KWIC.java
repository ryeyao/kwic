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
 *  Created: 20 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    The Master Control class
 * </file>
*/

package kwic.pf;

/*
 * $Log$
*/

import java.io.FileInputStream;
import java.io.IOException;

/**
 *  An object of the KWIC class creates the linear sequence (pipeline) of filters
 *  connected with pipes. To achieve the desired functionality of the KWIC index system
 *  the following pipeline is created:
 *  <ul>
 *  <li>The first filter is the Input filter. It reads the data from the file, parses it
 *  and outputs it to the in_cs pipe.
 *  <li>The in_cs pipe is the input pipe for the next filter in the pipeline: CircularShifter
 *  filter. CircularShifter reads the data from the in_cs pipe, process it by making circular shifts
 *  of lines in that pipe. The output is written to the cs_al pipe.
 *  <li>The cs_al pipe is the input pipe for the next filter in the pipeline: Alphabetizer
 *  filter. Alphabetizer reads the data from the cs_al pipe, process it by sorting circular shifts
 *  from that pipe. The output is written to the al_ou pipe.
 *  <li>The al_ou pipe is the input pipe for the next filter in the pipeline: Output
 *  filter. Output reads the data from the al_ou pipe and writes it to the standard output.
 *  </ul>
 *  Thus, the KWIC object manages the following pipe mechanism:
 *  <p>
 *  input file > Input > CircularShifter > Alphabetizer > Output > standard output
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
 */

  public void execute(String file){
    try{
      
          // pipes
      Pipe in_cs = new Pipe();
      Pipe cs_al = new Pipe();
      Pipe al_ou = new Pipe();
      
          // input file
      FileInputStream in = new FileInputStream(file);

          // filters connected into a pipeline
      Input input = new Input(in, in_cs);
      CircularShifter shifter = new CircularShifter(in_cs, cs_al);
      Alphabetizer alpha = new Alphabetizer(cs_al, al_ou);
      Output output = new Output(al_ou);
      
          // run it
      input.start();
      shifter.start();
      alpha.start();
      output.start();
    }catch(IOException exc){
      exc.printStackTrace();
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
 */

  public static void main(String[] args){
    if(args.length != 1){
      System.err.println("KWIC Usage: java kwic.ms.KWIC file_name");
      System.exit(1);
    }

    KWIC kwic = new KWIC();
    kwic.execute(args[0]);
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
