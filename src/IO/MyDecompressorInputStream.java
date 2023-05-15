package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class MyDecompressorInputStream extends InputStream {

    InputStream in;

    public MyDecompressorInputStream(InputStream in){
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] decompressedData = in.readAllBytes();
        Inflater inflater = new Inflater();
        inflater.setInput(decompressedData);
        int decompressedDataLength = b.length;

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(decompressedData);
                System.arraycopy(decompressedData, 0, b, decompressedDataLength, count);
                decompressedDataLength += count;
            }
        } catch (DataFormatException e) {
            // handle the exception
        } finally {
            inflater.end();
        }
        return 0;
    }
}
