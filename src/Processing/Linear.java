package Processing;

import UI.OutputForm;

import java.util.ArrayList;

public class Linear extends OutputForm {
	private double loan;
	private int months;
	private double percentage;
	private ArrayList<double[]> result;

	public Linear(double loan, int years, int months, double percentage) {
		super("Linear");

		// Save variables locally
		this.loan = loan;
		this.months = months + years*12;
		this.percentage = percentage;

		// Calculate payments
		this.result = new ArrayList<>();
		results(this.result);
		super.result = this.result;
	}


	public void results(ArrayList<double[]> res) {
		double tmpLoan = loan;
		double percentage = this.percentage / 12 / 100;
		double paid;
		double overallPaid = 0;
		double overallInterest = 0;

		// Calculate payments with linear mortgage formula
		for (int i = 0; i < months; i++) {
			tmpLoan -= loan / months;
			paid = tmpLoan * percentage;
			overallPaid += loan / months + paid;
			overallInterest += paid - loan / months;
			res.add(new double[] {Math.abs(loan / months + paid), Math.abs(paid), Math.abs(tmpLoan)});
		}

		res.add(new double[] {Math.abs(overallPaid), Math.abs(overallInterest), 0});

		// Display results in UI
		super.setTitle("Linear");
		super.results(res);
	}
}
