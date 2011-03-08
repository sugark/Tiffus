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

public abstract class NewOutputStream {

public NewOutputStream() {
}

public void close() throws IOException {
}

public void flush() throws IOException {
}

public void write(byte[] b) throws IOException {
	write(b, 0, b.length);
}

public void write(byte[] b, int off, int len) throws IOException {
	if (off < 0 || len < 0 || off >= b.length || off + len > b.length) {
		throw new IndexOutOfBoundsException();
	}
	for (int i = off, end = off + len; i < end; i++) {
		write(b[i]);
	}
}

public void write(int b) throws IOException {};
}