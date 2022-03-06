import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginForm implements ActionListener{
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel(new GridLayout(0, 1));
	JPanel contentPanel = new JPanel(new GridLayout(2, 2));
	JPanel buttonsPanel = new JPanel();
	JLabel loginLabel = new JLabel("Login");
	JLabel usernameLabel = new JLabel("Username :");
	JLabel passwordLabel = new JLabel("Password :");
	JTextField usernameTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JButton loginButton = new JButton("Login");
	JButton registerButton = new JButton("Register");
	Font headerFont = new Font("Calibri", Font.BOLD, 32);
	Font contentFont = new Font("Calibri", Font.PLAIN, 20);
	Font buttonFont = new Font("Calibri", Font.BOLD, 16);

	public LoginForm() {
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBorder(new EmptyBorder(0, 200, 0, 200));
		loginLabel.setFont(headerFont);
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		mainPanel.add(loginLabel);
		contentPanel.setBackground(Color.DARK_GRAY);
		buttonsPanel.setBackground(Color.DARK_GRAY);
		usernameLabel.setFont(contentFont);
		passwordLabel.setFont(contentFont);
		usernameLabel.setForeground(Color.WHITE);
		passwordLabel.setForeground(Color.WHITE);
		usernameTextField.setBackground(Color.DARK_GRAY);
		passwordField.setBackground(Color.DARK_GRAY);
		loginButton.setBackground(Color.DARK_GRAY);
		registerButton.setBackground(Color.DARK_GRAY);
		usernameTextField.setForeground(Color.WHITE);
		usernameTextField.setFont(contentFont);
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(contentFont);
		contentPanel.add(usernameLabel);
		contentPanel.add(usernameTextField);
		contentPanel.add(passwordLabel);
		contentPanel.add(passwordField);
		mainPanel.add(contentPanel);
		loginButton.setFont(buttonFont);
		registerButton.setFont(buttonFont);
		loginButton.setForeground(Color.WHITE);
		loginButton.addActionListener(this);
		registerButton.setForeground(Color.WHITE);
		registerButton.addActionListener(this);
		buttonsPanel.add(loginButton);
		buttonsPanel.add(registerButton);
		mainPanel.add(buttonsPanel);
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

	public static void main(String[] args) {
		Connect.getInstance();
		new LoginForm();
		//new ManageGenreForm();

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == registerButton) {
			frame.dispose();
			new RegisterForm();
		}
		if(e.getSource() == loginButton) {
			if(usernameTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Username cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				if(passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Password cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					String username = usernameTextField.getText(), password = passwordField.getText();
					ResultSet resultSet = Connect.getInstance().executeQuery("SELECT * FROM user;");
					try {
						while(resultSet.next()) {
							if(username.equals(resultSet.getString(2))) {
								if(password.equals(resultSet.getString(3))) {
									System.out.println("Login success");
									User.logInUser(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
									frame.dispose();
									new MainForm();
									return;
								} else {
									JOptionPane.showMessageDialog(frame, "Username / Password is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
									return;
								}
							}
						}
						JOptionPane.showMessageDialog(frame, "Username / Password is wrong", "Warning", JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
	}

}
