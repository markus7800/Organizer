package organizer;

import java.util.Date;

public class Appointment {
	private long id;
	private String title;
	private Date startdate;
	private Date enddate;

	public Appointment(long id, String title, Date startdate, Date enddate) {
		this.id = id;
		this.title = title;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public long getId() {
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
}