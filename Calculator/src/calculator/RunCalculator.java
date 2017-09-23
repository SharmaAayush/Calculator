package calculator;

public class RunCalculator {

	public static void main(String[] args) {

		CalculatorModel model = new CalculatorModel();
		CalculatorView view = new CalculatorView(model);
		@SuppressWarnings("unused")
		CalculatorControler controler = new CalculatorControler(model, view);
		view.setVisible(true);
		new java.util.Timer().schedule(new java.util.TimerTask(){

			@Override
			public void run() {
				view.requestFocus();
			}
			
		}, 10);

	}

}
