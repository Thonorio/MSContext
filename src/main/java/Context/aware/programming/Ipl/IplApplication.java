package Context.aware.programming.Ipl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.sputnikdev.bluetooth.URL;
import org.sputnikdev.bluetooth.manager.CharacteristicGovernor;
import org.sputnikdev.bluetooth.manager.impl.BluetoothManagerBuilder;
import tinyb.BluetoothManager;

import java.util.concurrent.ExecutionException;


@SpringBootApplication
public class IplApplication {

	public static void main(String[] args) {
		//SpringApplication.run(IplApplication.class, args);
		//IplApplication.class.getProtectionDomain().getCodeSource().getLocation();
		//BluetoothManager manager = BluetoothManager.getBluetoothManager();
		try {
			new BluetoothManagerBuilder()
					.withTinyBTransport(true)
					.withBlueGigaTransport("^*.$")
					.build()
					.getCharacteristicGovernor(new URL("/XX:XX:XX:XX:XX:XX/F7:EC:62:B9:CF:1F/"
							+ "0000180f-0000-1000-8000-00805f9b34fb/00002a19-0000-1000-8000-00805f9b34fb"), true)
					.whenReady(CharacteristicGovernor::read)
					.thenAccept(data -> {
						System.out.println("Battery level: " + data[0]);
					}).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
