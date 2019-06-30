package com.roll.casserole.sync;

import java.util.ArrayList;
import java.util.List;


/**
 * @author haozq
 * Date: 2018/7/23 下午5:53
 */
public class ReenTest {
	public static void main(String args[]){
		/*String a = "\"[{\"orderId\":6854801166089216,\"strOrderId\":\"6854801166089216\",\"start\":469031,\"end\":469032,\"......}]\"";
		System.out.println(a.contains("6854801166089216"));


		List<String> b = new ArrayList<>();
		b.add("q");
		b.add("w");
		System.out.println(convertToResult(b));*/

		Thread thread = new Thread(new testTask());
		thread.start();
	}

	/*private static String convertToResult(List<String> results) {
		StringBuilder sb = new StringBuilder();
		for (String result : results) {
			if (!StringUtils.isEmpty(result)) {
				sb.append("%").append(result).append("%").append(",");
			}
		}
		return sb.toString().substring(0,sb.length()-1);
	}*/

	private static class testTask implements Runnable{

		@Override
		public void run() {
			System.out.println("你的任务！");
		}
	}
}
