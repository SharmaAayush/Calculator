package calculator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Model class for the calculator.
 */
public class CalculatorModel {

	/**
	 * Private variable to store the expression.
	 */
	private StringBuilder expression = new StringBuilder();
	private StringBuilder operand = new StringBuilder();
	private StringBuilder lastExpression = new StringBuilder();
	
	/**
	 * Get the current value of lastExpression.
	 */
	public String getLastExpression(){
		return lastExpression.toString();
	}
	
	/**
	 * Set the value of lastExpression.
	 */
	public void setLastExpression(String lastExp){
		lastExpression = new StringBuilder(lastExp);
	}
	
	/**
	 * Method to add operand to the expression.
	 */
	public void appendOperand(String op){
		operand.append(op);
	}
	
	/**
	 * Resets the operand.
	 */
	public void resetOperand(){
		operand = new StringBuilder();
	}
	
	/**
	 * Returns the current contents of the operand variable.
	 */
	public String getOperand(){
		return operand.toString();
	}
	
	/**
	 * Returns the current contents of the expression ariable.
	 */
	public String getExpression(){
		return expression.toString();
	}
	
	/**
	 * Method to delete one character from the end of the expression.
	 */
	public void clearOperation(){
		if(operand.length()>0)
			operand.setLength(operand.length()-1);
		else if(expression.length()>0)
			expression.setLength(expression.length()-1);
		else
			reset();
	}
	
	/**
	 * Appends the current contents of the operand variable to expression variable.
	 */
	public void addOperand(){
		expression.append(operand.toString());
	}
	
	/**
	 * Method to add operator to the expression.
	 */
	public void addOperator(String operator){
		expression.append(operator);
	}
	
	/**
	 * Method to reset the expression.
	 */
	public void reset(){
		expression = new StringBuilder();
		operand = new StringBuilder();
		lastExpression = new StringBuilder();
	}
	
	/**
	 * Method to set the lastExpression to expression and clear the lastExpression.
	 */
	public void setExpression(){
		expression = lastExpression;
		lastExpression = new StringBuilder();
	}
	
	/**
	 * Method to evaluate the current expression.
	 */
	public String evaluate() throws ScriptException{
		return new ScriptEngineManager().getEngineByName("JavaScript").eval(getExpression()+getOperand()).toString();
	}
	public String eavluate(String exp){
		try {
			return new ScriptEngineManager().getEngineByName("JavaScript").eval(exp).toString();
		} catch (ScriptException e) {
			return "";
		}
	}

}
