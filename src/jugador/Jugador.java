package jugador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import juego.Tablero;


public class Jugador {

	private String id;
	private Tablero tablero;
	private String host = "localhost";

	public Jugador(String id) {
		this.id = id;
	}

	/**
	 * Lanza un mensaje al servidor para saber si un determinado jugador ha ganado
	 * 
	 * @return devuelve true si el jugador ha ganado y false en caso contrario.
	 */
	public boolean gana() {
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {
			out.writeBytes("GANA " + this.getId() + "\r\n");
			out.flush();
			String g = in.readLine();
			if (g.startsWith("si")) {
				return true;
			} else {
				return false;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Lanza un mensaje al servidor para disparar en una posición concreta del
	 * tablero
	 * 
	 * @param x número de fila
	 * @param y número de columna
	 * @return después de disparar, devuelve true
	 */
	public boolean dispara(int x, int y) { // DISPARA
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("DISPARA " + this.id + " " + x + " " + y + "\r\n");
			out.flush();
			String res = in.readLine(); // agua, tocado, o hundido
			System.out.println(res + "\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Lanza un mensaje al servidor para mostrar los tableros de todos los jugadores
	 * de la partida. Posteriormente lee el fichero en el que el servidor ha
	 * almacenado el String con los tableros de los 2 jugadores de la partida
	 * escritos y lo saca por pantalla.
	 */
	public void mostrarTableros() { // MUESTRA los dos tableros

		String fichero = "tableros.txt";
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());
				FileReader f = new FileReader(fichero);
				BufferedReader b = new BufferedReader(f);
				FileWriter fw = new FileWriter(fichero, false);
				BufferedWriter bw = new BufferedWriter(fw);) {

			out.writeBytes("MUESTRA " + this.getId() + "\r\n");
			out.flush();
			String res = in.readLine();
			if (res.startsWith("escrito")) {
				String cadena;
				while ((cadena = b.readLine()) != null) {
					System.out.println(cadena);
				}
			}
			bw.write("");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lanza un mensaje al servidor para que el jugador pueda colocar sus barcos
	 * manualmente.
	 * 
	 * @return devuelve true después de haber completado la elección.
	 */
	public boolean elegirPosicionesBarcoPersona() { // ELIGE
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("ELIGE " + this.id + "\r\n");
			out.flush();
			String res = in.readLine();
			if (res.startsWith("ok")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * Lanza un mensaje al servidor para que los barcos del jugador se coloquen de
	 * una manera automática aleatoria.
	 */

	public void posicionesOrdenador() { // PONER
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("PONER " + this.getId());
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***************** SETTERS Y GETTERS *****************/

	public void setTablero(Tablero t) {
		this.tablero = t;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public String getId() {
		return id;
	}

}
