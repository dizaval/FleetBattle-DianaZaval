package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jugador.Jugador;

public class Servidor {
	private static Jugador persona;
	private static Jugador ordenador;

	public static void main(String[] args) {

		persona = new Jugador("p");
		ordenador = new Jugador("o");

		ExecutorService pool = Executors.newCachedThreadPool();
		try (ServerSocket server = new ServerSocket(6666)) {

			while (true) {
				try {
					Socket s = server.accept();
					Gestiona g = new Gestiona(s, persona, ordenador);
					pool.execute(g);
				}

				catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Jugador encontrarJugador(String id) {
		if (persona.getId().equals(id)) {
			return persona;
		} else {
			return ordenador;
		}
	}

}
