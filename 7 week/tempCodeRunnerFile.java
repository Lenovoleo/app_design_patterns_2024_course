
public class modul07lab {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        WeatherDisplay mobileApp = new WeatherDisplay("Мобильное приложение");
        WeatherDisplay digitalBillboard = new WeatherDisplay("Электронное табло");

        weatherStation.registerObserver(mobileApp);
        weatherStation.registerObserver(digitalBillboard);

        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);

        weatherStation.removeObserver(digitalBillboard);
        weatherStation.setTemperature(28.0f);
    }
}
