package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import utils.GameConfig;
/**
 * A component of ControlPanel: Display the game timer for the current team
 * @author Phan Vo
 *
 */
public class TimePanel extends JPanel{
	
	private JTextField tfTime;
	
	public TimePanel() {
		// TODO Auto-generated constructor stub
		super();
		
	    tfTime = new JTextField();
	    tfTime.setHorizontalAlignment(JTextField.CENTER);
	    tfTime.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-40, 40));
	    tfTime.setText("...");
	    tfTime.setEditable(false);
	    tfTime.setBackground(Color.WHITE);
	    tfTime.setFont(new Font("Sans-serif", Font.BOLD, 20));
	    
		JPanel pnContainer = new JPanel(new FlowLayout());
		pnContainer.add(tfTime);    

		TitledBorder titled = new TitledBorder("Timer");
		pnContainer.setBorder(titled);

		this.add(pnContainer);		
	}
	
	/**
	 * update time countdown
	 * @param i
	 */
	public void setTime(int i) {
		tfTime.setText(String.valueOf(formatSeconds(i)));
	}
	
	private String formatSeconds(int i)
	{
		int minutes = (i % 3600) / 60;
		int seconds = i % 60;
		String timeString = String.format("%02d:%02d", minutes, seconds);
	    return timeString;
	}
}
