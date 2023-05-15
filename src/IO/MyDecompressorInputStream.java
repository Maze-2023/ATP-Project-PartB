package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
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
        // read all the bytes from the input stream
        byte[] compressedData = in.readAllBytes();

        // decode the base64 encoded data
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
        return 0;
    }
}
