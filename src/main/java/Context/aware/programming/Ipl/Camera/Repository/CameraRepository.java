package Context.aware.programming.Ipl.Camera.Repository;

import Context.aware.programming.Ipl.Camera.Entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {

    Optional<Camera> findByUuid(UUID uuid);
}
