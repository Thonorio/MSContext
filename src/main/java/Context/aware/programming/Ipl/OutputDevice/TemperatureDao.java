package Context.aware.programming.Ipl.OutputDevice;

public class TemperatureDao {
    private String location;
    private String temperature;
    private boolean state;

    public TemperatureDao(String location, String temperature, boolean state) {
        this.location = location;
        this.temperature = temperature;
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
