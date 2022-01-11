package Principal;

import java.util.InputMismatchException;
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
			// persona.asignaPersona();
			// System.out.println(persona.getId());
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
			int x = 0, y = 0;

			while (!acabadoO && !acabadoP) {

				System.out.println("Introduce coordenada X donde quieras disparar (de 0 a 9)");
				x = sc.nextInt();

				while (x < 0 || x > 9) {

					System.out.println("Introduce coordenada X donde quieras disparar (de 0 a 9)");
					x = sc.nextInt();

				}
				System.out.println("Introduce coordenada Y donde quieras disparar (de 0 a 9)");
				y = sc.nextInt();

				while (y < 0 || y > 9) {

					System.out.println("Introduce coordenada Y donde quieras disparar (de 0 a 9)");
					y = sc.nextInt();

				}

				while (!disparado) {
					Thread.sleep(1000);
					disparado = persona.dispara(x, y); // se dispara y se muestran los tableros
				}
				persona.mostrarTableros();
				x = r.nextInt(10);
				y = r.nextInt(10);
				disparado = false;
				System.out.println("\n\tTURNO DEL ORDENADOR\n");
				while (!disparado) {
					Thread.sleep(1000);
					disparado = ordenador.dispara(x, y); // se dispara y se muestran los tableros
				}
				System.out.println("El ordenador ha disparado en: " + x + "-" + y + "\n");
				persona.mostrarTableros();
				acabadoO = ordenador.gana();
				acabadoP = persona.gana();
				disparado = false;

			}

			String ganador = "Lo siento, has perdido";

			if (acabadoO)
				ganador = "Enhorabuena, has ganado";

			System.out.println("PARTIDA ACABADA: " + ganador);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
