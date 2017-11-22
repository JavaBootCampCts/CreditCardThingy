import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class CreditCardFineCalculator {

	public Map<Integer, Map> getCreditCardFineDetails(final String filePath)
			throws CreditCardFineCalculatorException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Map<Integer, Map> mainMap = new HashMap<Integer, Map>();
		Map<String, CreditCardVO> VISAMap = new HashMap<String, CreditCardVO>();
		Map<String, CreditCardVO> AMEXMap = new HashMap<String, CreditCardVO>();

		File file1 = new File(filePath);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// List<CreditCardVO> cardList = new ArrayList<CreditCardVO>();
		String currentLine;
		try {
			while ((currentLine = bufferedReader.readLine()) != null) {
				// System.out.println("currentLine: " + currentLine);
				// String[] current = currentLine.split("|");

				StringTokenizer stringTokenizer = new StringTokenizer(
						currentLine, "|");
				CreditCardVO cardVO = new CreditCardVO();
				while (stringTokenizer.hasMoreTokens()) {

					cardVO.setCreditCardNumber(stringTokenizer.nextToken());
					cardVO.setCustomerName(stringTokenizer.nextToken());
					cardVO.setBillAmmount(Integer.parseInt(stringTokenizer
							.nextToken()));
					try {
						cardVO.setDueDate((Date) formatter
								.parse(stringTokenizer.nextToken()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						cardVO.setPaymentDate((Date) formatter
								.parse(stringTokenizer.nextToken()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				// code to identify card type
				if (cardVO.getCreditCardNumber().startsWith("4")
						&& cardVO.getCreditCardNumber().length() == 16) {
					System.out.println("card type is VISA");
					long diff = cardVO.getPaymentDate().getTime()
							- cardVO.getDueDate().getTime();
					int days = (int) TimeUnit.DAYS.convert(diff,
							TimeUnit.MILLISECONDS);
					if (days <= 0) {
						cardVO.setCreditCardGrade('A');
					} else {
						cardVO.setCreditCardGrade('B');
					}
					if (days > 0 && days < 5) {
						cardVO.setFine((int) (cardVO.getBillAmmount() * 0.1));
					} else {
						cardVO.setFine((int) (cardVO.getBillAmmount() * 0.2));
					}

					if (VISAMap.containsKey(cardVO.getCreditCardNumber())) {
						CreditCardVO oldVO = VISAMap.get(cardVO
								.getCreditCardNumber());
						long diff2 = cardVO.getPaymentDate().getTime()
								- oldVO.getPaymentDate().getTime();
						int days2 = (int) TimeUnit.DAYS.convert(diff2,
								TimeUnit.MILLISECONDS);
						if (days2 > 0) {
							VISAMap.put(cardVO.getCreditCardNumber(), cardVO);
						} else {
							VISAMap.put(oldVO.getCreditCardNumber(), oldVO);
						}
					} else {
						VISAMap.put(cardVO.getCreditCardNumber(), cardVO);
					}
				} else if (cardVO.getCreditCardNumber().startsWith("34")
						|| cardVO.getCreditCardNumber().startsWith("37")) {
					if (cardVO.getCreditCardNumber().length() == 15) {
						System.out.println("cardtype if AMEX");
						long diff = cardVO.getPaymentDate().getTime()
								- cardVO.getDueDate().getTime();
						int days = (int) TimeUnit.DAYS.convert(diff,
								TimeUnit.MILLISECONDS);
						if (days <= 0) {
							cardVO.setCreditCardGrade('A');
						} else {
							cardVO.setCreditCardGrade('B');
						}
						if (days <= 0) {
							cardVO.setCreditCardGrade('A');
						} else {
							cardVO.setCreditCardGrade('B');
						}
						if (days > 0 && days < 5) {
							cardVO.setFine((int) (cardVO.getBillAmmount() * 0.1));
						} else if (cardVO.getBillAmmount() <= 15000) {
							cardVO.setFine((int) (cardVO.getBillAmmount() * 0.2));
						} else {
							cardVO.setFine((int) (cardVO.getBillAmmount() * 0.3));
						}
						if (AMEXMap.containsKey(cardVO.getCreditCardNumber())) {
							CreditCardVO oldVO = AMEXMap.get(cardVO
									.getCreditCardNumber());
							long diff2 = cardVO.getPaymentDate().getTime()
									- oldVO.getPaymentDate().getTime();
							int days2 = (int) TimeUnit.DAYS.convert(diff2,
									TimeUnit.MILLISECONDS);
							if (days2 > 0) {
								AMEXMap.put(cardVO.getCreditCardNumber(),
										cardVO);
							} else {
								AMEXMap.put(oldVO.getCreditCardNumber(), oldVO);
							}
						} else {
							AMEXMap.put(cardVO.getCreditCardNumber(), cardVO);
						}
					}

				}
			}
			mainMap.put(1, VISAMap);
			mainMap.put(2, AMEXMap);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mainMap;
	}
}
