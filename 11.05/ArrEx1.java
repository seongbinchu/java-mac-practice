/*
				복수데이터가 가치를 가질려면 식별성을 가져야함
				-원하는 데이터를 복수데이터 중 찾을 수 있어야함
				-그 다음 찾는 속도
				%binarysearch
	정렬 : 모든 복수데이터는 정렬됐다.
		-오름차순(정렬 기본)
		-내림차순
*/

import java.util.Arrays;
class ArrEx1{
	public static void main(String[] args){
		int[] arr1 = {1,6,4,2,9,5};
		Arrays.sort(arr1);
		System.out.println(Arrays.toString(arr1));

		String[] arr2 = {"apple","apply","adapter","application"};
		Arrays.sort(arr2);
		System.out.println(Arrays.toString(arr2));
	}
}
