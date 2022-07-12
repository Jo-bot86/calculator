package calculator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TaschenrechnerGUI  extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	
	private GridBagLayout gridbag;
	
	private GridBagConstraints c = new GridBagConstraints();

	public TaschenrechnerGUI() {
		setSize(350,500);
		setTitle("Rechner");
		setResizable(false);
		setLocationRelativeTo(null);
		
		addPanelToFrame();
		addGridBagToPanel();
		buildKeybord();
		
		setVisible(true);
	}
	
	private void addPanelToFrame() {
		panel = new JPanel();
		panel.setBackground(new Color(224, 224, 224));

		add(panel);
	}
	
	private void addGridBagToPanel() {
		gridbag = new GridBagLayout();
		panel.setLayout(gridbag);
	}
	
	
	private void buildKeybord() {
		

		c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 4;
        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 1;
        c.weighty = 1;


        JTextField display = new JTextField("0");
        Font font = new Font("SansSerif", Font.BOLD, 30);
        display.setFont(font);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        panel.add(display, c);
        
        
        c.gridwidth = 1;
//        c.gridheight = 1;
        makeButton("MC", 0, 2, panel.getBackground());
        makeButton("MR", 1, 2, panel.getBackground());
        makeButton("M+", 2, 2, panel.getBackground());
        makeButton("M-", 3, 2, panel.getBackground());
        makeButton("MS", 4, 2, panel.getBackground());
        makeButton("M\uD83E\uDC93", 5, 2, panel.getBackground());

        Color veryLightGrey = new Color(235, 235, 235);
        makeButton("\u0025", 0, 3, veryLightGrey);
        makeButton("\u221A", 1, 3, veryLightGrey);
        makeButton("x\u00B2", 2, 3, veryLightGrey);
        makeButton("1\u002Fx", 3, 3, veryLightGrey);
        
        makeButton("CE", 0, 4, veryLightGrey);
        makeButton("C", 1, 4, veryLightGrey);
        makeButton("\u232B", 2, 4, veryLightGrey);
        makeButton("\u00F7", 3, 4, veryLightGrey);
        
        makeButton("7", 0, 5, Color.WHITE);
        makeButton("8", 1, 5, Color.WHITE);
        makeButton("9", 2, 5, Color.WHITE);
        makeButton("\u00D7", 3, 5, veryLightGrey);
        
        makeButton("4", 0, 6, Color.WHITE);
        makeButton("5", 1, 6, Color.WHITE);
        makeButton("6", 2, 6, Color.WHITE);
        makeButton("\u2212", 3, 6, veryLightGrey);

        
        makeButton("1", 0, 7, Color.WHITE);
        makeButton("2", 1, 7, Color.WHITE);
        makeButton("3", 2, 7, Color.WHITE);
        makeButton("\u002B", 3, 7, veryLightGrey);
        
        makeButton("\u00B1", 0, 8, veryLightGrey);
        makeButton("0", 1, 8, Color.WHITE);
        makeButton(",", 2, 8, Color.WHITE);
        makeButton("=", 3, 8, veryLightGrey);

        
	}

	private void makeButton(String name, int gridx, int gridy, Color bgColor) {
		JButton button = new JButton(name);
		button.setBackground(bgColor);
		button.setBorder(BorderFactory.createEmptyBorder());
		c.gridx = gridx;
		c.gridy = gridy;
//		gridbag.setConstraints(button, c);
		panel.add(button,c);
	}
	
	
	
	
}
