// Mostert L.E.
// 20805330
// Projek

public abstract class Loan implements Bank
{
	private String firstName;
	private String lastName;
	private String idNumber;
	private String referenceNumber;
	private double loanAmount;
	private double rate;
	private int startingYear;

	public Loan(String firstName, String lastName, String idNumber, String referenceNumber, double loanAmount,
		              double rate, int startingYear)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.referenceNumber = referenceNumber;
		setLoanAmount(loanAmount);
		setRate(rate);
		setStartYear(startingYear);

	} //end constructor

	public Loan()
	{
		this.firstName = "";
		this.lastName = "";
		this.idNumber = "";
		this.referenceNumber = "";
		this.loanAmount = 0.0;
		this.rate = 0.0;
		this.startingYear = 0;
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

	public void setLoanAmount(double loanAmount)
	{
		if (loanAmount < 0.0)
			this.loanAmount = 0.0;
		else
			this.loanAmount = loanAmount;
	}

	public double getLoanAmount()
	{
		return loanAmount;
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


	public String toString()
	{
		return "\nLoaner Details:" +
			   "\n-First Name: " + firstName +
			   "\n-Last Name: " + lastName +
			   "\n-Identity Number: " + idNumber +
			   "\nLoan Details:" +
			   "\n-Reference Number: " + referenceNumber +
			   "\n-Loan Amount: R" + loanAmount +
			   "\n-Rate: " + rate + "%" +
			   "\n-Starting Year: " + startingYear;
	}


} //end abstract class Loan


