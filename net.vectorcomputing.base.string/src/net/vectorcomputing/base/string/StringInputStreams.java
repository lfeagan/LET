/*******************************************************************************
 * Copyright 2011 Lance Feagan
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.vectorcomputing.base.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class StringInputStreams {

	private static final int DEFAULT_BUFFER_SIZE = 1<<14;
	
	private static final String DEFAULT_FILE_ENCODING = System.getProperty("file.encoding"); //$NON-NLS-1$

	/**
	 * Reads content from an input stream until there are no more bytes to be
	 * read. Returns the content read as a string.
	 * 
	 * @param input
	 *            the input stream to read from
	 * @return the data read from the input stream as a string
	 * @throws IOException
	 */
	public static final String toString(InputStream input) throws IOException {
		return toString(input, DEFAULT_FILE_ENCODING, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Reads content from an input stream until there are no more bytes to be
	 * read and returns the content read as a string
	 * 
	 * @param input
	 *            the input stream to read from
	 * @param fileEncoding
	 *            the file encoding to use when converting the bytes in the
	 *            input stream to characters
	 * @return the data read from the input stream as a string
	 * @throws IOException
	 */
	public static final String toString(InputStream input, String fileEncoding, int bufferSize) throws IOException {
		final char[] buffer = new char[bufferSize];
		final InputStreamReader isr = new InputStreamReader(input, fileEncoding);
		final BufferedReader br = new BufferedReader(isr, bufferSize);

		final StringWriter sw = new StringWriter();
		final BufferedWriter bw = new BufferedWriter(sw, bufferSize);

		try {			
			int bytesRead;
			while ((bytesRead = br.read(buffer)) != -1) {
				bw.write(buffer, 0, bytesRead);
			}
			return bw.toString();
		} finally {
			br.close();
			isr.close();
			input.close();
			
			sw.flush();
			bw.flush();
			bw.close();
			sw.close();
		}
	}

	/**
	 * Converts a string to a byte representation using the default file
	 * encoding and returns an input stream that will read this encoded form.
	 * 
	 * @param str
	 *            the string to be encoded
	 * @return an input stream that contains the encoded string
	 */
	public static final InputStream toInputStream(String str) {
		if (DEFAULT_FILE_ENCODING != null) {
			return toInputStream(str, DEFAULT_FILE_ENCODING);
		} else {
			throw new RuntimeException(Messages.getString("StringInputStreams_NoDefaultFileEncoding")); //$NON-NLS-1$
		}
	}
	
	/**
	 * Converts a string to a byte representation using the specified file
	 * encoding and returns an input stream that will read this encoded form.
	 * 
	 * @param str
	 *            the string to encoding
	 * @param fileEncoding
	 *            the encoding to use when converting the string to its byte
	 *            representation
	 * @return an input stream that contains the encoded string
	 */
	public static final InputStream toInputStream(String str, String fileEncoding) {
		ByteBuffer buffer = Charset.forName(fileEncoding).encode(str);
		InputStream is = new ByteArrayInputStream(buffer.array(), 0, buffer.limit());
		return is;
	}
	
}
