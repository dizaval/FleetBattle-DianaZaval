package juego;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {

	private int [][] tablero;
	private int numFilas , numColumnas;
	private ArrayList<Barco> listaBarcos;
	
	public Tablero(int filas, int col) {
		this.tablero = new int [filas][col];
		this.numFilas=filas;
		this.numColumnas=col;
		this.listaBarcos=new ArrayList();
		for(int i = 0; i<filas; i++) {
			for(int j=0; j<col; j++ ) {
				tablero[i][j]=0;
			}
		}
	}
	public void colocarBarcosOrdenador() {
//---------------un barco de tamaño 4 (portaaviones)-------------------
		 Random r = new Random();
		 int x= r.nextInt(10);
		 int y = r.nextInt(10);
		 Barco b1= new Barco (x,y,4, "portaaviones");
		 colcaBarcoEnTablero(b1); //se ponen a 1 las ocupadas.
		 this.listaBarcos.add(b1);
		 
//--------------dos barcos de tamaño 3 (acorazados)--------------
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);
			 if((x-2>=0) && (x+2 < numFilas) && (y-2>=0) && (y+2 <numColumnas)) {
				 if((this.tablero[x+2][y+2]==1)||(this.tablero[x-2][y-2]==1)) {
					 x= r.nextInt(10);
					 y = r.nextInt(10);
				 }
			 }			 
		 }
		 	//ahora cabe en cualquier posicion.
		 Barco b2= new Barco (x,y,3, "acorazado");
		 colcaBarcoEnTablero(b2);
		 this.listaBarcos.add(b2);
		 
		 
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);
			 if((x-2>=0) && (x+2 < numFilas) && (y-2>=0) && (y+2 <numColumnas)) {
				 if((this.tablero[x+2][y+2]==1)||(this.tablero[x-2][y-2]==1)) {
					 x= r.nextInt(10);
					 y = r.nextInt(10);
				 }
			 }			 
		 }
		 	//ahora cabe en cualquier posicion.
		 Barco b3= new Barco (x,y,3, "acorazado");
		 colcaBarcoEnTablero(b3);
		 this.listaBarcos.add(b3);
		 
//-------------------dos barcos de tamaño 2 (buques)---------------------
		 
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);
			 if((x-1>=0) && (x+1 < numFilas) && (y-1>=0) && (y+1 <numColumnas)) {
				 if((this.tablero[x+1][y+1]==1)||(this.tablero[x-1][y-1]==1)) {
					 x= r.nextInt(10);
					 y = r.nextInt(10);
				 }
			 }			 
		 }
		 	//ahora cabe en cualquier posicion.
		 Barco b4= new Barco (x,y,2, "buque");
		 colcaBarcoEnTablero(b4);
		 this.listaBarcos.add(b4);
		 
		 
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);
			 if((x-1>=0) && (x+1 < numFilas) && (y-1>=0) && (y+1 <numColumnas)) {
				 if((this.tablero[x+1][y+1]==1)||(this.tablero[x-1][y-1]==1)) {
					 x= r.nextInt(10);
					 y = r.nextInt(10);
				 }
			 }			 
		 }
		 	//ahora cabe en cualquier posicion.
		 Barco b5= new Barco (x,y,2, "buque");
		 colcaBarcoEnTablero(b5);
		 this.listaBarcos.add(b5);
//-------------------------un barco de tamaño 1 (submarinos)----------------------
		 
		 
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);		 
		 }
		 	//ahora cabe en cualquier posicion.
		 Barco b6= new Barco (x,y,1, "submarino");
		 colcaBarcoEnTablero(b6);
		 this.listaBarcos.add(b6);
	}
	public void colcaBarcoEnTablero(Barco b) {
		//asignar casillas del tablero poniendolas a 1.
		int xInicial = b.getFilaInicial();
		int yInicial =b.getColumnaInicial();
		this.tablero[xInicial][yInicial]=1;
		int tam=b.getCasillas();
		Random r = new Random();
		int pos = r.nextInt(2); //0 o 1
		int contador=yInicial;
		b.addCasilla(xInicial*10+yInicial);
		if(pos==0) { //horizontal
			for(int i =0;i<tam;i++) {
				contador++;
				this.tablero[xInicial][contador]=1;
				b.addCasilla(xInicial*10+contador);
			}
		}else{ //vertical
			contador=xInicial;
			for(int i =0;i<tam;i++) {
				contador++;
				this.tablero[contador][yInicial]=1;
				b.addCasilla(contador*10+yInicial);
			}
		}
	}
	public void colocarBarcosJugador(int [][] tablero) {
		//lo elige manualmente desde la interfaz.
		this.tablero= tablero;
	}
	public int recibeDisparo(int x, int y) { //0-> agua; 1-> tocado; 2->tocado y hundido; -1 -> error
		//identificar a qué barco pertenece esa posición.
		if(this.tablero[x][y]==0) {
			return 0; //agua
		}
		if(this.tablero[x][y]==1) {
			Barco b;
			for(int i =0; i<this.listaBarcos.size();i++) {
				b= this.listaBarcos.get(i);
				int posi []=b.getArrayPosiciones();
				for(int j =0;j<posi.length;j++) {
					if(posi[j]==x*10+y) {
						b.tocado();
						if(b.getTocadas()==b.getCasillas()) {
							return 2; //tocado y hundido
						}if(b.getTocadas()<b.getCasillas()) { //tocado 
							return 1;
						}
					}
				}
				
			}
		}
		return -1;
	}
	public void visualizar() {
		for(int i = 0; i<this.numFilas; i++) {
			System.out.print("|");
			for(int j=0; j<this.numColumnas; j++ ) {
				System.out.println(tablero[i][j]);
				if(j!=this.tablero[i].length-1) System.out.print("\t");
			}
			System.out.println("|");
		}
	}
	
}
