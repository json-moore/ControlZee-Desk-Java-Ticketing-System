// Jmoore Final Project - Java
// Help Desk Ticketing System


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Image;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

// GUI class / frontend
public class TicketUI extends JFrame {

	private TicketManager manager = new TicketManager(); // import ticket manager to use manager methods
	private Ticket selectedTicket; // select ticket at id (UI user selection)
	
	private javax.swing.DefaultListModel<String> ticketListModel = 
			new javax.swing.DefaultListModel<>(); // create list to store tickets
	
	private javax.swing.JList<String> ticketList = 
			new javax.swing.JList<>(ticketListModel); // string title list of tickets to display in the UI
	
	// ticket UI constructor
	public TicketUI() {
		setTitle("Control-Zee Desk - IT Ticketing System");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		
		// center top title
		JLabel title = new JLabel("Control-Zee Desk | Ticketing System", SwingConstants.CENTER);
		title.setBounds(0, 0, 600, 50);
		add(title);
		
		
		
		/* LEFT PANEL - STORES TICKET LIST *////////////////////
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBounds(0, 50, 400, 500);
		
		JLabel openTicketsLabel = new JLabel("Ticket View");
		openTicketsLabel.setBounds(150, 10, 200, 20);
		leftPanel.add(openTicketsLabel);
		add(leftPanel);
		
		ticketList.setBounds(10, 40, 360, 500);
		leftPanel.add(ticketList);
		
		// get ticket id - selected index determined by which ticket in the list is clicked
		ticketList.addListSelectionListener(e -> {
			int index = ticketList.getSelectedIndex();
			
			if (index != -1) {
				selectedTicket = manager.getTickets().get(index);
			}
		});
		////////////////////////////////////////////////////////
		
		

		/* RIGHT PANEL - STORES ACTION BUTTONS *//////////////////
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBounds(400, 50, 400, 550);
		JLabel actionsLabel = new JLabel("Actions");
		actionsLabel.setBounds(60, 10, 200, 20);
		rightPanel.add(actionsLabel);
		add(rightPanel);
		
		
		JButton createButton = new JButton("Create Ticket");
		createButton.setBounds(5, 50, 150, 30);
		rightPanel.add(createButton);
		
		createButton.addActionListener(e -> { // create button action - open create ticket window
		    new CreateTicketWindow(manager, this);
		});

		
		JButton viewButton = new JButton("View Ticket");
		viewButton.setBounds(5, 90, 150, 30);
		rightPanel.add(viewButton);
		
		viewButton.addActionListener(e -> { // view ticket action - opens view ticket window
		    if (selectedTicket == null) {
		        JOptionPane.showMessageDialog(this, "No ticket selected.");
		        return;
		    }
		    new ViewTicketWindow(selectedTicket, manager, this);
		    });

		
		JButton deleteButton = new JButton("Delete Ticket");
		deleteButton.setBounds(5, 130, 150, 30);
		rightPanel.add(deleteButton);
		
		deleteButton.addActionListener(e -> { // delete ticket action - deletes selected ticket using selected ticket index
		    if (selectedTicket == null) {
		        JOptionPane.showMessageDialog(this, "No ticket selected.");
		        return;
		    }

		    int confirm = JOptionPane.showConfirmDialog(this, "Delete this ticket?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

		    if (confirm == JOptionPane.YES_OPTION) {
		        manager.deleteTicket(selectedTicket.getID());
		        selectedTicket = null;
		        refreshTicketList(); // refresh the ticket list to show the new list after a ticket is deleted
		    }
		});
		
		// add logo image to right panel of main screen
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/CZDLogo.png"));		
		Image img = icon.getImage();
		Image resized = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resized);
		JLabel logoLabel = new JLabel(resizedIcon);
		logoLabel.setBounds(-20, 180, 200, 200);
		rightPanel.add(logoLabel);
		
		
		// report button for exporting tickets
		JButton reportButton = new JButton("Export Report");
		reportButton.setBounds(5, 415, 150, 30);
		rightPanel.add(reportButton);

		reportButton.addActionListener(e -> {
		    exportAllTicketsReport();
		});
		
		
		
        JButton closeButton = new JButton("EXIT SYSTEM");
        closeButton.setBounds(5, 460, 150, 30);
        rightPanel.add(closeButton);

        closeButton.addActionListener(e -> dispose());
		//////////////////////////////////////////////////////
		
		setLocationRelativeTo(null); // center the ticket ui
		
		setVisible(true);
		refreshTicketList(); // call refresh ticket list to show ticket list after changes are made
	}
	
	
	
	// refresh ticket display
	public void refreshTicketList() {
		ticketListModel.clear();

		ArrayList<Ticket> tickets = manager.getTickets();
		
		// loop through ticket list and add tickets to ticket pane
		for (int i = 0; i < tickets.size(); i++) {
		    Ticket t = tickets.get(i);
		    
		    // change priority color based on priority
		    String priorityColor = switch (t.getPriority().toString()) {
			    case "URGENT" -> "red";
			    case "HIGH" -> "orange";
			    case "MEDIUM" -> "blue";
			    default -> "gray";
		    };

		    // change status color based on status
			String statusColor = switch (t.getStatus().toString()) {
			    case "OPEN" -> "green";
			    case "IN_PROGRESS" -> "orange";
			    case "CLOSED" -> "gray";
			    default -> "black";
			};

			ticketListModel.addElement(
			    "<html>" +
			    "Ticket ID: " + t.getID() +
			    " - [Priority: <span style='color:" + priorityColor + "'>" + t.getPriority() + "</span>]" +
			    " [Status: <span style='color:" + statusColor + "'>" + t.getStatus() + "</span>]" +
			    "<br>" +
			    t.getTitle() +
			    "</html>"
			);
		}
	}
	

	// this method exports all tickets in the ticket pane to a file or "report"
	public void exportAllTicketsReport() {
	    try {
	        FileWriter writer = new FileWriter("reports/TicketReport.txt");

	        writer.write("Ticket Report\n");
	        writer.write("====================\n\n");

	        for (Ticket t : manager.getTickets()) {
	            writer.write("ID: " + t.getID() + "\n");
	            writer.write("Title: " + t.getTitle() + "\n");
	            writer.write("Status: " + t.getStatus() + "\n");
	            writer.write("Priority: " + t.getPriority() + "\n");
	            writer.write("Team: " + t.getAssignedTeam() + "\n");

	            writer.write("Notes:\n" + t.getNoteString() + "\n");
	            writer.write("--------------------\n\n");
	        }
	        writer.close();

	        JOptionPane.showMessageDialog(this,
	            "Report exported successfully.");

	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(this,
	            "Error exporting report: " + e.getMessage()); // handle and display error if file cannot export
	    }
	}
	
	// execute ticket ui
	public static void main(String[] args) {
		new TicketUI();
	}
}
