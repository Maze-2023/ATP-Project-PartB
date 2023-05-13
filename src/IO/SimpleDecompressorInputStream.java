package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;
    public SimpleDecompressorInputStream(InputStream in){
        this.in = in;
    }
    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] b) throws IOException {
        byte[] inputBytes = in.readAllBytes();
        b[0] = inputBytes[0];
        b[1] = inputBytes[1];
        int index = 2;
        byte currentByte = 1;
        for(int i=2; i<inputBytes.length; i++){
            byte amount = inputBytes[i];
            int counter = 0;
            currentByte = (byte) ((currentByte + 1) % 2);
            while(counter < amount){
                b[index++] = currentByte;
                counter++;
            }
        }
        return 0;
    }
}
