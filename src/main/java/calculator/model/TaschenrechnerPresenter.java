package calculator.model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class TaschenrechnerPresenter extends Observable {

	private String value = "";

	private double numValue;

	private boolean operator;

	private String lastOperator = "+";

	private String errorMessage = "";

	public double add(double num1, double num2) {
		if (num1 + num2 > Double.MAX_VALUE)
			throw new IllegalArgumentException("Überlauf");
		return num1 + num2;
	}

	public double sub(double num1, double num2) {
		if (num1 - num2 < Double.MIN_VALUE)
			throw new IllegalArgumentException("Überlauf");
		return num1 - num2;
	}

	public double mul(double num1, double num2) {
		if (num1 * num2 > Double.MAX_VALUE)
			throw new IllegalArgumentException("Überlauf");
		return num1 * num2;
	}

	public double div(double num1, double num2) {
		if (num2 == 0) {
			throw new IllegalArgumentException("Teilen durch 0 nicht möglich");
		}
		return num1 / num2;
	}

	public double square(double num) {
		if (Math.pow(num, 2) > Double.MAX_VALUE)
			throw new IllegalArgumentException("Überlauf");
		return Math.pow(num, 2);
	}

	public double sqrt(double num) {
		if (num < 0)
			throw new IllegalArgumentException("Ungültige Eingabe");
		return Math.sqrt(num);
	}

	public double hyperbel(double num) {
		if (num == 0) {
			throw new IllegalArgumentException("Teilen durch 0 nicht möglich");
		}
		return 1 / num;
	}

	public String negateValue() {
		String value;
		if (this.value.startsWith("-")) {
			value = this.value.substring(1);
		} else {
			value = "-".concat(this.value);
		}
		return value;
	}

	public String removeDecimal() {
		String numValue = Double.valueOf(this.value).toString();
		if (numValue.endsWith(".0")) {
			numValue = numValue.replace(".0", "");
		}
		return numValue;
	}

	public void performOperation(String operation) {
		String numValue;
		switchLastOperator();
		operator = true;
		setValue("");
		lastOperator = operation;
		numValue = removeDecimal();
		value = "";
		setValue(numValue);
		value = "";
	}
	
	public void performInstantOperation(String operation) {
		String numValue;
		try {
			switch (operation) {
			case "x²":
				if (this.numValue == 0) {
					this.numValue = square(value.equals("") ? 0 : Double.parseDouble(value));
				} else {
					this.numValue = square(this.numValue);

				}
				break;
			case "√":
				if (this.numValue == 0) {
					this.numValue = sqrt(value.equals("") ? 0 : Double.parseDouble(value));
				} else {
					this.numValue = sqrt(this.numValue);

				}
				break;
			case "1/x":
				if (this.numValue == 0) {
					this.numValue = hyperbel(value.equals("") ? 0 : Double.parseDouble(value));
				} else {
					this.numValue = hyperbel(this.numValue);

				}
				break;
			}
			value = Double.valueOf(this.numValue).toString();

		} catch (IllegalArgumentException e) {
			errorMessage = e.getMessage();
		}
		operator = true;
		setValue("");
		lastOperator = operation;
		numValue = removeDecimal();
		value = "";
		setValue(numValue);
		value = "";
	}

	public void switchLastOperator() {
		try {
			switch (lastOperator) {
			case "÷":
				numValue = div(numValue, value.equals("") ? 1 : Double.parseDouble(value));
				break;
			case "×":
				numValue = mul(numValue, value.equals("") ? 1 : Double.parseDouble(value));
				break;
			case "−":
				numValue = sub(numValue, value.equals("") ? 0 : Double.parseDouble(value));
				break;
			case "+":
				numValue = add(numValue, value.equals("") ? 0 : Double.parseDouble(value));
				break;
			}
			this.value = Double.valueOf(numValue).toString();
		} catch (IllegalArgumentException e) {
			errorMessage = e.getMessage();
		}
	}

	public void performEqualsOperation() {
		String numValue;
		switchLastOperator();
		numValue = removeDecimal();
		value = "";
		setValue(numValue);
		value = "";
	}

	public void performClearEntry() {
		value = "";
		errorMessage = "";
		setValue("0");
		value = "";
	}

	public void performBackSpace() {
		String value = this.value;
		this.value = "";
		if (value.length() <= 1) {
			performClear();
		} else {
			setValue(value.substring(0, value.length() - 1));
		}
	}

	public void performClear() {
		value = "";
		errorMessage = "";
		numValue = 0;
		lastOperator = "+";
		setValue("0");
		value = "";
	}

	public void performNegateValue() {
		String value = negateValue();
		this.value = "";
		setValue(value);
	}

	private void setValue(String value) {
		if (!value.equals(".") || !this.value.contains(".")) {
			if (errorMessage.isEmpty()) {
				this.value = this.value.concat(value);
			} else {
				this.value = errorMessage;
				this.numValue = 0;
			}
		}
		if (operator) {
			operator = false;
		}
		notifyGui();
	}
	
	public String getValue() {
		return value;
	}

	public double getNumValue() {
		return numValue;
	}

	public boolean isOperator() {
		return operator;
	}


	public String getLastOperator() {
		return lastOperator;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	
	public void notifyGui() {
		setChanged();
		notifyObservers(value);
	}

	public void performConcatination(String text) {
		setValue(text);	
	}

	public void performDotConcatination() {
		setValue(".");
	}

}
