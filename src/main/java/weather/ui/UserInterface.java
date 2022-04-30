package weather.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weather.ctrl.WeatherController;

public class UserInterface 
{

	private WeatherController ctrl = new WeatherController();

	public void getWeatherForCity1(){
		
		//TODO enter the coordinates 
		ctrl.process(null);

	}

	public void getWeatherForCity2(){
		//TODO enter the coordinates 
		ctrl.process(null);

	}

	public void getWeatherForCity3(){
		//TODO enter the coordinates 
		ctrl.process(null);

}
	
	public void getWeatherByCoordinates() {
		//TODO read the coordinates from the cmd
		//TODO enter the coordinates 
		ctrl.process(null);
		
	}

	public void start() {
		Menu<Runnable> menu = new Menu<>("Weather Infos");
		menu.setTitel("Wählen Sie eine Stadt aus:");
		menu.insert("a", "City 1", this::getWeatherForCity1);
		menu.insert("b", "City 2", this::getWeatherForCity2);
		menu.insert("c", "City 3", this::getWeatherForCity3);
		menu.insert("d", "City via Coordinates:",this::getWeatherByCoordinates);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
