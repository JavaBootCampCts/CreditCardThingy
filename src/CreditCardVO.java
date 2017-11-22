import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCardVO {
	private String creditCardNumber;
	private int billAmmount;
	private int fine;
	private Date dueDate;
	private Date paymentDate;
	private String customerName;
	private char creditCardGrade;

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public int getBillAmmount() {
		return billAmmount;
	}

	public void setBillAmmount(int billAmmount) {
		this.billAmmount = billAmmount;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public char getCreditCardGrade() {
		return creditCardGrade;
	}

	public void setCreditCardGrade(char creditCardGrade) {
		this.creditCardGrade = creditCardGrade;
	}

	@Override
	public String toString() {
		return "CreditCardVO [creditCardNumber=" + creditCardNumber
				+ ", billAmmount=" + billAmmount + ", fine=" + fine
				+ ", dueDate=" + dueDate + ", paymentDate=" + paymentDate
				+ ", customerName=" + customerName + ", creditCardGrade="
				+ creditCardGrade + "]";
	}

	public boolean equals(Object obj) {
		CreditCardVO other = (CreditCardVO) obj;
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		boolean isEqual = false;
		if (creditCardGrade == other.creditCardGrade
				&& creditCardNumber.equals(other.creditCardNumber)
				&& fine == other.fine
				&& formatter.format(paymentDate).equals(
						formatter.format(other.paymentDate))) {
			isEqual = true;
		}
		return isEqual;

	}
}
