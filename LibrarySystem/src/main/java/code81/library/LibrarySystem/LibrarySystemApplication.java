package code81.library.LibrarySystem;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);
		String hashedPassword;
		hashedPassword = BCrypt.hashpw("admin", BCrypt.gensalt(12));
System.out.println("hashedPassword: " + hashedPassword);	}

}
