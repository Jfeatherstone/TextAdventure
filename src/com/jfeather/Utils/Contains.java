package com.jfeather.Utils;

import java.util.ArrayList;

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
	
	public final static int arrElementsAsWordsInString(String[] arr, String str) {
		// Search for any of the elements of arr in str1
		String[] words = separateBySpacesIntoArray(str);
		for (int j = 0; j < arr.length; j++) {
			for (int i = 0; i < words.length; i++) {
				if (words[i].equals(arr[j]))
					return i;
			}
		}
		return -1;

	}
	
	public static boolean string2InString1(String str1, String str2) {
		// Search for str2 in str1
		for (int i = 0; i < str1.length() - str2.length() + 1; i++) {
			if (str1.substring(i, i + str2.length()).toLowerCase().equals(str2.toLowerCase()))
				return true;
		}
		return false;
	}

	
	public final static boolean wordInString(String word, String str) {
		String[] words = separateBySpacesIntoArray(str);
		for (String s: words) {
			if (s.equalsIgnoreCase(word))
				return true;
		}
		return false;
	}
	
	public static String[] separateBySpacesIntoArray(String str) {
		ArrayList<String> words = new ArrayList<>();
		
		int index1 = 0, index2 = 0;
		int counter = 0;
		while (counter < str.length()) {
			boolean done = false;

			for (int j = index1 + 1; j < str.length(); j++) {
				if (str.substring(j, j + 1).equals(" ")) {
					index2 = j;
					done = true;
					break;
				}
			}
			
			if (!done)
				index2 = str.length();
			words.add(str.substring(index1, index2).trim());
			
			done = false;
			
			for (int j = index2 + 1; j < str.length(); j++) {
				if (str.substring(j, j + 1).equals(" ")) {
					index1 = j;
					done = true; 
					break;
				}
			}
			if (!done)
				index1 = str.length();

			if (index1 > str.length() || index2 >= str.length())
				break;
			
			words.add(str.substring(index2, index1).trim());
			counter = index1;
		} 
		
		String[] arr = new String[words.size()];
		int i = 0;
		for (String s: words) {
			arr[i] = s;
			i++;
		}
		
		return arr;

	}

}
