package sample;

public class Adress {
    private String key = "763e1e54ecf9df908c0deeeeeeeeeeeeeeeeeeee";
    private String keyAiryl = "uBMumGeeeeeeeeeeeeeeeeeeeeeeeeeeeeU";


    public String getForecast(Double latitude, Double longitude) {
        return "https://api.darksky.net/forecast/" + key + "/" + latitude + "," + longitude+"?lang=pl&units=si";
    }

    public String getLocalization() {
        return "http://ip-api.com/json/";
    }

    public String getAirPollution(){
        return "curl -X GET \\\n" + "    --header 'Accept: application/json' \\\n" + "    --header 'apikey: ueeeeeeeeeeeeeeeeeeeeeeeU' \\\n" + "    'https://airapi.airly.eu/v2/meta/indexes'"; //Airly api
    }

}
