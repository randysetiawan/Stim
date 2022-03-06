import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;

public class ManageGameForm implements ActionListener, MouseListener{
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	JPanel contentPanel = new JPanel(new GridLayout(1, 2));
	JPanel oldGameMainPanel = new JPanel(new BorderLayout());
	JPanel newGameMainPanel = new JPanel(new BorderLayout());
	JPanel oldGameContentPanel = new JPanel(new GridLayout(5, 2));
	JPanel newGameContentPanel = new JPanel(new GridLayout(4, 2));
	JPanel oldGameButtonPanel = new JPanel();
	JPanel newGameButtonPanel = new JPanel();
	JTable gameTable = new JTable(new DefaultTableModel(null, new Object[]{"Game ID", "Game Name", "Game Price", "Genre", "Quantity"}));
	DefaultTableModel defaultTableModel = (DefaultTableModel) gameTable.getModel();
	JScrollPane scrollPane = new JScrollPane(gameTable);
	JLabel oldIdLabel = new JLabel("Game ID");
	JLabel oldNameLabel = new JLabel("Game Name");
	JLabel oldPriceLabel = new JLabel("Game Price");
	JLabel oldGenreLabel = new JLabel("Game Genre");
	JLabel oldQuantityLabel = new JLabel("Game Quantity");
	JLabel newNameLabel = new JLabel("New Game Name");
	JLabel newPriceLabel = new JLabel("New Game Price");
	JLabel newGenreLabel = new JLabel("New Game Genre");
	JLabel newQuantityLabel = new JLabel("New Game Quantity");
	JTextField oldIdTextField = new JTextField();
	JTextField oldNameTextField = new JTextField();
	JTextField oldPriceTextField = new JTextField();
	JTextField newNameTextField = new JTextField();
	JTextField newPriceTextField = new JTextField();
	SpinnerModel oldQuantitySpinnerModel = new SpinnerNumberModel(0, 0, null, 1);
	JSpinner oldQuantitySpinner = new JSpinner(oldQuantitySpinnerModel);
	SpinnerModel newQuantitySpinnerModel = new SpinnerNumberModel(0, 0, null, 1);
	JSpinner newQuantitySpinner = new JSpinner(newQuantitySpinnerModel);
	Vector<String> oldGenreList = new Vector<>();
	JComboBox<String> oldGenreComboBox = new JComboBox<>(oldGenreList);
	Vector<String> newGenreList = new Vector<>();
	JComboBox<String> newGenreComboBox = new JComboBox<>(newGenreList);
	JButton backButton = new JButton("Back");
	JButton deleteButton = new JButton("Delete");
	JButton updateButton = new JButton("Update");
	JButton insertButton = new JButton("Insert");
	Random random = new Random();
	String id = null, name, genre;
	int price, quantity;

	public ManageGameForm() {
		initializeTable();
		initializeGenre();
		mainPanel.add(scrollPane);
		mainPanel.setBackground(Color.DARK_GRAY);
		scrollPane.setBackground(Color.DARK_GRAY);
		gameTable.setBackground(Color.DARK_GRAY);
		contentPanel.setBackground(Color.DARK_GRAY);
		oldGameContentPanel.setBackground(Color.DARK_GRAY);
		oldGameButtonPanel.setBackground(Color.DARK_GRAY);
		oldGameMainPanel.setBackground(Color.DARK_GRAY);
		newGameContentPanel.setBackground(Color.DARK_GRAY);
		newGameButtonPanel.setBackground(Color.DARK_GRAY);
		newGameMainPanel.setBackground(Color.DARK_GRAY);
		oldIdTextField.setBackground(Color.DARK_GRAY);
		oldNameTextField.setBackground(Color.DARK_GRAY);
		oldPriceTextField.setBackground(Color.DARK_GRAY);
		oldGenreComboBox.setBackground(Color.DARK_GRAY);
		newNameTextField.setBackground(Color.DARK_GRAY);
		newPriceTextField.setBackground(Color.DARK_GRAY);
		newGenreComboBox.setBackground(Color.DARK_GRAY);
		backButton.setBackground(Color.DARK_GRAY);
		deleteButton.setBackground(Color.DARK_GRAY);
		updateButton.setBackground(Color.DARK_GRAY);
		insertButton.setBackground(Color.DARK_GRAY);
		scrollPane.setForeground(Color.WHITE);
		gameTable.setForeground(Color.WHITE);
		oldIdLabel.setForeground(Color.WHITE);
		oldNameLabel.setForeground(Color.WHITE);
		oldPriceLabel.setForeground(Color.WHITE);
		oldGenreLabel.setForeground(Color.WHITE);
		oldQuantityLabel.setForeground(Color.WHITE);
		newNameLabel.setForeground(Color.WHITE);
		newPriceLabel.setForeground(Color.WHITE);
		newGenreLabel.setForeground(Color.WHITE);
		newQuantityLabel.setForeground(Color.WHITE);
		backButton.setForeground(Color.WHITE);
		deleteButton.setForeground(Color.WHITE);
		updateButton.setForeground(Color.WHITE);
		insertButton.setForeground(Color.WHITE);
		oldIdTextField.setForeground(Color.WHITE);
		oldNameTextField.setForeground(Color.WHITE);
		oldPriceTextField.setForeground(Color.WHITE);
		oldGenreComboBox.setForeground(Color.WHITE);
		newNameTextField.setForeground(Color.WHITE);
		newPriceTextField.setForeground(Color.WHITE);
		newGenreComboBox.setForeground(Color.WHITE);
		oldIdTextField.setEditable(false);
		oldGenreComboBox.setEditable(false);
		newGenreComboBox.setEditable(false);
		oldQuantitySpinner.setEditor(new JSpinner.DefaultEditor(oldQuantitySpinner));
		newQuantitySpinner.setEditor(new JSpinner.DefaultEditor(newQuantitySpinner));
		gameTable.addMouseListener(this);
		backButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		insertButton.addActionListener(this);
		oldGameMainPanel.setBorder(new EmptyBorder(25, 50, 25, 50));
		newGameMainPanel.setBorder(new EmptyBorder(25, 50, 25, 50));
		oldGameContentPanel.add(oldIdLabel);
		oldGameContentPanel.add(oldIdTextField);
		oldGameContentPanel.add(oldNameLabel);
		oldGameContentPanel.add(oldNameTextField);
		oldGameContentPanel.add(oldPriceLabel);
		oldGameContentPanel.add(oldPriceTextField);
		oldGameContentPanel.add(oldGenreLabel);
		oldGameContentPanel.add(oldGenreComboBox);
		oldGameContentPanel.add(oldQuantityLabel);
		oldGameContentPanel.add(oldQuantitySpinner);
		oldGameButtonPanel.add(backButton);
		oldGameButtonPanel.add(deleteButton);
		oldGameButtonPanel.add(updateButton);
		newGameContentPanel.add(newNameLabel);
		newGameContentPanel.add(newNameTextField);
		newGameContentPanel.add(newPriceLabel);
		newGameContentPanel.add(newPriceTextField);
		newGameContentPanel.add(newGenreLabel);
		newGameContentPanel.add(newGenreComboBox);
		newGameContentPanel.add(newQuantityLabel);
		newGameContentPanel.add(newQuantitySpinner);
		newGameButtonPanel.add(insertButton);
		oldGameMainPanel.add(oldGameContentPanel, BorderLayout.CENTER);
		oldGameMainPanel.add(oldGameButtonPanel, BorderLayout.SOUTH);
		newGameMainPanel.add(newGameContentPanel, BorderLayout.CENTER);
		newGameMainPanel.add(newGameButtonPanel, BorderLayout.SOUTH);
		contentPanel.add(oldGameMainPanel);
		contentPanel.add(newGameMainPanel);
		mainPanel.add(contentPanel);
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
	
	public void initializeTable() {
		defaultTableModel.setRowCount(0);
		ResultSet resultSet = Connect.getInstance().executeQuery("SELECT gameId, name, price, genreName, quantity FROM game JOIN genre ON game.genreId = genre.genreId;");
		try {
			while(resultSet.next()) {
				Object[] data = new Object[] {resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5)};
				defaultTableModel.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initializeGenre() {
		oldGenreList.add("Select a Genre");
		newGenreList.add("Select a Genre");
		ResultSet resultSet = Connect.getInstance().executeQuery("SELECT genreName FROM genre;");
		try {
			while(resultSet.next()) {
				oldGenreList.add(resultSet.getString(1));
				newGenreList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		oldGenreComboBox = new JComboBox<>(oldGenreList);
		newGenreComboBox = new JComboBox<>(newGenreList);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			frame.dispose();
			new MainForm();
		}
		if(e.getSource() == deleteButton) {
			if(id == null) {
				JOptionPane.showMessageDialog(frame, "Select a game first!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				Connect.getInstance().executeUpdate("DELETE FROM game WHERE gameId = '" +id +"';");
				JOptionPane.showMessageDialog(frame, "Delete Success", "Success", JOptionPane.WARNING_MESSAGE);
				initializeTable();
				oldIdTextField.setText(null);
				oldNameTextField.setText(null);
				oldPriceTextField.setText(null);
				oldGenreComboBox.setSelectedIndex(0);
				oldQuantitySpinner.setValue(0);
				id = null;
			}
		}
		if(e.getSource() == updateButton) {
			name = oldNameTextField.getText();
			String price = oldPriceTextField.getText();
			quantity = Integer.parseInt(oldQuantitySpinner.getValue().toString());
			if(id == null) {
				JOptionPane.showMessageDialog(frame, "Select a game first!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else if (name.length() < 5 || name.length() > 30){
				JOptionPane.showMessageDialog(frame, "Name must be between 5-30 characters!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else if (oldGenreComboBox.getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(frame, "Genre must be selected", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				ResultSet resultSet = Connect.getInstance().executeQuery("SELECT name FROM game;");
				try {
					while(resultSet.next()) {
						if(resultSet.getString(1).equals(name)) {
							JOptionPane.showMessageDialog(frame, "Game name already exists", "Warning", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for(int i = 0; i < price.length(); i++) {
					if(!isNumeric(price.charAt(i))) {
						JOptionPane.showMessageDialog(frame, "Price must be numeric", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				try {
					this.price = Integer.parseInt(price);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, "Price must be numeric", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(this.price < 1) {
					JOptionPane.showMessageDialog(frame, "Price must be more than 0", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if(quantity < 1) {
					JOptionPane.showMessageDialog(frame, "Quantity must be more than 0", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					String genreId = null;
					ResultSet resultSet2 = Connect.getInstance().executeQuery("SELECT * FROM genre;");
					try {
						while(resultSet2.next()) {
							if(genre.equals(resultSet2.getString(2))) {
								genreId = resultSet2.getString(1);
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					Connect.getInstance().executeUpdate("UPDATE game SET name = '" +name +"', price = " +price +", genreId = '" +genreId +"', quantity = " +quantity +" WHERE gameId = '" +id +"';");
					JOptionPane.showMessageDialog(frame, "Update Success", "Success", JOptionPane.WARNING_MESSAGE);
					initializeTable();
					oldIdTextField.setText(null);
					oldNameTextField.setText(null);
					oldPriceTextField.setText(null);
					oldGenreComboBox.setSelectedIndex(0);
					oldQuantitySpinner.setValue(0);
					id = null;
				}
			}
		}
		if(e.getSource() == insertButton) {
			String id;
			String name = newNameTextField.getText();
			String strPrice = newPriceTextField.getText();
			String genre = newGenreComboBox.getSelectedItem().toString();
			boolean unique = true;
			int quantity = Integer.parseInt(newQuantitySpinner.getValue().toString());
			int price;
			if(name.length() < 5 || name.length() > 30) {
				JOptionPane.showMessageDialog(frame, "Name must be between 5-30 characters!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				ResultSet resultSet = Connect.getInstance().executeQuery("SELECT name FROM game;");
				try {
					while(resultSet.next()) {
						if(name.equals(resultSet.getString(1))) {
							JOptionPane.showMessageDialog(frame, "Name must not be duplicate", "Warning", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					price = Integer.parseInt(strPrice);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, "Price must be numeric", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(price < 0) {
					JOptionPane.showMessageDialog(frame, "Price must be bigger than 0", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if(genre.equals("Select a Genre")) {
					JOptionPane.showMessageDialog(frame, "Genre must be selected", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if(quantity < 1) {
					JOptionPane.showMessageDialog(frame, "Quantity must be more than 0", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					String genreId = null;
					ResultSet resultSet3 = Connect.getInstance().executeQuery("SELECT genreId FROM genre WHERE genreName = '" +genre +"';");
					try {
						resultSet3.next();
						genreId = resultSet3.getString(1);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					do {
						id = "GAME";
						for(int i = 0; i < 3; i++) {
							id += Integer.toString(random.nextInt(10));
						}
						ResultSet resultSet2 = Connect.getInstance().executeQuery("SELECT gameId FROM game;");
						try {
							while(resultSet2.next()) {
								if(id.equals(resultSet2.getString(1))) {
									unique = false;
									continue;
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						unique = true;
					} while(!unique);
					Connect.getInstance().executeUpdate("INSERT INTO game VALUES ('" +id +"', '" +name +"', " +price +", '" +genreId +"', " +quantity +");");
					JOptionPane.showMessageDialog(frame, "Insert Success", "Success", JOptionPane.WARNING_MESSAGE);
					initializeTable();
					newNameTextField.setText(null);
					newPriceTextField.setText(null);
					newGenreComboBox.setSelectedIndex(0);
					newQuantitySpinner.setValue(0);
					id = null;
				}
			}
		}
	}
	
	public boolean isNumeric(char character) {
        return (character >= '0' && character <= '9');
    }

	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == gameTable) {
			id  = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 0);
			name = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 1);
			price = (int) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 2);
			genre = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 3);
			quantity = (int) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 4);
			oldIdTextField.setText(id);
			oldNameTextField.setText(name);
			oldPriceTextField.setText(Integer.toString(price));
			oldGenreComboBox.setSelectedItem(genre);
		}
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
