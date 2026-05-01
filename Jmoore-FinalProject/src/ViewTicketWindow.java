// Jmoore Final Project - Java
// Help Desk Ticketing System


import javax.swing.*;

// created class to view tickets in a new window
public class ViewTicketWindow extends JFrame {

    public ViewTicketWindow(Ticket ticket, TicketManager manager, TicketUI parentUI) {

        setTitle("View Ticket");
		setSize(600,600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Ticket Details");
        title.setBounds(20, 10, 450, 30);
        add(title);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(area); // scroll pane added to view the notes at the bottom of the text area
        scrollPane.setBounds(20, 50, 545, 150);
        add(scrollPane);

        // text displayed in scroll window of view ticket
        Runnable refreshText = () -> area.setText(
                "Ticket ID:\t" + ticket.getID() + "\n" +
                "Title:\t" + ticket.getTitle() + "\n" +
                "Description:\t" + ticket.getDescription() + "\n" +
                "Category:\t" + ticket.getCategory() + "\n" +
                "Priority:\t" + ticket.getPriority() + "\n" +
                "Status:\t" + ticket.getStatus() + "\n" +
                "Assigned To:\t" + ticket.getAssignedTeam() + "\n\n" +
                "Notes:\n" + ticket.getNoteString()
        );
        refreshText.run();

        
        
        JLabel noteLabel = new JLabel("Add Note:");
        noteLabel.setBounds(20, 220, 100, 20);
        add(noteLabel);

        JTextArea noteField = new JTextArea();
        noteField.setBounds(20, 245, 545, 50);
        noteField.setLineWrap(true);
        noteField.setWrapStyleWord(true);
        add(noteField);

        // add note buttton / action
        JButton addNoteButton = new JButton("Add Note");
        addNoteButton.setBounds(20, 300, 120, 30);
        add(addNoteButton);

        addNoteButton.addActionListener(e -> {
            String note = noteField.getText();
            
            // data validation for note field if blank
            if (noteField.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(this, "No note was added.", "Missing Information", JOptionPane.ERROR_MESSAGE);
            }
            
            if (!note.trim().isEmpty()) {                
                ticket.addNote("HelpDesk: " + note);
                JOptionPane.showMessageDialog(this, "Note added to ticket.");
                noteField.setText(""); // clear note field
                refreshText.run();
                parentUI.refreshTicketList();
            }

        });

        
        
        JLabel teamLabel = new JLabel("Reassign Team:");
        teamLabel.setBounds(20, 355, 120, 20);
        add(teamLabel);

        // reassign team combo box
        JComboBox<Ticket.AssignedTeam> teamBox = new JComboBox<>(Ticket.AssignedTeam.values()); // populated team combo box
        teamBox.setBounds(120, 355, 200, 25);
        teamBox.setSelectedItem(ticket.getAssignedTeam());
        add(teamBox);

        // reassign team button and action
        JButton reassignButton = new JButton("Reassign");
        reassignButton.setBounds(330, 355, 120, 25);
        add(reassignButton);

        reassignButton.addActionListener(e -> {
        	// combobox validation for assigning teams
        	if (teamBox.getSelectedItem() == Ticket.AssignedTeam.__) {
            	JOptionPane.showMessageDialog(this, "Not a valid selection.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
            	return;
        	}
        	
            ticket.setAssignedTeam((Ticket.AssignedTeam) teamBox.getSelectedItem());            
            JOptionPane.showMessageDialog(this, "Ticket Reassigned.");
            refreshText.run();
            parentUI.refreshTicketList();
        });

        
        
        JLabel statusLabel = new JLabel("Change Status:");
        statusLabel.setBounds(20, 400, 120, 20);
        add(statusLabel);

        JComboBox<Ticket.Status> statusBox = new JComboBox<>(Ticket.Status.values()); // populated ticket status combo box
        statusBox.setBounds(120, 400, 200, 25);
        statusBox.setSelectedItem(ticket.getStatus());
        add(statusBox);

        // update status button and action
        JButton updateStatusButton = new JButton("Update Status");
        updateStatusButton.setBounds(330, 400, 120, 25);
        add(updateStatusButton);

        updateStatusButton.addActionListener(e -> {
        	// combo box validation for status dropdown
        	if (statusBox.getSelectedItem() == Ticket.Status.__) {
            	JOptionPane.showMessageDialog(this, "Not a valid selection.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
            	return;
        	}
        	
            ticket.setStatus((Ticket.Status) statusBox.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Ticket Status Updated.");
            refreshText.run();
            parentUI.refreshTicketList();
        });

        
        
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(20, 510, 80, 30);
        add(closeButton);

        closeButton.addActionListener(e -> dispose());

		setLocationRelativeTo(null); // center the view ticket window
        setVisible(true);
    }
}