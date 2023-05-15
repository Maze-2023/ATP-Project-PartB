package IO;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.Deflater;
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


    /**
     * Deflater Object -
     * The Deflate algorithm is a combination of two other compression algorithms,
     * namely Huffman coding and LZ77 (Lempel-Ziv 1977) algorithm.
     * The Huffman coding is used to compress the frequently occurring data
     * while LZ77 is used to compress the repeating data in the input.
     */
    @Override
    public void write(byte[] b) throws IOException {
        // compress the input array
        Deflater deflater = new Deflater();
        deflater.setInput(b);

        byte[] compressedData = new byte[b.length];

        deflater.finish();
        int compressedDataLength = deflater.deflate(compressedData);

        // base64 encode the compressed data
        byte[] base64Data = Base64.getEncoder().encode(Arrays.copyOfRange(compressedData, 0, compressedDataLength));

        // write to out
        out.write(base64Data);

    }
}

