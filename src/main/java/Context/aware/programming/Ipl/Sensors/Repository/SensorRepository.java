package Context.aware.programming.Ipl.Sensors.Repository;

import Context.aware.programming.Ipl.Sensors.Entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Optional<Sensor> findByUuid(UUID uuid);

    Optional<Sensor> findByName(String name);

    Optional<Sensor> findByLocation(String location);
}
