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
 *  Name:    Pipe.java
 * 
 *  Purpose: Pipe connects two data streams and transmits the data 
 *  from the first stream to the second stream.
 * 
 *  Created: 20 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Pipe connects two data streams and transmits the data 
 *  from the first stream to the second stream.
 * </file>
*/

package kwic.pf;

/*
 * $Log$
*/

import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.IOException;

/**
 *  A pipe object connects two data streams allowing data to be transmitted between
 *  them. Thus, whenever a client writes some data to the first stream,  this data
 *  become then available for reading in the second stream.
 *  @author  dhelic
 *  @version $Id$
*/

public class Pipe{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

/**
 * Pipe reader
 *
 */

  private PipedReader reader_;

/**
 * Pipe writer
 *
 */ 

  private PipedWriter writer_;

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Default constructor. It establishes the connection between the character streams.
 * @exception IOException thrown if the pipe cannot connect the streams
 */

  public Pipe() throws IOException{
    writer_ = new PipedWriter();
    reader_ = new PipedReader();
    writer_.connect(reader_);
  }

//----------------------------------------------------------------------
/**
 * Methods
 *Input 
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * This method writes a character to the pipe.
 * @param c character to write
 * @exception IOException thrown if we cannot write to the pipe
 * @return void
 */

  public void write(int c) throws IOException{
    writer_.write(c);
  }

//----------------------------------------------------------------------
/**
 * This method reads a character from the pipe.
 * @exception IOException thrown if we cannot read from the pipe
 * @return char next chracter in the stream
 */

  public int read() throws IOException{
    return reader_.read();
  }

//----------------------------------------------------------------------
/**
 * Closes the writer of this pipe. After calling this method no data
 * can be written to the pipe.
 * @return void
 * @exception IOException thrown if we cannot close the writer
 */

  public void closeWriter() throws IOException{
    writer_.flush();
    writer_.close();
  }

//----------------------------------------------------------------------
/**
 * Closes the reader of this pipe. After calling this method no data
 * can be read from the pipe.
 * @return void
 * @exception IOException thrown if we cannot close the reader
 */

  public void closeReader() throws IOException{
    reader_.close();
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
