package weather.ctrl;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.util.ArrayList;
import java.util.List;

/**** https://github.com/simonhotes/prog2-exercise3.git ****/

public class WeatherController {
    
    public static String apiKey = "ab5c55091bfde0864c41b337f1c66af5";
    

    public void process(List<GeoCoordinates> location) throws MyException {
        //DONE: Liste an GeoCoordinates
//        System.out.println("process "+location.latitude().value().toString() + " " +location.longitude().value().toString()); //$NON-NLS-1$
        List<Forecast> data = getData(location);
        List<GeoCoordinates> listOfCity = new ArrayList<>();
        try{
            for (var item : data) {
                Double highestTemp = getHighestTemp(item);
                Double averageTemp = getAverageTemp(item);
                Double highestWind = getHighestWind(item);
            }

            for (var item : location) {
                listOfCity.add(item);
            }

            SequentialDownloader sequentialDownloader = new SequentialDownloader();
            sequentialDownloader.process(listOfCity);

            ParallelDownloader parallelDownloader = new ParallelDownloader();
            parallelDownloader.process(listOfCity);

//            System.out.println("Daily values: " + data.getDaily().getData().stream().count());
        }catch (Exception e){
            throw new MyException(e.getMessage());
        }
    }

    private double  getHighestTemp(Forecast data) {
        return data.getDaily().getData().stream().mapToDouble(temp-> temp.getTemperatureHigh()).max().getAsDouble();
    }

    private double  getAverageTemp(Forecast data) {
        return data.getDaily().getData().stream().mapToDouble(temp-> temp.getTemperatureHigh()).average().getAsDouble();
    }

    private double  getHighestWind(Forecast data) {
        return data.getHourly().getData().stream().mapToDouble(temp-> temp.getWindSpeed()).max().getAsDouble();
    }




    public List<Forecast> getData(List<GeoCoordinates> location) throws MyException {
        List<Forecast> forecasts = new ArrayList<>();
        for (var city : location) {
            ForecastRequest request = new ForecastRequestBuilder()
                    .key(new APIKey(apiKey))
                    .location(city)
                    .build();

            DarkSkyJacksonClient client = new DarkSkyJacksonClient();

            try {
                Forecast forecast = client.forecast(request);
                forecasts.add(forecast);
                return forecasts;
            } catch (ForecastException e) {
                throw new MyException(e.getMessage());
            }
        }
        return null;
    }
}
