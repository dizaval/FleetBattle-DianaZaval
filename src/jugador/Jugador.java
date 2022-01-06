package jugador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import juego.Barco;
import juego.Tablero;

public class Jugador {

	private int id;
	private Tablero tablero;
	private String host = "localhost";

	public Jugador(int id, Tablero r) {
		this.id = id;
		this.tablero = r;
	}

	public int getId() {
		return id;
	}

	public void dispara(int x, int y) { // DISPARA
		// cada vez que se dispara se muestra el tablero del oponente y el propio.
		// después de disparar el turno pasa al siguiente jugador. (esto desde el
		// principal?)
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("DISPARA " + x + " " + y + "\r\n");
			out.flush();
			String res = in.readLine(); // agua, tocado, o hundido
			System.out.println(res);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarTableros() { // MUESTRA los dos tableros
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("MUESTRA");
			String res =in.readLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void elegirPosicionesBarco() { // ELIGE
		ArrayList<Barco> l=this.tablero.getListaBarcos();
		Barco b;
		Scanner sc= new Scanner (System.in);
		String posi="";
		String xy[];
		int x, y;
		for (int i =0; i<l.size();i++) {
			b=l.get(i); //ya tendrá asignada la lista de barcos.
			System.out.println("Elige las posiciones del barco " + b.getId() + "("+b.getTipo()+")");
			int tam =b.getCasillas();
			for(int j=1;j<tam+1;j++) {
				System.out.println("\t"+"Introduce la posición "+ j + " con el formato x-y");
				posi=sc.nextLine();
				xy =posi.split("-");
				try {
					x=Integer.parseInt(xy[0]);
					y=Integer.parseInt(xy[1]);
					this.tablero.setPosicion(x, y, b);
					this.tablero.anadirBarco(b);
				}catch(NumberFormatException e) {
					System.out.println("Formato no válido: Introduce dos numeros separados por -");
				}
				
			}
		}
	}

}
