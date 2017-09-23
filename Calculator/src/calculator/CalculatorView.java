package calculator;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * View class for the Calculator.
 */
@SuppressWarnings("serial")
public class CalculatorView extends JFrame {

	/**
	*Reference variables for ui components.
	*/
	private JTextField m_userInputTf;
	private JTextField m_totalTf;
	private JButton m_numberBtns[] = new JButton[10];
	private JButton m_decimalBtn;
	private JButton m_operatorBtns[] = new JButton[5];
	private JButton m_allClearBtn;
	private JButton m_clearBtn;
	private JLabel m_input;
	private JLabel m_result;
	/**
	 *Reference variable for storing the Model's reference id.
	 */
	private CalculatorModel calcModel;
	
	/**
	 * Constructor to store the Model reference and provide the UI.
	 */
	public CalculatorView(CalculatorModel model){
		this.calcModel = model;
		calcModel.reset();
		
		/**
		 * Initialize, Style, and add the components the components to Frame.
		 */
		JPanel content = new JPanel();
		m_userInputTf = new JTextField(20);
		m_userInputTf.setEditable(false);
		m_userInputTf.setHorizontalAlignment(JTextField.RIGHT);
		content.add(m_userInputTf);
		m_totalTf = new JTextField(20);
		m_totalTf.setEditable(false);
		m_totalTf.setHorizontalAlignment(JTextField.RIGHT);
		content.add(m_totalTf);
		for(int i = 0;i < m_numberBtns.length;i++){
			m_numberBtns[i] = new JButton(""+i);
			content.add(m_numberBtns[i]);
			m_numberBtns[i].setBackground(new Color(58,58,58));
			m_numberBtns[i].setForeground(Color.WHITE);
		}
		m_decimalBtn = new JButton(".");
		m_decimalBtn.setBackground(new Color(58,58,58));
		m_decimalBtn.setForeground(Color.white);
		content.add(m_decimalBtn);
		m_operatorBtns[0] = new JButton("+");
		m_operatorBtns[1] = new JButton("-");
		m_operatorBtns[2] = new JButton("*");
		m_operatorBtns[3] = new JButton("/");
		m_operatorBtns[4] = new JButton("=");
		for(int i = 0; i < 5; i++){
			m_operatorBtns[i].setBackground(new Color(58,58,58));
			m_operatorBtns[i].setForeground(Color.white);
		}
		m_allClearBtn = new JButton("AC");
		m_allClearBtn.setBackground(new Color(167,45,69));
		m_allClearBtn.setForeground(Color.white);
		content.add(m_allClearBtn);
		m_clearBtn = new JButton("CE");
		m_clearBtn.setBackground(new Color(167,45,69));
		m_clearBtn.setForeground(Color.white);
		content.add(m_clearBtn);
		m_input = new JLabel("Input");
		content.add(m_input);
		m_result = new JLabel("Result");
		content.add(m_result);
		for(int i = 0;i < m_operatorBtns.length;i++){
			content.add(m_operatorBtns[i]);
		}
		
		/**
		 * Position the components
		 */
		this.setBounds(20,20,360,280);
		m_input.setBounds(20,20,40,20);
		m_userInputTf.setBounds(80,20,240,20);
		m_result.setBounds(20,50,40,20);
		m_totalTf.setBounds(80,50,240,20);
		m_allClearBtn.setBounds(20,80,60,20);
		m_clearBtn.setBounds(100,80,60,20);
		m_operatorBtns[3].setBounds(180,80,60,20);
		m_operatorBtns[2].setBounds(260,80,60,20);
		m_numberBtns[7].setBounds(20, 110, 60, 20);
		m_numberBtns[8].setBounds(100, 110, 60, 20);
		m_numberBtns[9].setBounds(180, 110, 60, 20);
		m_operatorBtns[1].setBounds(260, 110, 60, 20);
		m_numberBtns[4].setBounds(20, 140, 60, 20);
		m_numberBtns[5].setBounds(100, 140, 60, 20);
		m_numberBtns[6].setBounds(180, 140, 60, 20);
		m_operatorBtns[0].setBounds(260, 140, 60, 20);
		m_numberBtns[1].setBounds(20, 170, 60, 20);
		m_numberBtns[2].setBounds(100, 170, 60, 20);
		m_numberBtns[3].setBounds(180, 170, 60, 20);
		m_operatorBtns[4].setBounds(260, 170, 60, 50);
		m_numberBtns[0].setBounds(20, 200, 140, 20);
		m_decimalBtn.setBounds(180, 200, 60, 20);
		
		
		
		content.setLayout(null);
		this.setContentPane(content);
		this.setTitle("Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content.setBackground(new Color(221,221,221));
		reset();
	}
	
	/**
	 * Resets the content of User Input field and Total Field.
	 */
	public void reset(){
		calcModel.reset();
		m_userInputTf.setText("0");
		m_totalTf.setText("0");
	}
	
	/**
	 * Method to get the contents of m_userInputTf text field.
	 * Returns the expression inputed by user.
	 */
	public String getUserInput(){
		return m_userInputTf.getText();
	}
	
	/**
	 * Method used to set the String in user input field.
	 * Used to set the expression in user input field so that user may
	 * not enter invalid expressions.
	 */
	public void setUserInput(String input){
		m_userInputTf.setText(input);
	}
	
	/**
	 * returns the current value of Total field.
	 */
	public String getTotal(){
		return m_totalTf.getText();
	}
	
	/**
	 * sets the total value of the evaluated expression in Total field.
	 */
	public void setTotal(String total){
		m_totalTf.setText(total);
	}
	
	/**
	 * Show a message if the user tries an invalid expression.
	 */
	public void showError(String err){
		JOptionPane.showMessageDialog(this, err);
	}
	
	/**
	 * Adds an ActionListener to the m_decimalBtn ('.' button).
	 */
	public void addDecimalListener(ActionListener decimal,Action act){
		m_decimalBtn.addActionListener(decimal);
		m_decimalBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('.'), "pressed");
		m_decimalBtn.getActionMap().put("pressed", act);
	}

	/**
	 * Adds an ActionListener to the m_allClearBtn ('AC' button).
	 */
	public void addACListener(ActionListener ac,Action act){
		m_allClearBtn.addActionListener(ac);
		m_allClearBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "pressed");
		m_allClearBtn.getActionMap().put("pressed", act);
	}
	
	/**
	 * Adds an ActionListener to the m_clearBtn ('CE' button).
	 */
	public void addCEListener(ActionListener ce,Action act){
		m_clearBtn.addActionListener(ce);
		m_clearBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0), "pressed");
		m_clearBtn.getActionMap().put("pressed", act);
	}
	
	/**
	 * Adds an ActionListener to the specified numeral button (0-9 buttons).
	 */
	public void addNumeralListener(int number,ActionListener listener,Action act){
		m_numberBtns[number].addActionListener(listener);
		m_numberBtns[number].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)(number+48)), "pressed");
		m_numberBtns[number].getActionMap().put("pressed", act);
	}
	
	/**
	 * Adds an ActionListener to the addition button ('+' button).
	 */
	public void addAdditionListener(ActionListener add,Action act){
		m_operatorBtns[0].addActionListener(add);
		m_operatorBtns[0].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('+'), "pressed");
		m_operatorBtns[0].getActionMap().put("pressed", act);
	}
	
	/**
	 * Adds an ActionListener to the subtraction button ('-' button).
	 */
	public void addSubtractionListener(ActionListener sub,Action act){
		m_operatorBtns[1].addActionListener(sub);
		m_operatorBtns[1].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('-'), "pressed");
		m_operatorBtns[1].getActionMap().put("pressed", act);
	}
	
	/**
	 * Adds an ActionListener to the multiplication button ('*' button).
	 */
	public void addMultiplicationListener(ActionListener mul,Action act){
		m_operatorBtns[2].addActionListener(mul);
		m_operatorBtns[2].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('*'), "pressed");
		m_operatorBtns[2].getActionMap().put("pressed", act);
	}
	
	/**
	 * Adds an ActionListener to the division button ('/' button).
	 */
	public void addDivisionListener(ActionListener div,Action act){
		m_operatorBtns[3].addActionListener(div);
		m_operatorBtns[3].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('/'), "pressed");
		m_operatorBtns[3].getActionMap().put("pressed", act);
	}
	
	/**
	 * Adds an ActionListener to the equals button ('=' button).
	 */
	public void addEvaluationListener(ActionListener eval,Action act){
		m_operatorBtns[4].addActionListener(eval);
		m_operatorBtns[4].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "pressed");
		m_operatorBtns[4].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('='), "pressed");
		m_operatorBtns[4].getActionMap().put("pressed", act);
	}
	
}
