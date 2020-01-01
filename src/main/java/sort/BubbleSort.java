package sort;

import java.util.Arrays;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] array = new int[]{5,7,8,9,2,3,0};
		BubbleSort.bubblesort(array);
		System.out.println(Arrays.toString(array));
	}
	public static void bubblesort(int[] array) {
		int tmp;
		for(int i=0;i<array.length;i++) {
			for(int j =0;j<array.length-i-1;j++) {
				if (array[j] > array[j+1]) {
					tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
		}
	}
}
