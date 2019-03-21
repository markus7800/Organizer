package organizer;

import java.util.*;

public class User {

	private final Integer id;
	private String name;

	private Set<Appointment> appointments;

	public User(Integer id, String name) {
		this.id = id;
		this.name = name;
        this.appointments = new HashSet<Appointment>();
	}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<Appointment>(appointments);
    }

    public void setName(String name){
    	this.name = name;
    }

    public boolean checkTime(Date start, Date end) {
        for (Appointment app: appointments) {
            boolean a = start.after(app.getEndDate());
            boolean b = end.before(app.getStartDate());

            if (!(a || b)) {
                return false;
            }
        }
        return true;
    }

    public void addAppointment(Appointment app) {
        appointments.add(app);
    }
}