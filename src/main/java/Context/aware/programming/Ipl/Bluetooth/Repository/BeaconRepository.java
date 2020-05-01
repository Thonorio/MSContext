package Context.aware.programming.Ipl.Bluetooth.Repository;

import Context.aware.programming.Ipl.Bluetooth.Entity.Beacon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeaconRepository extends JpaRepository<Beacon, Integer> {

    Optional<Beacon> findById(Integer id);
}
