package juego;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tablero r = new Tablero(10,10);
		r.colocarBarcosOrdenador();
		String s=r.toString();
		System.out.println(s);
		
		System.out.println(r.muestraBarcos());

	}

}
