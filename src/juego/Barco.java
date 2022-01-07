package juego;

public class Barco {

	private int casillas, // tamaño del barco
			tocadas; // horizontal dcha (00), horizontal izquireda (01) o vertical arriba (10),
								// vertical abajo (11)
	private String tipo;
	private String arrayPosiciones[];
	private int filaInicial;
	private int columnaInicial;
	private int contadorCasillas;
	private String id;
	private int posicion;

	public Barco(int filaIni, int columnaIni, int casillas, String tipo, String id) {
		this.filaInicial = filaIni;
		this.columnaInicial = columnaIni;
		this.casillas = casillas;
		this.tocadas = 0;
		this.tipo = tipo;
		this.arrayPosiciones = new String[casillas];
		this.contadorCasillas = 0;
		//this.posicion = posicion;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addCasilla(String num) {
		this.arrayPosiciones[this.contadorCasillas] = num;
		this.contadorCasillas++;
	}

	public void tocado() {
		this.tocadas++;
	}

	public String toString() {

		
		String posiciones = "";
		for (int i = 0; i < this.casillas; i++) {
			posiciones = posiciones + this.arrayPosiciones[i] + ",";
		}
		return "Barco: " + this.getId() + "- " + tipo + "\n Tamaño: " + casillas 
				+ "\n Posiciones asiganadas: " + posiciones;
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

	public String[] getArrayPosiciones() {
		return arrayPosiciones;
	}

	public void setArrayPosiciones(String[] arrayPosiciones) {
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
