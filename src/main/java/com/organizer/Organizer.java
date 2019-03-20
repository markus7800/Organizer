package com.organizer;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class Organizer {

	@GetMapping("/users")
	public User test(){
		System.out.println("get");
		return new User(1,"Markus");
	}

	@PostMapping("/users")		
	public Map<String, String> addUser(@RequestParam Map<String, String> body) {
		System.out.println("post");
		return body;
	}

}