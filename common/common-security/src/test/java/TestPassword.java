import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPassword {

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Test
	void getPassword() {
		System.out.println("123456: " + passwordEncoder.encode("admin123"));
	}

	@Test
	void testPasswordMatch() {
		String rawPassword = "admin123";
		String encodedPassword = "$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq";
		boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
		System.out.println("密码匹配结果: " + matches);
	}

}
