import java.util.HashMap;
import java.util.Map;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreditCardFineCalculator calculator = new CreditCardFineCalculator();
		Map<Integer, Map> mainMap = new HashMap<Integer, Map>();
		try {
			mainMap = calculator.getCreditCardFineDetails("input.txt");
		} catch (CreditCardFineCalculatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mainMap);
	}
}
