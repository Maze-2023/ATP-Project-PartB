package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;
    public MyCompressorOutputStream(OutputStream out){
        this.out = out;

    }
    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        // we need something smarter
        out.write(b[0]); // row
        out.write(b[1]); // col
        byte count = 0;
        byte bit = 0;
        for (int i=2; i<b.length; i++) {
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

