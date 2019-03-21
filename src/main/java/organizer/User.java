package organizer;

import java.util.List;

public class User {

	private final Integer id;
	private String name;

	private List<Appointment> appointment;

	public User(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
    	this.name = name;
    }
}