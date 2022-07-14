package calculator.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TaschenrechnerPresenter {
	
	private double result;
	
	private double value1;
	
	
	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public double add(double num1, double num2) {
		return num1 + num2;
	}
	
	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		double oldResult = this.result;
		this.result = result;
		propertyChangeSupport.firePropertyChange("result", oldResult, result);
	}
	
	
	
	public double getValue1() {
		return value1;
	}

	public void setValue1(double value) {
		if(this.value1 == 0) {
			this.value1 = value;
		}else {
			this.value1 = Double.parseDouble( this.value1 + "" + (int) value);
		}
	}

}
