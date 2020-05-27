package Context.aware.programming.Ipl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class IplApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(IplApplication.class);
		application.run(args);

	}

	// to setup database at start (not recommended, requires root access )
	public static void configPostgrestDB() {
		Process exec = null;
		try {
			exec = Runtime.getRuntime().exec(System.getProperty("user.dir") +"/databaseInit.sh");
			java.util.Scanner s = new java.util.Scanner(exec.getInputStream()).useDelimiter("\\A");
			System.out.println(s.next());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// to setup node listener at start (not recommended, requires root access )
	public static void configNodeListener() {
		try {
			Process process = new ProcessBuilder("npm", "install noble").directory(new File(System.getProperty("user.dir")  )).start();
			process.info();
			ProcessBuilder pb = new ProcessBuilder("node", System.getProperty("user.dir") + "/bluetoothListener.js");
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectError(ProcessBuilder.Redirect.INHERIT);
			Process p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
