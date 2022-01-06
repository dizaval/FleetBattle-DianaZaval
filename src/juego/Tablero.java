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
	public int buscaPosicion(int casillas, int x, int y, int posicion) {
		if(posicion ==0) {
			if(y+casillas<numColumnas || y-casillas>=0) { //horizontal

				 if(y+casillas<numColumnas) {
					 posicion=00;
				 }else {
					 posicion=01;
				 }
				 
			 }
		}else if(posicion ==1) {
			if(x+casillas<numFilas|| x-casillas>=0) { //vertical

				 if(x+casillas<numFilas) {
					 posicion=11;
				 }else {
					 posicion=10;
				 }
			 
			 }
		}
		
		return posicion;
	}
	public void colocarBarcosOrdenador() {
//---------------un barco de tamaño 4 (portaaviones)-------------------
		 Random r = new Random();
		 int x= r.nextInt(10);
		 int y = r.nextInt(10);
		 int posicion=0;
		 int posia = r.nextInt(2);
		 posicion=buscaPosicion(3,x,y,posia);


		 while(posicion!=01 && posicion !=00 && posicion!=10 && posicion!=11) {
			  x= r.nextInt(10);
			  y = r.nextInt(10);
			 posicion=buscaPosicion(3,x,y,posicion);
		 }

		 Barco b1= new Barco (x,y,4, "portaaviones", posicion);
		 colcaBarcoEnTablero(b1); //se ponen a 1 las ocupadas.
		 this.listaBarcos.add(b1);
	
	 
//--------------dos barcos de tamaño 3 (acorazados)--------------
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 posia = r.nextInt(2);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);	 
		 }
		 posicion=buscaPosicion(3,x,y,posia);
		 while(posicion!=01 && posicion !=00 && posicion!=10 && posicion!=11) {
			  x= r.nextInt(10);
			  y = r.nextInt(10);
			 posicion=buscaPosicion(3,x,y,posicion);
		 }
		 Barco b2= new Barco (x,y,3, "acorazado", posicion);
		 colcaBarcoEnTablero(b2);
		 this.listaBarcos.add(b2);
		 
		 
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 posia = r.nextInt(2);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);	 
		 }
		 posicion=buscaPosicion(3,x,y,posia);
		 while(posicion!=01 && posicion !=00 && posicion!=10 && posicion!=11) {
			  x= r.nextInt(10);
			  y = r.nextInt(10);
			 posicion=buscaPosicion(3,x,y,posicion);
		 }
		 Barco b3= new Barco (x,y,3, "acorazado", posicion);
		 colcaBarcoEnTablero(b3);
		 this.listaBarcos.add(b3);
		 
//-------------------dos barcos de tamaño 2 (buques)---------------------
		 
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 posia = r.nextInt(2);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);	 
		 }
		 posicion=buscaPosicion(2,x,y,posia);
		 while(posicion!=01 && posicion !=00 && posicion!=10 && posicion!=11) {
			  x= r.nextInt(10);
			  y = r.nextInt(10);
			 posicion=buscaPosicion(2,x,y,posicion);
		 }

		 Barco b4= new Barco (x,y,2, "buque", posicion);
		 colcaBarcoEnTablero(b4);
		 this.listaBarcos.add(b4);
		 
		 
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 posia = r.nextInt(2);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);	 
		 }
		 posicion=buscaPosicion(2,x,y,posia);
		 while(posicion!=01 && posicion !=00 && posicion!=10 && posicion!=11) {
			  x= r.nextInt(10);
			  y = r.nextInt(10);
			 posicion=buscaPosicion(2,x,y,posicion);
		 }

		 Barco b5= new Barco (x,y,2, "buque", posicion);
		 colcaBarcoEnTablero(b5);
		 this.listaBarcos.add(b5);
//-------------------------un barco de tamaño 1 (submarinos)----------------------
		 
		 
		 x= r.nextInt(10);
		 y = r.nextInt(10);
		 while(this.tablero[x][y]==1) {
			 x= r.nextInt(10);
			 y = r.nextInt(10);		 
		 }
		 posicion=2;

		 Barco b6= new Barco (x,y,1, "submarino", posicion);
		 colcaBarcoEnTablero(b6);
		 this.listaBarcos.add(b6);
	}

	public void colcaBarcoEnTablero(Barco b) {
		//asignar casillas del tablero poniendolas a 1.
		int xInicial = b.getFilaInicial();
		int yInicial =b.getColumnaInicial();
		this.tablero[xInicial][yInicial]=1;
		int tam=b.getCasillas();
		int pos=b.getPosicion();	
		int contador=yInicial;
		b.addCasilla(xInicial*10+yInicial);
		if(pos==00) { //horizontal dcha
			for(int i =0;i<tam-1;i++) {
				contador++;
				this.tablero[xInicial][contador]=1;
				b.addCasilla(xInicial*10+contador);
			}
		}else if(pos==01){ //horizontal izda
			for(int i =0;i<tam-1;i++) {
				contador--;
				this.tablero[xInicial][contador]=1;
				b.addCasilla(xInicial*10+contador);
			}
		}else if(pos==10) { //vertical arriba
			contador=xInicial;
			for(int i =0;i<tam-1;i++) {
				contador--;
				this.tablero[contador][yInicial]=1;
				b.addCasilla(contador*10+yInicial);
			}
		}else if(pos==11){ //vertical abajo
			contador=xInicial;
			for(int i =0;i<tam-1;i++) {
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
	public String muestraBarcos() {
		String s="";
		for(int i = 0; i<this.listaBarcos.size();i++) {
			s =s+this.listaBarcos.get(i).toString();
			s=s+"\n";
		}
		return s;
	}
	public String  toString() {
		String res="";
		for(int i=0;i<this.numFilas;i++) {
			for(int j=0;j<this.numColumnas;j++) {
				res=res+this.tablero[i][j];
			}
			res=res+"\n";
		}
		return res;
	}
	
}
