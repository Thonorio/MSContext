package Context.aware.programming.Ipl;

import Context.aware.programming.Ipl.Camera.Entity.Camera;
import Context.aware.programming.Ipl.Camera.Repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CameraController {

    @Autowired
    CameraRepository cameraRepository;

    @GetMapping("/camera")
    public List<Camera> allCameras(){
        return cameraRepository.findAll();
    }

    @PostMapping("/camera/{id}")
    public boolean insertDataIntoCamera(@PathVariable String id){
        return true;
    }
}
