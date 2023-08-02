// Mostert L.E.
// 20805330
// Projek

public class FixedRate extends Loan
{
	private int years;

	public FixedRate(String firstName, String lastName, String idNumber, String referenceNumber, double loanAmount,
					   double rate, int startingYear, int years)
	{
		super(firstName, lastName, idNumber, referenceNumber, loanAmount, rate, startingYear);
		setYears(years);
	} //end constructor

	public FixedRate()
	{
		super();
		this.years = 0;
	}

	public void setYears(int years)
	{
		if (years < 0)
			this.years = 0;
		else
			this.years = years;
	}

	public int getYears()
	{
		return years;
	}

	public int getEndYear()
	{
		return getStartYear() + years;
	}


	public String toString()
	{
		return String.format("\nLoan Type: %s%s\n-Amount of Years: %s\n", this.getClass().getName(), super.toString(), getYears() ); 
	}

	public double totalAmount() //calculates the total amount to be repaid at the end of the loan term
	{
		double amount = 0.0;
		double rate = getRate() / 100; //converts perccentage to fraction

		amount = getLoanAmount() + (getLoanAmount() * rate * getYears());
		
		return amount;
	}

	public double runningAmount(int checkYear) //calculates the amount owed in the specified year
	{
		double amount = 0.0;
		double rate = getRate() / 100; //converts perccentage to fraction

		amount = getLoanAmount() + (getLoanAmount() * rate * (checkYear - getStartYear()));

		return amount;
	}

	public double totalInterest() //calculates the total amount of interest to be repaid at the end of the loan term
	{
		return totalAmount() - getLoanAmount();
	}

} //end class FixedRate

