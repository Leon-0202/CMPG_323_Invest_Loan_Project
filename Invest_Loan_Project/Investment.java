// Mostert L.E.
// 20805330
// Projek

public abstract class Investment implements Bank
{
	private String firstName;
	private String lastName;
	private String idNumber;
	private String referenceNumber;
	private double investmentAmount;
	private double rate;
	private int startingYear;
	private int years;

	public Investment(String firstName, String lastName, String idNumber, String referenceNumber, double investmentAmount,
		              double rate, int startingYear, int years)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.referenceNumber = referenceNumber;
		setInvestAmount(investmentAmount);
		setRate(rate);
		setStartYear(startingYear);
		setYears(years);

	} //end constructor

	public Investment()
	{
		this.firstName = "";
		this.lastName = "";
		this.idNumber = "";
		this.referenceNumber = "";
		this.investmentAmount = 0.0;
		this.rate = 0.0;
		this.startingYear = 0;
		this.years = 0;
	} //end default constructor

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setIDNumber(String idNumber)
	{
		this.idNumber = idNumber;
	}

	public String getIDNumber()
	{
		return idNumber;
	}

	public void setRefNumber(String referenceNumber)
	{
		this.referenceNumber = referenceNumber;
	}

	public String getRefNumber()
	{
		return referenceNumber;
	}

	public void setInvestAmount(double investmentAmount)
	{
		if (investmentAmount < 0.0)
			this.investmentAmount = 0.0;
		else
			this.investmentAmount = investmentAmount;
	}

	public double getInvestAmount()
	{
		return investmentAmount;
	}

	public void setRate(double rate)
	{
		if (rate < 0 || rate > 100)
			this.rate = 0.0;
		else
			this.rate = rate;
	}

	public double getRate()
	{
		return rate;
	}

	public void setStartYear(int startingYear)
	{
		if (startingYear < 0)
			this.startingYear = 0;
		else
			this.startingYear = startingYear;
	}

	public int getStartYear()
	{
		return startingYear;
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
		return startingYear + years;
	}


	public String toString()
	{
		return "\nInvestor Details:" + 
			   "\n-First Name: " + firstName +
			   "\n-Last Name: " + lastName + 
			   "\n-Identity Number: " + idNumber +
			   "\nInvestment Details:" + 
			   "\n-Reference Number: " + referenceNumber +
			   "\n-Investment Amount: R" + investmentAmount +
			   "\n-Rate: " + rate + "%" +
			   "\n-Starting Year: " + startingYear +
			   "\n-Amount of Years: " + years + "\n";
	}


} //end abstract class Investment


