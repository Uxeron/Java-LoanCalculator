package Processing;

import UI.OutputForm;

import java.util.ArrayList;
import java.lang.Math;

public class Annuity extends OutputForm {
	private double loan;
	private int months;
	private double percentage;
	public ArrayList<double[]> result;

	public Annuity(double loan, int years, int months, double percentage) {
		super("Annuity");

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

		// Calculate payments with annuity mortgage formula
		for (int i = 0; i < months; i++) {
			tmpLoan -= loan / months;
			paid = (percentage * loan) / (1 - Math.pow((1 + percentage), -months));
			overallPaid += paid;
			overallInterest += paid - loan / months;
			res.add(new double[] {Math.abs(paid), Math.abs(paid - loan / months), Math.abs(tmpLoan)});
		}

		res.add(new double[] {Math.abs(overallPaid), Math.abs(overallInterest), 0});

		// Display results in UI
		super.setTitle("Annuity");
		super.results(res);
	}
}
