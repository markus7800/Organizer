package organizer;

import java.util.List;

public class User {

	private final long id;
	private final String name;

	private List<Appointment> appointment;

	public User(long id, String name) {
		this.id = id;
		this.name = name;
	}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}