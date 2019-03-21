package organizer;

import java.util.*;

public class Appointment {
	private Integer id;
	private String title;
	private Date startdate;
	private Date enddate;
	private Set<User> attendees;

	public Appointment(Integer id, String title, Date startdate, Date enddate, Set<User> attendees) {
		this.id = id;
		this.title = title;
		this.startdate = startdate;
		this.enddate = enddate;
		this.attendees = attendees;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getStartDate() {
		return startdate;
	}

	public Date getEndDate() {
		return enddate;
	}

	public List<Integer> getAttendees() {
		List<Integer> ids = new ArrayList<Integer>();
		for (User attendee: attendees) {
			ids.add(attendee.getId());
		}
		return ids;
	}

	public void cancel() {
		for (User attendee: attendees) {
			attendee.removeAppointment(this);
		}
	}

	public void removeUser(User user) {
		attendees.remove(user);
	}

	public Boolean equals(Appointment other) {
		return this.id == other.id;
	}
}