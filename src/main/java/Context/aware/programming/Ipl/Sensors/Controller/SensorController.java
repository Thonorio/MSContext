package Context.aware.programming.Ipl.Sensors.Controller;

import Context.aware.programming.Ipl.Sensors.Entity.Sensor;
import Context.aware.programming.Ipl.Sensors.Repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SensorController {

    @Autowired
    SensorRepository bluetoothSensorRepository;

    @GetMapping("/sensors")
    public List<Sensor> allCameras(){
        return bluetoothSensorRepository.findAll();
    }

    @PostMapping("/sensors")
    public ResponseEntity createCamera(@RequestBody Map<String, Object> payload){

        UUID uuid = UUID.fromString(payload.get("uuid").toString());

        Optional<Sensor> sensors = bluetoothSensorRepository.findByUuid(uuid);

        if(sensors.isPresent()) {
            return new ResponseEntity("This camera already exists", HttpStatus.BAD_REQUEST);
        }

        Sensor newSensor = new Sensor();
        newSensor.setUuid(uuid);
        newSensor.setName(payload.get("name").toString());
        newSensor.setLocation(payload.get("location").toString());
        newSensor.setTemperature(payload.get("temperature").toString());
        newSensor.setHumidity(payload.get("humidity").toString());

        // Save
        bluetoothSensorRepository.save(newSensor);

        return new ResponseEntity(newSensor, HttpStatus.OK);
    }

    @PostMapping(value = "/sensors/{uuid}")
    public ResponseEntity insertDataIntoCamera(@PathVariable UUID uuid,
                                               @RequestBody Map<String, Object> payload){

        Optional<Sensor> sensor = bluetoothSensorRepository.findByUuid(uuid);

        if(!sensor.isPresent()){
            Sensor newSensor = new Sensor();
            newSensor.setUuid(uuid);
            newSensor.setName(payload.get("name").toString());
            newSensor.setLocation(payload.get("location").toString());
            newSensor.setTemperature(payload.get("temperature").toString());
            newSensor.setHumidity(payload.get("humidity").toString());
            return new ResponseEntity(newSensor, HttpStatus.OK);
        }

        sensor.get().setUuid(uuid);
        sensor.get().setName(payload.get("name").toString());
        sensor.get().setLocation(payload.get("location").toString());
        sensor.get().setTemperature(payload.get("temperature").toString());
        sensor.get().setHumidity(payload.get("humidity").toString());

        // Return
        return new ResponseEntity(sensor, HttpStatus.OK);
    }
}
