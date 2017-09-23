package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

public class CalculatorControler {

	/**
	 * Variables to store reference of CalculatorModel and CalculatorView.
	 */
	private CalculatorModel model;
	private CalculatorView view;
	
	/**
	 * Constructor
	 */
	public CalculatorControler(CalculatorModel model,CalculatorView view){
		this.model = model;
		this.view = view;
		
		/**
		 * Adding listener to the view.
		 */
		NumeralListener numL = new NumeralListener();
		OperatorListener opL = new OperatorListener();
		@SuppressWarnings("serial")
		Action act = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				source.doClick();
			}
		};
		for(int i = 0;i < 10;i++)
			view.addNumeralListener(i, numL, act);
		view.addDecimalListener(new DecimalListener(), act);
		view.addCEListener(new ClearListener(), act);
		view.addACListener(new AllClearListener(), act);
		view.addAdditionListener(opL, act);
		view.addSubtractionListener(opL, act);
		view.addMultiplicationListener(opL, act);
		view.addDivisionListener(opL, act);
		view.addEvaluationListener(new EvaluationListener(), act);
	}
	
	/**
	 *Listener Class for numeral buttons (0-9 buttons).
	 */
	class NumeralListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton source = (JButton)event.getSource();
			if(!model.getLastExpression().equals("")&&model.eavluate(model.getLastExpression()).equals(model.getOperand())){
				model.resetOperand();
				model.appendOperand(source.getText());
				view.setUserInput(model.getExpression() + model.getOperand());
			}
			else if(model.getOperand().equals("0")&&source.getText().equals("0")){
				view.setUserInput(model.getExpression() + model.getOperand());
			}
			else{
				model.appendOperand(source.getText());
				view.setUserInput(model.getExpression() + model.getOperand());
			}
		}
		
	}

	/**
	 * Listener Class for decimal button ('.' button)
	 */
	class DecimalListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			String operand = model.getOperand();
			if(operand.indexOf(".")==-1){
				if(operand.length()==0)
					model.appendOperand("0");
				model.appendOperand(".");
			}
			else{
				view.showError("Illegal syntax of operand.\n" +
			"Operand cannot contain more than one decimal.");
			}
			view.setUserInput(model.getExpression()+model.getOperand());
		}
		
	}

	/**
	 * Listener Class for clear button ('CE' button).
	 */
	class ClearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!model.getLastExpression().equals("")){
				model.setExpression();
				model.resetOperand();
			}
			model.clearOperation();
			String exp = model.getExpression() + model.getOperand();
			if(exp.length()==0)
				exp="0";
			view.setUserInput(exp);
		}
		
	}

	/**
	 * Listener Class for allclear button ('AC' button).
	 */
	class AllClearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.reset();
			view.setUserInput("0");
		}
		
	}

	/**
	 *Listener Class for operator buttons (+,-,*,/ buttons).
	 */
	class OperatorListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			String exp = model.getExpression()+model.getOperand();
			int l = exp.length();
			if(l==0 && !(source.getText()=="-" || source.getText()=="+"))
				view.showError("An operand is needed before operator");
			else if(l==0 && (source.getText()=="-"||source.getText()=="+")){
				model.appendOperand("0");
				model.addOperand();
				model.addOperator(source.getText());
				model.resetOperand();
				view.setUserInput(model.getExpression()+model.getOperand());
			}
			else if(exp.charAt(l-1)=='+'||exp.charAt(l-1)=='-'||exp.charAt(l-1)=='*'||exp.charAt(l-1)=='/'){
				view.showError("An Operand is needed after an operator.");
			}
			else{
				model.addOperand();
				model.addOperator(source.getText());
				model.resetOperand();
				view.setUserInput(model.getExpression()+model.getOperand());
			}
				
		}
		
	}

	/**
	 * Listener Class for equals button ('=' button).
	 */
	class EvaluationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String exp = model.getExpression()+model.getOperand();
			int l = exp.length();
			if(exp.charAt(l-1)=='+'||exp.charAt(l-1)=='-'||exp.charAt(l-1)=='*'||exp.charAt(l-1)=='/'){
				view.showError("Invalid Expression.\n"+
			"Expression cannot end with +,-,*,or /");
			}
			else{
				try {
					String result = model.evaluate();
					view.setTotal(result);
					view.setUserInput(exp);
					model.reset();
					model.setLastExpression(exp);
					model.appendOperand(result);
				} catch (ScriptException e1) {
					view.showError("Invalid Expression.");
				}
			}
		}
		
	}

}
