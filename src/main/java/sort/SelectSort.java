package sort;

import java.util.Arrays;

public class SelectSort {
	public static void main(String[] args) {
		int[] array= new int[]{2,1,3,4,9,5,6,7,8};
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	private static void sort(int[] array) {
//		int max = array[0];
		for (int i= 0;  i< array.length-1; i++) {
			int indexofMax=0;
			int tmp;
			for (int j = 1; j < array.length-i; j++) {
				if (array[j]>array[indexofMax]) {
					indexofMax = j;
				}
			}
			tmp = array[array.length-i-1];
			array[array.length-i-1] = array[indexofMax];
			array[indexofMax]=tmp;
		}
	}
}
