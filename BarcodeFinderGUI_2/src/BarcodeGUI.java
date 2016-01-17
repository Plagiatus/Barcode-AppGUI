
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class BarcodeGUI {
	String execute = new String();
	public static void main(String[] args) {

		new BarcodeGUI();
	}

	public BarcodeGUI(){

		
		execute = "Barcode-App.exe";
		JFrame guiFrame = new JFrame();
		try {
			guiFrame.setIconImage(ImageIO.read(new File("media/pupille.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Barcode Finder");
		guiFrame.setSize(300, 300);

		// Fenster zentrieren
		guiFrame.setLocationRelativeTo(null);

		// Optionen für die JComboBox
		String[] examplePictures = { "Chipstüte", "Mandarinen", "MnM's", "4 Barcodes" };

		
		////////////////////////////////////////////////Barcode
		final JPanel barcodePanel = new JPanel();
				
		// Beispielbilder benutzen
		JLabel comboLbl = new JLabel("Beispielbilder:");
		JComboBox pictures = new JComboBox(examplePictures);
		JButton exampleStartBut = new JButton("Start");

		barcodePanel.add(comboLbl);
		barcodePanel.add(pictures);
		barcodePanel.add(exampleStartBut);
		
		barcodePanel.add(Box.createVerticalStrut(100));

		// Neues Bild einlesen
		JButton webcamBut = new JButton("Bild über Webcam");
		JButton dateiBut = new JButton("Bild aus Datei");

		barcodePanel.add(webcamBut);
		barcodePanel.add(dateiBut);		
		
		
		///////////////////////////////////////////////////// Optionen
		final JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.PAGE_AXIS));
		
		JCheckBox searchResult = new JCheckBox("Führe Internet Suche durch");
		searchResult.setSelected(true);
		
		//interne/externe Webcam
		JLabel webcam = new JLabel("<html><font size=5>Webcam:</font></html>");
		JLabel webcamIE = new JLabel("intern/extern");
		JRadioButton webcamIntern = new JRadioButton("interne Webcam",true);
		JRadioButton webcamExtern = new JRadioButton("externe Webcam");
		ButtonGroup webcamGroup = new ButtonGroup();
		webcamGroup.add(webcamIntern); webcamGroup.add(webcamExtern);
		
		
		//einzelbild/bilderstream
		JLabel webcamStream = new JLabel("Eingabemodus:");
		JRadioButton webcamSingle = new JRadioButton("Einzelbild (manuell)",true);
		JRadioButton webcamMulti = new JRadioButton("Dauerstream");
		ButtonGroup webcamGroup2 = new ButtonGroup();
		webcamGroup2.add(webcamSingle); webcamGroup2.add(webcamMulti);
		
		
		optionPanel.add(Box.createVerticalStrut(10));
		optionPanel.add(searchResult);
		optionPanel.add(Box.createVerticalStrut(20));
		optionPanel.add(webcam); optionPanel.add(Box.createVerticalStrut(10));
		optionPanel.add(webcamIE);
		optionPanel.add(webcamIntern);
		optionPanel.add(webcamExtern);optionPanel.add(Box.createVerticalStrut(10));
		optionPanel.add(webcamStream);
		optionPanel.add(webcamSingle);
		optionPanel.add(webcamMulti);
		
		
		
		////////////////////////////////////////////////////// Erweitert
		final JPanel advancedPanel = new JPanel();
		advancedPanel.setLayout(new BoxLayout(advancedPanel, BoxLayout.PAGE_AXIS));
		
		JCheckBox stepByStep = new JCheckBox("Schritt für Schritt");
		JCheckBox showAllFrames = new JCheckBox("Alle Zwischenschritte zeigen");
		
		//Sprachausgabe an-aus
		JCheckBox speach = new JCheckBox("Sprachausgabe");
		
		// Barcode-App.exe finden
		JButton findExeBut = new JButton("Finde Barcode-App.exe");
		JLabel findExeButExplanation = new JLabel("(Nur wenn Datei nicht in selbem Ordner)");
		advancedPanel.add(Box.createVerticalStrut(10));
		advancedPanel.add(stepByStep);
		advancedPanel.add(showAllFrames);
		
		
		
		advancedPanel.add(Box.createVerticalStrut(20));
		advancedPanel.add(speach);
		
		advancedPanel.add(Box.createVerticalStrut(20));
		advancedPanel.add(findExeBut);
		advancedPanel.add(findExeButExplanation);
		
		
		///////////////////////////////////////////////////// Info
		final JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		
		//Logos
		ImageIcon logo = new ImageIcon("media/Logo_hr.png");
		Image logo1 = logo.getImage();
		Image logo2 = logo1.getScaledInstance(100, 55, java.awt.Image.SCALE_SMOOTH);
		ImageIcon logo3 = new ImageIcon(logo2);
		JLabel logoLabel = new JLabel(logo3);
		
		ImageIcon caLogo = new ImageIcon("media/campfire_logo.png");
		Image caLogo1 = caLogo.getImage();
		Image caLogo2 = caLogo1.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH);
		ImageIcon caLogo3 = new ImageIcon(caLogo2);
		JLabel caLogoLabel = new JLabel(caLogo3);
		
		JPanel logos = new JPanel();
		logos.setLayout(new BoxLayout(logos, BoxLayout.PAGE_AXIS));
		logos.add(logoLabel);
		logos.add(caLogoLabel);
		
		//Text
		
		String infoTxt = new String();
		infoTxt = "<html><strong>Barcode Finder</strong><br>"
				+ "<font size=2>Erstellt als Semesterprojekt an der <em>Fakultät Informatik der Hochschule Furtwangen</em>.<br>"
				+ "<br>" 
				+ "Verwendete Open-Source Bibliotheken:<br>"
				+ "<ul>"
				+ "<li>OpenCV</li>"
				+ "<li>ZBar</li>"
				+ "<li>...</li>"
				+ "</ul>"
				+ "&copy; Campfire Software, 2016<br>"
				+ "veröffentlicht unter \"GNU General Public License\"</html>";
		
		JLabel infoText = new JLabel(infoTxt);
		
		infoPanel.add(Box.createVerticalStrut(10));
		infoPanel.add(logos, BorderLayout.EAST);
		infoPanel.add(infoText, BorderLayout.CENTER);
		
		
		///////////////////////////////////////////////////// BUTTONS

		exampleStartBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String exampleImgPath = new String();
				
				switch(pictures.getSelectedItem().toString()){
					case("Chipstüte"):
						exampleImgPath = "media/internet/chips.jpg";
						break;
					case("Mandarinen"):
						exampleImgPath = "media/gut/mandarine.jpg";
						break;
					case("MnM's"):
						exampleImgPath = "media/gut/highQu_scaled.jpg";
						break;
					case("4 Barcodes"):
						exampleImgPath = "media/internet/test.png";
						break;
					default:
						break;
				}
				
				
				startDetection(exampleImgPath , execute, stepByStep.isSelected(),showAllFrames.isSelected(), speach.isSelected(), searchResult.isSelected());
			}
		});

		webcamBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				startDetection("", execute, stepByStep.isSelected(),showAllFrames.isSelected(), speach.isSelected(), searchResult.isSelected(), true, webcamIntern.isSelected(), webcamSingle.isSelected());;
			}
		});
		
		findExeBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filterExe = new FileNameExtensionFilter("Ausführbar", "exe");
				fileChooser.addChoosableFileFilter(filterExe);

				fileChooser.setVisible(true);
				int ret = fileChooser.showOpenDialog(guiFrame);

				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					//System.out.println(file.toString());
					execute = fixPath(file.toString());
					//System.out.println(execute);
				}
			}
		});

		dateiBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filterImg = new FileNameExtensionFilter("Bilder", "jpg", "jpeg", "png", "gif");
				fileChooser.addChoosableFileFilter(filterImg);

				fileChooser.setVisible(true);
				int ret = fileChooser.showOpenDialog(guiFrame);

				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String fileString = fixPath(file.toString());
					//System.out.println(fileString);
					startDetection(fileString, execute, stepByStep.isSelected(),showAllFrames.isSelected(), speach.isSelected(), searchResult.isSelected());
				}
			}
		});

		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPanel.addTab("Barcode", barcodePanel);
		tabbedPanel.addTab("Optionen",optionPanel);
		tabbedPanel.addTab("Erweitert", advancedPanel);
		tabbedPanel.addTab("Info",infoPanel);

		guiFrame.add(tabbedPanel, BorderLayout.CENTER);

		//guiFrame.pack();

		guiFrame.setVisible(true);
		guiFrame.setResizable(false);

	}
	
	private void startDetection(String file, String execute, boolean stepByStep, boolean showAllSteps, boolean speach, boolean search){
		startDetection(file,execute,stepByStep,showAllSteps,speach,search,false,false,false);
	}

	private void startDetection(String file, String execute, boolean stepByStep, boolean showAllSteps, boolean search, boolean speach, boolean webcam, boolean webcamIntern, boolean webcamSingle) {
		Process p;
		String command = execute + " " + stepByStep + " " + showAllSteps + " " + search + " " + webcam + " " + file;
		if(webcam){
			if(webcamIntern)
				command += "i ";
			else
				command += "e ";
			
			if(webcamSingle)
				command+= "s";
			else
				command += "m";
		}
		System.out.println(command);
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Barcode-App.exe nicht gefunden.\nIst der richtige Pfad hinterlegt?\n"
					+ "(\"Optionen\" -> \"Finde Barcode-App.exe\")",
					"Fehler", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Ein Vorgang wurde unterbrochen","Fehler", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}

	}
	
	private String fixPath(String in){
		return in.replace('\\', '/');
	}

}
