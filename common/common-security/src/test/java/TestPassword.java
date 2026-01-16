import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPassword {

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Test
	void getPassword() {
		System.out.println("admin123: " + passwordEncoder.encode("admin123"));
	}

	@Test
	void testPasswordMatch() {
		String rawPassword = "admin123";
		String encodedPassword = "$2a$10$9Ks901oebph0KW8jok9c1ew0mrGFW9LfAfuJjxTSsZWDFD03dvrJ.";
		boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
		System.out.println("密码匹配结果: " + matches);
	}

}
