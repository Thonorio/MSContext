package Context.aware.programming.Ipl.OutputDevice;

public class ContextDao {

    private String location;
    private String temperature;
    private String humidity;
    private boolean state;

    public ContextDao(String location, String temperature, String humidity, boolean state) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
