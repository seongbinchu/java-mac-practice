public class ScopeEx2 {
    public static void main(String[] args) {
        int num;
        
        num = 100;
        
        int num;
        
        num = 200;
        
        System.out.println(num);
        
        /* 메인 메소드에서 이미 정의된 num을 재정의 하기위해서 변수 선언을 다시 했지만 
        num 은 이미 선언되었기 때문에 가용범위를 설정해 변수초기화를 사용해야한다
        */
    }
}