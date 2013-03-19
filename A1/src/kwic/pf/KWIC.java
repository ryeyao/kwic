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

  public void execute(String file, String file2){
    try{
      
          // pipes
      Pipe in_lt = new Pipe();
      Pipe lt_cs = new Pipe();
      Pipe cs_sf = new Pipe();
      Pipe sf_al = new Pipe();
      Pipe al_lt = new Pipe();
      Pipe lt_ou = new Pipe();
      
          // input file
      FileInputStream in = new FileInputStream(file);
      FileInputStream inNoise = new FileInputStream(file2);
          // filters connected into a pipeline
      Input input = new Input(in, in_lt);
      LineTransformer lt = new LineTransformer(in_lt, lt_cs);
      CircularShifter shifter = new CircularShifter(lt_cs, cs_sf);
      ShiftFilter sf = new ShiftFilter(cs_sf, sf_al, inNoise);
      Alphabetizer alpha = new Alphabetizer(sf_al, al_lt);
      LineTransformer lt2 = new LineTransformer(al_lt, lt_ou);
      Output output = new Output(lt_ou);
      
      
          // run it
      input.start();
      sf.start();
      shifter.start();
      lt.start();
      alpha.start();
      lt2.start();
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
    if(args.length != 2){
      System.err.println("KWIC Usage: java kwic.ms.KWIC file_name");
      System.exit(1);
    }

    KWIC kwic = new KWIC();
    kwic.execute(args[0], args[1]);
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
