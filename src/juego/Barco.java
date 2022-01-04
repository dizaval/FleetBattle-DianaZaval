package juego;

public class Barco {

	private int 
	casillas, //tamaño del barco
	tocadas, 
	posicion; //horizontal (1) o vertical(2)
	private String tipo;
	private int arrayPosiciones [];
	private int filaInicial;
	private int columnaInicial;
	
	public Barco(int filaIni,int columnaIni, 
			int casillas,int posicion, String tipo) {
		this.filaInicial=filaIni;
		this.columnaInicial=columnaIni;
		this.casillas=casillas;
		this.tocadas=0;
		this.posicion=posicion;
		this.tipo=tipo;
		this.arrayPosiciones = new int [casillas];
		}
	
	

	public void tocado() {
		this.tocadas++;
	}
	public String toString() {
		return "Barco: "+tipo+"\n Tamaño: "+casillas + "\n Posición: "+posicion;
	}



	public int getCasillas() {
		return casillas;
	}



	public void setCasillas(int casillas) {
		this.casillas = casillas;
	}



	public int getTocadas() {
		return tocadas;
	}



	public void setTocadas(int tocadas) {
		this.tocadas = tocadas;
	}



	public int getPosicion() {
		return posicion;
	}



	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public int[] getArrayPosiciones() {
		return arrayPosiciones;
	}



	public void setArrayPosiciones(int[] arrayPosiciones) {
		this.arrayPosiciones = arrayPosiciones;
	}



	public int getFilaInicial() {
		return filaInicial;
	}



	public void setFilaInicial(int filaInicial) {
		this.filaInicial = filaInicial;
	}



	public int getColumnaInicial() {
		return columnaInicial;
	}



	public void setColumnaInicial(int columnaInicial) {
		this.columnaInicial = columnaInicial;
	}

	
}
