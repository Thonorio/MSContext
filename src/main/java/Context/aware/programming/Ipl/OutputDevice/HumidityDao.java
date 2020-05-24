package Context.aware.programming.Ipl.OutputDevice;

public class HumidityDao {
    private String location;
    private String humidity;
    private boolean state;

    public HumidityDao(String location, String humidity, boolean state) {
        this.location = location;
        this.humidity = humidity;
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
