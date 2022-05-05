package weather.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;
import weather.ctrl.WeatherController;

import javax.xml.stream.Location;

/**** https://github.com/simonhotes/prog2-exercise3.git ****/

public class UserInterface 
{

	private WeatherController ctrl = new WeatherController();

	public void getWeatherForCity1(){
		//DONE enter the coordinates
		GeoCoordinates bremen = new GeoCoordinates(new Longitude(8.80777), new Latitude(53.07516));
		ctrl.process(bremen);

	}

	public void getWeatherForCity2(){
		//DONE enter the coordinates
		GeoCoordinates portland = new GeoCoordinates(new Longitude(-0.127758), new Latitude(51.507351));
		ctrl.process(portland);

	}

	public void getWeatherForCity3(){
		//DONE enter the coordinates
		GeoCoordinates london = new GeoCoordinates(new Longitude(-122.679565), new Latitude(45.512794));
		ctrl.process(london);

}
	
	public void getWeatherByCoordinates(){
		//DONE read the coordinates from the cmd
		//DONE enter the coordinates
		Latitude city_lat = new Latitude(readDouble(-90,90));
		Longitude city_long = new Longitude(readDouble(-180,180));
		GeoCoordinates city = new GeoCoordinates(city_long, city_lat);
		ctrl.process(city);
	}


	public void start()  {
		Menu<Runnable> menu = new Menu<>("Weather Infos");
		menu.setTitel("Wählen Sie eine Stadt aus:");
		menu.insert("a", "Bremen", this::getWeatherForCity1);
		menu.insert("b", "Portland, Oregon, USA", this::getWeatherForCity2);
		menu.insert("c", "London", this::getWeatherForCity3);
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
