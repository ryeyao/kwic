/**
 * 
 */
package kwic.oo;

import java.util.ArrayList;

/**
 * @author Rye
 *
 */
public class Line extends ArrayList {
	private ArrayList<String> words_ = this;
	
	public void setChar(char c, int position, int word) {
		char[] curr_word = words_.get(word).toCharArray();
		curr_word[position] = c;
		words_.set(word, String.valueOf(curr_word));
	}
	
	public char getChar(int position, int word) {
		char[] curr_word = words_.get(word).toCharArray();
		return curr_word[position];
	}
	
	public void addChar(char c, int word) {
		char[] curr_word = words_.get(word).toCharArray();
		char[] new_curr_word = new char[curr_word.length + 1];
		System.arraycopy(curr_word, 0, new_curr_word, 0, curr_word.length);
		new_curr_word[curr_word.length] = c;
		words_.set(word, String.valueOf(new_curr_word));
	}
	
	public void deleteChar(int position, int word) {
		char[] curr_word = words_.get(word).toCharArray();
		char[] new_curr_word = new char[curr_word.length - 1];
		System.arraycopy(curr_word, 0, new_curr_word, 0, position);
		System.arraycopy(curr_word, position + 1, new_curr_word, position, curr_word.length - position - 1);
		words_.set(word, String.valueOf(new_curr_word));
	}
	
	public int getCharCount(int word) {
		String curr_word = words_.get(word);
		return curr_word.length();
	}
	
	public void setWord(char[] w, int word) {
		words_.set(word, String.valueOf(w));
	}
	
	public void setWord(String w, int word) {
		words_.set(word, w);
	}
	
	public String getWord(int word) {
		return words_.get(word);
	}
	
	public void addWord(char[] w) {
		words_.add(String.valueOf(w));
	}
	
	public void addWord(String w) {
		words_.add(w);
	}
	
	public void addEmptyWord() {
		words_.add(new String());
	}
	
	public void deleteWord(int word) {
		words_.remove(word);
	}
	
	public int getWordCount() {
		return words_.size();
	}
}
