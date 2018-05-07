package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputForm extends JFrame implements ActionListener{
	private JTable Table;
	public ArrayList<double[]> result;
	public ArrayList<String[]> resultStr;

	public OutputForm(String name) {
		// Set window name
		super(name);

		// Define variables
		JButton Button_Export;
		JScrollPane Scroll;

		// Setup UI
		setLayout(new BorderLayout(0, 0));

		// Setup table
		Table = new JTable(new DefaultTableModel(new Object[]{"Mokejimo nr", "Moketi, €", "Palukanos, €", "Likusi suma, €"}, 0));
		Scroll = new JScrollPane(Table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Table.setDefaultEditor(Object.class, null);
		add(Scroll, BorderLayout.CENTER);

		Button_Export = new JButton("Export");
		Button_Export.addActionListener(this);
		add(Button_Export, BorderLayout.SOUTH);

		// Finish setting up and display the UI
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public void results(ArrayList<double[]> res) {
		DefaultTableModel model = (DefaultTableModel) Table.getModel();
		resultStr = new ArrayList<String[]>();
		int index = 1;

		// Go over each entry changing precision to 2 digits and converting it to a string
		for (double[] d: res) {
			String[] tmp = new String[4];
			tmp[0] = Integer.toString(index);
			for (int i = 1; i < 4; i++)
				tmp[i] = String.format("%.2f", d[i-1]);
			resultStr.add(tmp);

			// Add converted elements to the table
			model.addRow(new Object[]{tmp[0], tmp[1], tmp[2], tmp[3]});
			index++;
		}
        model.setValueAt("Bendra suma:", res.size() - 1, 0);

	}

	public void actionPerformed(ActionEvent event) {
		// Output data to file
		File file = new File("Sumos.txt");

		try {
			FileWriter out = new FileWriter(file);
			// Write column names to file
			out.write(String.format("%4s %10s %10s %12s \r\n", "Nr", "Moketi, €", "Palukanos, €", "Likusi suma, €"));
			int index = 1;

			// Write data to file
			for (String[] s: resultStr) {
				out.write(String.format("%4s %10s %10s %12s \r\n", index, s[1], s[2], s[3]));
				index++;
			}
			out.flush();
			out.close();
			JOptionPane.showMessageDialog(new JFrame(), "Sekmingai issaugota į faila");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Saugant i faila ivyko klaida");
			e.printStackTrace();
		}

	}
}
