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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Rye
 * 
 */
public class ShiftFilter extends Filter {

	private FileInputStream file = null;
	private ArrayList<String> noiseWords;
	
	public ShiftFilter(Pipe input, Pipe output, FileInputStream file) {
		super(input, output);
		this.file = file;
		this.noiseWords = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void transform() {
		// TODO Auto-generated method stub
		this.readNoiseWords();
		try {
			CharArrayWriter writer = new CharArrayWriter();
//			HashMap<String, String> lines = new HashMap<String, String>();
			Map<String, String> lines = new IdentityHashMap<String, String>();
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
//					System.out.println("line2 : " + line);
				}

				c = input_.read();
			}
			// Remove lines who's fist word is in the noise words.
			lines = this.trimLine(lines);
			
			// Write lines to output
			for (String line : lines.keySet()) {
				char[] lineChars = (line +lines.get(line)).toCharArray();
				for (int i = 0; i < lineChars.length; i++) {
					output_.write((int)lineChars[i]);
				}
			}
			
			writer.close();
//			output_.write('\n');
			output_.closeWriter();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void readNoiseWords () {
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(this.file, "UTF-8"));
			String word = in.readLine();
			while (word != null) {
//				System.out.println("Noise word read: " + word);
				this.noiseWords.add(word);
				word = in.readLine();
			}
			in.close();
			this.file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
		}
	}
	
	private Map<String, String> trimLine (Map<String, String> lines) {
		for (String word : this.noiseWords) {
			while (lines.containsKey(word)) {
				lines.remove(word);
			}
		}
		return lines;
	}

}
