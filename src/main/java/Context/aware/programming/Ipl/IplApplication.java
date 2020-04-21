package Context.aware.programming.Ipl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tinyb.BluetoothDevice;
import tinyb.BluetoothManager;

import java.util.List;

@SpringBootApplication
public class IplApplication {

	public static void main(String[] args) {

		BluetoothManager manager = BluetoothManager.getBluetoothManager();
		SpringApplication.run(IplApplication.class, args);
		System.out.println("hi");
	}

	static BluetoothDevice getDevice(String address) throws InterruptedException  {
		BluetoothManager manager = BluetoothManager.getBluetoothManager();
		BluetoothDevice sensor = null;
		for (int i = 0; (i < 15) ; ++i) {
			List<BluetoothDevice> list = manager.getDevices();
			for (BluetoothDevice device : list) {
				/*
				 * Here we check if the address matches.
				 */
				if (device.getAddress().equals(address))
					sensor = device;
			}
			if (sensor != null) {
				return sensor;
			}
			Thread.sleep(4000);
		}
		return null;
	}
}
