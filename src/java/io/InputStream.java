/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package java.io;

public abstract class InputStream {

public InputStream() {
}

public int available() throws IOException {
	return 0;
}

public void close() throws IOException {
}

public void mark(int readlimit) {
}

public boolean markSupported() {
	return false;
}

public abstract int read() throws IOException;

public int read(byte[] b) throws IOException {
	return read(b, 0, b.length);
}

public int read(byte[] b, int off, int len) throws IOException {
	int start = off;
	while (len > 0) {
		int c = 0;
		try {
			c = read();
		} catch (IOException e) {
			if (off == start) throw e;
			break;
		}
		if (c < 0) {
			if (off == start) return -1;
			break;
		}
		b[off++] = (byte)c;
		len--;
	}
	return off - start;
}

public void reset() throws IOException {
	throw new IOException();
}

public long skip(long n) throws IOException {
	if (n <= 0) return 0;
	int len = n < 512 ? (int)n : 512;
	byte[] buffer = new byte[len];
	int skipped = 0;
	while (skipped < n) {
		int read = read(buffer, 0, len);
		if (read == -1) break;
		skipped += read;
		if (read < len) break;
		if (n - skipped > buffer.length) {
			len = (int)n - skipped;
		}
	}
	return skipped;
}
}