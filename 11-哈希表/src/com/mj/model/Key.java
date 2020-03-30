package com.mj.model;

public class Key {
	protected int value;

	public Key(int value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		return value / 10;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != getClass()) return false;
		return ((Key) obj).value == value;
	}
	
	@Override
	public String toString() {
		return "v(" + value + ")";
	}
}
