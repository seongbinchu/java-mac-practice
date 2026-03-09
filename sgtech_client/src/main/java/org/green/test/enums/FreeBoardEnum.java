package org.green.test.enums;

public enum FreeBoardEnum {
	COMMON_BOARD(6666),
	QUESTION_BOARD(7777),
	LUNCH_BOARD(8888);
	
	private final int b_no;

	FreeBoardEnum(int b_no) {
		this.b_no = b_no;
	}
	
	public int getB_no() {
		return b_no;
	}
	
	public static boolean contains(int b_no) {
		for (FreeBoardEnum f : FreeBoardEnum.values()) {
			if(f.getB_no() == b_no) {
				return true;
			}
		}
		return false;
	}
	
}
