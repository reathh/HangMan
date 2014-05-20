import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QDecoderStream;
 
 
public class UI extends JFrame {
	
    public UI() {
       
        initUI();
    }
 
    private void initUI() {
    	JPanel panel = new JPanel();
        panel.setLayout(null);
        
        final JLabel guessWord = new JLabel("guessWord");
   
        
        final String shot = "1.png";
       
        JMenuBar menubar = new JMenuBar();
        //create icons
        ImageIcon iconRestart = new ImageIcon("restart.png");
        ImageIcon iconExit = new ImageIcon("exit.png");
 
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
 
        JMenu imp = new JMenu("Difficult");
        imp.setMnemonic(KeyEvent.VK_M);
        //set submenu for difficult
        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem hard = new JMenuItem("Hard");
 
        imp.add(easy);
        imp.add(medium);
        imp.add(hard);
       
        JMenu language = new JMenu("Language");
        language.setMnemonic(KeyEvent.VK_L);
        //set submenu for language
        JMenuItem bulgarian = new JMenuItem("Bulgarian");
        JMenuItem english = new JMenuItem("English");
 
        language.add(bulgarian);
        language.add(english);
 
      
        JMenuItem restart = new JMenuItem("Restart", iconRestart);
        //set shortcut for restart
        restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                ActionEvent.CTRL_MASK));
        //exit event
        JMenuItem fileExit = new JMenuItem("Exit", iconExit);
        fileExit.setMnemonic(KeyEvent.VK_C);
        fileExit.setToolTipText("Exit application");
        //set shortcut for exit
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
            ActionEvent.CTRL_MASK));
 
        fileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
           try {
			Logic.InitGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           Logic.paint(guessWord);               
               repaint();
            }
        });
 
//        file.add(fileNew);
        //put all in the file
        file.add(language);
        file.add(restart);
        file.addSeparator();
        file.add(imp);
        file.addSeparator();
        file.add(fileExit);
 
        menubar.add(file);
 
        setJMenuBar(menubar);
        //name of frame
        setTitle("Submenu");
        //size of frame
        setSize(600, 650);
        //Centered
        setLocationRelativeTo(null);
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        
      
        add(panel);
       
       
        //qwerty buttons
        JButton q = new JButton("Q");
        q.setBounds(160, 480, 25, 25);
        q.setMargin(new Insets(0, 0, 0, 0));
        q.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	URL url = null;
				try {
					url = new URL(
							"file:ding.wav");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
            	AudioClip clip = Applet.newAudioClip(url);
        		clip.play();
        		
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
            }
        });
       
       
        JButton w = new JButton("W");
        w.setMargin(new Insets(0, 0, 0, 0));
        w.setBounds(190, 480, 25, 25);
       
        JButton e = new JButton("E");
        e.setMargin(new Insets(0, 0, 0, 0));
        e.setBounds(220, 480, 25, 25);
       
        JButton r = new JButton("R");
        r.setMargin(new Insets(0, 0, 0, 0));
        r.setBounds(250, 480, 25, 25);
       
        JButton t = new JButton("T");
        t.setMargin(new Insets(0, 0, 0, 0));
        t.setBounds(280, 480, 25, 25);
       
        JButton yy = new JButton("Y");
        yy.setMargin(new Insets(0, 0, 0, 0));
        yy.setBounds(310, 480, 25, 25);
       
        JButton u = new JButton("U");
        u.setMargin(new Insets(0, 0, 0, 0));
        u.setBounds(340, 480, 25, 25);
       
        JButton i = new JButton("I");
        i.setMargin(new Insets(0, 0, 0, 0));
        i.setBounds(370, 480, 25, 25);
       
        JButton o = new JButton("O");
        o.setMargin(new Insets(0, 0, 0, 0));
        o.setBounds(400, 480, 25, 25);
       
        JButton p = new JButton("P");
        p.setMargin(new Insets(0, 0, 0, 0));
        p.setBounds(430, 480, 25, 25);
       
        JButton a = new JButton("A");
        a.setMargin(new Insets(0, 0, 0, 0));
        a.setBounds(170, 510, 25, 25);
       
        JButton s = new JButton("S");
        s.setMargin(new Insets(0, 0, 0, 0));
        s.setBounds(200, 510, 25, 25);
       
        JButton d = new JButton("D");
        d.setMargin(new Insets(0, 0, 0, 0));
        d.setBounds(230, 510, 25, 25);
       
        JButton f = new JButton("F");
        f.setMargin(new Insets(0, 0, 0, 0));
        f.setBounds(260, 510, 25, 25);
       
        JButton g = new JButton("G");
        g.setMargin(new Insets(0, 0, 0, 0));
        g.setBounds(290, 510, 25, 25);
       
        JButton h = new JButton("H");
        h.setMargin(new Insets(0, 0, 0, 0));
        h.setBounds(320, 510, 25, 25);
       
        JButton j = new JButton("J");
        j.setMargin(new Insets(0, 0, 0, 0));
        j.setBounds(350, 510, 25, 25);
       
        JButton k = new JButton("K");
        k.setMargin(new Insets(0, 0, 0, 0));
        k.setBounds(380, 510, 25, 25);
       
        JButton l = new JButton("L");
        l.setMargin(new Insets(0, 0, 0, 0));
        l.setBounds(410, 510, 25, 25);
       
        JButton z = new JButton("Z");
        z.setMargin(new Insets(0, 0, 0, 0));
        z.setBounds(185, 540, 25, 25);
       
        JButton xx = new JButton("X");
        xx.setMargin(new Insets(0, 0, 0, 0));
        xx.setBounds(215, 540, 25, 25);
       
        JButton c = new JButton("C");
        c.setMargin(new Insets(0, 0, 0, 0));
        c.setBounds(245, 540, 25, 25);
       
        JButton v = new JButton("V");
        v.setMargin(new Insets(0, 0, 0, 0));
        v.setBounds(275, 540, 25, 25);
       
        JButton b = new JButton("B");
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setBounds(305, 540, 25, 25);
       
        JButton n = new JButton("N");
        n.setMargin(new Insets(0, 0, 0, 0));
        n.setBounds(335, 540, 25, 25);
       
        JButton m = new JButton("M");
        m.setMargin(new Insets(0, 0, 0, 0));
        m.setBounds(365, 540, 25, 25);
        
        
       
     
       
       
 
        panel.add(a);
        panel.add(b);
        panel.add(c);
        panel.add(d);
        panel.add(e);
        panel.add(f);
        panel.add(g);
        panel.add(h);
        panel.add(i);
        panel.add(j);
        panel.add(k);
        panel.add(l);
        panel.add(m);
        panel.add(n);
        panel.add(o);
        panel.add(p);
        panel.add(q);
        panel.add(r);
        panel.add(s);
        panel.add(t);
        panel.add(u);
        panel.add(v);
        panel.add(w);
        panel.add(xx);
        panel.add(yy);
        panel.add(z);
 
        
        guessWord.setHorizontalAlignment(SwingConstants.CENTER);
        guessWord.setBounds(1, 30, 600, 40);
        guessWord.setFont(new Font("TimesRoman", Font.BOLD, 30));
        panel.add(guessWord);
        add(panel);
       
    }
 
    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UI ex = new UI();
                ex.setVisible(true);
            }
        });
    }
}