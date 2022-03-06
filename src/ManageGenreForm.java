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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManageGenreForm implements ActionListener, MouseListener{
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	JPanel contentPanel = new JPanel(new GridLayout(1, 2));
	JPanel leftMainPanel = new JPanel(new BorderLayout());
	JPanel rightMainPanel = new JPanel(new BorderLayout());
	JPanel leftContentPanel = new JPanel(new GridLayout(2, 2));
	JPanel rightContentPanel = new JPanel(new GridLayout(1, 2));
	JPanel leftButtonPanel = new JPanel();
	JPanel rightButtonPanel = new JPanel();
	JTable genreTable = new JTable(new DefaultTableModel(null, new Object[]{"Genre ID", "Genre Name"}));
	DefaultTableModel defaultTableModel = (DefaultTableModel) genreTable.getModel();
	JScrollPane scrollPane = new JScrollPane(genreTable);
	JLabel idLabel = new JLabel("Genre ID");
	JLabel oldNameLabel = new JLabel("Genre Name");
	JLabel newNameLabel = new JLabel("New Genre Name");
	JTextField idTextField = new JTextField();
	JTextField oldNameTextField = new JTextField();
	JTextField newNameTextField = new JTextField();
	JButton backButton = new JButton("Back");
	JButton deleteButton = new JButton("Delete");
	JButton updateButton = new JButton("Update");
	JButton insertButton = new JButton("Insert");
	Random random = new Random();
	String id, oldName, newName;

	public ManageGenreForm() {
		initializeTable();
		mainPanel.add(scrollPane);
		mainPanel.setBackground(Color.DARK_GRAY);
		scrollPane.setBackground(Color.DARK_GRAY);
		genreTable.setBackground(Color.DARK_GRAY);
		contentPanel.setBackground(Color.DARK_GRAY);
		leftMainPanel.setBackground(Color.DARK_GRAY);
		leftContentPanel.setBackground(Color.DARK_GRAY);
		leftButtonPanel.setBackground(Color.DARK_GRAY);
		rightMainPanel.setBackground(Color.DARK_GRAY);
		rightContentPanel.setBackground(Color.DARK_GRAY);
		rightButtonPanel.setBackground(Color.DARK_GRAY);
		idTextField.setBackground(Color.DARK_GRAY);
		oldNameTextField.setBackground(Color.DARK_GRAY);
		newNameTextField.setBackground(Color.DARK_GRAY);
		backButton.setBackground(Color.DARK_GRAY);
		deleteButton.setBackground(Color.DARK_GRAY);
		updateButton.setBackground(Color.DARK_GRAY);
		insertButton.setBackground(Color.DARK_GRAY);
		scrollPane.setForeground(Color.WHITE);
		genreTable.setForeground(Color.WHITE);
		idLabel.setForeground(Color.WHITE);
		oldNameLabel.setForeground(Color.WHITE);
		newNameLabel.setForeground(Color.WHITE);
		backButton.setForeground(Color.WHITE);
		deleteButton.setForeground(Color.WHITE);
		updateButton.setForeground(Color.WHITE);
		insertButton.setForeground(Color.WHITE);
		idTextField.setForeground(Color.WHITE);
		oldNameTextField.setForeground(Color.WHITE);
		newNameTextField.setForeground(Color.WHITE);
		genreTable.addMouseListener(this);
		backButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		insertButton.addActionListener(this);
		idTextField.setEditable(false);
		leftMainPanel.setBorder(new EmptyBorder(25, 50, 25, 50));
		rightMainPanel.setBorder(new EmptyBorder(25, 50, 25, 50));
		leftContentPanel.setBorder(new EmptyBorder(0, 0, 75, 0));
		rightContentPanel.setBorder(new EmptyBorder(0, 0, 125, 0));
		leftContentPanel.add(idLabel);
		leftContentPanel.add(idTextField);
		leftContentPanel.add(oldNameLabel);
		leftContentPanel.add(oldNameTextField);
		leftButtonPanel.add(backButton);
		leftButtonPanel.add(deleteButton);
		leftButtonPanel.add(updateButton);
		rightContentPanel.add(newNameLabel);
		rightContentPanel.add(newNameTextField);
		rightButtonPanel.add(insertButton);
		leftMainPanel.add(leftContentPanel, BorderLayout.CENTER);
		leftMainPanel.add(leftButtonPanel, BorderLayout.SOUTH);
		rightMainPanel.add(rightContentPanel, BorderLayout.CENTER);
		rightMainPanel.add(rightButtonPanel, BorderLayout.SOUTH);
		contentPanel.add(leftMainPanel);
		contentPanel.add(rightMainPanel);
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
		ResultSet resultSet = Connect.getInstance().executeQuery("SELECT * FROM genre;");
		try {
			while(resultSet.next()) {
				Object[] data = new Object[] {resultSet.getString(1), resultSet.getString(2)};
				defaultTableModel.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == genreTable) {
			id = (String) genreTable.getValueAt(genreTable.rowAtPoint(e.getPoint()), 0);
			oldName = (String) genreTable.getValueAt(genreTable.rowAtPoint(e.getPoint()), 1);
			idTextField.setText(id);
			oldNameTextField.setText(oldName);
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
		}
		if(e.getSource() == deleteButton) {
			if(id == null) {
				JOptionPane.showMessageDialog(frame, "Select a genre first!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				Connect.getInstance().executeUpdate("DELETE FROM genre WHERE genreId = '" +id +"';");
				JOptionPane.showMessageDialog(frame, "Delete Success", "Success", JOptionPane.WARNING_MESSAGE);
				initializeTable();
				idTextField.setText(null);
				oldNameTextField.setText(null);
				id = null;
			}
		}
		if(e.getSource() == updateButton) {
			String name = oldNameTextField.getText();
			if(id == null) {
				JOptionPane.showMessageDialog(frame, "Select a genre first!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				ResultSet resultSet = Connect.getInstance().executeQuery("SELECT genreName FROM genre;");
				try {
					while(resultSet.next()) {
						if(name.equals(resultSet.getString(1))) {
							JOptionPane.showMessageDialog(frame, "Genre already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(name.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter a name!", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					Connect.getInstance().executeUpdate("UPDATE genre SET genreName = '" +name +"' WHERE genreId = '" +id +"';");
					JOptionPane.showMessageDialog(frame, "Update Success", "Success", JOptionPane.WARNING_MESSAGE);
					initializeTable();
					idTextField.setText(null);
					oldNameTextField.setText(null);
					id = null;
				}
			}
		}
		if(e.getSource() == insertButton) {
			String id;
			String name = newNameTextField.getText();
			boolean unique = true;
			if(newNameTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please enter a name!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				do {
					id = "GEN";
					for(int i = 0; i < 3; i++) {
						id += Integer.toString(random.nextInt(10));
					}
					ResultSet resultSet2 = Connect.getInstance().executeQuery("SELECT genreId FROM genre;");
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
				Connect.getInstance().executeUpdate("INSERT INTO genre VALUES ('" +id +"', '" +name +"');");
				JOptionPane.showMessageDialog(frame, "Insert Success", "Success", JOptionPane.WARNING_MESSAGE);
				initializeTable();
				newNameTextField.setText(null);
			}
		}
		
	}

}
