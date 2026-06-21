import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class FlashDataDecompressor {
	public static byte[] readFileWithStream(String filePath) throws IOException {
		try (FileInputStream fis = new FileInputStream(filePath);
				ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[8192];
			int len;
			while ((len = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		}
	}

	public static byte[] decompress(byte[] compressedData, boolean isRawDeflate) throws DataFormatException {
		// IsRawDeflate is true, indicating that the data is in raw DEFLATE format
		// (without zlib header)
		Inflater inflater = new Inflater(isRawDeflate);
		inflater.setInput(compressedData);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedData.length);
		byte[] buffer = new byte[1024];

		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		inflater.end();
		return outputStream.toByteArray();
	}

	public static void main(String[] args) {
		// Assuming that compressiondData is compressed data received from Flash
		// byte[] compressedData = ...;
		byte[] compressedData = null;
		try {
			compressedData = readFileWithStream("0.txt");
			// Try decompression in standard zlib format first
			byte[] result = decompress(compressedData, false);
			System.out.println("Decompression successful (zlib format)"); // run here
			System.out.println(new String(result, "GBK"));
		} catch (DataFormatException e) {
			System.out.println("Standard zlib decompression failed, attempting original DEFLATE format ..");
			try {
				// If it fails, try decompression in the original DEFLATE format again
				byte[] result = decompress(compressedData, true);
				System.out.println("Decompression successful (original DEFLATE format)");
				System.out.println(new String(result, "GBK"));
			} catch (DataFormatException ex) {
				System.err.println(
						"Decompression failed, the data may be corrupted or an unsupported format may have been used.");
				ex.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
