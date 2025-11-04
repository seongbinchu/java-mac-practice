class HomeNetworkServer{
	public void performTurnOn(IMachine temp){
		temp.turnOn();
	}
	public void performTurnOff(IMachine temp){
		temp.turnOff();
	}
	public void performVolUp(IVolumeController cont){
		cont.volUp();
	}
	public void performVolDown(IVolumeController cont){
		cont.volDown();
	}
}
interface IMachine{
	void turnOn();
	void turnOff();
}
interface IVolumeController{
	void volUp();
	void volDown();
}
class Tv implements IMachine,IVolumeController{
	@Override
	public void turnOn(){
		System.out.println("Tv on");
	}
	@Override
	public void turnOff(){
		System.out.println("Tv off");
	}
	@Override
	public void volUp(){
		System.out.println("Tv vol up");
	}
	@Override
	public void volDown(){
		System.out.println("Tv vol down");
	}
}
class Audio implements IMachine,IVolumeController{
	@Override
	public void turnOn(){
		System.out.println("Audio on");
	}
	@Override
	public void turnOff(){
		System.out.println("Audio off");
	}
	@Override
	public void volUp(){
		System.out.println("Audio vol up");
	}
	@Override
	public void volDown(){
		System.out.println("Audio vol down");
	}
}
class Lamp implements IMachine{
	@Override
	public void turnOn(){
		System.out.println("Lamp On");
	}
	public void turnOff(){
		System.out.println("Lamp off");
	}
}
class Ex2{
	public static void main(String[] args){
		HomeNetworkServer server = new HomeNetworkServer();

		Tv tv = new Tv();
		Audio audio =new Audio();
		Lamp lamp = new Lamp();

		server.performTurnOn(tv);
		server.performTurnOff(tv);
		server.performTurnOn(audio);
		server.performTurnOff(audio);
		server.performVolDown(audio);
		server.performTurnOn(lamp);
		tv.turnOn();
	}
}