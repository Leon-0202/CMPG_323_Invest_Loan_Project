// Mostert L.E.
// 20805330
// Projek

public class YearlyAnuity extends Investment
{

	public YearlyAnuity(String firstName, String lastName, String idNumber, String referenceNumber, double investmentAmount,
					   double rate, int startingYear, int years)
	{
		super(firstName, lastName, idNumber, referenceNumber, investmentAmount, rate, startingYear, years);
	} //end constructor

	public YearlyAnuity()
	{
		super();
	}

	public String toString()
	{
		return String.format("\nInvestment Type: %s%s", this.getClass().getName(), super.toString()); 
	}

	public double totalAmount() //calculates the total value of the investment at the end of investment period
	{
		double amount = 0.0;
		double rate = getRate() / 100; //converts perccentage to fraction
		double factor = 0.0;

		factor = Math.pow(1 + rate, getYears());
		factor = (factor - 1)/rate;
		amount = getInvestAmount() * factor;
		
		return amount;
	}

	public double runningAmount(int checkYear) //calculates the current value of the investment in the specified year
	{
		double amount = 0.0;
		double rate = getRate() / 100; //converts perccentage to fraction
		double factor = 0.0;

		factor = Math.pow(1 + rate, checkYear - getStartYear());
		factor = (factor - 1) / rate;
		amount = getInvestAmount() * factor;

		return amount;
	}

	public double totalInterest() //calculates the amount of interest earned by the end of the investment period
	{
		return totalAmount() - (getInvestAmount() * getYears());
	}

} //end class YearlyAnuity

