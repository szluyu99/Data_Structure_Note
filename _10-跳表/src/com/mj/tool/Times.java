package com.mj.tool;

public class Times {
	public interface Task {
		void execute();
	}
	
	public interface ReturnTask {
		Object execute();
	}
	
	public static void test(String title, Task task) {
		if (task == null) return;
		printTitle(title);
		
		long begin = System.currentTimeMillis();
		task.execute();
		long end = System.currentTimeMillis();
		
		printDuration(begin, end);
	}
	
	public static Object test(String title, ReturnTask task) {
		if (task == null) return null;
		printTitle(title);
		
		long begin = System.currentTimeMillis();
		Object result = task.execute();
		long end = System.currentTimeMillis();
		
		printDuration(begin, end);
		return result;
	}
	
	private static void printTitle(String title) {
		title = (title == null) ? "" : ("【" + title + "】");
		System.out.println(title);
	}
	
	private static void printDuration(long begin, long end) {
		double duration = end - begin;
		System.out.println("耗时：" + duration / 1000.0 + "s(" + duration + "ms)");
		System.out.println("-------------------------------------");
	}
}
