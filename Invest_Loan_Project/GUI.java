// Mostert L.E.
// 20805330
// Projek

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.util.ArrayList;


public class GUI extends JFrame
{
	private JButton viewInvestButton;
	private JButton viewLoanButton;
	private JButton createInvestButton;
	private JButton createLoanButton;

	private JLabel labelInvest;
	private JLabel labelInvestFixed;
	private JLabel labelInvestAnuity;
	private JLabel labelLoan;
	private JLabel labelLoanFixed;
	private JLabel labelLoanPayment;

	private JPanel buttonPanel;
	private JPanel investPanel;
	private JPanel loanPanel;

    private ArrayList investments;
	private ArrayList loans;

	private int investCount;
	private int loanCount;
	private int fixedInvestCount;
	private int anuityInvestCount;
	private int fixedLoanCount;
	private int paymentLoanCount;

	public GUI()
	{
		super("Investment & Loan Manager");
		setLayout(new GridLayout(5, 1, 10, 10));

		investCount = 0;
		loanCount = 0;
		fixedInvestCount = 0;
		anuityInvestCount = 0;
		fixedLoanCount = 0;
		paymentLoanCount = 0;


		viewInvestButton = new JButton("View and Manage Investments");

		viewLoanButton = new JButton("View and Manage Loans");

		createInvestButton = new JButton("Create a new Investment");

		createLoanButton = new JButton("Create a new Loan");

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 2, 8, 8));
		buttonPanel.add(viewInvestButton);
		buttonPanel.add(viewLoanButton);
		buttonPanel.add(createInvestButton);
		buttonPanel.add(createLoanButton);
		add(buttonPanel);

		labelInvest = new JLabel(String.format("Number of Investments: %d", investCount));
		add(labelInvest);

		labelInvestFixed = new JLabel(String.format("Number of Fixed Amount Investments: %d", fixedInvestCount));
		labelInvestAnuity = new JLabel(String.format("Number of Yearly Anuity Investments: %d", anuityInvestCount));

		investPanel = new JPanel();
		investPanel.setLayout(new GridLayout(1, 2, 3, 3));
		investPanel.add(labelInvestFixed);
		investPanel.add(labelInvestAnuity);
		add(investPanel);

		labelLoan = new JLabel(String.format("Number of Loans: %d", loanCount));
		add(labelLoan);

		labelLoanFixed = new JLabel(String.format("Number of Fixed Amount Loans: %d", fixedLoanCount));
		labelLoanPayment = new JLabel(String.format("Number of Yeary Payment Loans: %d", paymentLoanCount));

		loanPanel = new JPanel();
		loanPanel.setLayout(new GridLayout(1, 2, 3, 3));
		loanPanel.add(labelLoanFixed);
		loanPanel.add(labelLoanPayment);
		add(loanPanel);

		JMenu fileMenu = new JMenu("File");

		JMenuItem openItem = new JMenuItem("Open file");
		fileMenu.add(openItem);

		JMenuItem saveItem = new JMenuItem("Save file");
		fileMenu.add(saveItem);

		JMenuItem exitItem = new JMenuItem("Exit Program");
		fileMenu.add(exitItem);

		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu);


		createInvestButton.addActionListener(new CreateInvestHandler());
		createLoanButton.addActionListener(new CreateLoanHandler());
		viewInvestButton.addActionListener(new ViewInvestHandler());
		viewLoanButton.addActionListener(new ViewLoanHandler());
		exitItem.addActionListener(new ExitHandler());


		investments = new ArrayList();
		loans = new ArrayList();


	} //end constructor


	public void createFixedInvestment(String firstName, String lastName, String idNumber, String refNumber, double amount,
									  double rate, int startYear, int years)
	{
		investments.add(new FixedAmount(firstName, lastName, idNumber, refNumber, amount, rate, startYear, years));
		investCount++;
		fixedInvestCount++;

		labelInvest.setText(String.format("Number of Investments: %d", investCount));
		labelInvestFixed.setText(String.format("Number of Fixed Amount Investments: %d", fixedInvestCount));
	}

	
	public void createAnuityInvestment(String firstName, String lastName, String idNumber, String refNumber, double amount,
									  double rate, int startYear, int years)
	{
		investments.add(new YearlyAnuity(firstName, lastName, idNumber, refNumber, amount, rate, startYear, years));
		investCount++;
		anuityInvestCount++;

		labelInvest.setText(String.format("Number of Investments: %d", investCount));
		labelInvestAnuity.setText(String.format("Number of Yearly Anuity Investments: %d", anuityInvestCount));
	}


	public void createFixedLoan(String firstName, String lastName, String idNumber, String refNumber, double amount,
									  double rate, int startYear, int years)
	{
		loans.add(new FixedRate(firstName, lastName, idNumber, refNumber, amount, rate, startYear, years));
		loanCount++;
		fixedLoanCount++;

		labelLoan.setText(String.format("Number of Loans: %d", loanCount));
		labelLoanFixed.setText(String.format("Number of Fixed Amount Loans: %d", fixedLoanCount));
	}


	public void createPaymentLoan(String firstName, String lastName, String idNumber, String refNumber, double amount,
									  double rate, int startYear, double payment)
	{
		loans.add(new YearlyPayment(firstName, lastName, idNumber, refNumber, amount, rate, startYear, payment));
		loanCount++;
		paymentLoanCount++;

		labelLoan.setText(String.format("Number of Loans: %d", loanCount));
		labelLoanPayment.setText(String.format("Number of Yeary Payment Loans: %d", paymentLoanCount));
	}

	public void deleteFixedInvestment(int index)
	{
		investments.remove(index);
		investCount--;
		fixedInvestCount--;

		labelInvest.setText(String.format("Number of Investments: %d", investCount));
		labelInvestFixed.setText(String.format("Number of Fixed Amount Investments: %d", fixedInvestCount));
	}

	public void deleteAnuityInvestment(int index)
	{
		investments.remove(index);
		investCount--;
		anuityInvestCount--;

		labelInvest.setText(String.format("Number of Investments: %d", investCount));
		labelInvestAnuity.setText(String.format("Number of Yearly Anuity Investments: %d", anuityInvestCount));
	}

	public void deleteFixedLoan(int index)
	{
		loans.remove(index);
		loanCount--;
		fixedLoanCount--;

		labelLoan.setText(String.format("Number of Loans: %d", loanCount));
		labelLoanFixed.setText(String.format("Number of Fixed Amount Loans: %d", fixedLoanCount));
	}

	public void deletePaymentLoan(int index)
	{
		loans.remove(index);
		loanCount--;
		paymentLoanCount--;

		labelLoan.setText(String.format("Number of Loans: %d", loanCount));
		labelLoanPayment.setText(String.format("Number of Yeary Payment Loans: %d", paymentLoanCount));
	}

	public void reDrawInvest()
	{
		GUIViewInvest viewInvest = new GUIViewInvest();
		viewInvest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewInvest.setSize(500, 175);
		viewInvest.setVisible(true);
	}

	public void reDrawLoan()
	{
		GUIViewLoan viewLoan = new GUIViewLoan();
		viewLoan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewLoan.setSize(500, 175);
		viewLoan.setVisible(true);
	}


	private class CreateInvestHandler implements ActionListener  //event handling for button create investment
	{
		public void actionPerformed(ActionEvent event)
		{
			GUICreateInvest createInvest = new GUICreateInvest();
			createInvest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			createInvest.setSize(500, 500);
			createInvest.setVisible(true);
		}
	}


	private class CreateLoanHandler implements ActionListener  //event handling for button create loan
	{
		public void actionPerformed(ActionEvent event)
		{
			GUICreateLoan createLoan = new GUICreateLoan();
			createLoan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			createLoan.setSize(500, 500);
			createLoan.setVisible(true);
		}
	}


	private class ViewInvestHandler implements ActionListener  //event handling for button manage investment
	{
		public void actionPerformed(ActionEvent event)
		{
			GUIViewInvest viewInvest = new GUIViewInvest();
			viewInvest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			viewInvest.setSize(500, 175);
			viewInvest.setVisible(true);
		}
	}


	private class ViewLoanHandler implements ActionListener  //event handling for button manage loan
	{
		public void actionPerformed(ActionEvent event)
		{
			GUIViewLoan viewLoan = new GUIViewLoan();
			viewLoan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			viewLoan.setSize(500, 175);
			viewLoan.setVisible(true);
		}
	}


	private class ExitHandler implements ActionListener  //event handling for menu item exit
	{
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}


	private class GUIViewInvest extends JFrame
	{
		private JList listInvest;

		private JButton buttonView;
		private JButton buttonCalculate;
		private JButton buttonEdit;
		private JButton buttonDelete;
		private JButton buttonBack;

		private JPanel buttonPanel;

		private Investment investmentNames[];
		private String referenceNumbers[];

		public GUIViewInvest()
		{
			super("Investment Manager");
			setLayout(new GridLayout(1, 2, 3, 3));

			investmentNames = new Investment[investments.size()];
			referenceNumbers = new String[investments.size()];

			for (int i = 0; i < investments.size(); i++)
			{
				investmentNames[i] = (Investment)investments.get(i);
				referenceNumbers[i] = investmentNames[i].getRefNumber();
			}

			listInvest = new JList(referenceNumbers);
			listInvest.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			add(new JScrollPane(listInvest));

			buttonView = new JButton("Details of selected Investment");
			buttonCalculate = new JButton("Calculate Projected Value");
			buttonEdit = new JButton("Edit selected Investment");
			buttonDelete = new JButton("Delete selected Investment");
			buttonBack = new JButton("Back");

			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(5, 1, 3, 3));
			buttonPanel.add(buttonView);
			buttonPanel.add(buttonCalculate);
			buttonPanel.add(buttonEdit);
			buttonPanel.add(buttonDelete);
			buttonPanel.add(buttonBack);
			add(buttonPanel);


			buttonBack.addActionListener(new BackHandler());
			buttonView.addActionListener(new ViewHandler());
			buttonEdit.addActionListener(new EditHandler());
			buttonCalculate.addActionListener(new CalculateHandler());
			buttonDelete.addActionListener(new DeleteHandler());
		}

		public int getIndex()
		{
			return listInvest.getSelectedIndex();
		}


		private class BackHandler implements ActionListener  //event handling for button back
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
				repaint();
			}
		}


		private class ViewHandler implements ActionListener  //event handling for button view
		{
			public void actionPerformed(ActionEvent event)
			{
				if (investmentNames[listInvest.getSelectedIndex()] instanceof FixedAmount)
				{
					FixedAmount investor = (FixedAmount)investmentNames[listInvest.getSelectedIndex()];

					JOptionPane.showMessageDialog(null, String.format("%sTotal value of investment in %d: R%.2f\nTotal interest earned by %d: R%.2f", investor, investor.getEndYear(), investor.totalAmount(), investor.getEndYear(), investor.totalInterest()), "Details of Investment", JOptionPane.PLAIN_MESSAGE);
				}

				if (investmentNames[listInvest.getSelectedIndex()] instanceof YearlyAnuity)
				{
					YearlyAnuity investor = (YearlyAnuity)investmentNames[listInvest.getSelectedIndex()];

					JOptionPane.showMessageDialog(null, String.format("%sTotal value of investment in %d: R%.2f\nTotal interest earned by %d: R%.2f", investor, investor.getEndYear(), investor.totalAmount(), investor.getEndYear(), investor.totalInterest()), "Details of Investment", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}


		private class EditHandler implements ActionListener  //event handling for button edit
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);

				GUIEditInvest editInvest = new GUIEditInvest(listInvest.getSelectedIndex(), investmentNames[listInvest.getSelectedIndex()]);
				editInvest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				editInvest.setSize(500, 500);
				editInvest.setVisible(true);
			}
		}


		private class CalculateHandler implements ActionListener  //event handling for button calculate
		{
			private int inputYear;

			public void actionPerformed(ActionEvent event)
			{

				if (investmentNames[listInvest.getSelectedIndex()] instanceof FixedAmount)
				{
					FixedAmount investorCalculate = (FixedAmount)investmentNames[listInvest.getSelectedIndex()];

					String input = JOptionPane.showInputDialog(String.format("Enter a year between %d and %d", investorCalculate.getStartYear(), investorCalculate.getEndYear()) );
					inputYear = Integer.parseInt(input);

					JOptionPane.showMessageDialog(null, String.format("The projected value of the investment in the year %d is: R%.2f", inputYear, investorCalculate.runningAmount(inputYear)), "Projected Investment Details", JOptionPane.PLAIN_MESSAGE);
				}

				if (investmentNames[listInvest.getSelectedIndex()] instanceof YearlyAnuity)
				{
					YearlyAnuity investorCalculate = (YearlyAnuity)investmentNames[listInvest.getSelectedIndex()];

					String input = JOptionPane.showInputDialog(String.format("Enter a year between %d and %d", investorCalculate.getStartYear(), investorCalculate.getEndYear()));
					inputYear = Integer.parseInt(input);

					JOptionPane.showMessageDialog(null, String.format("The projected value of the investment in the year %d is: R%.2f", inputYear, investorCalculate.runningAmount(inputYear)), "Projected Investment Details", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}


		private class DeleteHandler implements ActionListener  //event handling for button delete
		{
			public void actionPerformed(ActionEvent event)
			{
				if (investmentNames[listInvest.getSelectedIndex()] instanceof FixedAmount)
				{
					deleteFixedInvestment(listInvest.getSelectedIndex());
				}

				if (investmentNames[listInvest.getSelectedIndex()] instanceof YearlyAnuity)
				{
					deleteAnuityInvestment(listInvest.getSelectedIndex());
				}

				labelInvest.setText(String.format("Number of Investments: %d", investCount));
				labelInvestFixed.setText(String.format("Number of Fixed Amount Investments: %d", fixedInvestCount));
				labelInvestAnuity.setText(String.format("Number of Yearly Anuity Investments: %d", anuityInvestCount));

				setVisible(false);

				GUIViewInvest viewInvest = new GUIViewInvest();
				viewInvest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				viewInvest.setSize(500, 175);
				viewInvest.setVisible(true);
			}
		}
		

	} //end class GUIViewInvest


	private class GUIViewLoan extends JFrame
	{
		private JList listLoan;

		private JButton buttonView;
		private JButton buttonCalculate;
		private JButton buttonEdit;
		private JButton buttonDelete;
		private JButton buttonBack;

		private JPanel buttonPanel;

		private Loan loanNames[];
		private String referenceNumbers[];

		public GUIViewLoan()
		{
			super("Loan Manager");
			setLayout(new GridLayout(1, 2, 3, 3));

			loanNames = new Loan[loans.size()];
			referenceNumbers = new String[loans.size()];

			for (int i = 0; i < loans.size(); i++)
			{
				loanNames[i] = (Loan)loans.get(i);
				referenceNumbers[i] = loanNames[i].getRefNumber();
			}

			listLoan = new JList(referenceNumbers);
			listLoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			add(new JScrollPane(listLoan));

			buttonView = new JButton("Details of selected Loan");
			buttonCalculate = new JButton("Calculate Projected Value");
			buttonEdit = new JButton("Edit selected Loan");
			buttonDelete = new JButton("Delete selected Loan");
			buttonBack = new JButton("Back");

			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(5, 1, 3, 3));
			buttonPanel.add(buttonView);
			buttonPanel.add(buttonCalculate);
			buttonPanel.add(buttonEdit);
			buttonPanel.add(buttonDelete);
			buttonPanel.add(buttonBack);
			add(buttonPanel);


			buttonBack.addActionListener(new BackHandler());
			buttonView.addActionListener(new ViewHandler());
			buttonEdit.addActionListener(new EditHandler());
			buttonCalculate.addActionListener(new CalculateHandler());
			buttonDelete.addActionListener(new DeleteHandler());
		}


		private class BackHandler implements ActionListener  //event handling for button back
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
				repaint();
			}
		}


		private class ViewHandler implements ActionListener  //event handling for button view
		{
			public void actionPerformed(ActionEvent event)
			{
				if (loanNames[listLoan.getSelectedIndex()] instanceof FixedRate)
				{
					FixedRate loaner = (FixedRate)loanNames[listLoan.getSelectedIndex()];

					JOptionPane.showMessageDialog(null, String.format("%sTotal amount to be repaid at the end of %d: R%.2f\nTotal interest by %d: R%.2f", loaner, loaner.getEndYear(), loaner.totalAmount(), loaner.getEndYear(), loaner.totalInterest()), "Details of Loan", JOptionPane.PLAIN_MESSAGE);
				}

				if (loanNames[listLoan.getSelectedIndex()] instanceof YearlyPayment)
				{
					YearlyPayment loaner = (YearlyPayment)loanNames[listLoan.getSelectedIndex()];

					JOptionPane.showMessageDialog(null, String.format("%sTotal amount to be repaid to clear the debt: R%.2f\nAmount of years until dept is repaid: %d\nTotal interest by %d: R%.2f", loaner, loaner.totalAmount(), loaner.getYearCount(), loaner.getYearCount() + loaner.getStartYear(), loaner.totalInterest()), "Details of Loan", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		private class EditHandler implements ActionListener  //event handling for button edit
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);

				GUIEditLoan editLoan = new GUIEditLoan(listLoan.getSelectedIndex(), loanNames[listLoan.getSelectedIndex()]);
				editLoan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				editLoan.setSize(500, 500);
				editLoan.setVisible(true);
			}
		}


		private class CalculateHandler implements ActionListener  //event handling for button calculate
		{
			private int inputYear;

			public void actionPerformed(ActionEvent event)
			{

				if (loanNames[listLoan.getSelectedIndex()] instanceof FixedRate)
				{
					FixedRate loanerCalculate = (FixedRate)loanNames[listLoan.getSelectedIndex()];

					String input = JOptionPane.showInputDialog(String.format("Enter a year between %d and %d", loanerCalculate.getStartYear(), loanerCalculate.getEndYear()));
					inputYear = Integer.parseInt(input);

					JOptionPane.showMessageDialog(null, String.format("The projected debt of the loan in the year %d is: R%.2f", inputYear, loanerCalculate.runningAmount(inputYear)), "Projected Loan Details", JOptionPane.PLAIN_MESSAGE);
				}

				if (loanNames[listLoan.getSelectedIndex()] instanceof YearlyPayment)
				{
					YearlyPayment loanerCalculate = (YearlyPayment)loanNames[listLoan.getSelectedIndex()];

					String input = JOptionPane.showInputDialog(String.format("Enter a year between %d and %d", loanerCalculate.getStartYear(), loanerCalculate.getYearCount() + loanerCalculate.getStartYear()));
					inputYear = Integer.parseInt(input);

					JOptionPane.showMessageDialog(null, String.format("The projected value of the investment in the year %d is: R%.2f", inputYear, loanerCalculate.runningAmount(inputYear)), "Projected Loan Details", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}


		private class DeleteHandler implements ActionListener  //event handling for button delete
		{
			public void actionPerformed(ActionEvent event)
			{
				if (loanNames[listLoan.getSelectedIndex()] instanceof FixedRate)
				{
					deleteFixedLoan(listLoan.getSelectedIndex());
				}

				if (loanNames[listLoan.getSelectedIndex()] instanceof YearlyPayment)
				{
					deletePaymentLoan(listLoan.getSelectedIndex());
				}

				labelLoan.setText(String.format("Number of Loans: %d", loanCount));
				labelLoanFixed.setText(String.format("Number of Fixed Amount Loans: %d", fixedLoanCount));
				labelLoanPayment.setText(String.format("Number of Yearly Payment Loans: %d", paymentLoanCount));

				setVisible(false);

				GUIViewLoan viewLoan = new GUIViewLoan();
				viewLoan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				viewLoan.setSize(500, 175);
				viewLoan.setVisible(true);
			}
		}

	} //end class GUIViewLoan


	private class GUICreateInvest extends JFrame
	{

		private JLabel labelType;
		private JLabel labelFName;
		private JLabel labelLName;
		private JLabel labelIDNumber;
		private JLabel labelRefNumber;
		private JLabel labelAmount;
		private JLabel labelRate;
		private JLabel labelStartYear;
		private JLabel labelYears;

		private JPanel radPanel;

		private JRadioButton radFixed;
		private JRadioButton radAnuity;

		private ButtonGroup radGroup;

		private JTextField textFName;
		private JTextField textLName;
		private JTextField textIDNumber;
		private JTextField textRefNumber;
		private JTextField textAmount;

		private JComboBox comboRate;
		private String rates[] = {"2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10","10.5",
	                          "11","11.5","12","12.5"};

		private JComboBox comboStartYear;
		private String startYears[] = { "2009", "2010", "2011", "2012", "2013", "2014", "2015" };

		private JComboBox comboYears;
		private String years[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", 
		                       "17", "18", "19", "20" };

		private JButton buttonCreate;
		private JButton buttonCancel;


		public GUICreateInvest()
		{
			super("Create a new Investment");
			setLayout(new GridLayout(10, 2, 3, 10));

			labelType = new JLabel("Choose the type of investment:");
			add(labelType);

			radFixed = new JRadioButton("Fixed Amount", true);
			radAnuity = new JRadioButton("Yearly Anuity", false);

			radPanel = new JPanel();
			radPanel.setLayout(new GridLayout(1, 2));
			radPanel.add(radFixed);
			radPanel.add(radAnuity);
			add(radPanel);

			radGroup = new ButtonGroup();
			radGroup.add(radFixed);
			radGroup.add(radAnuity);

			labelFName = new JLabel("Enter first name:");
			add(labelFName);

			textFName = new JTextField();
			add(textFName);

			labelLName = new JLabel("Enter last name:");
			add(labelLName);

			textLName = new JTextField();
			add(textLName);

			labelIDNumber = new JLabel("Enter ID number:");
			add(labelIDNumber);

			textIDNumber = new JTextField();
			add(textIDNumber);

			labelRefNumber = new JLabel("Enter reference number:");
			add(labelRefNumber);

			textRefNumber = new JTextField();
			add(textRefNumber);

			labelAmount = new JLabel("Enter the amount:");
			add(labelAmount);

			textAmount = new JTextField();
			add(textAmount);

			labelRate = new JLabel("Select the interest rate:");
			add(labelRate);

			comboRate = new JComboBox(rates);
			add(comboRate);

			labelStartYear = new JLabel("Select the starting year:");
			add(labelStartYear);

			comboStartYear = new JComboBox(startYears);
			add(comboStartYear);

			labelYears = new JLabel("Select the amount of years:");
			add(labelYears);

			comboYears = new JComboBox(years);
			add(comboYears);

			buttonCreate = new JButton("Create");
			add(buttonCreate);

			buttonCancel = new JButton("Cancel");
			add(buttonCancel);


			buttonCancel.addActionListener(new CancelHandler());
			buttonCreate.addActionListener(new CreateHandler());

		} //end constructor


		private class CancelHandler implements ActionListener  //event handling for button cancel
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
			}
		}


		private class CreateHandler implements ActionListener  //event handling for button create
		{
			private String fName = "";
			private String lName = "";
			private String id = "";
			private String refNumber = "";
			private Double amount = 0.0;
			private Double rate = 0.0;
			private int startYear = 0;
			private int newYears = 0;


			public void actionPerformed(ActionEvent event)
			{
				if (radFixed.isSelected() == true)
				{
					try
					{
						this.fName = textFName.getText();
					    this.lName = textLName.getText();
					    this.id = textIDNumber.getText();
					    this.refNumber = textRefNumber.getText();
					    this.amount = Double.parseDouble(textAmount.getText());

					    this.rate = Double.parseDouble(rates[comboRate.getSelectedIndex()]);

					    this.startYear = Integer.parseInt(startYears[comboStartYear.getSelectedIndex()]);

					    this.newYears = Integer.parseInt(years[comboYears.getSelectedIndex()]);

						createFixedInvestment(fName, lName, id, refNumber, amount, rate, startYear, newYears);
						setVisible(false);
					}
					catch(NumberFormatException inputMismatch)
					{
						JOptionPane.showMessageDialog(null, "You must enter a numerical value for the amount.", "Exception: " + inputMismatch, JOptionPane.WARNING_MESSAGE);
					}

				}

				if (radAnuity.isSelected() == true)
				{
					try
					{
						this.fName = textFName.getText();
					    this.lName = textLName.getText();
					    this.id = textIDNumber.getText();
					    this.refNumber = textRefNumber.getText();
					    this.amount = Double.parseDouble(textAmount.getText());

					    this.rate = Double.parseDouble(rates[comboRate.getSelectedIndex()]);

					    this.startYear = Integer.parseInt(startYears[comboStartYear.getSelectedIndex()]);

					    this.newYears = Integer.parseInt(years[comboYears.getSelectedIndex()]);

					    createAnuityInvestment(fName, lName, id, refNumber, amount, rate, startYear, newYears);
					    setVisible(false);
					}
					catch (NumberFormatException inputMismatch)
					{
						JOptionPane.showMessageDialog(null, "You must enter a numerical value for the amount.", "Exception: " + inputMismatch, JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		}

	} //end class GUICreateInvest


	private class GUICreateLoan extends JFrame
	{

		private JLabel labelType;
		private JLabel labelFName;
		private JLabel labelLName;
		private JLabel labelIDNumber;
		private JLabel labelRefNumber;
		private JLabel labelAmount;
		private JLabel labelRate;
		private JLabel labelStartYear;
		private JLabel labelYearsORPayment;

		private JPanel radPanel;

		private JRadioButton radFixed;
		private JRadioButton radPayment;

		private ButtonGroup radGroup;

		private JTextField textFName;
		private JTextField textLName;
		private JTextField textIDNumber;
		private JTextField textRefNumber;
		private JTextField textAmount;
		private JTextField textPayment;

		private JComboBox comboRate;
		private String rates[] = {"2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10","10.5",
	                          "11","11.5","12","12.5"};

		private JComboBox comboStartYear;
		private String startYears[] = { "2009", "2010", "2011", "2012", "2013", "2014", "2015" };

		private JComboBox comboYears;
		private String years[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", 
		                       "17", "18", "19", "20" };

		private JButton buttonCreate;
		private JButton buttonCancel;


		public GUICreateLoan()
		{
			super("Create a new Loan");
			setLayout(new GridLayout(10, 2, 3, 10));

			labelType = new JLabel("Choose the type of loan:");
			add(labelType);

			radFixed = new JRadioButton("Fixed Amount", true);
			radPayment = new JRadioButton("Yearly Payment", false);

			radPanel = new JPanel();
			radPanel.setLayout(new GridLayout(1, 2));
			radPanel.add(radFixed);
			radPanel.add(radPayment);
			add(radPanel);

			radGroup = new ButtonGroup();
			radGroup.add(radFixed);
			radGroup.add(radPayment);

			labelFName = new JLabel("Enter first name:");
			add(labelFName);

			textFName = new JTextField();
			add(textFName);

			labelLName = new JLabel("Enter last name:");
			add(labelLName);

			textLName = new JTextField();
			add(textLName);

			labelIDNumber = new JLabel("Enter ID number:");
			add(labelIDNumber);

			textIDNumber = new JTextField();
			add(textIDNumber);

			labelRefNumber = new JLabel("Enter reference number:");
			add(labelRefNumber);

			textRefNumber = new JTextField();
			add(textRefNumber);

			labelAmount = new JLabel("Enter the amount:");
			add(labelAmount);

			textAmount = new JTextField();
			add(textAmount);

			labelRate = new JLabel("Select the interest rate:");
			add(labelRate);

			comboRate = new JComboBox(rates);
			add(comboRate);

			labelStartYear = new JLabel("Select the starting year:");
			add(labelStartYear);

			comboStartYear = new JComboBox(startYears);
			add(comboStartYear);

			labelYearsORPayment = new JLabel("Select the amount of years:");
			add(labelYearsORPayment);

			comboYears = new JComboBox(years);
			add(comboYears);

			buttonCreate = new JButton("Create");
			add(buttonCreate);

			buttonCancel = new JButton("Cancel");
			add(buttonCancel);

			textPayment = new JTextField();


			buttonCancel.addActionListener(new CancelHandler());
			buttonCreate.addActionListener(new CreateHandler());
			radPayment.addItemListener(new RadHandler());
			radFixed.addItemListener(new RadHandler());

		} //end constructor


		private class CancelHandler implements ActionListener //event handling for button cancel
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
			}
		}


		private class RadHandler implements ItemListener  //event handling for radio buttons
		{
			public void itemStateChanged(ItemEvent event)
			{
				if (event.getSource() == radPayment)
				{
					if (labelYearsORPayment.getText() == "Select the amount of years:")
					{
						labelYearsORPayment.setText("Enter the yearly payment:");

						remove(comboYears);
						remove(buttonCreate);
						remove(buttonCancel);

						add(textPayment);
						add(buttonCreate);
						add(buttonCancel);

						repaint();
					}
				}

				if (event.getSource() == radFixed)
				{
					if (labelYearsORPayment.getText() == "Enter the yearly payment:")
					{
						labelYearsORPayment.setText("Select the amount of years:");

						remove(textPayment);
						remove(buttonCreate);
						remove(buttonCancel);

						add(comboYears);
						add(buttonCreate);
						add(buttonCancel);

						repaint();
					}
				}
			}
		}


		private class CreateHandler implements ActionListener  //event handling for button create
		{
			private String fName = "";
			private String lName = "";
			private String id = "";
			private String refNumber = "";
			private Double amount = 0.0;
			private Double rate = 0.0;
			private int startYear = 0;
			private int newYears = 0;
			private double payment = 0;


			public void actionPerformed(ActionEvent event)
			{
				if (radFixed.isSelected() == true)
				{
					try
					{
						this.fName = textFName.getText();
					    this.lName = textLName.getText();
					    this.id = textIDNumber.getText();
					    this.refNumber = textRefNumber.getText();
					    this.amount = Double.parseDouble(textAmount.getText());

					    this.rate = Double.parseDouble(rates[comboRate.getSelectedIndex()]);

					    this.startYear = Integer.parseInt(startYears[comboStartYear.getSelectedIndex()]);

					    this.newYears = Integer.parseInt(years[comboYears.getSelectedIndex()]);

					    createFixedLoan(fName, lName, id, refNumber, amount, rate, startYear, newYears);
					    setVisible(false);
					}
					catch (NumberFormatException inputMismatch)
					{
						JOptionPane.showMessageDialog(null, "You must enter a numerical value for the amount.", "Exception: " + inputMismatch, JOptionPane.WARNING_MESSAGE);
					}
				}

				if (radPayment.isSelected() == true)
				{
					try
					{
						this.fName = textFName.getText();
					    this.lName = textLName.getText();
					    this.id = textIDNumber.getText();
					    this.refNumber = textRefNumber.getText();
					    this.amount = Double.parseDouble(textAmount.getText());

					    this.rate = Double.parseDouble(rates[comboRate.getSelectedIndex()]);

					    this.startYear = Integer.parseInt(startYears[comboStartYear.getSelectedIndex()]);

					    this.payment = Double.parseDouble(textPayment.getText());

					    createPaymentLoan(fName, lName, id, refNumber, amount, rate, startYear, payment);
					    setVisible(false);
					}
					catch (NumberFormatException inputMismatch)
					{
						JOptionPane.showMessageDialog(null, "You must enter a numerical value for the amount and payment.", "Exception: " + inputMismatch, JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		}

	} //end class GUICreateInvest


	private class GUIEditInvest extends JFrame
	{

		private JLabel labelType;
		private JLabel labelFName;
		private JLabel labelLName;
		private JLabel labelIDNumber;
		private JLabel labelRefNumber;
		private JLabel labelAmount;
		private JLabel labelRate;
		private JLabel labelStartYear;
		private JLabel labelYears;

		private JPanel radPanel;

		private JRadioButton radFixed;
		private JRadioButton radAnuity;

		private ButtonGroup radGroup;

		private JTextField textFName;
		private JTextField textLName;
		private JTextField textIDNumber;
		private JTextField textRefNumber;
		private JTextField textAmount;

		private JComboBox comboRate;
		private String rates[] = {"2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10","10.5",
	                          "11","11.5","12","12.5"};

		private JComboBox comboStartYear;
		private String startYears[] = { "2009", "2010", "2011", "2012", "2013", "2014", "2015" };

		private JComboBox comboYears;
		private String years[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", 
		                       "17", "18", "19", "20" };

		private JButton buttonEdit;
		private JButton buttonCancel;

		private int index;
		private Investment object;

		public GUIEditInvest(int index, Investment object)
		{
			super("Edit an Investment");
			setLayout(new GridLayout(10, 2, 3, 10));

			labelType = new JLabel("Choose the type of investment:");
			add(labelType);

			radFixed = new JRadioButton("Fixed Amount", true);
			radAnuity = new JRadioButton("Yearly Anuity", false);

			radPanel = new JPanel();
			radPanel.setLayout(new GridLayout(1, 2));
			radPanel.add(radFixed);
			radPanel.add(radAnuity);
			add(radPanel);

			radGroup = new ButtonGroup();
			radGroup.add(radFixed);
			radGroup.add(radAnuity);

			labelFName = new JLabel("Enter first name:");
			add(labelFName);

			textFName = new JTextField();
			add(textFName);

			labelLName = new JLabel("Enter last name:");
			add(labelLName);

			textLName = new JTextField();
			add(textLName);

			labelIDNumber = new JLabel("Enter ID number:");
			add(labelIDNumber);

			textIDNumber = new JTextField();
			add(textIDNumber);

			labelRefNumber = new JLabel("Enter reference number:");
			add(labelRefNumber);

			textRefNumber = new JTextField();
			add(textRefNumber);

			labelAmount = new JLabel("Enter the amount:");
			add(labelAmount);

			textAmount = new JTextField();
			add(textAmount);

			labelRate = new JLabel("Select the interest rate:");
			add(labelRate);

			comboRate = new JComboBox(rates);
			add(comboRate);

			labelStartYear = new JLabel("Select the starting year:");
			add(labelStartYear);

			comboStartYear = new JComboBox(startYears);
			add(comboStartYear);

			labelYears = new JLabel("Select the amount of years:");
			add(labelYears);

			comboYears = new JComboBox(years);
			add(comboYears);

			buttonEdit = new JButton("Edit");
			add(buttonEdit);

			buttonCancel = new JButton("Cancel");
			add(buttonCancel);

			this.index = index;
			this.object = object;


			buttonCancel.addActionListener(new CancelHandler());
			buttonEdit.addActionListener(new EditHandler());

		} //end constructor


		private class CancelHandler implements ActionListener  //event handling for button cancel
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
				reDrawInvest();
			}
		}


		private class EditHandler implements ActionListener  //event handling for button edit
		{
			private String fName = "";
			private String lName = "";
			private String id = "";
			private String refNumber = "";
			private Double amount = 0.0;
			private Double rate = 0.0;
			private int startYear = 0;
			private int newYears = 0;


			public void actionPerformed(ActionEvent event)
			{
				if (radFixed.isSelected() == true)
				{
					try
					{
						this.fName = textFName.getText();
					    this.lName = textLName.getText();
					    this.id = textIDNumber.getText();
					    this.refNumber = textRefNumber.getText();
					    this.amount = Double.parseDouble(textAmount.getText());

					    this.rate = Double.parseDouble(rates[comboRate.getSelectedIndex()]);

					    this.startYear = Integer.parseInt(startYears[comboStartYear.getSelectedIndex()]);

					    this.newYears = Integer.parseInt(years[comboYears.getSelectedIndex()]);

					    if (object instanceof FixedAmount)
					    {
						    deleteFixedInvestment(index);
					    }

					    if (object instanceof YearlyAnuity)
					    {
						    deleteAnuityInvestment(index);
					    }
				
					    createFixedInvestment(fName, lName, id, refNumber, amount, rate, startYear, newYears);
					    setVisible(false);
					    reDrawInvest();
					}
					catch (NumberFormatException inputMismatch)
					{
						JOptionPane.showMessageDialog(null, "You must enter a numerical value for the amount.", "Exception: " + inputMismatch, JOptionPane.WARNING_MESSAGE);
					}
				}

				if (radAnuity.isSelected() == true)
				{
					try
					{
						this.fName = textFName.getText();
					    this.lName = textLName.getText();
					    this.id = textIDNumber.getText();
					    this.refNumber = textRefNumber.getText();
					    this.amount = Double.parseDouble(textAmount.getText());

				    	this.rate = Double.parseDouble(rates[comboRate.getSelectedIndex()]);

				    	this.startYear = Integer.parseInt(startYears[comboStartYear.getSelectedIndex()]);

				    	this.newYears = Integer.parseInt(years[comboYears.getSelectedIndex()]);

			    		if (object instanceof FixedAmount)
			    		{
			    			deleteFixedInvestment(index);
			    		}

			    		if (object instanceof YearlyAnuity)
			    		{
			    			deleteAnuityInvestment(index);
			    		}

				    	createAnuityInvestment(fName, lName, id, refNumber, amount, rate, startYear, newYears);
				    	setVisible(false);
				    	reDrawInvest();
					}
					catch (NumberFormatException inputMismatch)
					{
						JOptionPane.showMessageDialog(null, "You must enter a numerical value for the amount.", "Exception: " + inputMismatch, JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		}

	} //end class GUIEditInvest


	private class GUIEditLoan extends JFrame
	{
		private JLabel labelType;
		private JLabel labelFName;
		private JLabel labelLName;
		private JLabel labelIDNumber;
		private JLabel labelRefNumber;
		private JLabel labelAmount;
		private JLabel labelRate;
		private JLabel labelStartYear;
		private JLabel labelYearsORPayment;

		private JPanel radPanel;

		private JRadioButton radFixed;
		private JRadioButton radPayment;

		private ButtonGroup radGroup;

		private JTextField textFName;
		private JTextField textLName;
		private JTextField textIDNumber;
		private JTextField textRefNumber;
		private JTextField textAmount;
		private JTextField textPayment;

		private JComboBox comboRate;
		private String rates[] = {"2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10","10.5",
	                          "11","11.5","12","12.5"};

		private JComboBox comboStartYear;
		private String startYears[] = { "2009", "2010", "2011", "2012", "2013", "2014", "2015" };

		private JComboBox comboYears;
		private String years[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", 
		                       "17", "18", "19", "20" };

		private JButton buttonEdit;
		private JButton buttonCancel;

		private int index;
		private Loan object;


		public GUIEditLoan(int index, Loan object)
		{
			super("Edit a new Loan");
			setLayout(new GridLayout(10, 2, 3, 10));

			labelType = new JLabel("Choose the type of loan:");
			add(labelType);

			radFixed = new JRadioButton("Fixed Amount", true);
			radPayment = new JRadioButton("Yearly Payment", false);

			radPanel = new JPanel();
			radPanel.setLayout(new GridLayout(1, 2));
			radPanel.add(radFixed);
			radPanel.add(radPayment);
			add(radPanel);

			radGroup = new ButtonGroup();
			radGroup.add(radFixed);
			radGroup.add(radPayment);

			labelFName = new JLabel("Enter first name:");
			add(labelFName);

			textFName = new JTextField();
			add(textFName);

			labelLName = new JLabel("Enter last name:");
			add(labelLName);

			textLName = new JTextField();
			add(textLName);

			labelIDNumber = new JLabel("Enter ID number:");
			add(labelIDNumber);

			textIDNumber = new JTextField();
			add(textIDNumber);

			labelRefNumber = new JLabel("Enter reference number:");
			add(labelRefNumber);

			textRefNumber = new JTextField();
			add(textRefNumber);

			labelAmount = new JLabel("Enter the amount:");
			add(labelAmount);

			textAmount = new JTextField();
			add(textAmount);

			labelRate = new JLabel("Select the interest rate:");
			add(labelRate);

			comboRate = new JComboBox(rates);
			add(comboRate);

			labelStartYear = new JLabel("Select the starting year:");
			add(labelStartYear);

			comboStartYear = new JComboBox(startYears);
			add(comboStartYear);

			labelYearsORPayment = new JLabel("Select the amount of years:");
			add(labelYearsORPayment);

			comboYears = new JComboBox(years);
			add(comboYears);

			buttonEdit = new JButton("Edit");
			add(buttonEdit);

			buttonCancel = new JButton("Cancel");
			add(buttonCancel);

			textPayment = new JTextField();

			this.index = index;
			this.object = object;


			buttonCancel.addActionListener(new CancelHandler());
			buttonEdit.addActionListener(new EditHandler());
			radPayment.addItemListener(new RadHandler());
			radFixed.addItemListener(new RadHandler());

		} //end constructor


		private class RadHandler implements ItemListener  //event handling for radio buttons
		{
			public void itemStateChanged(ItemEvent event)
			{
				if (event.getSource() == radPayment)
				{
					if (labelYearsORPayment.getText() == "Select the amount of years:")
					{
						labelYearsORPayment.setText("Enter the yearly payment:");

						remove(comboYears);
						remove(buttonEdit);
						remove(buttonCancel);

						add(textPayment);
						add(buttonEdit);
						add(buttonCancel);

						repaint();
					}
				}

				if (event.getSource() == radFixed)
				{
					if (labelYearsORPayment.getText() == "Enter the yearly payment:")
					{
						labelYearsORPayment.setText("Select the amount of years:");

						remove(textPayment);
						remove(buttonEdit);
						remove(buttonCancel);

						add(comboYears);
						add(buttonEdit);
						add(buttonCancel);

						repaint();
					}
				}
			}
		}


		private class CancelHandler implements ActionListener  //event handling for button cancel
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
				reDrawLoan();
			}
		}


		private class EditHandler implements ActionListener  //event handling for button edit
		{
			private String fName = "";
			private String lName = "";
			private String id = "";
			private String refNumber = "";
			private Double amount = 0.0;
			private Double rate = 0.0;
			private int startYear = 0;
			private int newYears = 0;
			private double newPayment = 0.0;


			public void actionPerformed(ActionEvent event)
			{
				if (radFixed.isSelected() == true)
				{
					try
					{
						this.fName = textFName.getText();
			    		this.lName = textLName.getText();
			    		this.id = textIDNumber.getText();
			    		this.refNumber = textRefNumber.getText();
			    		this.amount = Double.parseDouble(textAmount.getText());

			    		this.rate = Double.parseDouble(rates[comboRate.getSelectedIndex()]);

			    		this.startYear = Integer.parseInt(startYears[comboStartYear.getSelectedIndex()]);

			    		this.newYears = Integer.parseInt(years[comboYears.getSelectedIndex()]);

			    		if (object instanceof FixedRate)
			    		{
			    			deleteFixedLoan(index);
			    		}

			    		if (object instanceof YearlyPayment)
			    		{
						    deletePaymentLoan(index);
				    	}

			    		createFixedLoan(fName, lName, id, refNumber, amount, rate, startYear, newYears);
			    		setVisible(false);
			    		reDrawLoan();
				   	}
					catch (NumberFormatException inputMismatch)
					{
						JOptionPane.showMessageDialog(null, "You must enter a numerical value for the amount.", "Exception: " + inputMismatch, JOptionPane.WARNING_MESSAGE);
					}
				}

				if (radPayment.isSelected() == true)
				{
					try
					{
						this.fName = textFName.getText();
			    		this.lName = textLName.getText();
			    		this.id = textIDNumber.getText();
			    		this.refNumber = textRefNumber.getText();
			    		this.amount = Double.parseDouble(textAmount.getText());

			    		this.rate = Double.parseDouble(rates[comboRate.getSelectedIndex()]);

			    		this.startYear = Integer.parseInt(startYears[comboStartYear.getSelectedIndex()]);

			    		this.newPayment = Double.parseDouble(textPayment.getText());

			    		if (object instanceof FixedRate)
			    		{
			    			deleteFixedLoan(index);
			    		}

			    		if (object instanceof YearlyPayment)
			    		{
			    			deletePaymentLoan(index);
			    		}

				    	createPaymentLoan(fName, lName, id, refNumber, amount, rate, startYear, newPayment);
				    	setVisible(false);
			    		reDrawLoan();
					}
					catch (NumberFormatException inputMismatch)
					{
						JOptionPane.showMessageDialog(null, "You must enter a numerical value for the amount and payment.", "Exception: " + inputMismatch, JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		}

	} //end class GUIEditLoan


} //end class GUIMain

