package sample;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

import static java.lang.Math.round;

public class Formatter {
    //formatuje dane do pokazania

    Localization localization = new Localization();
    Download download = new Download();
    Adress adress = new Adress();
    ObjectMapper mapper = new ObjectMapper();
    Weather weather = new Weather();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");


    public Formatter() {
        try {
            localization = mapper.readValue(download.download(adress.getLocalization()), Localization.class);
            weather = mapper.readValue(download.download(adress.getForecast(localization.getLat(), localization.getLon())), Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTodayIcon() {
        return weather.getCurrently().getIcon();
    }

    public String getHourlyIcons(int i) {
        return weather.getHourly().getData().get(i).getIcon();
    }

    public String getHourlyTemp(int i) {
        return round(weather.getHourly().getData().get(i).getTemperature()) + "°C";
    }


    public String getTemp(int i) {

        if (i != 0) {
            return round(weather.getHourly().getData().get(i).getTemperature()) + "";
        } else return round(weather.getCurrently().getTemperature()) + "";
    }

    public String getTempApparent(int i) {
        if (i != 0) {
            return round(weather.getHourly().getData().get(i).getApparentTemperature()) + "°C";
        } else return round(weather.getCurrently().getApparentTemperature()) + "°C";
    }

    public String getPressure(int i) {
        if (i != 0) {
            return weather.getHourly().getData().get(i).getPressure() + " hPa";
        } else return weather.getCurrently().getPressure() + " hPa";
    }

    public String getPrecipProbability(int i) {
        if (i != 0) {
            return round(weather.getHourly().getData().get(i).getPrecipProbability() * 100) + " %";
        } else return round(weather.getCurrently().getPrecipProbability() * 100) + " %";
    }

    public String getHumidity(int i) {
        if (i != 0) {
            return round(weather.getHourly().getData().get(i).getHumidity() * 100) + " %";
        } else return round(weather.getCurrently().getHumidity() * 100) + " %";
    }

    public String getWindSpeed(int i) {
        if (i != 0) {
            return round(weather.getHourly().getData().get(i).getWindSpeed() * 3.6) + "km/h";
        } else return round(weather.getCurrently().getWindSpeed() * 3.6) + "km/h";
    }

    public String getSummary(int i) {
        if (i != 0) {
            return weather.getHourly().getData().get(i).getSummary() + "";
        } else return weather.getCurrently().getSummary() + "";
    }

    public String getHour(int i) {
        if (i != 0) {
            if (LocalTime.now().getHour() + i - 1 > 23) return LocalTime.now().getHour() + i - 25 + ":00";
            else return LocalTime.now().getHour() + i - 1 + ":00";
        } else return LocalTime.now().format(dtf);
    }

    public int getHfuck2() {
        return  LocalTime.now().getHour();
    }

    public String getLocalization() {
        return localization.getCity() + "";
    }

    public String getDailyIcons(int i) {
        return weather.getDaily().getData().get(i).getIcon();
    }

    public String getDay(int i) {

        return LocalDate.now().plusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pl")) + "";
    }

    public String getDailyTemp(int i) {
        return round(weather.getDaily().getData().get(i).getApparentTemperatureHigh()) + "° " + round(weather.getDaily().getData().get(i).getApparentTemperatureLow()) + "°";
    }

}
