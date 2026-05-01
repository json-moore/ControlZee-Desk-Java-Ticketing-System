// Jmoore Final Project - Java
// Help Desk Ticketing System


import java.util.ArrayList;

// class manages tickets using methods to take in data from the ticketUI / backend
public class TicketManager {
	
	private ArrayList<Ticket> tickets = new ArrayList<>(); // store tickets in a list and auto-increment to create ticket ID's
	private int nextId = 1;
	
	
	// create the ticket object in memory and add it to the arraylist
	public void addTicket(String title, String description, Ticket.Category category, Ticket.Priority priority, Ticket.AssignedTeam assignedTeam) {
		
		Ticket ticket = new Ticket(nextId++, title, description, category , Ticket.Status.OPEN, priority, assignedTeam);
		tickets.add(ticket);
	}
	
	// get all existing tickets and put them in a new list
	public ArrayList<Ticket> getTickets(){
		return new ArrayList<>(tickets);
	}
	
	// get ticket ID method - using for updates to existing tickets
	public Ticket getTicketById(int id) {
		for (int i = 0; i < tickets.size(); i++) {
			Ticket tkt = tickets.get(i);
			
			if (tkt.getID() == id)
			{
				return tkt;
			}
		}
		return null;
	}
	

	
	// add a note to a ticket
	public void addNoteToTicket(int id, String note) {
		Ticket tkt = getTicketById(id);
		
		if (tkt != null) {
			tkt.addNote(note);
		}
	}
	
	// deletes a ticket at the selected index
	public void deleteTicket(int id) {
		Ticket tkt = getTicketById(id);
		
		if (tkt != null) {
			tickets.remove(tkt);
		}
	}
	
	// update status of a ticket
	public void updateStatus(int id, Ticket.Status status) {
		Ticket tkt = getTicketById(id);
		
		if (tkt != null) {
			tkt.setStatus(status);
		}
	}
	
	// assign a ticket to a team
	public void assignTeam(int id, Ticket.AssignedTeam team) {
	Ticket tkt = getTicketById(id);
	
		if (tkt != null) {
			tkt.setAssignedTeam(team);
		}
	}
}