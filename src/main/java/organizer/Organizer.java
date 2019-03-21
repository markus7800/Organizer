package organizer;

import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import org.springframework.http.*;
import java.util.Map;
import java.util.HashMap;

@RestController
public class Organizer {

	private HashMap<Integer,User> users;
	private HashMap<Long, Appointment> appointments;

	static private Organizer instance;
	static public Organizer shared() {
		if (instance == null) {
			instance = new Organizer();
		}
		return instance;
	}

	private Organizer() {
		 users = new HashMap<Integer,User>();
		 appointments = new HashMap<Long, Appointment>();
	}
	Integer currentId = 0;
	public Integer generateId() {
		currentId = currentId + 1;
		return currentId;
	}

	public void addUser(User user) {
		users.put(user.getId(), user);
	}

	public User getUser(Integer id) {
		return users.get(id);
	}

	public void removeUser(Integer id) {
		// TODO: appointments
		users.remove(id);
	}

}