import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String admin = encoder.encode("admin");
        System.out.println(admin);
    }
}
