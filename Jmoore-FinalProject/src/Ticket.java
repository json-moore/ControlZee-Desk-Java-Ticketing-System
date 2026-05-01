// Jmoore Final Project - Java
// Help Desk Ticketing System


import java.util.ArrayList;

public class Ticket {
	
	// ticket attributes
	private int id;
	private String title;
	private String description;
	private Category category;
	private Status status;
	private Priority priority;
	private AssignedTeam assignedTeam;
	private ArrayList<String> notes = new ArrayList<>();
	
	// ticket  constructor
	public Ticket(int id, String title, String description, Category category,
			Status status, Priority priority, AssignedTeam assignedTeam) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.status = status;
		this.priority = priority;
		this.assignedTeam = assignedTeam;
		this.notes = new ArrayList<>();
	}
	
	
	
	// getter methods
	public int getID() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public Category getCategory() {
		return category;
	}
	public Status getStatus() {
		return status;
	}
	public Priority getPriority() {
		return priority;
	}
	public AssignedTeam getAssignedTeam() {
		return assignedTeam;
	}
	public ArrayList<String> getNotes()
	{
		return new ArrayList<>(notes);
	}

	
	// setter methods for updating ticket attributes
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;	
	}
	public void setAssignedTeam(AssignedTeam assignedTeam) {
		this.assignedTeam = assignedTeam;
	}
	public void addNote(String note) {
		notes.add(note);
	}
	public String getNoteString() {
		return String.join("\n", notes); // use for UI
	}
	
	
	// create enums to populate dropdowns for ticket UI form
	public enum Status{
		__,
		OPEN,
		IN_PROGRESS,
		RESOLVED,
		CLOSED
	}
	public enum Priority {
		__,
	    LOW,
	    MEDIUM,
	    HIGH,
	    URGENT
	}
	public enum Category{
		__,
		HARDWARE,
		SOFTWARE,
		NETWORK,
		ACCOUNT_ACCESS,
		PRINTER,
		EMAIL,
		SECURITY,
		OTHER
	}
	public enum AssignedTeam {
		__,
	    IT_SUPPORT,
	    NETWORK_TEAM,
	    SECURITY_TEAM,
	    SOFTWARE_TEAM,
	    HARDWARE_TEAM
	}
}
