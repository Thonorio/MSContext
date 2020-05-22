package Context.aware.programming.Ipl.Camera.Entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Camera {
    @Id
    private UUID uuid;

    private String name;

    private boolean occupied;

    private String location;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
