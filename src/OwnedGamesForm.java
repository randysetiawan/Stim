import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class OwnedGamesForm implements ActionListener, MouseListener{
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	JPanel contentPanel = new JPanel(new GridLayout(7, 2));
	JPanel emptyPanel = new JPanel();
	JTable gameTable = new JTable(new DefaultTableModel(null, new Object[]{"Game ID", "Game Name", "Game Genre", "Quantity", "Price"}));
	DefaultTableModel defaultTableModel = (DefaultTableModel) gameTable.getModel();
	JScrollPane scrollPane = new JScrollPane(gameTable);
	JLabel idLabel = new JLabel("Game ID");
	JLabel nameLabel = new JLabel("Game Name");
	JLabel priceLabel = new JLabel("Game Price");
	JLabel genreLabel = new JLabel("Game Genre");
	JLabel quantitiyLabel = new JLabel("Owned Quantity");
	JLabel totalPriceLabel = new JLabel("Total Spent on Games");
	JTextField idTextField = new JTextField();
	JTextField nameTextField = new JTextField();
	JTextField priceTextField = new JTextField();
	JTextField genreTextField = new JTextField();
	JTextField quantityTextField = new JTextField();
	JTextField totalPriceTextField = new JTextField();
	JButton backButton = new JButton("Back");
	String id, name, genre;
	int quantity, price, totalPrice;

	public OwnedGamesForm() {
		initializeTable();
		mainPanel.setBackground(Color.DARK_GRAY);
		scrollPane.setBackground(Color.DARK_GRAY);
		gameTable.setBackground(Color.DARK_GRAY);
		contentPanel.setBackground(Color.DARK_GRAY);
		idTextField.setBackground(Color.DARK_GRAY);
		nameTextField.setBackground(Color.DARK_GRAY);
		priceTextField.setBackground(Color.DARK_GRAY);
		genreTextField.setBackground(Color.DARK_GRAY);
		quantityTextField.setBackground(Color.DARK_GRAY);
		totalPriceTextField.setBackground(Color.DARK_GRAY);
		backButton.setBackground(Color.DARK_GRAY);
		emptyPanel.setBackground(Color.DARK_GRAY);
		scrollPane.setForeground(Color.WHITE);
		gameTable.setForeground(Color.WHITE);
		idLabel.setForeground(Color.WHITE);
		nameLabel.setForeground(Color.WHITE);
		priceLabel.setForeground(Color.WHITE);
		genreLabel.setForeground(Color.WHITE);
		quantitiyLabel.setForeground(Color.WHITE);
		totalPriceLabel.setForeground(Color.WHITE);
		backButton.setForeground(Color.WHITE);
		idTextField.setForeground(Color.WHITE);
		nameTextField.setForeground(Color.WHITE);
		priceTextField.setForeground(Color.WHITE);
		genreTextField.setForeground(Color.WHITE);
		quantityTextField.setForeground(Color.WHITE);
		totalPriceTextField.setForeground(Color.WHITE);
		mainPanel.add(scrollPane);
		gameTable.addMouseListener(this);
		backButton.addActionListener(this);
		idTextField.setEditable(false);
		nameTextField.setEditable(false);
		priceTextField.setEditable(false);
		genreTextField.setEditable(false);
		quantityTextField.setEditable(false);
		totalPriceTextField.setEditable(false);
		contentPanel.setBorder(new EmptyBorder(25, 200, 25, 200));
		contentPanel.add(idLabel);
		contentPanel.add(idTextField);
		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);
		contentPanel.add(priceLabel);
		contentPanel.add(priceTextField);
		contentPanel.add(genreLabel);
		contentPanel.add(genreTextField);
		contentPanel.add(quantitiyLabel);
		contentPanel.add(quantityTextField);
		contentPanel.add(totalPriceLabel);
		contentPanel.add(totalPriceTextField);
		contentPanel.add(backButton);
		contentPanel.add(emptyPanel);
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
		ResultSet resultSet = Connect.getInstance().executeQuery("SELECT game.gameId, name, genreName, gameQuantity, price FROM transaction JOIN game ON transaction.gameId = game.gameId JOIN genre ON game.genreId = genre.genreId WHERE userId = 0;");
		try {
			while(resultSet.next()) {
				Object[] data = new Object[] {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)};
				defaultTableModel.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			frame.dispose();
			new MainForm();
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == gameTable) {
			id  = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 0);
			name = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 1);
			genre = (String) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 2);
			quantity = (int) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 3);
			price = (int) gameTable.getValueAt(gameTable.rowAtPoint(e.getPoint()), 4);
			totalPrice = quantity * price;
			idTextField.setText(id);
			nameTextField.setText(name);
			priceTextField.setText(Integer.toString(price));
			genreTextField.setText(genre);
			quantityTextField.setText(Integer.toString(quantity));
			totalPriceTextField.setText(Integer.toString(quantity * price));
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
