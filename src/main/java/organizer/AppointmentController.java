package organizer;

import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import org.springframework.http.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

@RestController
public class AppointmentController {

	private Organizer organizer = Organizer.shared();

	@GetMapping("appointments")
	public List<Appointment> getAppointments() {
		return organizer.getAllAppointments();
	}

	@GetMapping("appointments/{id}")
	public ResponseEntity getAppointments(@PathVariable Integer id) {
		List<Appointment> apps = organizer.getAppointments(id);
		if (apps == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with id " + id + " not found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(apps);
	}

	@PostMapping("appointments")
	public ResponseEntity createAppointment(@RequestBody Map<String,Object> body) {
		
		// Check if all field are there
		String title = (String) body.get("title");
		String startDateStr = (String) body.get("startDate");
		String endDateStr = (String) body.get("endDate");
		List<Integer> attendeeIds = (List<Integer>) body.get("attendees");

		if (title == null || startDateStr == null || endDateStr == null || attendeeIds == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RequestBody has wrong format.");
		}

		if (attendeeIds.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment must have atleas 1 attendee.");
		}

		// Check if dates are in correct format
		Date startDate;
		Date endDate;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			startDate = formatter.parse(startDateStr);
			endDate = formatter.parse(endDateStr);
		} catch(Exception x) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date is not in correct format.");
		}

		// Check if all ids match users
		List<User> attendees = new ArrayList<User>();

		for (Integer id : attendeeIds) {
			User user = organizer.getUser(id);
			if (user == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Attendee with id " + id + " not found.");
			}
			attendees.add(user);
		}

		// check if all attendees have time
		for (User attendee : attendees) {
			if (!attendee.checkTime(startDate, endDate)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Attendee with id " + attendee.getId() + " is busy at that time.");
			}
		}

		// set appointment
		Appointment newApp = new Appointment(organizer.generateApppointmentId(), title, startDate, endDate, attendees);
		attendees.forEach(a->a.addAppointment(newApp));
		organizer.addAppointment(newApp);

		return ResponseEntity.status(HttpStatus.OK).body(newApp);
	}


	@PatchMapping(path="appointments/{id}", consumes="application/json")	
	public ResponseEntity updateAppointment(@PathVariable Integer id, @RequestBody Map<String,Object> body) {

		Appointment app = organizer.getAppointment(id);
		if (app == null) {
			return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Appointment with id " + id + " not found.");
		}

		return createAppointment(body);
	}

	@DeleteMapping(path="appointments/{id}")	
	public ResponseEntity removeAppointment(@PathVariable Integer id) {

		organizer.removeAppointment(id);
		return ResponseEntity.status(HttpStatus.OK).body("Appointment succesfully removed.");
	}
}