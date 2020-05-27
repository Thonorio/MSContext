package Context.aware.programming.Ipl.Camera.Controller;

import Context.aware.programming.Ipl.Camera.Entity.Camera;
import Context.aware.programming.Ipl.Camera.Repository.CameraRepository;
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
public class CameraController {

    @Autowired
    CameraRepository cameraRepository;

    @Autowired
    SensorRepository sensorRepository;

    @GetMapping("/locations")
    public List<Camera> allCameras(){
        return cameraRepository.findAll();
    }

    @PostMapping("/locations")
    public ResponseEntity createCamera(@RequestBody Map<String, Object> payload){

        UUID uuid = UUID.fromString(payload.get("uuid").toString());

        Optional<Camera> camera = cameraRepository.findByUuid(uuid);

        if(camera.isPresent()) {
            return new ResponseEntity("This camera already exists", HttpStatus.BAD_REQUEST);
        }

        Camera newCamera = new Camera();
        newCamera.setUuid(uuid);
        newCamera.setName(payload.get("name").toString());
        newCamera.setOccupied(Boolean.parseBoolean(payload.get("occupied").toString()));
        newCamera.setLocation(payload.get("location").toString());

        // Set location of sensor
        if (payload.get("sensor") != null){
            Optional<Sensor> sensor = sensorRepository.findByName(payload.get("sensor").toString());
            sensor.get().setLocation(payload.get("location").toString());
        }

        // Save
        cameraRepository.save(newCamera);

        return new ResponseEntity(newCamera, HttpStatus.OK);
    }

    @PostMapping(value = "/locations/{uuid}")
    public ResponseEntity insertDataIntoCamera(@PathVariable UUID uuid,
                                               @RequestBody Map<String, Object> payload){

        Optional<Camera> camera = cameraRepository.findByUuid(uuid);

        // Set location of sensor
        if (payload.get("sensor") != null){
            Optional<Sensor> sensor = sensorRepository.findByName(payload.get("sensor").toString());
            sensor.get().setLocation(payload.get("location").toString());
        }

        if(!camera.isPresent()){
            Camera newCamera = new Camera();
            newCamera.setUuid(uuid);
            newCamera.setName(payload.get("name").toString());
            newCamera.setOccupied(Boolean.parseBoolean(payload.get("occupied").toString()));
            newCamera.setLocation(payload.get("location").toString());

            cameraRepository.save(newCamera);
            return new ResponseEntity(newCamera, HttpStatus.OK);
        }

        camera.get().setUuid(uuid);
        camera.get().setName(payload.get("name").toString());
        camera.get().setOccupied(Boolean.parseBoolean(payload.get("occupied").toString()));
        camera.get().setLocation(payload.get("location").toString());

        cameraRepository.save(camera.get());
        // Return
        return new ResponseEntity(camera, HttpStatus.OK);
    }
}
