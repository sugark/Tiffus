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
package hu.newphrox.java.io;


import java.io.IOException;



public class NewByteArrayOutputStream extends NewOutputStream {

	protected byte[] buf;

	protected int count;
	
public NewByteArrayOutputStream() {
	this(32);
}

public NewByteArrayOutputStream(int size) {
	if (size < 0) throw new IllegalArgumentException();
	buf = new byte[size];
}

public void close() throws IOException {
	//super.close();
}

private void grow(int n) {
	byte[] newBuf = new byte[(buf.length + n) * 2 + 4096];
	System.arraycopy(buf, 0, newBuf, 0, count);
	buf = newBuf;
}

public void reset() {
	count = 0;
}

public int size() {
	return count;
}

public byte[] toByteArray() {
	byte[] result = new byte[count];
	System.arraycopy(buf, 0, result, 0, count);
	return result;
}

public String toString() {
	return new String(buf, 0, count);
}

public void write(byte[] b, int off, int len) {
	if (off < 0 || len < 0 || off >= b.length || off + len > b.length) {
		throw new IndexOutOfBoundsException();
	}
	if (count + len > buf.length) grow(len);
	System.arraycopy(b, off, buf, count, len);
	count += len;
}

public void write(int b) {
	if (count + 1 > buf.length) grow(1);
	buf[count++] = (byte)b;
}

}