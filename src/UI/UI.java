package UI;

import Processing.Annuity;
import Processing.Linear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.commons.lang3.math.NumberUtils;

public class UI extends JFrame implements ActionListener {
	private JTextField Text_Suma;
	private JTextField Text_Metai;
	private JTextField Text_Menesiai;
	private JTextField Text_Proc;
	private JComboBox Combo_Grafikas;

	public UI() {
		// Set window name
		super("UI");

		// Define variables
		JLabel Label_Suma;
		JLabel Label_Metai;
		JLabel Label_Menesiai;
		JLabel Labelai_Proc;
		JLabel Label_Grafikas;
		JButton Button_Testi;

		// Place UI elements
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

		Label_Suma = new JLabel("Paskolos suma, €");
		add(Label_Suma);

		Text_Suma = new JTextField(12);
		add(Text_Suma);

		Label_Metai = new JLabel("Metai");
		add(Label_Metai);

		Text_Metai = new JTextField("0",12);
		add(Text_Metai);

		Label_Menesiai = new JLabel("Menesiai");
		add(Label_Menesiai);

		Text_Menesiai = new JTextField("0",12);
		add(Text_Menesiai);

		Labelai_Proc = new JLabel("Metines palukanos, %");
		add(Labelai_Proc);

		Text_Proc = new JTextField(12);
		add(Text_Proc);

		Label_Grafikas = new JLabel("Gražinimo grafikas");
		add(Label_Grafikas);

		Combo_Grafikas = new JComboBox(new String[] {"Linijinis", "Anuiteto"});
		add(Combo_Grafikas);

		Button_Testi = new JButton("Testi");
		Button_Testi.addActionListener(this);
		add(Button_Testi);

		// Finish setting up and display the UI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 380);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event){
		if (NumberUtils.isCreatable(Text_Suma.getText())) {		// Sum field has a number
			if (NumberUtils.isCreatable(Text_Metai.getText())) {	// Year field has a number
				if (NumberUtils.isCreatable(Text_Menesiai.getText())) {		// Month field has a number
						if (NumberUtils.isCreatable(Text_Proc.getText())) {		// Percentage field has a number
							if (Combo_Grafikas.getSelectedIndex() == 0) {		// Run calculation using linear formula
								new Linear(Double.parseDouble(Text_Suma.getText()),
										Integer.parseInt(Text_Metai.getText()),
										Integer.parseInt(Text_Menesiai.getText()),
										Double.parseDouble(Text_Proc.getText()));
							} else {											// Run calculation using annuity formula
								new Annuity(Double.parseDouble(Text_Suma.getText()),
										Integer.parseInt(Text_Metai.getText()),
										Integer.parseInt(Text_Menesiai.getText()),
										Double.parseDouble(Text_Proc.getText()));
							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "Iveskite metinius procentus");
						}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Iveskite menesiu skaiciu");
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Iveskite metu skaiciu");
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Iveskite paskolos suma");
		}
	}

}