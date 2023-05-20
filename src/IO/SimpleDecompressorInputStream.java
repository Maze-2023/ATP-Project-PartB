package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    /**
     * Constructs a new SimpleDecompressorInputStream with the specified input stream.
     * @param in the input stream containing the compressed data
     */
    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * @throws IOException always thrown as this method is not supported
     */
    @Override
    public int read() throws IOException {
        throw new IOException("read() method is not supported in SimpleDecompressorInputStream");
    }

    /**
     * Reads some number of bytes from the input stream and stores them into the buffer array b.
     * Decompresses the data read from the input stream using a simple decompression algorithm.
     * The compressed data consists of sequences of consecutive bytes and their counts.
     * Each sequence starts with a count byte, followed by a byte representing the repeated bit.
     * @param b the buffer into which the data is read
     * @return the total number of bytes read into the buffer
     * @throws IOException if an I/O error occurs
     */
    @Override
    public int read(byte[] b) throws IOException {
        byte[] inputBytes = in.readAllBytes();
        b[0] = inputBytes[0];
        b[1] = inputBytes[1];
        b[2] = inputBytes[2];
        b[3] = inputBytes[3];
        b[4] = inputBytes[4];
        b[5] = inputBytes[5];
        int index = 6;
        byte currentByte = 1;
        for (int i = 6; i < inputBytes.length; i++) {
            byte amount = inputBytes[i];
            int counter = 0;
            currentByte = (byte) ((currentByte + 1) % 2);
            while (counter < amount) {
                b[index++] = currentByte;
                counter++;
            }
        }
        return b.length;
    }
}
