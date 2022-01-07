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
			System.out.println("metodo gana");
			out.writeBytes("GANA "+ this.getId());
			out.flush();
			String g =in.readLine();
			System.out.println(g);
			if(g.startsWith("si")) {
				return true;
			}else {
				return false;
			}
			
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void dispara(int x, int y) { // DISPARA
		// cada vez que se dispara se muestra el tablero del oponente y el propio.
		// después de disparar el turno pasa al siguiente jugador. (esto desde el
		// principal?)
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("DISPARA " +this.id+" "+ x + " " + y + "\r\n");
			out.flush();
			String res = in.readLine(); // agua, tocado, o hundido
			System.out.println(res);
			//mostrarTableros();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarTableros() { // MUESTRA los dos tableros
		System.out.println("metodo muestra");
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("MUESTRA "+this.getId());
			out.flush();
			String res =in.readLine();
			System.out.println("metodo muestra 2");
			System.out.println(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public boolean elegirPosicionesBarcoPersona() { // ELIGE
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("ELIGE "+this.id+"\r\n");
			out.flush();
			String res=in.readLine();
			if(res.startsWith("ok")) {
				return true;
			}else {
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

	public void posicionesOrdenador() { //PONER
		try (Socket s = new Socket(host, 6666);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				DataInputStream in = new DataInputStream(s.getInputStream());) {

			out.writeBytes("PONER "+this.getId());
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
