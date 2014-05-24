import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BGKeyboard implements ActionListener {
	public static JButton я, в, е, р, т, ъ, у, и, о, п, ч, а, с, д, ф, г, х, й,
			к, л, ш, щ, з, ь, ц, ж, б, н, м, ю;
	public String[] keysBG = { "я", "в", "е", "р", "т", "ъ", "у", "и", "о",
			"п", "ч", "а", "с", "д", "ф", "г", "х", "й", "к", "л", "ш", "щ",
			"з", "ь", "ц", "ж", "б", "н", "м", "ю" };
	public static JButton[] keyboardBG = { я, в, е, р, т, ъ, у, и, о, п, ч, а,
			с, д, ф, г, х, й, к, л, ш, щ, з, ь, ц, ж, б, н, м, ю };

	public BGKeyboard() {
		for (int i = 0; i < 11; i++) {
			keyboardBG[i] = generateKeyButton(keysBG[i], 130 + i * 30, 480);
		}
		for (int i = 11; i < 22; i++) {
			keyboardBG[i] = generateKeyButton(keysBG[i], 130 + (i - 10) * 30,
					510);
		}
		for (int i = 22; i < keyboardBG.length; i++) {
			keyboardBG[i] = generateKeyButton(keysBG[i], 165 + (i - 22) * 30,
					540);
		}

		for (int i = 0; i < keyboardBG.length; i++) {
			UI.panel.add(keyboardBG[i]);
		}
	}

	private JButton generateKeyButton(String text, int cordFirstLine,
			int cordLine) {
		JButton name = new JButton(text.toUpperCase());
		name.setBounds(cordFirstLine, cordLine, 25, 25);
		name.setMargin(new Insets(0, 0, 0, 0));
		name.addActionListener(this);
		name.requestFocus();
		return name;
	}

	public void actionPerformed(ActionEvent e) {
		if (!Logic.endIt) {
			for (int i = 0; i < keyboardBG.length; i++) {
				if (keyboardBG[i].getText() == e.getActionCommand()) {
					keyboardBG[i].setEnabled(false);
				}
			}
			Logic.ProcessTurn(e.getActionCommand().toString());
		} else {
			JOptionPane.showMessageDialog(UI.panel, "Please start a new game",
					"Please start a new game", JOptionPane.PLAIN_MESSAGE);
		}
	}
}