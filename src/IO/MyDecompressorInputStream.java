package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    /**
     * Constructs a new MyDecompressorInputStream with the specified input stream.
     * @param in the input stream containing the compressed data
     */
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * @throws IOException always thrown as this method is not supported
     */
    @Override
    public int read() throws IOException {
        throw new IOException("read() method is not supported in MyDecompressorInputStream");
    }

    /**
     * Reads some number of bytes from the input stream and stores them into the buffer array b.
     * Decompresses the data read from the input stream using Base64 decoding and Inflater decompression.
     * @param b the buffer into which the data is read
     * @return the total number of bytes read into the buffer
     * @throws IOException if an I/O error occurs
     */
    @Override
    public int read(byte[] b) throws IOException {
        // read all the bytes from the input stream
        byte[] compressedData = in.readAllBytes();

        // decode the Base64 encoded data
        byte[] decodedData = Base64.getDecoder().decode(compressedData);

        // decompress the decoded data
        Inflater inflater = new Inflater();
        inflater.setInput(decodedData);
        try {
            inflater.inflate(b);
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        }
        inflater.end();
        return b.length;
    }
}
