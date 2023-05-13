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
//        int count = 0;
//        int bit = 0;
//        for (byte currentByte : b) {
//            for (int i = 0; i < 8; i++) {
//                int currentBit = (currentByte >> i) & 1;
//                if (currentBit == bit) {
//                    count++;
//                } else {
//                    // Output the count and bit to the stream
//                    out.write(count);
//                    out.write(bit);
//                    // Reset the count and bit for the next sequence
//                    count = 1;
//                    bit = currentBit;
//                }
//            }
//        }
//        // Output the final count and bit to the stream
//        out.write(count);
//        out.write(bit);

    }
}
