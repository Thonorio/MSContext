package Context.aware.programming.Ipl.Camera.Repository;

import Context.aware.programming.Ipl.Camera.Entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {

    Optional<Camera> findById(Integer id);
}
