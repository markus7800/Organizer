package organizer;

import java.util.*;

public class Organizer {

	private HashMap<Integer,User> users;
	private HashMap<Integer, Appointment> appointments;

	static private Organizer instance;
	static public Organizer shared() {
		if (instance == null) {
			instance = new Organizer();
		}
		return instance;
	}

	private Organizer() {
		 users = new HashMap<Integer,User>();
		 appointments = new HashMap<Integer, Appointment>();
	}

	Integer currentUserId = 0;
	public Integer generateUserId() {
		currentUserId = currentUserId + 1;
		return currentUserId;
	}

	Integer currentAppointmentId = 0;
	public Integer generateApppointmentId() {
		currentAppointmentId = currentAppointmentId + 1;
		return currentAppointmentId;
	}

	public void addAppointment(Appointment app) {
		appointments.put(app.getId(), app);
	}

	public Appointment getAppointment(Integer id) {
		return appointments.get(id);
	}

	public void removeAppointment(Integer id) {
		Appointment app = getAppointment(id);
		app.cancel();
		appointments.remove(id);
	}

	public List<Appointment> getAppointments(Integer id){
		User user = getUser(id);
		if (user != null) {
			return new ArrayList<Appointment>(user.getAppointments());
		} else {
			return null;
		}
	}

	public List<Appointment> getAllAppointments(){
		return new ArrayList<Appointment>(appointments.values());
	}

	public void addUser(User user) {
		users.put(user.getId(), user);
	}

	public User getUser(Integer id) {
		return users.get(id);
	}

	public void removeUser(Integer id) {
		User user = getUser(id);
		user.cancelAppointments();
		users.remove(id);
	}

	public List<User> getAllUsers(){
		return new ArrayList<User>(users.values());
	}

}