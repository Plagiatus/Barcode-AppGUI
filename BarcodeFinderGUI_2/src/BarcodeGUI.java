
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
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
			guiFrame.setIconImage(ImageIO.read(new File("media/Logo.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Barcode Finder");
		guiFrame.setSize(300, 250);

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

		
		////////////////////////////////////////////////////// Optionen
		final JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
		
		JLabel opt = new JLabel("Optionen:");
		JCheckBox stepByStep = new JCheckBox("Schritt für Schritt");
		JCheckBox showAllFrames = new JCheckBox("Alle Zwischenschritte zeigen");

		// Barcode-App.exe finden
		JButton findExeBut = new JButton("Finde Barcode-App.exe");
		JCheckBox searchResult = new JCheckBox("Führe Internet Suche durch");
		searchResult.setSelected(true);
		JLabel findExeButExplanation = new JLabel("(Nur wenn Datei nicht in selbem Ordner)");
		optionsPanel.add(opt);
		optionsPanel.add(stepByStep);
		optionsPanel.add(showAllFrames);
		optionsPanel.add(searchResult);
		
		optionsPanel.add(Box.createVerticalStrut(20));
		
		optionsPanel.add(findExeBut);
		optionsPanel.add(findExeButExplanation);

		
		
		
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
				
				
				startDetection(exampleImgPath , execute, stepByStep.isSelected(),showAllFrames.isSelected(),searchResult.isSelected());
			}
		});

		webcamBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				startDetection("", execute, stepByStep.isSelected(),showAllFrames.isSelected(),searchResult.isSelected(), true);;
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
					startDetection(fileString, execute, stepByStep.isSelected(),showAllFrames.isSelected(),searchResult.isSelected());
				}
			}
		});

		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPanel.addTab("Barcode", barcodePanel);
		tabbedPanel.addTab("Optionen", optionsPanel);

		guiFrame.add(tabbedPanel, BorderLayout.CENTER);

		//guiFrame.pack();

		guiFrame.setVisible(true);
		guiFrame.setResizable(false);

	}
	
	private void startDetection(String file, String execute, boolean sbs, boolean aza, boolean search){
		startDetection(file,execute,sbs,aza,search,false);
	}

	private void startDetection(String file, String execute, boolean sbs, boolean aza, boolean search, boolean webcam) {
		Process p;
		String command = execute + " " + sbs + " " + aza + " " + search + " " + webcam + " " + file;
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
