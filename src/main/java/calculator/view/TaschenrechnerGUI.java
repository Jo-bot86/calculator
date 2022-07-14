package calculator.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import calculator.model.TaschenrechnerPresenter;

public class TaschenrechnerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaschenrechnerPresenter presenter = new TaschenrechnerPresenter();

	private JPanel panel;

	private GridBagLayout gridbag;

	private GridBagConstraints c = new GridBagConstraints();

	public TaschenrechnerGUI() {
		setSize(350, 500);
		setTitle("Rechner");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addPanelToFrame();
		addGridBagToPanel();
		buildCalculator();

		addActionListenerToNumPad();
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

	private void buildCalculator() {
		Color veryLightGrey = new Color(235, 235, 235);

		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 4;
		c.insets = new Insets(2, 2, 2, 2);
		c.weightx = 1;
		c.weighty = 1;

		JTextField display = buildDisplay();

		panel.add(display, c);
		JPanel topRow = new JPanel();
		topRow.setBackground(panel.getBackground());
		c.gridwidth = 1;
		c.weighty = 0;
		addGB(topRow, makeTopRowButton("MC", 0, 2, panel.getBackground()), 0, 0);
//		c.weightx = 0.67;
		addGB(topRow, makeTopRowButton("MR", 1, 2, panel.getBackground()), 1, 0);
//		c.weightx = 1.0;
		addGB(topRow, makeTopRowButton("M+", 2, 2, panel.getBackground()), 2, 0);
		addGB(topRow, makeTopRowButton("M-", 3, 2, panel.getBackground()), 3, 0);
//		c.weightx = 0.67;
		addGB(topRow, makeTopRowButton("MS", 4, 2, panel.getBackground()), 4, 0);
//		c.weightx = 1.0;
		addGB(topRow, makeTopRowButton("M\uD83E\uDC93", 5, 2, panel.getBackground()), 5, 0);
		c.gridwidth = 4;
		addGB(panel, topRow, 0, 1);
		c.weighty = 1;
		c.gridwidth = 1;
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

	private JTextField buildDisplay() {
		JTextField display = new JTextField("0");
		Font font = new Font("SansSerif", Font.BOLD, 30);
		display.setFont(font);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setEditable(false);
		display.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		presenter.addPropertyChangeListener(event -> {
			System.out.println("oldValue " + event.getOldValue().toString());
			System.out.println("newValue " + event.getNewValue().toString());
			presenter.setValue1(Double.parseDouble(event.getNewValue().toString()));
			String value = Double.valueOf(presenter.getValue1()).toString();
			value = value.replace(".0", "");
			display.setText(value);
			System.out.println(value);
		});
		return display;
	}

	private void makeButton(String name, int gridx, int gridy, Color bgColor) {
		JButton button = new JButton(name);
		button.setBackground(bgColor);
		button.setBorder(BorderFactory.createEmptyBorder());
		c.gridx = gridx;
		c.gridy = gridy;
//		gridbag.setConstraints(button, c);
		panel.add(button, c);
	}

	private JButton makeTopRowButton(String name, int gridx, int gridy, Color bgColor) {
		JButton button = new JButton(name);
		button.setBackground(bgColor);
		button.setBorder(BorderFactory.createEmptyBorder());
		c.gridx = gridx;
		c.gridy = gridy;
		return button;
	}

	private void addGB(Container cont, Component comp, int x, int y) {
		if ((cont.getLayout() instanceof GridBagLayout) == false)
			cont.setLayout(new GridBagLayout());
		c.gridx = x;
		c.gridy = y;
		cont.add(comp, c);
	}

	private void addActionListenerToNumPad() {
		Component[] allComponents = panel.getComponents();
		// select all buttons containing a number
		for (Component c : allComponents) {
			if (c instanceof JButton button && button.getText().matches("[0-9]")) {
				button.addActionListener(event -> {
					presenter.setResult(Double.parseDouble(button.getText()));
					System.out.println(presenter.getResult());
				});
			}
		}
	}

}
