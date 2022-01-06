package jugador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
		
	}

}
