import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterForm implements ActionListener{
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel(new GridLayout(7, 2));
	JPanel emptyPanel = new JPanel();
	JPanel genderPanel = new JPanel(new GridLayout(1, 2));
	JPanel rolePanel = new JPanel(new GridLayout(1, 2));
	JButton backButton = new JButton("Back");
	JButton registerButton = new JButton("Register");
	JLabel createAnAccountLabel = new JLabel("Create An Account");
	JLabel usernameLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	JLabel genderLabel = new JLabel("Gender");
	JLabel countryLabel = new JLabel("Country");
	JLabel roleLabel = new JLabel("Choose a role :");
	JTextField usernameTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JRadioButton maleRadioButton = new JRadioButton("Male");
	JRadioButton femaleRadioButton = new JRadioButton("Female");
	JRadioButton playerRadioButton = new JRadioButton("Player");
	JRadioButton developerRadioButton = new JRadioButton("Developer");
	ButtonGroup genderButtonGroup = new ButtonGroup();
	ButtonGroup roleButtonGroup = new ButtonGroup();
	String[] countryList = {"Select a Country", "Indonesia", "America", "England", "Malaysia", "Singapore", "South Korea", "German"};
	JComboBox<String> countryComboBox = new JComboBox<>(countryList);
	Font headerFont = new Font("Calibri", Font.BOLD, 28);
	Font contentFont = new Font("Calibri", Font.PLAIN, 20);
	Font buttonFont = new Font("Calibri", Font.BOLD, 20);
	
	public RegisterForm() {
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBorder(new EmptyBorder(0, 75, 0, 75));
		createAnAccountLabel.setFont(headerFont);
		usernameLabel.setFont(contentFont);
		passwordLabel.setFont(contentFont);
		usernameTextField.setForeground(Color.WHITE);
		usernameTextField.setFont(contentFont);
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(contentFont);
		genderLabel.setFont(contentFont);
		countryLabel.setFont(contentFont);
		roleLabel.setFont(contentFont);
		backButton.setFont(buttonFont);
		registerButton.setFont(buttonFont);
		maleRadioButton.setFont(contentFont);
		femaleRadioButton.setFont(contentFont);
		countryComboBox.setFont(contentFont);
		playerRadioButton.setFont(contentFont);
		developerRadioButton.setFont(contentFont);
		createAnAccountLabel.setForeground(Color.WHITE);
		usernameLabel.setForeground(Color.WHITE);
		passwordLabel.setForeground(Color.WHITE);
		genderLabel.setForeground(Color.WHITE);
		genderPanel.setForeground(Color.DARK_GRAY);
		maleRadioButton.setForeground(Color.WHITE);
		femaleRadioButton.setForeground(Color.WHITE);
		countryLabel.setForeground(Color.WHITE);
		roleLabel.setForeground(Color.WHITE);
		rolePanel.setForeground(Color.DARK_GRAY);
		backButton.setBackground(Color.DARK_GRAY);
		registerButton.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.WHITE);
		registerButton.setForeground(Color.WHITE);
		emptyPanel.setBackground(Color.DARK_GRAY);
		usernameTextField.setBackground(Color.DARK_GRAY);
		passwordField.setBackground(Color.DARK_GRAY);
		maleRadioButton.setBackground(Color.DARK_GRAY);
		femaleRadioButton.setBackground(Color.DARK_GRAY);
		countryComboBox.setBackground(Color.DARK_GRAY);
		countryComboBox.setForeground(Color.WHITE);
		rolePanel.setBackground(Color.DARK_GRAY);
		playerRadioButton.setBackground(Color.DARK_GRAY);
		developerRadioButton.setBackground(Color.DARK_GRAY);
		playerRadioButton.setForeground(Color.WHITE);
		developerRadioButton.setForeground(Color.WHITE);
		countryComboBox.setEditable(false);
		mainPanel.add(createAnAccountLabel);
		mainPanel.add(emptyPanel);
		mainPanel.add(usernameLabel);
		mainPanel.add(usernameTextField);
		mainPanel.add(passwordLabel);
		mainPanel.add(passwordField);
		mainPanel.add(genderLabel);
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		genderPanel.add(maleRadioButton);
		genderPanel.add(femaleRadioButton);
		mainPanel.add(genderPanel);
		mainPanel.add(countryLabel);
		mainPanel.add(countryComboBox);
		mainPanel.add(roleLabel);
		roleButtonGroup.add(playerRadioButton);
		roleButtonGroup.add(developerRadioButton);
		rolePanel.add(playerRadioButton);
		rolePanel.add(developerRadioButton);
		mainPanel.add(rolePanel);
		backButton.addActionListener(this);
		registerButton.addActionListener(this);
		mainPanel.add(backButton);
		mainPanel.add(registerButton);
		frame.add(mainPanel);
		initializeFrame();
	}
	
	public void initializeFrame() {
		frame.setTitle("");
		frame.setSize(700, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			frame.dispose();
			new LoginForm();
		}
		if(e.getSource() == registerButton) {
			String username = usernameTextField.getText();
			if(username.length() < 5 || username.length() > 15) {
				JOptionPane.showMessageDialog(frame, "Username must be between 5 - 15 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				ResultSet resultSet = Connect.getInstance().executeQuery("SELECT username FROM user;");
				try {
					while(resultSet.next()) {
						if(username.equals(resultSet.getString(1))) {
							JOptionPane.showMessageDialog(frame, "Username already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
							return;
						} 
					}
					String password = passwordField.getText();
					if(password.length() < 3 || password.length() > 10) {
						JOptionPane.showMessageDialog(frame, "Password must be between 3 – 10 characters.", "Warning", JOptionPane.WARNING_MESSAGE);
					} else {
						if(!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
							JOptionPane.showMessageDialog(frame, "Please select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
						} else {
							String gender;
							if(maleRadioButton.isSelected()) {
								gender = "Male";
							} else {
								gender = "Female";
							}
							if(countryComboBox.getSelectedItem().equals("Select a Country")) {
								JOptionPane.showMessageDialog(frame, "Please select a country", "Warning", JOptionPane.WARNING_MESSAGE);
							} else {
								String country = countryComboBox.getSelectedItem().toString();
								if(!playerRadioButton.isSelected() && !developerRadioButton.isSelected()) {
									JOptionPane.showMessageDialog(frame, "Please select a role", "Warning", JOptionPane.WARNING_MESSAGE);
								} else {
									String role;
									if(playerRadioButton.isSelected()) {
										role = "Player";
									} else {
										role = "Developer";
									}
									Connect.getInstance().executeUpdate("INSERT INTO user VALUES (null, '" +username +"', '" +password +"', '" +gender +"', '" +country +"', '" +role +"');");
									System.out.println("Register success");
									JOptionPane.showMessageDialog(frame, "Successfully registered user!", "Success", JOptionPane.WARNING_MESSAGE);
									frame.dispose();
									new LoginForm();
								}
							}
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
