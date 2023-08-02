// Mostert L.E.
// 20805330
// Projek

public class YearlyPayment extends Loan
{
	private double payment;

	public YearlyPayment(String firstName, String lastName, String idNumber, String referenceNumber, double loanAmount,
					   double rate, int startingYear, double payment)
	{
		super(firstName, lastName, idNumber, referenceNumber, loanAmount, rate, startingYear);
		setPayment(payment);
	} //end constructor

	public YearlyPayment()
	{
		super();
		this.payment = 0;
	}

	public void setPayment(double payment)
	{
		if (payment < 0.0)
			this.payment = 0.0;
		else
			this.payment = payment;
	}

	public double getPayment()
	{
		return payment;
	}

	public String toString()
	{
		return String.format("\nLoan Type: %s%s\n-Payment per year: R%.2f\n", this.getClass().getName(), super.toString(), getPayment() ); 
	}

	public double totalAmount() //calculates the total amount that was repaid to clear the debt
	{
		double amountOwed = getLoanAmount();
		double totalAmount = 0.0;
		double rate = getRate() / 100; //converts perccentage to fraction

		while (amountOwed > payment)
		{
			amountOwed = amountOwed + (amountOwed * rate) - payment;
			totalAmount = totalAmount + payment;
		}

		totalAmount = totalAmount + amountOwed;
		
		return totalAmount;
	}

	public double runningAmount(int checkYear) //calculates the amount currently still owed in the specified year
	{
		double amountOwed = getLoanAmount();
		double rate = getRate() / 100; //converts perccentage to fraction

		for (int i = 0; i < (checkYear - getStartYear()); i++ )
		{
			amountOwed = amountOwed + (amountOwed * rate) - payment;
		}

		return amountOwed;
	}

	public int getYearCount() //calculates the amount of years it will take to repay the dept
	{
		int yearCount = 0;
		double amountOwed = getLoanAmount();
		double rate = getRate() / 100; //converts perccentage to fraction

		while (amountOwed > payment)
		{
			amountOwed = amountOwed + (amountOwed * rate) - payment;
			yearCount++;
		}

		return yearCount;
	}

	public double totalInterest() //calculates the amount of interest that was paid till the debt was cleared
	{
		return ( totalAmount() - getLoanAmount() );
	}

} //end class YearlyPayment

