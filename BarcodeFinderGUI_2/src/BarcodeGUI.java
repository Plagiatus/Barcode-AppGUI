
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
		String[] examplePictures = { "Chipstüte", "Chipstüte gedreht", "Mandarinen 1",
										"Mandarinen 2", "Joghurt", "Toffifee", "MnM's",
										"Essig", "Testbild: Brief", "4 Barcodes" };

		
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
		ImageIcon hfuLogo = new ImageIcon("media/Logo_HFU_low_trans.png");
		Image hfuLogo1 = hfuLogo.getImage();
		Image hfuLogo2 = hfuLogo1.getScaledInstance(100, 41, java.awt.Image.SCALE_SMOOTH); // 80, 33,
		ImageIcon hfuLogo3 = new ImageIcon(hfuLogo2);
		JLabel hfuLogoLabel = new JLabel(hfuLogo3);
		
		ImageIcon logo = new ImageIcon("media/Logo_small3.png"); //Logo_hr.png
		Image logo1 = logo.getImage();
		Image logo2 = logo1.getScaledInstance(100, 55, java.awt.Image.SCALE_SMOOTH); //80, 44,
		ImageIcon logo3 = new ImageIcon(logo2);
		JLabel logoLabel = new JLabel(logo3);
		
		ImageIcon caLogo = new ImageIcon("media/campfire_logo.png");
		Image caLogo1 = caLogo.getImage();
		Image caLogo2 = caLogo1.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH); // 80, 120,
		ImageIcon caLogo3 = new ImageIcon(caLogo2);
		JLabel caLogoLabel = new JLabel(caLogo3);
		
		JPanel logos = new JPanel();
		logos.setLayout(new BoxLayout(logos, BoxLayout.PAGE_AXIS));
		logos.add(hfuLogoLabel);
		logos.add(logoLabel);
		logos.add(caLogoLabel);

		//Text
		
		String infoTxt = new String();
		infoTxt = "<html><strong>Barcode Finder</strong><br>"
				+ "<font size=2>Erstellt als Semesterprojekt an der<br>"
				+ " <em>Fakultät Informatik der Hochschule Furtwangen (WS 2015/16)</em>.<br>"
				+ "<br>" 
				+ "Projektteilnehmer:<br>"
//				+ "<ul>"
				+ " - Björn Beha<br>"
				+ " - Johannes Czech<br>"
				+ " - Lukas Scheuerle<br>"
				+ " - Suhay Sevinc<br>"
//				+ "</ul>"
				+ "<br>"
				+ "Projektbetreuer:<br>"
//				+ "<ul>"
				+ " - Prof. Dr. Peter Fleischer<br>"
				+ " - Judith Jakob<br>"
//				+ "</ul>"
				+ "<br>"
				+ "&copy; Campfire Software, 2016 <br>" //veröffentlicht
				+ "\"GNU General Public License\"" //unter
				+ "</html>";
		
		JLabel infoText = new JLabel(infoTxt);
		
		infoPanel.add(Box.createVerticalStrut(10));
		infoPanel.add(logos, BorderLayout.EAST);
		infoPanel.add(infoText, BorderLayout.CENTER);
		

		///////////////////////////////////////////////////// Info2
		final JPanel infoPanel2 = new JPanel();
		infoPanel2.setLayout(new BorderLayout());
		
		String infoTxt2 = new String();
		infoTxt2 = "<html><header><h2>Barcode Finder</h2></header><br>"
				+ "<br>"
				+ "Verwendete <br>"
				+ "Open-Source Bibliotheken:<br>"
				+ "<ul>"
				+ "<li>OpenCV</li>"
				+ "<li>ZBar</li>"
				+ "<li>eSpeak</li>"
				+ "<li>libcurl</li>"
				+ "</ul>"
				+ "<br>"			
				+ "<br>"
				+ "<br>"
				+ "</html>";
		
		JLabel infoText2 = new JLabel(infoTxt2);
		
		infoPanel2.add(infoText2, BorderLayout.CENTER);	
		
		///////////////////////////////////////////////////// BUTTONS

		exampleStartBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String exampleImgPath = new String();
				
				switch(pictures.getSelectedItem().toString()){
					case("Chipstüte"):
						exampleImgPath = "media/internet/chips.jpg";
						break;
					case("Chipstüte gedreht"):
						exampleImgPath = "media/internet/Chips_rotated.jpg";
						break;
					case("Mandarinen 1"):
						exampleImgPath = "media/gut/mandarine.jpg";
						break;
					case("Mandarinen 2"):
						exampleImgPath = "media/gut/mandarine_scaled.jpg";
						break;
					case("Joghurt"):
						exampleImgPath = "media/gut/joghurt_scaled.jpg";
						break;
					case("Toffifee"):
						exampleImgPath = "media/gut/toffifee_scaled.jpg";
						break;	
					case("MnM's"):
						exampleImgPath = "media/gut/highQu_scaled.jpg";
						break;
					case("Essig"):
						exampleImgPath = "media/gut/essig.jpg";
			 			break;
					case("Testbild: Brief"):
						exampleImgPath = "media/internet/brief.jpg";
						break;		
					case("4 Barcodes"):
						exampleImgPath = "media/internet/test.png";
						break;

					default:
						break;
				}
				
				startDetection(execute, stepByStep.isSelected(), showAllFrames.isSelected(), searchResult.isSelected(), false, speach.isSelected(), exampleImgPath, false, false);
//				startDetection(exampleImgPath , execute, stepByStep.isSelected(),showAllFrames.isSelected(), speach.isSelected(), searchResult.isSelected());
			}
		});

		webcamBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				startDetection(execute, stepByStep.isSelected(),showAllFrames.isSelected(), searchResult.isSelected(), true, speach.isSelected(), "leer",  webcamIntern.isSelected(), webcamSingle.isSelected());
//				startDetection("leer", execute, stepByStep.isSelected(),showAllFrames.isSelected(), speach.isSelected(), searchResult.isSelected(), true, webcamIntern.isSelected(), webcamSingle.isSelected());
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
					startDetection(execute, stepByStep.isSelected(),showAllFrames.isSelected(), searchResult.isSelected(), false, speach.isSelected(), fileString, false, false);
				}
			}
		});

		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPanel.addTab("Barcode", barcodePanel);
		tabbedPanel.addTab("Optionen",optionPanel);
		tabbedPanel.addTab("Erweitert", advancedPanel);
		tabbedPanel.addTab("Info",infoPanel);
		tabbedPanel.addTab("Info2",infoPanel2);
		
		guiFrame.add(tabbedPanel, BorderLayout.CENTER);

//		guiFrame.pack();

		guiFrame.setVisible(true);
		guiFrame.setResizable(false);

	}
	
	/*private void startDetection(String file, String execute, boolean stepByStep, boolean showAllSteps, boolean speach, boolean search){
		startDetection(file,execute,stepByStep,showAllSteps,search,speach,false,false,false);
	}*/

	private void startDetection(String execute, boolean stepByStep, boolean showAllSteps, boolean search, boolean webcam, boolean speach, String file, boolean webcamIntern, boolean webcamSingle) {
		Process p;
		String command = execute + " " + stepByStep + " " + showAllSteps + " " + search + " " + webcam + " " + speach + " \"" + file + "\" ";
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
//		JOptionPane.showMessageDialog(null, "command:" + command);
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
