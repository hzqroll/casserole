package com.roll.casserole.set;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author haozq
 * Date: 2018/8/16 上午10:23
 */
public class MapSizeTest {
	public static void main(String args[]) {
		/**
		 * 一千万的数据占用内存900M，耗时10s左右
		 */
		long startTime = System.currentTimeMillis();
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		Map<Long, List<Integer>> cacheMap = new ConcurrentHashMap<>();
		for (int i = 0; i < 10000000; i++) {
			cacheMap.put((long) i, list);
		}
		System.out.println(System.currentTimeMillis() - startTime + "size: " + cacheMap.size());
		for (;;){

		}
	}
}
