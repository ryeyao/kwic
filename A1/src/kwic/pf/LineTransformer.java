/**
 * 
 */
package kwic.pf;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rye
 * 
 */
public class LineTransformer extends Filter {

	public LineTransformer(Pipe input, Pipe output) {
		super(input, output);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void transform() {
		// TODO Auto-generated method stub
		try {
			CharArrayWriter writer = new CharArrayWriter();
			ArrayList<String> lines = new ArrayList<String>();
			int c;
			c = input_.read();
			while (c != -1) {
				writer.write(c);
				// add line
				if (((char) c) == '\n') {
					String line = writer.toString();
					if (line.split(" ").length == 1) {
						lines.add(line.toUpperCase());
					} else {
//						lines.add(line.split(" ")[0].toUpperCase(),
//								line.substring(line.indexOf(" ") + 1));
						lines.add(line.split(" ")[0].toUpperCase() + line.substring(line.indexOf(" ")));
					}
					writer.reset();
//					System.out.println("line : " + line);
				}

				c = input_.read();
			}
			// Write lines to output
			for (String line : lines) {
				char[] lineChars = line.toCharArray();
				for (int i = 0; i < lineChars.length; i++) {
					output_.write((int)lineChars[i]);
				}
			}
			writer.close();
			output_.closeWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
