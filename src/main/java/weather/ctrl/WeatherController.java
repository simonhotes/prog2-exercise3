package weather.ctrl;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Forecast;

public class WeatherController {
    
    private String apiKey = "Enter your API key";
    

    public void process(GeoCoordinates location) {
        System.out.println("process "+location); //$NON-NLS-1$
		//Forecast data = getData();
		
		//TODO implement Error handling 
		
		//TODO implement methods for
		// highest temperature 
		// average temperature 
		// count the daily values
		
		// implement a Comparator for the Windspeed 
		
	}
    
    
    public Forecast getData(GeoCoordinates location) {
		ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apiKey))
                .location(location)
                .build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        //Forecast forecast = client.forecast(request);
        
        return null;
    }
}
