package jugador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import juego.Barco;
import juego.Tablero;
import servidor.Servidor;

public class Jugador {

	private String id;
	private Tablero tablero;
	private String host = "localhost";

	public Jugador(String id) {
		this.id = id;
	}

	public void setTablero(Tablero t) {
		this.tablero = t;
	}

	public String getId() {
		return id;
	}

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

	public boolean dispara(int x, int y) { // DISPARA
		// cada vez que se dispara se muestra el tablero del oponente y el propio.
		// después de disparar el turno pasa al siguiente jugador. (esto desde el
		// principal?)
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("DISPARA " + this.id + " " + x + " " + y + "\r\n");
			out.flush();
			String res = in.readLine(); // agua, tocado, o hundido
			System.out.println(res+"\n");
			// mostrarTableros();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

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

	/*public void asignaPersona() {
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("ASIGNAP " + this.id + "\r\n");
			out.flush();
			//String res = in.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
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

	public Tablero getTablero() {
		return tablero;
	}

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

	
}
