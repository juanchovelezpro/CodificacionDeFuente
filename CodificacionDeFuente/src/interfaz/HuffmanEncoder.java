package interfaz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.Codificacion;

public class HuffmanEncoder {

	private Codificacion encoder;

	public void crearCodificacionArchivoTxt(String frase) {

		encoder = new Codificacion(frase);

		File file = new File("huffmanEncoder.txt");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(encoder.toString());
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			
			if (writer != null) {
				
				try {
					writer.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			
		}

	}

	public static void main(String[] args) {

		HuffmanEncoder encoder = new HuffmanEncoder();

		String frase = JOptionPane.showInputDialog("Introduzca la frase a codificar:");

		encoder.crearCodificacionArchivoTxt(frase);

	}

}
