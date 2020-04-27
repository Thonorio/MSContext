package Context.aware.programming.Ipl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tinyb.BluetoothManager;


@SpringBootApplication
public class IplApplication {

	public static void main(String[] args) {
		IplApplication.class.getProtectionDomain().getCodeSource().getLocation();
		SpringApplication.run(IplApplication.class, args);
		BluetoothManager manager = BluetoothManager.getBluetoothManager();
	}

}
