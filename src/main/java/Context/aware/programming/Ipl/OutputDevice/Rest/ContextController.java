package Context.aware.programming.Ipl.OutputDevice.Rest;

import Context.aware.programming.Ipl.Camera.Entity.Camera;
import Context.aware.programming.Ipl.Camera.Repository.CameraRepository;
import Context.aware.programming.Ipl.OutputDevice.ContextDao;
import Context.aware.programming.Ipl.OutputDevice.HumidityDao;
import Context.aware.programming.Ipl.OutputDevice.TemperatureDao;
import Context.aware.programming.Ipl.Sensors.Entity.Sensor;
import Context.aware.programming.Ipl.Sensors.Repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ContextController {

    @Autowired
    SensorRepository sensorRepository;
    @Autowired
    CameraRepository cameraRepository;

    // Get Temperature
    @GetMapping("/locations/{location}/state/")
    public String currentLocationState(@PathVariable String location){

        Optional<Camera> camera = cameraRepository.findByLocation(location);

        return camera.get().isOccupied() == true ? "Occupied" : "Unoccupied";
    }

    @GetMapping("/locations/current")
    public List<String> getCurrentLocations(){

        List<String> locations = new ArrayList<>();
        Optional<List<Camera>> camera = cameraRepository.findByOccupied(true);
        camera.get().forEach(x -> locations.add(x.getLocation()));

        return locations;
    }

    // Get Temperature
    @GetMapping("/temperatures/{location}")
    public String currentTemperatureForLocation(@PathVariable String location){

        Optional<Sensor> sensor = sensorRepository.findByLocation(location);

        return sensor.get().getTemperature();
    }

    @GetMapping("/temperatures/current")
    public List<TemperatureDao> getTempForCurrentLocation(){

        List<TemperatureDao> tempDaos = new ArrayList<>();
        Optional<List<Camera>> camera = cameraRepository.findByOccupied(true);
        Optional<Sensor> sensor;
        for (Camera c: camera.get()) {
            sensor = sensorRepository.findByLocation(c.getLocation());
            tempDaos.add(new TemperatureDao(c.getLocation(), sensor.get().getTemperature(), c.isOccupied()));
        }

        return tempDaos;
    }

    // Get Humidity
    @GetMapping("/humidities/{location}")
    public String currentHumidityForLocation(@PathVariable String location){

        Optional<Sensor> sensor = sensorRepository.findByLocation(location);

        return sensor.get().getHumidity();
    }

    @GetMapping("/humidities/current")
    public List<HumidityDao> getHumidityForCurrentLocation(){

        List<HumidityDao> humidityDaos = new ArrayList<>();
        Optional<List<Camera>> camera = cameraRepository.findByOccupied(true);
        Optional<Sensor> sensor;
        for (Camera c: camera.get()) {
            sensor = sensorRepository.findByLocation(c.getLocation());
            humidityDaos.add(new HumidityDao(c.getLocation(), sensor.get().getHumidity(), c.isOccupied()));
        }

        return humidityDaos;
    }

    // Context already defined
    @GetMapping("/contexts")
    public List<Sensor> allContext(){
        return sensorRepository.findAll();
    }

    @GetMapping("/contexts/{location}")
    public ContextDao currentContextForLocation(@PathVariable String location){

        Optional<Sensor> sensor = sensorRepository.findByLocation(location);
        Optional<Camera> camera = cameraRepository.findByLocation(location);
        ContextDao contextDao = new ContextDao(location, sensor.get().getTemperature(), sensor.get().getHumidity(), camera.get().isOccupied());

        return contextDao;
    }

    @GetMapping("/contexts/current")
    public List<ContextDao> currentContextForCurrentLocation(){
        List<ContextDao> contextDaos = new ArrayList<>();
        Optional<List<Camera>> camera = cameraRepository.findByOccupied(true);
        Optional<Sensor> sensor;
        for (Camera c: camera.get()) {
            sensor = sensorRepository.findByLocation(c.getLocation());
            contextDaos.add(new ContextDao(c.getLocation(), sensor.get().getTemperature(), sensor.get().getHumidity(), c.isOccupied()));
        }

        return contextDaos;
    }

}
