/**
 * 
 */
package kwic.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Rye
 * 
 */
public class WordsIndex implements Observer {

	private Map wordsIndex_;

	public WordsIndex(Map wordsIndex) {
		this.wordsIndex_ = wordsIndex;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		LineStorageWrapper lines = (LineStorageWrapper) arg0;
		LineStorageChangeEvent event = (LineStorageChangeEvent) arg1;
		
		switch (event.getType()) {

		// if this is an ADD change make words indices
		// of the new line and add them to words list
		case LineStorageChangeEvent.ADD:

			// get the last added line
			String[] words = lines.getLine(lines.getLineCount() - 1);
			// iterate through all words of the line
			// and make words indices
		
			for (String word : words) {
				String count_str = (String)wordsIndex_.get(word);
				if (count_str != null) {
					int count = Integer.parseInt(count_str);
					count ++;
					wordsIndex_.put(word, String.valueOf(count));
				} else {
					wordsIndex_.put(word, "1");
				}
			}
			break;
		case LineStorageChangeEvent.DELETE:
				String[] lineToDel = LineStorage.toWordsArray(event.getArg());
				for (String word : lineToDel) {
					String count_str = (String)wordsIndex_.get(word);
					if (count_str != null) {
						int count = Integer.parseInt(count_str);
						count --;
						if (count == 0) {
							wordsIndex_.remove(word);
						} else {
							wordsIndex_.put(word, String.valueOf(count));
						}
					}
				}
			break;
		default:
			break;
		}

	}
	
	public Map getWordsIndex() {
		return this.wordsIndex_;
	}

}
