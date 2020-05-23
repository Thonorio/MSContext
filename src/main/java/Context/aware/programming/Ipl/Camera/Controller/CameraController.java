package Context.aware.programming.Ipl.Camera.Controller;

import Context.aware.programming.Ipl.Camera.Entity.Camera;
import Context.aware.programming.Ipl.Camera.Repository.CameraRepository;
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

    @GetMapping("/cameras")
    public List<Camera> allCameras(){
        return cameraRepository.findAll();
    }

    @PostMapping("/cameras")
    public ResponseEntity createCamera(@RequestBody Map<String, Object> payload){


        System.out.println("creating");
        UUID uuid = UUID.fromString(payload.get("uuid").toString());

        Optional<Camera> camera = cameraRepository.findByUuid(uuid);

        if(camera.isPresent()) {
            return new ResponseEntity("This camera already exists", HttpStatus.BAD_REQUEST);
        }

        Camera newCamera = new Camera();
        newCamera.setUuid(uuid);
        newCamera.setName(payload.get("name").toString());
        newCamera.setOccupied((Boolean) payload.get("occupied"));
        newCamera.setLocation(payload.get("location").toString());

        // Save
        cameraRepository.save(newCamera);

        return new ResponseEntity(newCamera, HttpStatus.OK);
    }

    @PostMapping(value = "/cameras/{uuid}")
    public ResponseEntity insertDataIntoCamera(@PathVariable UUID uuid,
                                               @RequestBody Map<String, Object> payload){

        Optional<Camera> camera = cameraRepository.findByUuid(uuid);

        if(!camera.isPresent()){
            Camera newCamera = new Camera();
            newCamera.setUuid(uuid);
            newCamera.setName(payload.get("name").toString());
            newCamera.setOccupied((Boolean) payload.get("occupied"));
            newCamera.setLocation(payload.get("location").toString());
        }else{
            camera.get().setUuid(uuid);
            camera.get().setName(payload.get("name").toString());
            camera.get().setOccupied((Boolean) payload.get("occupied"));
            camera.get().setLocation(payload.get("location").toString());
        }

        // Return
        return new ResponseEntity(camera, HttpStatus.OK);
    }
}
