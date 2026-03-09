package kr.ac.green;

public class SynchVsNotCynch {
	private static final long CALL_COUNT = 100000000L;
	public static void main(String[] args) {
		trial(CALL_COUNT, new NotSynch());
		trial(CALL_COUNT, new Synch());
	}
	public static void trial(long count,Object obj) {
		String msg = obj.toString();
		System.out.println(msg +": BEGIN" );
		long startTime = System.currentTimeMillis();
		for(int i =0;i<count;i++) {
			obj.toString();
		}
		System.out.println(
				"Elapesd time = "+
				(System.currentTimeMillis() - startTime) + "ms"
		);
		System.out.println(msg +" : END");
	}
}

class Synch{
	private final String name = "Synch";
	@Override
	public synchronized String toString() {
		return "[" + name +"]";
		//synchronized override 판단기준 x
	}
}

class NotSynch extends Synch{
	private final String name = "NotSynch";
	@Override
	public String toString() {
		return "[" + name +"]";
	}
}


// synchronized 공짜가아님 비용발생
// 병목현상도 단점