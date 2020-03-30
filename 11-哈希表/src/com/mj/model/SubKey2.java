package com.mj.model;

public class SubKey2 extends Key {

	public SubKey2(int value) {
		super(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || 
				(obj.getClass() != SubKey1.class 
				&& obj.getClass() != SubKey2.class)) return false;
		return ((Key) obj).value == value;
	}
}
