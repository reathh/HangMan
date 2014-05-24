import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ENKeyboard extends JPanel implements ActionListener {
	public static JButton q, w, e, r, t, yy, u, i, o, p, a, s, d, f, g, h, j,
			k, l, z, xx, c, v, b, n, m;
	public String[] keys = { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
			"a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v",
			"b", "n", "m" };
	public static JButton[] keyboard = { q, w, e, r, t, yy, u, i, o, p, a, s,
			d, f, g, h, j, k, l, z, xx, c, v, b, n, m };

	public ENKeyboard() {
		for (int i = 0; i < 10; i++) {
			keyboard[i] = generateKeyButton(keys[i], 160 + i * 30, 480);
		}
		for (int i = 10; i < 19; i++) {
			keyboard[i] = generateKeyButton(keys[i], 170 + (i - 10) * 30, 510);
		}
		for (int i = 19; i < keyboard.length; i++) {
			keyboard[i] = generateKeyButton(keys[i], 185 + (i - 19) * 30, 540);
		}

		for (int i = 0; i < keyboard.length; i++) {
			UI.panel.add(keyboard[i]);
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
			for (int i = 0; i < keyboard.length; i++) {
				if (keyboard[i].getText() == e.getActionCommand()) {
					keyboard[i].setEnabled(false);
				}
			}

			Logic.ProcessTurn(e.getActionCommand().toString());
		} else {
			JOptionPane.showMessageDialog(UI.panel, "Please start a new game",
					"Please start a new game", JOptionPane.PLAIN_MESSAGE);
		}
	}

}