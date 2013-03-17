/**
 * 
 */
package kwic.pf;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rye
 * 
 */
public class ShiftFilter extends Filter {

	private String filePath = null;
	private ArrayList<String> noiseWords;
	
	public ShiftFilter(Pipe input, Pipe output, String filePath) {
		super(input, output);
		this.filePath = filePath;
		this.noiseWords = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void transform() {
		// TODO Auto-generated method stub
		this.readNoiseWords();
		try {
			CharArrayWriter writer = new CharArrayWriter();
			HashMap<String, String> lines = new HashMap<String, String>();
			int c;
			c = input_.read();
			while (c != -1) {
				writer.write(c);
				// add line
				if (((char) c) == '\n') {
					String line = writer.toString();
					if (line.split(" ").length == 1) {
						lines.put(line, "");
					}
					else {
						lines.put(line.split(" ")[0], line.substring(line.indexOf(" ")));
					}
					writer.reset();
				}

				c = input_.read();
			}
			// Remove lines who's fist word is in the noise words.
			lines = this.trimLine(lines);
			
			// Write lines to output
			for (String line : lines.keySet()) {
				char[] lineChars = (line + " " +lines.get(line)).toCharArray();
				for (int i = 0; i < lineChars.length; i++) {
					output_.write(i);
				}
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void readNoiseWords () {
		
		try {
			FileInputStream fis;
			fis = new FileInputStream(this.filePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis,
					"UTF-8"));
			String word = in.readLine();
			while (word != null) {
				word = in.readLine();
				this.noiseWords.add(word);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private HashMap<String, String> trimLine (HashMap<String, String> lines) {
		for (String word : this.noiseWords) {
			lines.remove(word);
		}
		return lines;
	}

}
