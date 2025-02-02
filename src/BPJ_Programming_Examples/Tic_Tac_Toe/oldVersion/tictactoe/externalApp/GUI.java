package BPJ_Programming_Examples.Tic_Tac_Toe.oldVersion.tictactoe.externalApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BPJ_Programming_Examples.Tic_Tac_Toe.oldVersion.tictactoe.bThreads.ClickHandler;
import bpSourceCode.bp.BProgram;
import bpSourceCode.bp.BThread;

/**
 * Class that implements the Graphical User Interface for the game
 */
public class GUI implements ActionListener {
	private JFrame window = new JFrame("Tic-Tac-Toe");
	public JButton buttons[][] = new JButton[3][];
	public JLabel message = new JLabel();
	
	private BProgram bp;
	/**
	 * Constructor.
	 */

	public GUI(BProgram bp) {
		this.bp = bp; 

		// Create window
		window.setSize(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());

		// The board
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(3, 3));
		this.bp = bp;
		// The message label
		message.setHorizontalAlignment(JLabel.CENTER);

		// Create buttons
		for (int i = 0; i < 3; i++) {
			buttons[i] = new JButton[3];
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new TTTButton(i, j);
				board.add(buttons[i][j]);
				buttons[i][j].addActionListener(this);
			}
		}

		// Add the board and the message component to the window
		window.add(board, BorderLayout.CENTER);
		window.add(message, BorderLayout.SOUTH);

		// Make the window visible
		window.setVisible(true);
	}

	/**
	 * @see ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		final TTTButton btt = ((TTTButton) a.getSource());
		BThread sc = new ClickHandler(btt.row,btt.col);
		bp.add(sc,20.0);
		sc.startBThread();

}

/**
 * A button that remembers its position on the board
 */
@SuppressWarnings("serial")
class TTTButton extends JButton {
	int row;
	int col;

	/**
	 * Constructor.
	 *
	 * @param row
	 *            The row of the button.
	 * @param col
	 *            The column of the button.
	 */
	public TTTButton(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

}
}