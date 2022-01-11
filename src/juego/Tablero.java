package juego;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {

	private String[][] tablero;
	private int numFilas, numColumnas;
	private ArrayList<Barco> listaBarcos;
	private int barcosHundidos;

	public Tablero(int filas, int col) {
		this.tablero = new String[filas][col];
		this.numFilas = filas;
		this.numColumnas = col;
		this.listaBarcos = new ArrayList();
		this.barcosHundidos = 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < col; j++) {
				tablero[i][j] = "0";
			}
		}
	}
/**
 * Devuelve falso si la posición no está ocupada por otro barco, y verdadero en caso contrario.
 */
	public boolean ocupado(int x, int y) {
		if (!this.tablero[x][y].equals("0")) {
			return true;
		} else {
			return false;
		}
	}
/**
 * Añade un barco más a la lista de barcos del tablero
 * @param b es el barco que se quiere añadir
 */
	public void anadirBarco(Barco b) {
		this.listaBarcos.add(b);
	}
/**
 * Busca una posición disponible en el tablero según el tamaño del barco y las casillas iniciales del barco.
 * @param casillas
 * @param x
 * @param y
 * @param posicion
 * @return un numero que representa la posición. Si devuelve 0, la posición es horizontal hacia la derecha. 
 * Si devuelve 1, la posición es horizontal hacia la izquierda
 * Si devuelve 10, la posición es vertical hacia arriba
 * Si devuelve 11 la posición es vertical hacia abajo
 */
	public int buscaPosicion(int casillas, int x, int y, int posicion) {
		if (posicion == 0) {
			if (y + casillas < numColumnas || y - casillas >= 0) { // horizontal

				if (y + casillas < numColumnas) {
					posicion = 00;
				} else {
					posicion = 01;
				}

			}
		} else if (posicion == 1) {
			if (x + casillas < numFilas || x - casillas >= 0) { // vertical

				if (x + casillas < numFilas) {
					posicion = 11;
				} else {
					posicion = 10;
				}

			}
		}

		return posicion;
	}
/**
 * Coloca los 6 barcos del ordenador de forma aleatoria en el tablero, buscando posiciones disponibles para cada uno de ellos.
 */
	public void colocarBarcosOrdenador() {
//---------------un barco de tamaño 4 (portaaviones)-------------------
		Random r = new Random();
		int x = r.nextInt(10);
		int y = r.nextInt(10);
		int posicion = 0;
		int posia = r.nextInt(2);
		posicion = buscaPosicion(3, x, y, posia);

		while (posicion != 01 && posicion != 00 && posicion != 10 && posicion != 11) {
			x = r.nextInt(10);
			y = r.nextInt(10);
			posicion = buscaPosicion(3, x, y, posicion);
		}

		Barco b1 = new Barco(x, y, 4, "portaaviones", "b1");
		b1.setPosicion(posicion);
		colcaBarcoEnTablero(b1); // se ponen a 1 las ocupadas.
		this.listaBarcos.add(b1);

//--------------dos barcos de tamaño 3 (acorazados)--------------
		x = r.nextInt(10);
		y = r.nextInt(10);
		posia = r.nextInt(2);
		while (!this.tablero[x][y].equals("0")) {
			x = r.nextInt(10);
			y = r.nextInt(10);
		}
		posicion = buscaPosicion(3, x, y, posia);
		while (posicion != 01 && posicion != 00 && posicion != 10 && posicion != 11) {
			x = r.nextInt(10);
			y = r.nextInt(10);
			posicion = buscaPosicion(3, x, y, posicion);
		}
		Barco b2 = new Barco(x, y, 3, "acorazado", "b2");
		b2.setPosicion(posicion);
		colcaBarcoEnTablero(b2);
		this.listaBarcos.add(b2);

		x = r.nextInt(10);
		y = r.nextInt(10);
		posia = r.nextInt(2);
		while (!this.tablero[x][y].equals("0")) {
			x = r.nextInt(10);
			y = r.nextInt(10);
		}
		posicion = buscaPosicion(3, x, y, posia);
		while (posicion != 01 && posicion != 00 && posicion != 10 && posicion != 11) {
			x = r.nextInt(10);
			y = r.nextInt(10);
			posicion = buscaPosicion(3, x, y, posicion);
		}
		Barco b3 = new Barco(x, y, 3, "acorazado", "b3");
		b3.setPosicion(posicion);
		colcaBarcoEnTablero(b3);
		this.listaBarcos.add(b3);

//-------------------dos barcos de tamaño 2 (buques)---------------------

		x = r.nextInt(10);
		y = r.nextInt(10);
		posia = r.nextInt(2);
		while (!this.tablero[x][y].equals("0")) {
			x = r.nextInt(10);
			y = r.nextInt(10);
		}
		posicion = buscaPosicion(2, x, y, posia);
		while (posicion != 01 && posicion != 00 && posicion != 10 && posicion != 11) {
			x = r.nextInt(10);
			y = r.nextInt(10);
			posicion = buscaPosicion(2, x, y, posicion);
		}

		Barco b4 = new Barco(x, y, 2, "buque", "b4");
		b4.setPosicion(posicion);
		colcaBarcoEnTablero(b4);
		this.listaBarcos.add(b4);

		x = r.nextInt(10);
		y = r.nextInt(10);
		posia = r.nextInt(2);
		while (!this.tablero[x][y].equals("0")) {
			x = r.nextInt(10);
			y = r.nextInt(10);
		}
		posicion = buscaPosicion(2, x, y, posia);
		while (posicion != 01 && posicion != 00 && posicion != 10 && posicion != 11) {
			x = r.nextInt(10);
			y = r.nextInt(10);
			posicion = buscaPosicion(2, x, y, posicion);
		}

		Barco b5 = new Barco(x, y, 2, "buque", "b5");
		b5.setPosicion(posicion);
		colcaBarcoEnTablero(b5);
		this.listaBarcos.add(b5);
//-------------------------un barco de tamaño 1 (submarinos)----------------------

		x = r.nextInt(10);
		y = r.nextInt(10);
		while (!this.tablero[x][y].equals("0")) {
			x = r.nextInt(10);
			y = r.nextInt(10);
		}
		posicion = 2;

		Barco b6 = new Barco(x, y, 1, "submarino", "b6");
		b6.setPosicion(posicion);
		colcaBarcoEnTablero(b6);
		this.listaBarcos.add(b6);
	}
/**
 * Coloca en la posición indicada el nombre del barco que está colocado y añade el número de casilla correspondiente al barco.
 * @param x número de fila
 * @param y número de columna
 * @param b barco que se coloca en esa posición
 */
	public void setPosicion(int x, int y, Barco b) {
		this.tablero[x][y] = b.getId();
		int xMy = x * 10 + y;
		String c = Integer.toString(xMy);
		b.addCasilla(c);
	}
/**
 * Coloca el barco pasado como parámetro en el tablero, dependiendo si es horizontal,vertical y de la dirección en la que va.
 * Llama al método setPosicion de esta clase para cada posicion de cada barco colocado.
 * @param b
 */
	public void colcaBarcoEnTablero(Barco b) {
		int xInicial = b.getFilaInicial();
		int yInicial = b.getColumnaInicial();

		int tam = b.getCasillas();
		int pos = b.getPosicion();
		int contador = yInicial;

		setPosicion(xInicial, yInicial, b);

		if (pos == 00) { // horizontal dcha
			for (int i = 0; i < tam - 1; i++) {
				contador++;
				setPosicion(xInicial, contador, b);

			}
		} else if (pos == 01) { // horizontal izda
			for (int i = 0; i < tam - 1; i++) {
				contador--;
				setPosicion(xInicial, contador, b);
				
			}
		} else if (pos == 10) { // vertical arriba
			contador = xInicial;
			for (int i = 0; i < tam - 1; i++) {
				contador--;
				setPosicion(contador, yInicial, b);
				
			}
		} else if (pos == 11) { // vertical abajo
			contador = xInicial;
			for (int i = 0; i < tam - 1; i++) {
				contador++;
				setPosicion(contador, yInicial, b);
				
			}
		}
	}
/**
 * Indica si todos los barcos de un tablero han sido hundidos.
 * @return devuelve true si todos los barcos han sido hundidos y false en caso contrario.
 */
	public boolean todosTocados() {
		if (this.barcosHundidos == this.listaBarcos.size()) {
			return true;
		} else {
			return false;
		}
	}
/**
 * Una posición del tablero recibe un disparo y se cambia el tablero para indicar el resultado del disparo.
 * @param x numero de fila
 * @param y numero de columna
 * @return devuelve 0 si en la posición no habís ningún barco colocado.
 * devuelve 1 si en la posición había un barco colocado y aún no se han tocado todas las posiciones del barco.
 * devuelve 2 si en la posición había un barco colocado y sí se han tocado todas las posiciones del barco.
 */
	public int recibeDisparo(int x, int y) { // 0-> agua; 1-> tocado; 2->tocado y hundido; -1 -> error
		if (this.tablero[x][y].equals("0")) {
			this.tablero[x][y] = "A";
			return 0; // agua
		}
		if (!this.tablero[x][y].equals("0") && !this.tablero[x][y].equals("A") && !this.tablero[x][y].equals("[]")) {
			Barco b;
			int posicion;
			String posicionS = "";
			this.tablero[x][y] = "[]";
			for (int i = 0; i < this.listaBarcos.size(); i++) {
				b = this.listaBarcos.get(i);
				String posi[] = b.getArrayPosiciones();
				for (int j = 0; j < posi.length; j++) {
					posicion = x * 10 + y;
					posicionS = Integer.toString(posicion);
					if (posi[j].equals(posicionS)) {
						b.tocado();

						if (b.getTocadas() == b.getCasillas()) {
							this.barcosHundidos++;
							return 2; // tocado y hundido
						}
						if (b.getTocadas() < b.getCasillas()) { // tocado
							return 1;
						}
					}
				}

			}
		}
		return -1;
	}

/**
 * 
 * @return
 */
	public String toString() {
		String res = "";
		for (int i = 0; i < this.numFilas; i++) {
			for (int j = 0; j < this.numColumnas; j++) {
				res = res + this.tablero[i][j] + "\t";
			}
			res = res + "\n";
		}
		return res;
	}
/**
 * 
 * @return
 */
	public String toStringSoloTocadas() {
		String res = "";
		String t = "";
		for (int i = 0; i < this.numFilas; i++) {
			for (int j = 0; j < this.numColumnas; j++) {
				if (this.tablero[i][j].equals("[]")) {
					t = "[]";
				} else if (this.tablero[i][j].equals("A")) {
					t = "A";
				} else {
					t = "0";
				}
				res = res + t + "\t";
			}
			res = res + "\n";
		}
		return res;
	}
	
	/***************************GETTERS Y SETTERS***************************/
	
	public String[][] getTablero() {
		return tablero;
	}

	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}

	public ArrayList<Barco> getListaBarcos() {
		return listaBarcos;
	}

	public void setListaBarcos(ArrayList<Barco> listaBarcos) {
		this.listaBarcos = listaBarcos;
	}

	public int getBarcosHundidos() {
		return barcosHundidos;
	}

	public void setBarcosHundidos(int barcosHundidos) {
		this.barcosHundidos = barcosHundidos;
	}

	

}
