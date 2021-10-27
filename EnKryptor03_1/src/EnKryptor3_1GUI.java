import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EnKryptor3_1GUI {
	
	public static int MAX_TEXT = 1000;
	public static int KEYS_NUM = 5;
	public static JTextArea InputText, OutputText;
	public static String inputT, inputP, outputT;
	public static String encrypt_status;
	
	private static JLabel UserText, UserReturnText, UserPassword;
	private static JScrollPane InputScroll, OutputScroll;
	private static JPasswordField InputPassword;
	private static JButton text_b, pi_b, enc_b, dec_b;
	private static String gInfo_1, gInfo_2, gInfo_3, gInfo_full;
	private static String passwordInfo_1, passwordInfo_2, passwordInfo_3, passwordInfo_full;
	private static String textWarning1, textWarning2;
	private static boolean goodInput;
	
	public static void E_GUI() {
		JPanel p = new JPanel();
		JFrame f = new JFrame("EnKryptor v3.1");
		f.setSize(700, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		
		p.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		grid.insets = new Insets(10, 10, 10, 10);
		
		UserText = new JLabel("Your Text:");
		grid.gridx = 0;
		grid.gridy = 0;
		grid.gridwidth = 2;
		grid.fill = GridBagConstraints.BOTH;
		grid.insets = new Insets(5, 5, 5, 5);
		p.add(UserText, grid);
		
		text_b = new JButton("General Info");
		grid.gridx = 2;
		grid.gridy = 0;
		grid.gridwidth = 1;
		grid.weightx = 1;
		grid.fill = GridBagConstraints.BOTH;
		p.add(text_b, grid);
		
		gInfo_1 = "Welcome to the EnKryptor, a simple way to keep your notes & information safe.";
		gInfo_2 = "*All [returns] and tabulations/indentations in your text will be lost.";
		gInfo_3 = "Please use this freely";
		gInfo_full = gInfo_1 + "\nP.S.\n" + gInfo_2;
		text_b.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				//System.out.println("Click");
				JOptionPane.showMessageDialog(null, gInfo_full, "EnKryptor Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	
		InputText = new JTextArea();
		grid.gridx = 0;
		grid.gridy = 1;
		grid.gridwidth = 5;
		grid.weightx = 1;
		grid.weighty = 1;
		grid.fill = GridBagConstraints.BOTH;
		p.add(InputText, grid);
		InputScroll = new JScrollPane(InputText);
		InputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		InputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p.add(InputScroll, grid);
		textWarning1 = "The text you have entered is longer than the accepted limit. All extra words will not be processed.";
		textWarning2 = "Please fill in all incomplete fields.";
		
		UserPassword = new JLabel("Password:");
		grid.gridx = 0;
		grid.gridy = 9;
		grid.weightx = 0;
		grid.weighty = 0;
		grid.fill = GridBagConstraints.BOTH;
		p.add(UserPassword, grid);
		
		InputPassword = new JPasswordField();
		grid.gridx = 0;
		grid.gridy = 10;
		grid.gridwidth = 1;
		grid.fill = GridBagConstraints.BOTH;
		p.add(InputPassword, grid);
		
		pi_b = new JButton("Password Info");
		grid.gridx = 2;
		grid.gridy = 10;
		grid.gridwidth = 1;
		grid.weightx = 1;
		grid.fill = GridBagConstraints.BOTH;
		p.add(pi_b, grid);
		
		passwordInfo_1 = "Your password determines how your text will be encrypted.";
		//Tell them to input 8 even though I only use 5
		passwordInfo_2 = "*This should only be 8 characters long.";
		passwordInfo_3 = "Password should be 8 characters long!";
		passwordInfo_full = passwordInfo_1 + "\n" + passwordInfo_2;
		pi_b.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.out.println("Click");
				JOptionPane.showMessageDialog(null, passwordInfo_full, "Password Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		enc_b = new JButton("Encrypt");
		grid.gridx = 0;
		grid.gridy = 12;
		grid.weightx = 1;
		grid.fill = GridBagConstraints.BOTH;
		p.add(enc_b, grid);
		enc_b.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				goodInput = false;
				//System.out.println("Want to encrypt");
				InputErrors();
				if (goodInput == true){
					//System.out.println("Encrypt");
					encrypt_status = "encrypt";
					//call function to encrypt
					EnKryptor.encryption();
				}
			}
		});
		
		dec_b = new JButton("Decrypt");
		grid.gridx = 2;
		grid.gridy = 12;
		//grid.gridwidth = 1;
		grid.fill = GridBagConstraints.BOTH;
		p.add(dec_b, grid);
		dec_b.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				goodInput = false;
				//System.out.println("Want to decrypt");
				InputErrors();
				if (goodInput == true){
					//System.out.println("Decrypt");
					encrypt_status = "decrypt";
					//call function to decrypt
					EnKryptor.decryption();
				}
			}
		});
		
		UserReturnText = new JLabel("Your New Text:");
		grid.gridx = 0;
		grid.gridy = 13;
		grid.weightx = 0;
		grid.fill = GridBagConstraints.BOTH;
		p.add(UserReturnText, grid);
		
		OutputText = new JTextArea();
		grid.gridx = 0;
		grid.gridy = 14;
		grid.gridwidth = 5;
		grid.weightx = 1;
		grid.weighty = 1;
		grid.fill = GridBagConstraints.BOTH;
		p.add(OutputText, grid);
		OutputScroll = new JScrollPane(OutputText);
		OutputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		OutputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p.add(OutputScroll, grid);
		
		f.setVisible(true);
	}
	
	public static void InputErrors() {
		inputT = InputText.getText();
		inputP = String.valueOf(InputPassword.getPassword());
		
		if (inputT.length() > MAX_TEXT)
			{JOptionPane.showMessageDialog(null, textWarning1, "Warning", JOptionPane.INFORMATION_MESSAGE);}
		if (inputP.length() != 8 && inputP.length() > 0)
			{JOptionPane.showMessageDialog(null, passwordInfo_3, "Password Info", JOptionPane.INFORMATION_MESSAGE);}
		else if (inputT.length() == 0 || inputP.length() == 0)
			{JOptionPane.showMessageDialog(null, textWarning2, "Input Error", JOptionPane.INFORMATION_MESSAGE);}
		else
			{goodInput = true;}
	}
	
}
