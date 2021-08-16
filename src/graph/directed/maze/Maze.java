package graph.directed.maze;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Maze {

	private JFrame maze;
	private JTextField sizeField;

	public static void main(String[] args) {
		new Maze();
	}

	public Maze() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					maze.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		maze = new JFrame();
		maze.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nahuel-Nico\\git\\Avanzada\\src\\graph\\directed\\maze\\thumb-1920-691524.jpg"));
		maze.setResizable(false);
		maze.setTitle("Maze Generator");
		maze.setSize(500,313);
		maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maze.setLocationRelativeTo(null);
		maze.getContentPane().setLayout(null);
		
		sizeField = new JTextField();
		sizeField.setFont(new Font("Tahoma", Font.BOLD, 16));
		sizeField.setHorizontalAlignment(SwingConstants.CENTER);
		sizeField.setBounds(239, 190, 60, 30);
		maze.getContentPane().add(sizeField);
		sizeField.setColumns(10);
		
		JButton kruskalButton = new JButton("Kruskal");
		kruskalButton.setForeground(Color.BLACK);
		kruskalButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		kruskalButton.setBounds(158, 140, 161, 30);
		maze.getContentPane().add(kruskalButton);
		
		JButton primButton = new JButton("Prim");
		primButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(sizeField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Insert Size", "Size Error", JOptionPane.ERROR_MESSAGE );
				else {
					int size = Integer.parseInt(sizeField.getText());
					new MazeDrawer(size, "Prim");
					maze.dispose();
				}
			}
		});
		primButton.setForeground(Color.BLACK);
		primButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		primButton.setBounds(158, 90, 161, 30);
		maze.getContentPane().add(primButton);
		
		JLabel size = new JLabel("Size:");
		size.setHorizontalAlignment(SwingConstants.CENTER);
		size.setForeground(Color.WHITE);
		size.setFont(new Font("Tahoma", Font.BOLD, 16));
		size.setBounds(158, 190, 60, 30);
		maze.getContentPane().add(size);
		
		JLabel tittle = new JLabel("Maze Generator");
		tittle.setForeground(Color.WHITE);
		tittle.setBounds(158, 40, 161, 30);
		tittle.setFont(new Font("Tahoma", Font.BOLD, 20));
		tittle.setHorizontalAlignment(SwingConstants.LEFT);
		maze.getContentPane().add(tittle);
		
		JLabel background = new JLabel("");
		background.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		background.setForeground(new Color(255, 255, 255));
		background.setIcon(new ImageIcon("C:\\Users\\Nahuel-Nico\\git\\Avanzada\\src\\graph\\directed\\maze\\Webp.net-resizeimage (1).jpg"));
		background.setBounds(0, 0, 494, 284);
		background.setHorizontalAlignment(SwingConstants.RIGHT);
		maze.getContentPane().add(background);
	}
}
