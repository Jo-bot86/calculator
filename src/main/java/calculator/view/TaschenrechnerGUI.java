package calculator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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

	private JTextField display;

	private JPanel menuPanel;

	public TaschenrechnerGUI() {
		setSize(335, 538);
		setTitle("Rechner");
//		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addPanelToFrame();
		addGridBagToPanel();
		buildCalculator();

		handleClickOnButton();
		handleHoverOnButton();
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
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0;
		menuPanel = buildMenu();
		panel.add(menuPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.weighty = 1;
		display = buildDisplay();
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
		addGB(panel, topRow, 0, 2);
		c.weighty = 1;
		c.gridwidth = 1;
		makeButton("%", 0, 3, veryLightGrey);
		makeButton("√", 1, 3, veryLightGrey);
		makeButton("x²", 2, 3, veryLightGrey);
		makeButton("1/x", 3, 3, veryLightGrey);

		makeButton("CE", 0, 4, veryLightGrey);
		makeButton("C", 1, 4, veryLightGrey);
		makeButton("⌫", 2, 4, veryLightGrey);
		makeButton("÷", 3, 4, veryLightGrey); // Division

		makeButton("7", 0, 5, Color.WHITE);
		makeButton("8", 1, 5, Color.WHITE);
		makeButton("9", 2, 5, Color.WHITE);
		makeButton("×", 3, 5, veryLightGrey); // Multiplikation

		makeButton("4", 0, 6, Color.WHITE);
		makeButton("5", 1, 6, Color.WHITE);
		makeButton("6", 2, 6, Color.WHITE);
		makeButton("−", 3, 6, veryLightGrey); // Substraktion

		makeButton("1", 0, 7, Color.WHITE);
		makeButton("2", 1, 7, Color.WHITE);
		makeButton("3", 2, 7, Color.WHITE);
		makeButton("+", 3, 7, veryLightGrey); // Addition

		makeButton("±", 0, 8, veryLightGrey);
		makeButton("0", 1, 8, Color.WHITE);
		makeButton(",", 2, 8, Color.WHITE);
		makeButton("=", 3, 8, veryLightGrey);
	}

	private JButton buildHamburgerButton() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/hamburger-menu.png"));
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImage);
//		JButton hamburgerBtn = new JButton("\u2630");
		JButton hamburgerBtn = new JButton(icon);

		hamburgerBtn.setBorder(BorderFactory.createEmptyBorder());
		hamburgerBtn.setBackground(panel.getBackground());
		hamburgerBtn.setPreferredSize(new Dimension(40, 40));
		hamburgerBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		addHoverEffect(hamburgerBtn);
		return hamburgerBtn;
	}

	private void addHoverEffect(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			Color originalColor = btn.getBackground();

			@Override
			public void mouseEntered(MouseEvent event) {
				btn.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent event) {
				btn.setBackground(originalColor);
			}

		});
	}

	private JButton buildHistoryButton() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/history-clock.png"));
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImage);
		JButton historyBtn = new JButton(icon);
//		JButton historyBtn = new JButton("history");
		historyBtn.setPreferredSize(new Dimension(40, 40));
		historyBtn.setBorder(BorderFactory.createEmptyBorder());
		historyBtn.setBackground(panel.getBackground());
		addHoverEffect(historyBtn);
		return historyBtn;
	}

	private JPanel buildMenu() {
		JPanel menu = new JPanel(new BorderLayout());
		JTextField calculatorType = new JTextField("Standard");
		calculatorType.setEditable(false);
		calculatorType.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
		calculatorType.setFont(new Font("Arial", Font.BOLD, 20));
		calculatorType.setBackground(panel.getBackground());

		JButton hamburgerBtn = buildHamburgerButton();
		JButton historyBtn = buildHistoryButton();
		menu.add(hamburgerBtn, BorderLayout.WEST);
		menu.add(calculatorType, BorderLayout.CENTER);
		menu.add(historyBtn, BorderLayout.EAST);
		return menu;
	}

	private JTextField buildDisplay() {
		JTextField display = new JTextField("0");
		Font font = new Font("SansSerif", Font.BOLD, 50);
		presenter.addPropertyChangeListener(event -> {
			display.setHorizontalAlignment(SwingConstants.RIGHT);
			if (presenter.getErrorMessage().isEmpty()) {
				if (presenter.getValue().length() > 11)
					display.setFont(new Font("SansSerif", Font.BOLD, 25));
				else
					display.setFont(new Font("SansSerif", Font.BOLD, 50));
			} else {
				display.setFont(new Font("SansSerif", Font.BOLD, 20));
				display.setHorizontalAlignment(SwingConstants.CENTER);
			}
		});
		display.setFont(font);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setEditable(false);
		display.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 5));
		display.setBackground(panel.getBackground());
		handlePropertyChange(display);
		return display;
	}

	private void handlePropertyChange(JTextField display) {
		presenter.addPropertyChangeListener(event -> {
			if (presenter.isOperator()) {
				presenter.setOperator(false);
			}
			display.setText(event.getNewValue().toString());
		});
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
		addHoverEffect(button);
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

	private void handleHoverOnButton() {
		Component[] allComponents = panel.getComponents();
		for (Component c : allComponents) {
			if (c instanceof JButton button) {
				addHoverEffect(button);
			}
		}
	}

	private void handleClickOnButton() {
		Component[] allComponents = panel.getComponents();
		// select all buttons containing a number
		for (Component c : allComponents) {
			if (c instanceof JButton button && button.getText().matches("[0-9]")) {
				button.addActionListener(event -> {
					if (presenter.getValue().length() < 15)
						presenter.setValue(button.getText());
				});
			}
		}

		for (Component c : allComponents) {
			if (c instanceof JButton button) {
				button.addActionListener(event -> {
					String numValue;
					switch (button.getText()) {
					case "÷":
						performOperation("÷");
						break;
					case "×":
						performOperation("×");
						break;
					case "−":
						performOperation("−");
						break;
					case "+":
						performOperation("+");
						break;
					case "x²":
						performInstantOperation("x²");
						break;
					case "√":
						performInstantOperation("√");
						break;
					case "1/x":
						performInstantOperation("1/x");
						break;
					case "=":
						switchLastOperator();
						numValue = removeDecimal();
						presenter.resetValue();
						presenter.setValue(numValue);
						presenter.resetValue();
						break;
					case "\u002C":
						presenter.setValue(".");
						break;
					case "±":
						negateValue();
						break;
					case "⌫":
						String value = presenter.getValue();
						presenter.resetValue();
						if (value.length() <= 1) {
							presenter.resetValue();
							presenter.setErrorMessage("");
							presenter.setNumValue(0);
							presenter.setLastOperator("+");
							presenter.setValue("0");
							presenter.resetValue();
						} else {
							presenter.setValue(value.substring(0, value.length() - 1));
						}

						break;
					case "C":
						presenter.resetValue();
						presenter.setErrorMessage("");
						presenter.setNumValue(0);
						presenter.setLastOperator("+");
						presenter.setValue("0");
						presenter.resetValue();
						break;
					case "CE":
						presenter.resetValue();
						presenter.setErrorMessage("");
						presenter.setValue("0");
						presenter.resetValue();
						break;
					}

				});
			}
		}
	}

	private void negateValue() {
		String value;
		if (presenter.getValue().startsWith("-")) {
			value = presenter.getValue().substring(1);
		} else {
			value = "-".concat(presenter.getValue());
		}
		presenter.resetValue();
		presenter.setValue(value);
	}

	private void performInstantOperation(String operation) {
		String numValue;
		try {
			switch (operation) {
			case "x²":
				if (presenter.getNumValue() == 0) {
					presenter.setNumValue(presenter
							.square(presenter.getValue().equals("") ? 0 : Double.parseDouble(presenter.getValue())));
				} else {
					presenter.setNumValue(presenter.square(presenter.getNumValue()));

				}
				break;
			case "√":
				if (presenter.getNumValue() == 0) {
					presenter.setNumValue(presenter
							.sqrt(presenter.getValue().equals("") ? 0 : Double.parseDouble(presenter.getValue())));
				} else {
					presenter.setNumValue(presenter.sqrt(presenter.getNumValue()));

				}
				break;
			case "1/x":
				if (presenter.getNumValue() == 0) {
					presenter.setNumValue(presenter
							.hyperbel(presenter.getValue().equals("") ? 0 : Double.parseDouble(presenter.getValue())));
				} else {
					presenter.setNumValue(presenter.hyperbel(presenter.getNumValue()));

				}
			}
		} catch (IllegalArgumentException e) {
			presenter.setErrorMessage(e.getMessage());
		}
		presenter.setOperator(true);
		presenter.setValue("");
		presenter.setLastOperator(operation);
		numValue = removeDecimal();
		presenter.resetValue();
		presenter.setValue(numValue);
		presenter.resetValue();
	}

	private void performOperation(String operation) {
		String numValue;
		switchLastOperator();
		presenter.setOperator(true);
		presenter.setValue("");
		presenter.setLastOperator(operation);
		numValue = removeDecimal();
		presenter.resetValue();
		presenter.setValue(numValue);
		presenter.resetValue();
	}

	private String removeDecimal() {
		String numValue = Double.valueOf(presenter.getNumValue()).toString();
		if (numValue.endsWith(".0")) {
			numValue = numValue.replace(".0", "");
		}
		return numValue;
	}

	private void switchLastOperator() {
		try {
			switch (presenter.getLastOperator()) {
			case "÷":
				presenter.setNumValue(presenter.div(presenter.getNumValue(),
						presenter.getValue().equals("") ? 1 : Double.parseDouble(presenter.getValue())));
				break;
			case "×":
				presenter.setNumValue(presenter.mul(presenter.getNumValue(),
						presenter.getValue().equals("") ? 1 : Double.parseDouble(presenter.getValue())));
				break;
			case "−":
				presenter.setNumValue(presenter.sub(presenter.getNumValue(),
						presenter.getValue().equals("") ? 0 : Double.parseDouble(presenter.getValue())));
				break;
			case "+":
				presenter.setNumValue(presenter.add(presenter.getNumValue(),
						presenter.getValue().equals("") ? 0 : Double.parseDouble(presenter.getValue())));
				break;
			}
		} catch (IllegalArgumentException e) {
			presenter.setErrorMessage(e.getMessage());
		}
	}

}
