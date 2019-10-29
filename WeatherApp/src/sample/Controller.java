package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {

    Formatter formatter = new Formatter();

    @FXML
    private Button close;
    @FXML
    private Pane paneDown;
    @FXML
    private ImageView imgPaneUp;
    @FXML
    private ImageView imgPaneDown;
    @FXML
    private ImageView imgPaneDownInside;
    @FXML
    private Pane paneInside;
    @FXML
    private Label pogodaDzisiaj;
    @FXML
    private Label pogoda7Dni;
    @FXML
    private Label jakoscPowietrza;
    @FXML
    private ImageView imgToday;
    @FXML
    private Label temp;
    @FXML
    private Label cisnienie;
    @FXML
    private Label szansaNaOpad;
    @FXML
    private Label wilgoc;
    @FXML
    private Label predkoscWiatru;
    @FXML
    private Label krotkiOpis;
    @FXML
    private Label godzina;
    @FXML
    ArrayList<ImageView> images;
    @FXML
    ArrayList<ImageView> imagesDays;
    @FXML
    ArrayList<Label> tempHourly;
    @FXML
    ArrayList<Label> tempDays;
    @FXML
    ArrayList<Label> days;
    @FXML
    ArrayList<Label> godzHourly;
    @FXML
    private Label cityName;

    public void initialize() {
        if ((formatter.getHfuck2() < 21)&&(formatter.getHfuck2() > 6)) {
            imgPaneUp.setImage(new Image("pics/sunnyday1.jpg"));
            imgPaneDown.setImage(new Image("pics/day.jpg"));

        } else{
            close.setTextFill(Color.WHITE);
            imgPaneUp.setImage(new Image("pics/night.jpg"));
            imgPaneDown.setImage(new Image("pics/night.png"));
        }

        updateCurrentIcon(formatter.getTodayIcon());
        updateHourlyIcons();
        updateHourlyTemp();
        setInfo(0);
        cityName.setText(formatter.getLocalization());
        paneInside.visibleProperty().setValue(false);
    }

    @FXML
    public void close(ActionEvent event) {
        if (event.getSource().equals(close)) Platform.exit();
    }

    @FXML
    public void sthClicked(Event event) {
        if (event.getSource().equals(pogodaDzisiaj)) {
            paneDownVisible(true);
            updateCurrentIcon(formatter.getTodayIcon());
            setInfo(0);
            paneInside.visibleProperty().setValue(false);
        }
        if (event.getSource().equals(pogoda7Dni)) {
            if ((formatter.getHfuck2() < 21)&&(formatter.getHfuck2() > 6)) {
                imgPaneDownInside.setImage(new Image("pics/day.jpg"));

            } else{
                close.setTextFill(Color.WHITE);
                imgPaneDownInside.setImage(new Image("pics/night.png"));
            }

            paneDownVisible(false);
            paneInside.visibleProperty().setValue(true);
            updateDayIcon();
            setInfoDaily();
        }
        //if (event.getSource().equals(jakoscPowietrza)) System.out.println(formatter.download.curl());


        if (event.getSource().equals(images.get(0))) {
            updateCurrentIcon(formatter.getTodayIcon());
            setInfo(0);
        }
        for (int i = 1; i < images.size(); i++) {
            if (event.getSource().equals(images.get(i))) {
                updateCurrentIcon(formatter.getHourlyIcons(i));
                setInfo(i + 1);
            }
        }
    }

    public void setInfo(int i) {
        godzina.setText(formatter.getHour(i));
        temp.setText(formatter.getTemp(i) + " / " + formatter.getTempApparent(i));
        cisnienie.setText(formatter.getPressure(i));
        szansaNaOpad.setText(formatter.getPrecipProbability(i));
        wilgoc.setText(formatter.getHumidity(i));
        predkoscWiatru.setText(formatter.getWindSpeed(i));
        krotkiOpis.setText(formatter.getSummary(i));
    }

    public void setInfoDaily() {
        for (int j = 0; j < 7; j++) {
            days.get(j).setText(formatter.getDay(j));
            tempDays.get(j).setText(formatter.getDailyTemp(j));
        }
    }

    public void updateCurrentIcon(String x) {

        switch (x) {
            case "clear-day":
                imgToday.setImage(new Image("pics/climacons/clear-day.png"));
                break;

            case "clear-night":
                imgToday.setImage(new Image("pics/climacons/clear-night.png"));
                break;

            case "rain":
                imgToday.setImage(new Image("pics/climacons/rain.png"));
                break;

            case "snow":
                imgToday.setImage(new Image("pics/climacons/snow.png"));
                break;

            case "sleet":
                imgToday.setImage(new Image("pics/climacons/sleet.png"));
                break;

            case "wind":
                imgToday.setImage(new Image("pics/climacons/wind.png"));
                break;

            case "fog":
                imgToday.setImage(new Image("pics/climacons/fog.png"));
                break;

            case "cloudy":
                imgToday.setImage(new Image("pics/climacons/cloudy.png"));
                break;

            case "partly-cloudy-day":
                imgToday.setImage(new Image("pics/climacons/partly-cloudy-day.png"));
                break;

            case "partly-cloudy-night":
                imgToday.setImage(new Image("pics/climacons/partly-cloudy-night.png"));
                break;

            default:
                imgToday.setImage(new Image("pics/climacons/problem.png"));
                break;
        }
    }

    public void updateHourlyIcons() {

        switch (formatter.getTodayIcon()) {
            case "clear-day":
                images.get(0).setImage(new Image("pics/climacons/clear-day.png"));
                break;

            case "clear-night":
                images.get(0).setImage(new Image("pics/climacons/clear-night.png"));
                break;

            case "rain":
                images.get(0).setImage(new Image("pics/climacons/rain.png"));
                break;

            case "snow":
                images.get(0).setImage(new Image("pics/climacons/snow.png"));
                break;

            case "sleet":
                images.get(0).setImage(new Image("pics/climacons/sleet.png"));
                break;

            case "wind":
                images.get(0).setImage(new Image("pics/climacons/wind.png"));
                break;

            case "fog":
                images.get(0).setImage(new Image("pics/climacons/fog.png"));
                break;

            case "cloudy":
                images.get(0).setImage(new Image("pics/climacons/cloudy.png"));
                break;

            case "partly-cloudy-day":
                images.get(0).setImage(new Image("pics/climacons/partly-cloudy-day.png"));
                break;

            case "partly-cloudy-night":
                images.get(0).setImage(new Image("pics/climacons/partly-cloudy-night.png"));
                break;

            default:
                images.get(0).setImage(new Image("pics/climacons/problem.png"));
                break;
        }


        for (int i = 1; i < 6; i++) {
            switch (formatter.getHourlyIcons(i)) {
                case "clear-day":
                    images.get(i).setImage(new Image("pics/climacons/clear-day.png"));
                    break;

                case "clear-night":
                    images.get(i).setImage(new Image("pics/climacons/clear-night.png"));
                    break;

                case "rain":
                    images.get(i).setImage(new Image("pics/climacons/rain.png"));
                    break;

                case "snow":
                    images.get(i).setImage(new Image("pics/climacons/snow.png"));
                    break;

                case "sleet":
                    images.get(i).setImage(new Image("pics/climacons/sleet.png"));
                    break;

                case "wind":
                    images.get(i).setImage(new Image("pics/climacons/wind.png"));
                    break;

                case "fog":
                    images.get(i).setImage(new Image("pics/climacons/fog.png"));
                    break;

                case "cloudy":
                    images.get(i).setImage(new Image("pics/climacons/cloudy.png"));
                    break;

                case "partly-cloudy-day":
                    images.get(i).setImage(new Image("pics/climacons/partly-cloudy-day.png"));
                    break;

                case "partly-cloudy-night":
                    images.get(i).setImage(new Image("pics/climacons/partly-cloudy-night.png"));
                    break;

                default:
                    images.get(i).setImage(new Image("pics/climacons/problem.png"));
                    break;
            }
        }
    }

    public void updateDayIcon() {
        for (int i = 0; i < 7; i++) {
            switch (formatter.getDailyIcons(i)) {
                case "clear-day":
                    imagesDays.get(i).setImage(new Image("pics/climacons/clear-day.png"));
                    break;

                case "clear-night":
                    imagesDays.get(i).setImage(new Image("pics/climacons/clear-night.png"));
                    break;

                case "rain":
                    imagesDays.get(i).setImage(new Image("pics/climacons/rain.png"));
                    break;

                case "snow":
                    imagesDays.get(i).setImage(new Image("pics/climacons/snow.png"));
                    break;

                case "sleet":
                    imagesDays.get(i).setImage(new Image("pics/climacons/sleet.png"));
                    break;

                case "wind":
                    imagesDays.get(i).setImage(new Image("pics/climacons/wind.png"));
                    break;

                case "fog":
                    imagesDays.get(i).setImage(new Image("pics/climacons/fog.png"));
                    break;

                case "cloudy":
                    imagesDays.get(i).setImage(new Image("pics/climacons/cloudy.png"));
                    break;

                case "partly-cloudy-day":
                    imagesDays.get(i).setImage(new Image("pics/climacons/partly-cloudy-day.png"));
                    break;

                case "partly-cloudy-night":
                    imagesDays.get(i).setImage(new Image("pics/climacons/partly-cloudy-night.png"));
                    break;

                default:
                    imagesDays.get(i).setImage(new Image("pics/climacons/problem.png"));
                    break;
            }
        }
    }


    public void updateHourlyTemp() {
        for (int i = 0; i < 6; i++) {
            tempHourly.get(i).setText(formatter.getHourlyTemp(i));
            if (LocalTime.now().getHour() + i > 23) {
                godzHourly.get(i).setText(LocalTime.now().getHour() + i - 24 + ":00");
            } else godzHourly.get(i).setText(LocalTime.now().getHour() + i + ":00");
        }
    }

    public void paneDownVisible(boolean x) {
        paneDown.visibleProperty().setValue(x);
    }

}
