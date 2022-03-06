import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainForm implements ActionListener{
	String role;
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel(new GridLayout(1, 1));
	JLabel stimLabel = new JLabel("Stim");
	JMenuBar menuBar = new JMenuBar();
	JMenu accountMenu = new JMenu("Account");
	JMenu gamesMenu = new JMenu("Games");
	JMenu manageMenu = new JMenu("Manage");
	JMenuItem logOutMenuItem = new JMenuItem("Log Out");
	JMenuItem buyGamesMenuItem = new JMenuItem("Buy Games");
	JMenuItem ownedGamesMenuItem = new JMenuItem("Owned Games");
	JMenuItem manageGamesMenuItem = new JMenuItem("Manage Games");
	JMenuItem manageGenresMenuItem = new JMenuItem("Manage Genres");
	Font headerFont = new Font("Calibri", Font.ITALIC, 72);
	Font contentFont = new Font("Calibri", Font.PLAIN, 20);
	
	public MainForm() {
		stimLabel.setForeground(Color.DARK_GRAY);
		stimLabel.setFont(headerFont);
		stimLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stimLabel.setVerticalAlignment(SwingConstants.CENTER);
		mainPanel.add(stimLabel);
		frame.setJMenuBar(menuBar);
		accountMenu.setBackground(Color.DARK_GRAY);
		logOutMenuItem.setBackground(Color.DARK_GRAY);
		logOutMenuItem.setForeground(Color.WHITE);
		logOutMenuItem.addActionListener(this);
		accountMenu.setForeground(Color.WHITE);
		menuBar.setBackground(Color.DARK_GRAY);
		accountMenu.add(logOutMenuItem);
		gamesMenu.setBackground(Color.DARK_GRAY);
		gamesMenu.setForeground(Color.WHITE);
		buyGamesMenuItem.setBackground(Color.DARK_GRAY);
		buyGamesMenuItem.setForeground(Color.WHITE);
		buyGamesMenuItem.addActionListener(this);
		ownedGamesMenuItem.setBackground(Color.DARK_GRAY);
		ownedGamesMenuItem.setForeground(Color.WHITE);
		ownedGamesMenuItem.addActionListener(this);
		gamesMenu.add(buyGamesMenuItem);
		gamesMenu.add(ownedGamesMenuItem);
		manageMenu.setBackground(Color.DARK_GRAY);
		manageMenu.setForeground(Color.WHITE);
		manageGamesMenuItem.setBackground(Color.DARK_GRAY);
		manageGamesMenuItem.setForeground(Color.WHITE);
		manageGamesMenuItem.addActionListener(this);
		manageGenresMenuItem.setBackground(Color.DARK_GRAY);
		manageGenresMenuItem.setForeground(Color.WHITE);
		manageGenresMenuItem.addActionListener(this);
		manageMenu.add(manageGamesMenuItem);
		manageMenu.add(manageGenresMenuItem);
		menuBar.add(accountMenu);
		if(User.getUser().role.equals("Player")) {
			menuBar.add(gamesMenu);
		} else {
			menuBar.add(manageMenu);
		}
		frame.add(mainPanel);
		initializeFrame();
	}
	
	public void initializeFrame() {
		frame.setTitle("");
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logOutMenuItem) {
			frame.dispose();
			User.user = null;
			new LoginForm();
		}
		if(e.getSource() == buyGamesMenuItem) {
			frame.dispose();
			new BuyGamesForm();
		}
		if(e.getSource() == ownedGamesMenuItem) {
			frame.dispose();
			new OwnedGamesForm();
		}
		if(e.getSource() == manageGamesMenuItem) {
			frame.dispose();
			new ManageGameForm();
		}
		if(e.getSource() == manageGenresMenuItem) {
			frame.dispose();
			new ManageGenreForm();
		}
		
	}

}
