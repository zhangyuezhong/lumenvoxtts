package com.sun.jna.ptr;

import com.sun.jna.Memory;

public class StringByReference extends ByReference {
	public StringByReference() {
		this(0);
	}

	public StringByReference(int size) {
		super(size < 4 ? 4 : size);
		getPointer().clear(size < 4 ? 4 : size);
	}

	public StringByReference(String str) {
		super(str.length() < 4 ? 4 : str.length() + 1);
		getPointer().setString(0, str);
	}

	public void setValue(String str) {
		int dataSize = (str.length() < 4 ? 4 : str.length() + 1);
		setPointer(new Memory(dataSize));
		getPointer().setString(0, str);
	}

	public String getValue() {
		return getPointer().getString(0);
	}

	public long getLength() {
		Memory pointer = (Memory) getPointer();
		return pointer.size();
	}
}
