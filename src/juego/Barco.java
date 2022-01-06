package juego;

public class Barco {

	private int 
	casillas, //tamaño del barco
	tocadas, 
	posicion; //horizontal dcha (00), horizontal izquireda (01) o vertical arriba (10), vertical abajo (11)
	private String tipo;
	private int arrayPosiciones [];
	private int filaInicial;
	private int columnaInicial;
	private int contadorCasillas;
	
	public Barco(int filaIni,int columnaIni, 
			int casillas, String tipo, int posicion) {
		this.filaInicial=filaIni;
		this.columnaInicial=columnaIni;
		this.casillas=casillas;
		this.tocadas=0;
		this.tipo=tipo;
		this.arrayPosiciones = new int [casillas];
		this.contadorCasillas=0;
		this.posicion=posicion;
		}
	public void addCasilla(int num) {
		this.arrayPosiciones[this.contadorCasillas]=num;
		this.contadorCasillas++;
	}
	public void tocado() {
		this.tocadas++;
	}
	public String toString() {
		String posi="";
		if(posicion==11 || posicion==10) {
			posi="vertical";
		}else if (posicion ==00 || posicion ==01) {
			posi = "horizontal";
		}else if(posicion ==2) {
			posi ="tamaño 1";
		}
		String posiciones="";
		for(int i =0;i<this.casillas;i++) {
			posiciones=posiciones + this.arrayPosiciones[i]+ ",";
		}
		return "Barco: "+tipo+"\n Tamaño: "+casillas + "\n Posición: "+posi+"\n Posiciones asiganadas: "+ posiciones;
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
