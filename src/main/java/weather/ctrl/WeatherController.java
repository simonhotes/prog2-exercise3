package weather.ctrl;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.util.Comparator;

/**** https://github.com/simonhotes/prog2-exercise3.git ****/

public class WeatherController {
    
    private String apiKey = "ab5c55091bfde0864c41b337f1c66af5";
    

    public void process(GeoCoordinates location) {
        System.out.println("process "+location); //$NON-NLS-1$
		Forecast data = getData(location);
		//DONE implement Error handling
		//DONE implement methods for
		// highest temperature
        // average temperature
        // count the daily values
        try{
            data.getDaily().getData().stream().mapToDouble(temp-> temp.getTemperatureHigh()).max().ifPresent(maxTemp-> System.out.println("Highest Temp: " + maxTemp));
            data.getDaily().getData().stream().mapToDouble(temp-> temp.getTemperatureHigh()).average().ifPresent(maxTemp-> System.out.println("Avg Temp: " + maxTemp));
            System.out.println("Daily values:" + data.getDaily().getData().stream().count());
        }catch (Exception e){
            e.printStackTrace();
        }

		// TODO implement a Comparator for the Windspeed
	}
    
    public Forecast getData(GeoCoordinates location) {
		ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apiKey))
                .location(location)
                .build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();

        try {
            Forecast forecast = client.forecast(request);
            return forecast;
        } catch (ForecastException e) {
        }

        return null;
    }
}
