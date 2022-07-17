package calculator.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TaschenrechnerPresenter {

	private String value = "";

	private double numValue;

	private boolean operator;

	private String lastOperator = "+";

	private String errorMessage = "";

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public double add(double num1, double num2) {
		if(num1 + num2 > Double.MAX_VALUE) throw new IllegalArgumentException("Überlauf");
		return num1 + num2;
	}

	public double sub(double num1, double num2) {
		return num1 - num2;
	}
	
	public double mul(double num1, double num2) {
		if(num1 * num2 > Double.MAX_VALUE) throw new IllegalArgumentException("Überlauf");
		return num1 * num2;
	}

	public double div(double num1, double num2) {
		if (num2 == 0) {
			throw new IllegalArgumentException("Teilen durch 0 nicht möglich");
		}
		return num1 / num2;
	}
	
	public double square(double num) {
		if(Math.pow(num, 2) > Double.MAX_VALUE) throw new IllegalArgumentException("Überlauf");
		return Math.pow(num, 2);
	}
	
	public double sqrt(double num) {
		if(num<0) throw new IllegalArgumentException("Ungültige Eingabe");
		return Math.sqrt(num);
	}
	
	public double hyperbel(double num) {
		if (num == 0) {
			throw new IllegalArgumentException("Teilen durch 0 nicht möglich");
		}
		return 1/num;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		String oldValue = this.value;
		if (!value.equals(".") || !this.value.contains(".")) {
			if (errorMessage.isEmpty()) {
				this.value = this.value.concat(value);
			} else {
				this.value = errorMessage;
				this.numValue = 0;
			}
		}
		propertyChangeSupport.firePropertyChange("value1", oldValue, this.value);
	}
	

	public double getNumValue() {
		return numValue;
	}

	public void setNumValue(double numValue1) {
		this.numValue = numValue1;
	}

	public void resetValue() {
		this.value = "";
	}

	public boolean isOperator() {
		return operator;
	}

	public void setOperator(boolean operator) {
		this.operator = operator;
	}

	public String getLastOperator() {
		return lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		String oldErrorMessage = this.errorMessage;
		this.errorMessage = errorMessage;
		propertyChangeSupport.firePropertyChange("errorMessage", oldErrorMessage , this.errorMessage);
	}

}
