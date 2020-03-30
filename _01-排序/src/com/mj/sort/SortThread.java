package com.mj.sort;

/**
 * 休眠排序
 */
public class SortThread extends Thread{
	public int value;
	public SortThread(int value) {
		this.value = value;
	}
	public void run(){
		try{
			Thread.sleep(value);
			System.out.println(value);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		int [] array = {10, 100, 50, 30, 60, 61, 64, 47,79, 59, 11000};
		for (int i = 0; i < array.length; i++) {
			new SortThread(array[i]).start();
		}
		
	}
}
