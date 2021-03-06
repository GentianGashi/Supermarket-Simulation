import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
// Accessed: https://stackoverflow.com/questions/1994255/how-to-write-console-output-to-a-txt-file
// Modified By Gentian Gashi
public class OutputPrintStream extends PrintStream {
	private final PrintStream second;

	public OutputPrintStream(OutputStream main, PrintStream second) {
		super(main);
		this.second = second;
	}

	@Override
	public void close() {
	// just for documentation
	super.close();
	}

	@Override
	public void flush() {
	super.flush();
	second.flush();
	}

	@Override
	public void write(byte[] buf, int off, int len) {
		super.write(buf, off, len);
		second.write(buf, off, len);
	}

	@Override
	public void write(int b) {
		super.write(b);
		second.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		super.write(b);
		second.write(b);
	}
}