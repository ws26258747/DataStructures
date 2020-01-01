package sort;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] array = new int[]{4,7,9,5,1,2,6,3,0};
		QuickSort.sort(array,0,array.length-1);
		System.out.println(Arrays.toString(array));
	}

	private static void sort(int[] array, int start, int end) {
		if(start < end) {
			int stand = array[start];
			int i = start;
			int j = end;
			while (i<j) {
				while(i<j) {
					if (array[j] < stand) {
						array[i] = array[j];
						break;
					}else {
						j--;
					}
				}
				while(i<j) {
					if (array[i] > stand) {
						array[j] = array[i];
						break;
					}else {
						i++;
					}
				}
			}
		array[i] = stand;
		sort(array,start,i);
		sort(array, i+1, end);
		}
	}
}
