package com.roll.casserole.algorithm;

import java.util.Arrays;

/**
 * @author haozq
 * Date: 2018/8/27 下午7:58
 */
public class Sort {
	public static void quickSort(int[] arr, int low, int high) {
		if (arr.length <= 0) {
			return;
		}
		if (low >= high) {
			return;
		}
		int left = low;
		int right = high;
		int temp = arr[left];
		while (left < right) {
			while (left < right && arr[right] >= temp) {
				right--;
			}
			arr[left] = arr[right];
			while (left < right && arr[left] <= temp) {
				left++;
			}
			arr[right] = arr[left];
		}
		arr[left] = temp;
		System.out.println("sorting: " + Arrays.toString(arr));
		quickSort(arr, low, left - 1);
		quickSort(arr, left + 1, high);
	}

	private static void bubbleSort(int data[]) {
		int temp = 0;
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = 0; j < data.length - 1 - i; j++) {
				if (data[j] > data[j + 1]) {
					temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
	}

	public static void main(String args[]) {
		int[] data = new int[]{3, 4, 0, 3, 4, 8, 6, 8, 2, 3};
		quickSort(data, 0, data.length - 1);

	}

	private static void quickSort2(int data[], int low, int high) {
		if (low >= high) {
			return;
		}
		int pivot = data[low];

	}

	private static int partition(int arr[], int low, int high) {
		int pivot = arr[low];
		while (low < high) {
			if (arr[low] <= pivot) {
				++low;
				arr[high] = arr[low];
			}
			if (arr[high] >= pivot) {
				--high;
				arr[low] = arr[high];
			}
		}
		arr[low] = pivot;
		return low;
	}
}
