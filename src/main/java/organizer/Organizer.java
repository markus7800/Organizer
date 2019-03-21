package organizer;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class Organizer {

	private Map<Long,User> users;
	private Map<Long, Appointment> appointments;

	public Organizer() {
		// users = new Map<long,User>();
		// appointments = new Map<long, Appointment>();
	}

	@GetMapping("v1/test")
	public void test() {
		System.out.println("test");
	}

	// MARK: - 
	@GetMapping("v1/users")
	public User users(){
		System.out.println("get");
		return new User(1,"Markus");
	}

	@PostMapping("v1/users")		
	public Map<String, String> addUser(@RequestParam Map<String, String> body) {
		System.out.println("post");
		return body;
	}

}