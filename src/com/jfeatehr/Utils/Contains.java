package com.jfeatehr.Utils;

abstract public class Contains {

	public final static int arrElementsInString(String[] arr, String str) {
		// Search for any of the elements of arr in str1
		for (int j = 0; j < arr.length; j++) {
			for (int i = 0; i < str.length() - arr[j].length() + 1; i++) {
				if (str.substring(i, i + arr[j].length()).toLowerCase().equals(arr[j].toLowerCase()))
					return i;
			}
		}
		return -1;

	}
}
