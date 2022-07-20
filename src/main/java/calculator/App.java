package calculator;

import calculator.model.TaschenrechnerPresenter;
import calculator.view.TaschenrechnerGUI;

public class App {
	public static void main(String[] args) {
		TaschenrechnerPresenter presenter = new TaschenrechnerPresenter();
		TaschenrechnerGUI gui = new TaschenrechnerGUI(presenter);
		gui.setVisible(true);
	}
}
