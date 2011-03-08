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
package java.util;

public class Hashtable implements Map {
	
	int elementCount;
	Entry[] elementData;
	private int limit;
	
private static class Entry {
	Object key, value;
	Entry next;
}

public Hashtable() {
	this(10);
}

public Hashtable(int initialCapacity) {
	if (initialCapacity < 0) throw new IllegalArgumentException();
	elementData = new Entry[initialCapacity];
	limit = elementData.length * 3 / 4;
}

public void clear() {
	for (int i = 0; i < elementData.length; i++) {
		elementData[i] = null;
	}
	elementCount = 0;
}

public boolean contains(Object value) {
	if (value == null) throw new NullPointerException();
	for (int i = 0; i < elementData.length; i++) {
		Entry entry = elementData[i];
		while (entry != null) {
			if (value.equals(entry.value)) return true;
			entry = entry.next;
		}
	}
	return false;
}

public boolean containsKey(Object key) {
	if (key == null) throw new NullPointerException();
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % elementData.length;
	Entry entry = elementData[index];
	while (entry != null) {
		if (key.equals(entry.key)) return true;
		entry = entry.next;
	}
	return false;
}

public Enumeration elements() {
	return new Enumeration() {
		int count, pos;
		Entry entry;
		
		public boolean hasMoreElements() {
			return count < elementCount;
		}
		
		public Object nextElement() {
			if (count >= elementCount) throw new NoSuchElementException();
			while (entry == null) entry = elementData[pos++];
			Object result = entry.value;
			entry = entry.next;
			count++;
			return result;
		}
	};
}

public Object get(Object key) {
	if (key == null) throw new NullPointerException();
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % elementData.length;
	Entry entry = elementData[index];
	while (entry != null) {
		if (key.equals(entry.key)) return entry.value;
		entry = entry.next;
	}
	return null;
}

public boolean isEmpty() {
	return elementCount == 0;
}

public Enumeration keys() {
	return new Enumeration() {
		int count, pos;
		Entry entry;
		
		public boolean hasMoreElements() {
			return count < elementCount;
		}
		
		public Object nextElement() {
			if (count >= elementCount) throw new NoSuchElementException();
			while (entry == null) entry = elementData[pos++];
			Object result = entry.key;
			entry = entry.next;
			count++;
			return result;
		}
	};
}

public Object put(Object key, Object value) {
	if (key == null || value == null) throw new NullPointerException();
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % elementData.length;
	if (elementCount > 0) {
		Entry entry = elementData[index];
		while (entry != null) {
			if (entry.equals(key)) {
				Object result = entry.value;
				entry.value = value;
				return result;				
			}
			entry = entry.next;
		}
	}
	if (elementCount++ >= limit) {
		rehash();
		index = (hash & 0x7FFFFFFF) % elementData.length;
	}
	Entry newEntry = new Entry();
	Entry entry = elementData[index];
	if (entry != null) newEntry.next = entry;
	newEntry.key = key;
	newEntry.value = value;
	elementData[index] = newEntry;
	return null;
}

protected void rehash() {
	Entry[] newEntries = new Entry[elementData.length * 2 + 1];
	for (int i = 0; i < elementData.length; i++) {
		Entry entry = elementData[i];
		while (entry != null) {
			int hash = entry.key.hashCode();
			int index = (hash & 0x7FFFFFFF) % newEntries.length;
			Entry previous = newEntries[index];
			Entry next = entry.next;
			entry.next = previous != null ? previous : null;
			newEntries[index] = entry;
			entry = next;
		}
	}
	elementData = newEntries;
	limit = elementData.length * 3 / 4;
}

public Object remove(Object key) {
	if (key == null) throw new NullPointerException();
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % elementData.length;
	Entry entry = elementData[index], previous = null;
	while (entry != null) {
		if (key.equals(entry.key)) {
			elementCount--;
			if (previous == null) {
				elementData[index] = entry.next;
			} else {
				previous.next = entry.next;
			}
			return entry.value;
		}
		previous = entry;
		entry = entry.next;
	}
	return null;
}

public int size() {
	return elementCount;
}

public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("{");
	boolean first = true;
	Enumeration keys = keys();
	while (keys.hasMoreElements()) {
		if (!first) buffer.append(", ");
		first = false;
		Object key = keys.nextElement();
		Object value = get(key);
		buffer.append(key);
		buffer.append("=");
		buffer.append(value);
	}
	buffer.append("}");
	return buffer.toString();
}

public boolean containsValue(Object value) {
	return contains(value);
}

public Set entrySet() {
	// TODO Auto-generated method stub
	return null;
}

public Set keySet() {
	// TODO Auto-generated method stub
	return null;
}

public void putAll(Map t) {
	// TODO Auto-generated method stub
	
}

public Collection values() {
	// TODO Auto-generated method stub
	return null;
}
}