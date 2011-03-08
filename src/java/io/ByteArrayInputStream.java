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

import com.jcraft.jzlib.ZInputStream;

public class ByteArrayInputStream extends InputStream {

	protected byte[] buf;
	
	protected int pos;
	
	protected int mark;
	
	protected int count;
	
public ByteArrayInputStream(byte[] buf) {
	this.buf = buf;
	this.count = buf.length;
}

public ByteArrayInputStream(byte[] buf, int offset, int length) {
	this.buf = buf;
	this.pos = offset > buf.length ? buf.length : offset;
	this.count = offset + length;
	if (this.count > buf.length) this.count = buf.length;
	this.mark = pos;
}

public ByteArrayInputStream(InputStream in) {
	// TODO Auto-generated constructor stub
	return;
}

public ByteArrayInputStream(ZInputStream in) {
	// TODO Auto-generated constructor stub
	return;
}

public int available() {
	return count - pos;
}

public void close() throws IOException {
}

public void mark(int readlimit) {
	mark = pos;
}

public boolean markSupported() {
	return true;
}

public int read() throws IOException {
	return pos < count ? buf[pos++] & 0xFF : -1;
}

public int read(byte[] b) throws IOException {
	return read(b, 0, b.length);
}

public int read(byte[] b, int off, int len) throws IOException {
	if (off < 0 || len < 0 || off >= b.length || off + len > b.length) {
		throw new IndexOutOfBoundsException();
	}
	if (pos >= count) return -1;
	if (len == 0) return 0;
	if (pos + len >= count) len = count - pos;
	System.arraycopy(buf, pos, b, off, len);
	pos += len;
	return len;
}

public void reset() throws IOException {
	pos = mark;
}

public long skip(long n) throws IOException {
	if (pos + n < count) {
		pos += n;
		return n;
	}
	int result = count - pos;
	pos = count;
	return result;
}

}