package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;

    /**
     * Constructs a new SimpleCompressorOutputStream with the specified output stream.
     * @param out the output stream to write compressed data to
     */
    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * Writes the specified byte as a single byte to the output stream. This method is not supported in this class
     * and always throws an IOException.
     * @param b the byte to be written
     * @throws IOException always thrown as this method is not supported
     */
    @Override
    public void write(int b) throws IOException {
        throw new IOException("write(int b) method is not supported in SimpleCompressorOutputStream");
    }

    /**
     * Writes the specified byte array to the output stream using a simple compression algorithm.
     * The compressed data consists of sequences of consecutive bytes and their counts.
     * Each sequence starts with a count byte, followed by a byte representing the repeated bit.
     * @param b the byte array to be written
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(byte[] b) throws IOException {
        byte count = 0;
        byte bit = 0;
        for (int i = 0; i < 6; i++) {
            out.write(b[i]);
        }
        for (int i = 6; i < b.length; i++) {
            byte currentByte = b[i];
            if (count == (byte) 0xFF) {
                out.write(0);
                count = 0;
            }
            if (currentByte == bit)
                count++;
            else {
                // Output the count and bit to the stream
                out.write(count);
                // Reset the count and bit for the next sequence
                count = 1;
                bit = currentByte;
            }
        }
        // Output the final count and bit to the stream
        out.write(count);
    }
}
