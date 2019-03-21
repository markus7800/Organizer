package organizer;

import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import org.springframework.http.*;
import java.util.Map;
import java.util.List;

@RestController
public class UserController {

	private Organizer organizer = Organizer.shared();

	@GetMapping("users")
	public List<User> getUsers(){
		return organizer.getAllUsers();
	}

	@GetMapping("users/{id}")
	public ResponseEntity getUser(@PathVariable Integer id) {
		
		User user = organizer.getUser(id);
		if (user == null) {
			return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("User with id " + id + " not found.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping(path="users", consumes="application/json")		
	public ResponseEntity addUser(@RequestBody Map<String,String> body) {
	
		String name = body.get("name");
		if (name == null) {
			return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Name field is missing.");
		}

		Integer id = organizer.generateUserId();
		User newUser = new User(id,(String) name);
		organizer.addUser(newUser);

		return ResponseEntity.status(HttpStatus.OK).body(newUser);
	}

	@PatchMapping(path="users/{id}", consumes="application/json")	
	public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody Map<String,String> body) {

		User user = organizer.getUser(id);
		if (user == null) {
			return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("User with id " + id + " not found.");
		}

		String name = body.get("name");
		user.setName(name);

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@DeleteMapping(path="users/{id}")	
	public ResponseEntity removeUser(@PathVariable Integer id) {

		organizer.removeUser(id);
		return ResponseEntity.status(HttpStatus.OK).body("User succesfully removed.");
	}

}