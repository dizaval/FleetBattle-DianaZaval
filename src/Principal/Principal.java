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
			System.out.println("pOSICIONES ordenador acabadas");
			boolean colocados =false;
			while(!colocados) {
				Thread.sleep(1000);
				System.out.println("dormir");
				colocados=persona.elegirPosicionesBarcoPersona();
			}

			System.out.println("pOSICIONES personas acabadas");
			boolean acabadoO = false;
			//acabadoO = ordenador.gana();
			//System.out.println(acabadoO);
			boolean acabadoP = false;
			//System.out.println(acabadoP);
			Scanner sc = new Scanner(System.in);
			Random r = new Random();
			while (!acabadoO && !acabadoP) {
				System.out.println("Introduce coordenada x donde quieras disparar");
				int x = sc.nextInt();
				System.out.println("Introduce coordenada y donde quieras disparar");
				int y = sc.nextInt();
				persona.dispara(x, y); // se dispara y se muestran los tableros
				persona.mostrarTableros();
				x = r.nextInt(10);
				ordenador.dispara(x, y);
				ordenador.mostrarTableros();
				System.out.println("El ordenador ha disparado en: "+x+"-"+y);
				acabadoO = ordenador.gana();
				acabadoP = persona.gana();
			}
			String ganador = "";
			if (acabadoP)
				ganador = "Enhorabuena, has ganado";
			if (acabadoP)
				ganador = "Lo siento, has perdido";

			System.out.println("PARTIDA ACABADA: " + ganador);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
