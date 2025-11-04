import java.util.Arrays;
class ArrEx3{
	public static void main(String[] args){
		Object[] arr = new Object[3];
		arr[0] = new Some(100);
		arr[1] = new int[]{1,2,3};
		arr[2] = new int[][]{ {1,2},{3,4},{5,6}};

		Some s = (Some)arr[0];
		int[] arr1 = (int[])arr[1];
		int[][] arr2 = (int[][])arr[2];
		System.out.println(s);
		System.out.println(Arrays.toString(arr1));
		for(int[] temp : arr2){
			System.out.println(Arrays.toString(temp));
		}
	}
}