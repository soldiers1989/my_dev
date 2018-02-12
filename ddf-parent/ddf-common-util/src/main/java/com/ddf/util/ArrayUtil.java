package com.ddf.util;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

	public static List<List<String>> splitAry(String[] ary, int subSize) {
		int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;

		List<List<String>> subAryList = new ArrayList<List<String>>();

		for (int i = 0; i < count; i++) {
			int index = i * subSize;
			List<String> list = new ArrayList<String>();
			int j = 0;
			while (j < subSize && index < ary.length) {
				list.add(ary[index++]);
				j++;
			}
			subAryList.add(list);
		}
		return subAryList;
	}
	
	
	public static void main(String[] args) {
		String[] usernames = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13"};
		List<List<String>> arrayList = splitAry(usernames, 10);
		for (List<String> list: arrayList) {
			for (String userName : list) {
				System.out.println(userName);
			}
		}
	}

}
