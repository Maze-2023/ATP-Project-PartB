package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {

    OutputStream out;
    public SimpleCompressorOutputStream(OutputStream out){
        this.out = out;

    }
    @Override
    public void write(int b) throws IOException {
    }

    @Override
    public void write(byte[] b) throws IOException {
        byte count = 0;
        byte bit = 0;
        for (int i=0; i<6; i++) {
            out.write(b[i]);
        }
        for (int i=6; i<b.length; i++) {
            byte currentByte = b[i];
            if(count == (byte) 0xFF){
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
