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
 *  Name:    LineStorageWrapper.java
 * 
 *  Purpose: Wraps LineStorage class providing Observable functionality to it
 * 
 *  Created: 05 Nov 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    LineStorageWrapper class wraps LineStorage class in order to extend
 *  its functionality.
 * </file>
 */

package kwic.es;

/*
 * $Log$
 */

import java.util.ArrayList;
import java.util.Observable;
import java.util.StringTokenizer;

/**
 * LineStorageWrapper class wraps LineStorage class in order to extend its
 * functionality. The added functionality allows objects of the LineStorageClass
 * to be "observed" by other objects in the system. The "observation" mechanism
 * works as follows. Whenever an object of LineStorageWrapper class changes, it
 * generates and sends a message (event) to all other objects, which have
 * registered their interest in receiving such notification messages from the
 * observed object. The LineStorageWrapper class applies the "observation"
 * mechanism from the java.util package. Thus, the class of "observed" objects
 * is a sub-class of java.util.Observable class, whereas the class of "observer"
 * objects implements java.util.Observer interface.
 * 
 * @author dhelic
 * @see kwic.es.LineStorage
 */

public class LineStorageWrapper extends Observable {

	// ----------------------------------------------------------------------
	/**
	 * Fields
	 * 
	 */
	// ----------------------------------------------------------------------

	/**
	 * Wrapped line storage
	 * 
	 */

	private LineStorage lines_ = new LineStorage();

	// ----------------------------------------------------------------------
	/**
	 * Constructors
	 * 
	 */
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	/**
	 * Methods
	 * 
	 */
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	/**
	 * Adds a new line to this line storage wrapper. The line is added at the
	 * end of the storage. After adding the line all observers of this object
	 * are notified about the change.
	 * 
	 * @param words
	 *            new line to be added
	 * @return void
	 * @see #deleteLine
	 * @see #insertLine
	 * @see #getLine
	 */

	public void addLine(String[] words) {

		// add the line
		lines_.addLine(words);

		// create a new change event
		LineStorageChangeEvent event = new LineStorageChangeEvent(
				LineStorageChangeEvent.ADD);

		// set change flag for this object
		// we need to do this, otherwise observers won't be notified
		setChanged();

		// send the event
		notifyObservers(event);
	}

	// ----------------------------------------------------------------------
	/**
	 * Inserts a new line to this line storage wrapper. The line is inserted at
	 * the specified index in the storage. After inserting the line all
	 * observers of this object are notified about the change.
	 * 
	 * @param words
	 *            new line to be added
	 * @param index
	 *            position of the new line
	 * @return void
	 * @see #deleteLine
	 * @see #addLine
	 * @see #getLine
	 */

	public void insertLine(String[] words, int index) {

		// insert the line
		lines_.insertLine(words, index);

		// keep the line so we can create an event object
		String line = lines_.getLineAsString(index);

		// create a new change event
		LineStorageChangeEvent event = new LineStorageChangeEvent(
				LineStorageChangeEvent.INSERT, line);

		// set change flag for this object
		// we need to do this, otherwise observers won't be notified
		setChanged();

		// send the event
		notifyObservers(event);
	}

	// ----------------------------------------------------------------------
	/**
	 * Deletes a line from this line storage wrapper. The deleted line resides
	 * at the specified index in the storage. After deleting the line all
	 * observers of this object are notified about the change.
	 * 
	 * @param index
	 *            position from where to delete the line
	 * @return void
	 * @see #addLine
	 * @see #insertLine
	 * @see #getLine
	 */

	public void deleteLine(int index) {

		// keep the line so we can create an event object
		String line = lines_.getLineAsString(index);

		// delete the line
		lines_.deleteLine(index);

		// create a new change event
		LineStorageChangeEvent event = new LineStorageChangeEvent(
				LineStorageChangeEvent.DELETE, line);

		// set change flag for this object
		// we need to do this, otherwise observers won't be notified
		setChanged();

		// send the event
		notifyObservers(event);
	}

	// ----------------------------------------------------------------------
	/**
	 * Gets the line from the specified index. The line is represented as String
	 * object.
	 * 
	 * @param index
	 *            position from where to get the line
	 * @return String
	 * @see #addLine
	 * @see #insertLine
	 * @see #deleteLine
	 */

	public String getLineAsString(int index) {
		return lines_.getLineAsString(index);
	}

	// ----------------------------------------------------------------------
	/**
	 * Gets the line from the specified index.
	 * 
	 * @param index
	 *            position from where to get the line
	 * @return String[]
	 * @see #addLine
	 * @see #insertLine
	 * @see #deleteLine
	 */

	public String[] getLine(int index) {
		return lines_.getLine(index);
	}

	// ----------------------------------------------------------------------
	/**
	 * Gets the number of lines
	 * 
	 * @return int
	 */

	public int getLineCount() {
		return lines_.getLineCount();
	}
	

	public int findLine(String line) {
		return lines_.findLine(line);
	}
	// ----------------------------------------------------------------------
	/**
	 * Inner classes
	 * 
	 */
	// ----------------------------------------------------------------------

}
