interface IRoot{
	void methodRoot();
}
interface IFoo{
	void methodIFoo();
}
interface IBar extends IFoo,IRoot{
	void methodIBar();
}
class Other implements IBar{
	@Override
	public void methodRoot(){
	}
	@Override
	public void methodIFoo(){
	}
	@Override
	public void methodIBar(){
	}
}
class InterfaceEx3{
	public static void main(String[] args){

	}
}
