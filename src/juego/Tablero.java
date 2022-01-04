package juego;

import java.util.Random;

public class Tablero {

	private int [][] tablero;
	private int numFilas , numColumnas;
	
	public Tablero(int filas, int col) {
		this.tablero = new int [filas][col];
		for(int i = 0; i<filas; i++) {
			for(int j=0; j<col; j++ ) {
				tablero[i][j]=0;
			}
		}
	}
	public void colocarBarcosOrdenador() {
		//un barco de tamaño 4 (portaaviones)
		 Random r = new Random();
		 int x= r.nextInt();
		 int y = r.nextInt();
		 int posicion = r.nextInt();
		 Barco b1= new Barco (x,y,4, posicion, "portaaviones");
		 colcaBarcoEnTablero(b1);
		//dos barcos de tamaño 3 (acorazados)
		 
		//tres barcos de tamaño 2 (buques)
		//cuatro barcos de tamaño 1 (submarinos)
	}
	public void colcaBarcoEnTablero(Barco b) {
		//asignar casillas del tablero.
	}
	public void colocarBarcosJugador(int [][] tablero) {
		//lo elige manualmente desde la interfaz.
		this.tablero= tablero;
	}
	public void recibeDisparo() {
		
	}
	
}
