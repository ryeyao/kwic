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
 *  Name:    Input.java
 * 
 *  Purpose: Input reads and parses the KWIC input file
 * 
 *  Created: 20 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Input reads and parses the KWIC input file
 * </file>
*/

package kwic.oo;

/*
 * $Log$
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

/**
 *  An object of the Input class is responsible for reading and parsing the content of 
 *  a KWIC input file. The format of the KWIC input file is as follows:
 *  <ul>
 *  <li>Lines are separated by the line separator character(s) (on Unix '\n', on Windows '\r\n')
 *  <li>Each line consists of a number of words. Words are delimited by any number and combination
 *  of the space chracter (' ') and the horizontal tabulation chracter ('\t').
 *  </ul>
 *  The entered data is parsed and stored in memory as an instance of the LineStorage class. The data
 *  is parsed in the following way:
 *  <ul>
 *  <li>All line separators are removed from the data; for each new line in the file a new line
 *  is added to the LineStorage instance
 *  <li>All horizontal tabulation word delimiters are removed
 *  <li>All space character word delimiters are removed
 *  <li>From characters between any two word delimiters a new string is created; the new string
 *  is added to the LineStorage instance.
 *  </ul>
 *  @author  dhelic
 *  @version $Id$
*/

public class Input{

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
 * This method reads and parses a KWIC input file. If an I/O exception occurs
 * during the execution of this method, an error message is shown and program
 * exits.
 * @param file name of KWIC input file
 * @param line_storage holds the parsed data
 * @return void
 */

  public void parse(String file, LineStorage line_storage){
    try{
      
          // open the file for reading
//      BufferedReader reader = new BufferedReader(new FileReader(file));
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

          // read all lines until EOF occurs
          // (Note that all line feed chracters are removed by the readLine() method)
      String line = reader.readLine();
//      while(line != null){
        
            // parse the line
            // the default delimiter set for StringTokenizer
            // is the set " \t\n\r\f" of characters
            // (Note that the delimiter characters are not
            // themselves treated as tokens)
        StringTokenizer tokenizer = new StringTokenizer(line);
        
            // if this is not an empty line add a new empty line
            // to the line storage
        if(tokenizer.countTokens() > 0)
          line_storage.addEmptyLine();

            // add all words from this line to the last line
        while(tokenizer.hasMoreTokens())
          line_storage.addWord(tokenizer.nextToken(), line_storage.getLineCount() - 1);
        
            // read next line
//        line = reader.readLine();
//      }
      
    }catch(FileNotFoundException exc){

          // handle the exception if the file could not be found
      exc.printStackTrace();
      System.err.println("KWIC Error: Could not open " + file + "file.");
      System.exit(1);

    }catch(IOException exc){
      
          // handle other system I/O exception
      exc.printStackTrace();
      System.err.println("KWIC Error: Could not read " + file + "file.");
      System.exit(1);
      
    }
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
