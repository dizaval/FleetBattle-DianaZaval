package Principal;

import java.util.Random;
import java.util.Scanner;

import jugador.Jugador;

public class Principal {

	public static void main(String[] args) {
		try {
			Jugador ordenador = new Jugador("o");
			Jugador persona = new Jugador("p");
			ordenador.posicionesOrdenador();
			boolean colocados = false;
			while (!colocados) {
				Thread.sleep(1000);
				colocados = persona.elegirPosicionesBarcoPersona();
			}

			boolean acabadoO = false;
			// acabadoO = ordenador.gana();
			// System.out.println(acabadoO);
			boolean acabadoP = false;
			// System.out.println(acabadoP);
			Scanner sc = new Scanner(System.in);
			Random r = new Random();
			boolean disparado = false;
			while (!acabadoO && !acabadoP) {
				System.out.println("Introduce coordenada x donde quieras disparar");
				int x = sc.nextInt();
				System.out.println("Introduce coordenada y donde quieras disparar");
				int y = sc.nextInt();
				while (!disparado) {
					Thread.sleep(1000);
					System.out.println("dormir");
					disparado = persona.dispara(x, y); // se dispara y se muestran los tableros
				}
				persona.mostrarTableros();
				x = r.nextInt(10);
				y = r.nextInt(10);
				disparado = false;
				while (!disparado) {
					Thread.sleep(1000);
					disparado = ordenador.dispara(x, y); // se dispara y se muestran los tableros
				}
				persona.mostrarTableros();
				System.out.println("El ordenador ha disparado en: " + x + "-" + y);
				acabadoO = ordenador.gana();
				acabadoP = persona.gana();
				disparado = false;
			}
			String ganador = "Enhorabuena, has ganado";

			if (acabadoO)
				ganador = "Lo siento, has perdido";

			System.out.println("PARTIDA ACABADA: " + ganador);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
