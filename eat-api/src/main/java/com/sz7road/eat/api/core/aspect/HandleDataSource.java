package com.sz7road.eat.api.core.aspect;

/**
 * @author Panda.Z
 */
public class HandleDataSource {

	// 数据源名称线程池
	private static final ThreadLocal<String> holder = new ThreadLocal<>();

	public static void putDataSource(String datasource) {
		holder.set(datasource);
	}

	public static String getDataSource() {
		return holder.get();
	}

	public static void clear() {
		holder.remove();
	}
}
