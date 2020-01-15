package model;

import java.util.*;

/**
 * Codificacion de fuente. Huffman.
 * 
 * @author juanchovelezpro
 *
 */
public class Codificacion {

	private String frase;
	private String infoOrganizada;
	private ArrayList<NodoHuffman> frecuenciasHuffman;
	private ArrayList<Character> alfabeto;
	private ArrayList<Integer> frecuencias;
	private ArrayList<String> probabilidadesString;
	private ArrayList<Double> probabilidades;
	private ArrayList<Double> informacionesPropias;
	private double entropiaMensaje;

	public Codificacion(String frase) {

		this.frase = frase;

		infoOrganizada = "";
		alfabeto = new ArrayList<>();
		frecuencias = new ArrayList<>();
		frecuenciasHuffman = new ArrayList<>();
		probabilidadesString = new ArrayList<>();
		probabilidades = new ArrayList<>();
		informacionesPropias = new ArrayList<>();
		entropiaMensaje = 0;

		crearAlfabeto();
		calcularFrecuencias();
		calcularProbabilidades();
		calcularInformacionesPropias();
		calcularEntropia();
		crearInfoOrganizada();
		huffman();
		formato();

	}

	public void formato() {

		for (int i = 0; i < probabilidades.size(); i++) {

			probabilidades.set(i, Double.parseDouble(String.format("%.5f", probabilidades.get(i))));
			informacionesPropias.set(i, Double.parseDouble(String.format("%.5f", informacionesPropias.get(i))));

		}

	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public String getInfoOrganizada() {
		return infoOrganizada;
	}

	public void setInfoOrganizada(String infoOrganizada) {
		this.infoOrganizada = infoOrganizada;
	}

	public ArrayList<Character> getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(ArrayList<Character> alfabeto) {
		this.alfabeto = alfabeto;
	}

	public ArrayList<Integer> getFrecuencias() {
		return frecuencias;
	}

	public void setFrecuencias(ArrayList<Integer> frecuencias) {
		this.frecuencias = frecuencias;
	}

	public ArrayList<String> getProbabilidadesString() {
		return probabilidadesString;
	}

	public void setProbabilidadesString(ArrayList<String> probabilidadesString) {
		this.probabilidadesString = probabilidadesString;
	}

	public ArrayList<Double> getProbabilidades() {
		return probabilidades;
	}

	public void setProbabilidades(ArrayList<Double> probabilidades) {
		this.probabilidades = probabilidades;
	}

	public ArrayList<Double> getInformacionesPropias() {
		return informacionesPropias;
	}

	public void setInformacionesPropias(ArrayList<Double> informacionesPropias) {
		this.informacionesPropias = informacionesPropias;
	}

	public double getEntropiaMensaje() {
		return entropiaMensaje;
	}

	public void setEntropiaMensaje(double entropiaMensaje) {
		this.entropiaMensaje = entropiaMensaje;
	}

	public void crearInfoOrganizada() {

		for (int i = 0; i < alfabeto.size(); i++) {

			infoOrganizada += "\n" + "Simbolo: -> " + alfabeto.get(i) + "\n" + "Probabilidad: "
					+ probabilidadesString.get(i) + "\n" + "Valor probabilidad: "
					+ String.format("%.5f", probabilidades.get(i)) + "\n" + "Informacion Propia: "
					+ String.format("%.5f", informacionesPropias.get(i)) + "\n"
					+ "--------------------------------------------------------------------" + "\n";

		}

	}

	public void crearAlfabeto() {

		for (int i = 0; i < frase.length(); i++) {

			if (!alfabeto.contains(frase.charAt(i))) {
				alfabeto.add(frase.charAt(i));
			}
		}

	}

	public void calcularProbabilidades() {

		for (int i = 0; i < alfabeto.size(); i++) {

			int contador = 0;

			for (int j = 0; j < frase.length(); j++) {

				if (alfabeto.get(i).equals(frase.charAt(j))) {

					contador++;

				}

			}

			double probabilidad = (double) contador / (double) frase.length();

			probabilidadesString.add("" + contador + "/" + frase.length());
			probabilidades.add(probabilidad);

		}

	}

	public void calcularInformacionesPropias() {

		for (int i = 0; i < probabilidades.size(); i++) {

			double infoPropia = Math.log10(Math.pow(probabilidades.get(i), -1)) / Math.log10(2);

			informacionesPropias.add(infoPropia);

		}

	}

	public void calcularEntropia() {

		for (int i = 0; i < informacionesPropias.size(); i++) {

			entropiaMensaje += informacionesPropias.get(i) * probabilidades.get(i);

		}

	}

	public void calcularFrecuencias() {

		for (int i = 0; i < alfabeto.size(); i++) {

			int frecuencia = 0;

			for (int j = 0; j < frase.length(); j++) {

				if (alfabeto.get(i).equals(frase.charAt(j))) {

					frecuencia++;

				}

			}

			frecuencias.add(frecuencia);

		}

	}

	public ArrayList<NodoHuffman> llenarFrecuenciasHuffman(ArrayList<Integer> frecuencias) {

		for (int i = 0; i < frecuencias.size(); i++) {

			frecuenciasHuffman.add(new NodoHuffman(frecuencias.get(i)));

		}

		return frecuenciasHuffman;

	}

	public ArrayList<NodoHuffman> huffman() {

		int stop = frecuencias.size() * 2 - 1;

		Collections.sort(frecuencias);

		llenarFrecuenciasHuffman(frecuencias);

		return huffman(stop, frecuenciasHuffman);

	}

	private ArrayList<NodoHuffman> huffman(int stop, ArrayList<NodoHuffman> nodosHuffman) {

		if (nodosHuffman.size() == stop) {

			frecuenciasHuffman = nodosHuffman;
			return nodosHuffman;

		} else {

			NodoHuffman menorUno = null;
			NodoHuffman menorDos = null;
			boolean auxStopUno = false;
			boolean auxStopDos = false;

			for (int i = 0; i < nodosHuffman.size() && !auxStopUno; i++) {

				if (!nodosHuffman.get(i).isUsed()) {

					menorUno = nodosHuffman.get(i);
					nodosHuffman.get(i).setUsed(true);
					auxStopUno = true;
				}
			}

			for (int i = 0; i < nodosHuffman.size() && !auxStopDos; i++) {

				if (!nodosHuffman.get(i).isUsed()) {

					menorDos = nodosHuffman.get(i);
					nodosHuffman.get(i).setUsed(true);
					auxStopDos = true;

				}

			}

			nodosHuffman.add(new NodoHuffman(menorUno, menorDos));
			Collections.sort(nodosHuffman);

			return huffman(stop, nodosHuffman);

		}
	}

	// Lee al reves el binario que consigue.
	public String getRealBinary(String binary) {

		String realBinary = "";

		for (int i = binary.length() - 1; i >= 0; i--) {

			realBinary += binary.charAt(i);

		}

		return realBinary;

	}

	public String getBinary(NodoHuffman nodo) {

		String binary = "";

		while (nodo != null) {

			if (nodo.getValorBinaryGo() != null)
				binary += nodo.getValorBinaryGo();

			nodo = nodo.getGo();

		}

		return binary;

	}

	public String getHuffmanBinaryCodes() {

		String binaryCodes = "";

		for (int i = frecuenciasHuffman.size() - 1; i >= 0; i--) {

			if (frecuenciasHuffman.get(i).isSencillo()) {

				binaryCodes += frecuenciasHuffman.get(i).getValor() + " ---> "
						+ getRealBinary(getBinary(frecuenciasHuffman.get(i))) + "\n";

			}

		}

		return binaryCodes;

	}

	public ArrayList<NodoHuffman> getFrecuenciasHuffman() {
		return frecuenciasHuffman;
	}

	public void setFrecuenciasHuffman(ArrayList<NodoHuffman> frecuenciasHuffman) {
		this.frecuenciasHuffman = frecuenciasHuffman;
	}

	@Override
	public String toString() {

		return "Mensaje: " + frase + "\n" + "Length mensaje: " + frase.length() + "\n" + "Alfabeto -> :" + alfabeto
				+ "\n" + "Length Alfabeto: " + alfabeto.size() + "\n" + "Probabilidades: " + probabilidades + "\n"
				+ "Probabilidades en fraccion: " + probabilidadesString + "\n" + "Informaciones Propias: "
				+ informacionesPropias + "\n" + "La entropia del mensaje es: " + String.format("%.5f", entropiaMensaje)
				+ "\n" + infoOrganizada + "\n" + "Codigos Binarios con el arbol de codificacion de Huffman " + "\n"
				+ getHuffmanBinaryCodes() + "\n" + "By: JuanchoVelezPro";

	}

	public static void main(String[] args) {

		Codificacion code = new Codificacion("Pepper Clemens sent the messenger.");
		System.out.println(code.toString());

	}

}
