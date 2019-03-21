package organizer;

import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import org.springframework.http.*;
import java.util.Map;
import java.util.HashMap;

@RestController
public class UserController {

	private Organizer organizer = Organizer.shared();

	@GetMapping("v1/users")
	public User getUsers(){
		System.out.println("get");
		return new User(1,"Markus");
	}

	@PostMapping(path="v1/users", consumes="application/json")		
	public ResponseEntity addUser(@RequestBody Map<String,String> body) throws Exception {
	
		String name = body.get("name");
		if (name == null) {
			return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Name field is missing.");
		}

		Integer id = organizer.generateId();
		User newUser = new User(id,(String) name);
		organizer.addUser(newUser);

		return ResponseEntity.status(HttpStatus.OK).body(newUser);
	}

	@PatchMapping(path="v1/users/{id}", consumes="application/json")	
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

	@DeleteMapping(path="v1/users/{id}")	
	public ResponseEntity removeUser(@PathVariable Integer id) {

		organizer.removeUser(id);
		return ResponseEntity.status(HttpStatus.OK).body("User succesfully removed.");
	}

}