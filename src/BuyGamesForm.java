import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class BuyGamesForm implements ActionListener, MouseListener{
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	JPanel contentPanel = new JPanel(new GridLayout(7, 2));
	JTable gameTable = new JTable(new DefaultTableModel(null, new Object[]{"Game ID", "Game Name", "Game Price", "Genre", "Quantity"}));
	DefaultTableModel defaultTableModel = (DefaultTableModel) gameTable.getModel(); 
	JScrollPane scrollPane = new JScrollPane(gameTable);
	SpinnerModel quantitySpinnerModel = new SpinnerNumberModel(0, 0, null, 1);
	JSpinner quantitySpinner = new JSpinner(quantitySpinnerModel);
	JLabel idLabel = new JLabel("Game ID");
	JLabel nameLabel = new JLabel("Game Name");
	JLabel priceLabel = new JLabel("Game Price");
	JLabel genreLabel = new JLabel("Game Genre");
	JLabel quantityLabel = new JLabel("How many do you want to buy");
	JLabel confirmationLabel = new JLabel("Once bought game cannot be returned!");
	JTextField idTextField = new JTextField();
	JTextField nameTextField = new JTextField();
	JTextField priceTextField = new JTextField();
	JTextField genreTextField = new JTextField();
	JCheckBox confirmationCheckBox = new JCheckBox();
	JButton backButton = new JButton("Back");
	JButton buyButton = new JButton("Buy Game");
	String id = null, name, genre;
	int price, quantity;

	public BuyGamesForm() {
		initializeTable();
		mainPanel.setBackground(Color.DARK_GRAY);
		scrollPane.setBackground(Color.DARK_GRAY);
		gameTable.setBackground(Color.DARK_GRAY);
		contentPanel.setBackground(Color.DARK_GRAY);
		idTextField.setBackground(Color.DARK_GRAY);
		nameTextField.setBackground(Color.DARK_GRAY);
		priceTextField.setBackground(Color.DARK_GRAY);
		genreTextField.setBackground(Color.DARK_GRAY);
		backButton.setBackground(Color.DARK_GRAY);
		buyButton.setBackground(Color.DARK_GRAY);
		scrollPane.setForeground(Color.WHITE);
		gameTable.setForeground(Color.WHITE);
		idLabel.setForeground(Color.WHITE);
		nameLabel.setForeground(Color.WHITE);
		priceLabel.setForeground(Color.WHITE);
		genreLabel.setForeground(Color.WHITE);
		quantityLabel.setForeground(Color.WHITE);
		confirmationLabel.setForeground(Color.WHITE);
		confirmationCheckBox.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.WHITE);
		buyButton.setForeground(Color.WHITE);
		idTextField.setForeground(Color.WHITE);
		nameTextField.setForeground(Color.WHITE);
		priceTextField.setForeground(Color.WHITE);
		genreTextField.setForeground(Color.WHITE);
		idTextField.setEditable(false);
		nameTextField.setEditable(false);
		priceTextField.setEditable(false);
		genreTextField.setEditable(false);
		quantitySpinner.setEditor(new JSpinner.DefaultEditor(quantitySpinner));
		gameTable.addMouseListener(this);
		backButton.addActionListener(this);
		buyButton.addActionListener(this);
		mainPanel.add(scrollPane);
		contentPanel.setBorder(new EmptyBorder(25, 200, 25, 200));
		contentPanel.add(idLabel);
		contentPanel.add(idTextField);
		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);
		contentPanel.add(priceLabel);
		contentPanel.add(priceTextField);
		contentPanel.add(genreLabel);
		contentPanel.add(genreTextField);
		contentPanel.add(quantityLabel);
		contentPanel.add(quantitySpinner);
		contentPanel.add(confirmationLabel);
		contentPanel.add(confirmationCheckBox);
		contentPanel.add(backButton);
		contentPanel.add(buyButton);
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

	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == gameTable) {
			id  = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 0);
			name = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 1);
			price = (int) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 2);
			genre = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 3);
			quantity = (int) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 4);
			idTextField.setText(id);
			nameTextField.setText(name);
			priceTextField.setText(Integer.toString(price));
			genreTextField.setText(genre);
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

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			frame.dispose();
			new MainForm();
		} else if(e.getSource() == buyButton) {
			if(id == null) {
				JOptionPane.showMessageDialog(frame, "Game must be selected", "Warning", JOptionPane.WARNING_MESSAGE);
			} else if((int) quantitySpinner.getValue() < 1 || (int) quantitySpinner.getValue() > quantity) {
				JOptionPane.showMessageDialog(frame, "Game quantity cannot be less than 1 or more than stock", "Warning", JOptionPane.WARNING_MESSAGE);
			} else if(!confirmationCheckBox.isSelected()) {
				JOptionPane.showMessageDialog(frame, "Checkbox must be checked!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				ResultSet resultSet = Connect.getInstance().executeQuery("SELECT * FROM transaction WHERE userId = '" +User.getUser().userId +"' AND gameId = '" +id +"';");
				try {
					if(resultSet.next()) {
						int quantity = resultSet.getInt(4);
						Connect.getInstance().executeUpdate("UPDATE transaction SET gameQuantity = " +(quantity + (int) quantitySpinner.getValue()) +" WHERE userId = '" +User.getUser().userId +"' AND gameId = '" +id +"';");
					} else {
						Connect.getInstance().executeUpdate("INSERT INTO transaction VALUES (null, '" +User.getUser().userId +"', '" +id +"', " +(int) quantitySpinner.getValue() +");");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Connect.getInstance().executeUpdate("UPDATE game SET quantity = " +(quantity - (int) quantitySpinner.getValue()) +" WHERE gameId = '" +id +"';");
				JOptionPane.showMessageDialog(frame, "Game Bought", "Success", JOptionPane.WARNING_MESSAGE);
				initializeTable();
				idTextField.setText(null);
				nameTextField.setText(null);
				priceTextField.setText(null);
				genreTextField.setText(null);
				quantitySpinner.setValue(0);
				confirmationCheckBox.setSelected(false);
				id = null;
			}
		}
		SwingUtilities.updateComponentTreeUI(frame);
	}

}
