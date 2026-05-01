// Jmoore Final Project - Java
// Help Desk Ticketing System


import javax.swing.*;

// class created to open a ticket creation window
public class CreateTicketWindow extends JFrame {

    // construct create ticket window
    public CreateTicketWindow(TicketManager manager, TicketUI parentUI) {

        setTitle("Create Ticket");
        setSize(600, 380);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Create Ticket", SwingConstants.CENTER);
        title.setBounds(0, 10, 600, 30);
        add(title);
        
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 60, 100, 20);
        add(titleLabel);

        JTextField titleField = new JTextField();
        titleField.setBounds(120, 60, 400, 25);
        add(titleField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 100, 100, 20);
        add(descLabel);

        JTextArea descArea = new JTextArea();
        descArea.setBounds(120, 100, 400, 60);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        add(descArea);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(20, 170, 100, 20);
        add(categoryLabel);
        
        // use category enum for dropdown
        JComboBox<Ticket.Category> categoryBox = new JComboBox<>(Ticket.Category.values());
        categoryBox.setBounds(120, 170, 200, 25);
        add(categoryBox);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(20, 200, 100, 20);
        add(priorityLabel);
        
        // use priority level enum for dropdown
        JComboBox<Ticket.Priority> priorityBox = new JComboBox<>(Ticket.Priority.values());
        priorityBox.setBounds(120, 200, 200, 25);
        add(priorityBox);

        JLabel teamLabel = new JLabel("Assigned Team:");
        teamLabel.setBounds(20, 230, 120, 20);
        add(teamLabel);
        
        // use assigned team enum for dropdown
        JComboBox<Ticket.AssignedTeam> teamBox = new JComboBox<>(Ticket.AssignedTeam.values());
        teamBox.setBounds(120, 230, 200, 25);
        add(teamBox);

        JButton createButton = new JButton("Create Ticket");
        createButton.setBounds(120, 280, 200, 30);
        add(createButton);

        createButton.addActionListener(e -> {

        	// get text and combo box selections to store in ticket object
            String titleText = titleField.getText();
            String description = descArea.getText();
            
            // data validation for title and description
            if (titleText.isEmpty() || description.isEmpty()) {
            	JOptionPane.showMessageDialog(this, "Title and Description are required.", "Missing Information", JOptionPane.ERROR_MESSAGE);
            	return;
            }
            
            Ticket.Category category = (Ticket.Category) categoryBox.getSelectedItem();
            Ticket.Priority priority = (Ticket.Priority) priorityBox.getSelectedItem();
            Ticket.AssignedTeam team = (Ticket.AssignedTeam) teamBox.getSelectedItem();
            
            // validation for selecting dropdowns
            if (category == Ticket.Category.__ ||
            		priority == Ticket.Priority.__ ||
            		team == Ticket.AssignedTeam.__) {
            	
            	JOptionPane.showMessageDialog(this, "Please select an option from all dropdowns.", "Missing Selection", JOptionPane.ERROR_MESSAGE);
            	return;
            }

            manager.addTicket(titleText, description, category, priority, team);
            parentUI.refreshTicketList();
            dispose();
        });
        
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(420, 280, 100, 30);
        add(closeButton);
        closeButton.addActionListener(e -> dispose());

		setLocationRelativeTo(null); // center the create ticket window
        setVisible(true);
    }
}