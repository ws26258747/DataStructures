package sort;

import java.util.Arrays;

public class InsertSrot {
	public static void main(String[] args) {
		int[] array = new int[]{4,5,9,6,1,3,2,7};
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	private static void sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j-1] > array[j]) {
					int tmp;
					tmp = array[j-1];
					array[j-1]=array[j];
					array[j]=tmp;
				}else {
					break;
				}
			}
		}
	}
}
