package model;

import java.util.Comparator;

public class NodoHuffman implements Comparator<NodoHuffman>, Comparable<NodoHuffman> {

	private boolean used;
	private int valor;
	private NodoHuffman izq;
	private NodoHuffman der;
	private NodoHuffman go;
	private Integer valorBinaryGo;
	private boolean sencillo;

	// Constructor para los nodos del comienzo.
	public NodoHuffman(int valor) {

		this.valor = valor;
		izq = null;
		der = null;
		used = false;
		valorBinaryGo = null;
		sencillo = true;

	}

	// Constructor para los nodos compuestos
	public NodoHuffman(NodoHuffman uno, NodoHuffman dos) {

		valor = uno.valor + dos.valor;
		izq = dos;
		der = uno;
		used = false;
		sencillo = false;
		valorBinaryGo = null;
		izq.go = this;
		der.go = this;

		izq.valorBinaryGo = 0;
		der.valorBinaryGo = 1;

	}

	public boolean isSencillo() {
		return sencillo;
	}

	public void setSencillo(boolean sencillo) {
		this.sencillo = sencillo;
	}

	public NodoHuffman getGo() {
		return go;
	}

	public void setGo(NodoHuffman go) {
		this.go = go;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public NodoHuffman getIzq() {
		return izq;
	}

	public void setIzq(NodoHuffman izq) {
		this.izq = izq;
	}

	public NodoHuffman getDer() {
		return der;
	}

	public void setDer(NodoHuffman der) {
		this.der = der;
	}

	@Override
	public String toString() {

		return valor + "";

	}

	@Override
	public int compare(NodoHuffman o1, NodoHuffman o2) {
		if (o1.getValor() >= o2.getValor()) {

			return 1;

		} else {

			return -1;

		}
	}

	public Integer getValorBinaryGo() {
		return valorBinaryGo;
	}

	public void setValorBinaryGo(Integer valorBinaryGo) {
		this.valorBinaryGo = valorBinaryGo;
	}

	@Override
	public int compareTo(NodoHuffman o) {
		if (this.valor >= o.getValor()) {

			return 1;

		} else {
			return -1;
		}
	}

}
