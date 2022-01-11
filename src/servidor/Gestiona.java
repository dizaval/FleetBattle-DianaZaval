package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import juego.Barco;
import juego.Tablero;
import jugador.Jugador;

public class Gestiona implements Runnable {

	private Jugador persona;
	private Jugador ordenador;
	private Socket socket;

	/**
	 * Crea un objeto Gestiona que tiene por jugadores aquellos que se han pasado
	 * desde el servidor.
	 * 
	 * @param s
	 * @param persona
	 * @param ordenador
	 */
	public Gestiona(Socket s, Jugador persona, Jugador ordenador) {
		this.persona = persona;
		this.ordenador = ordenador;
		this.socket = s;
	}

	/**
	 * Se encarga de recoger los mensajes que se lanzan al servidor y gestionarlos
	 * según el mensaje recogido.
	 */
	public void run() {
		String fichero = "tableros.txt";
		try (DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
			String peticion = in.readLine();

			// cuando sea ELIGE:
			/**
			 * La persona que juege manualmente podrá elegir la posición de sus 6 barcos.
			 * Las posiciones que se introduzcan variarán de 0 a 9, y la colocación de un
			 * mismo barco se realiza de forma consecutiva.
			 */

			if (peticion.startsWith("ELIGE")) {
				System.out.println("----------BIENVENIDO A FLEET BATTLE----------");
				System.out.println("Esta partida la jugarás contra el ordenador, con un tablero 10x10 y 6 barcos: "
						+ "\t\n" + "- uno de tamaño 4" + "\t\n" + "- dos de tamaño 3" + "\t\n" + "- dos de tamaño 2"
						+ "\t\n" + "- uno de tamaño 1");
				String p[] = peticion.split(" ");
				String id = p[1];
				this.persona = Servidor.encontrarJugador(id);
				Tablero t = new Tablero(10, 10);
				this.persona.setTablero(t);

				ArrayList<Barco> l = new ArrayList<Barco>();
				Barco b1 = new Barco(0, 0, 4, "portaaviones", "b1");
				Barco b2 = new Barco(0, 0, 3, "acorazado", "b2");
				Barco b3 = new Barco(0, 0, 3, "acorazado", "b3");
				Barco b4 = new Barco(0, 0, 2, "buque", "b4");
				Barco b5 = new Barco(0, 0, 2, "buque", "b5");
				Barco b6 = new Barco(0, 0, 1, "submarino", "b6");
				l.add(b1);
				l.add(b2);
				l.add(b3);
				l.add(b4);
				l.add(b5);
				l.add(b6);
				Barco b;
				Scanner sc = new Scanner(System.in);
				String posi = "";
				String xy[];
				int x, y;
				int cont = 1;
				int disparos = 0;
				int xant = 0;
				int yant = 0;
				int dosx = 0;
				int dosy = 0;
				boolean conse = false;
				for (int i = 0; i < l.size(); i++) {
					b = l.get(i);
					System.out.println("Elige las posiciones del barco " + b.getId() + " de tamaño " + b.getCasillas()
							+ " (" + b.getTipo() + ")");
					int tam = b.getCasillas();

					for (int j = 1; j < tam + 1; j++) {

						System.out.println(
								"\t" + "Introduce la posición " + j + " con el formato x-y entre los número 0 y 9");
						posi = sc.nextLine();
						xy = posi.split("-");

						try {
							x = Integer.parseInt(xy[0]);
							y = Integer.parseInt(xy[1]);

							boolean ocupado = this.persona.getTablero().ocupado(x, y);
							while (ocupado) {
								System.out.println(
										"\t" + "POSICION OCUPADA- Introduce la posición " + j + " con el formato x-y");
								posi = sc.nextLine();
								xy = posi.split("-");
								x = Integer.parseInt(xy[0]);
								y = Integer.parseInt(xy[1]);
								ocupado = this.persona.getTablero().ocupado(x, y);
							}

							if (j == 2) {
								while (!conse) {
									if (x == xant) {
										dosx = 1;
										dosy = 0;
										conse = true;
									} else if (y == yant) {
										dosy = 1;
										dosx = 0;
										conse = true;
									} else {
										System.out.println("\t" + "Introduce coordenadas consecutivas por favor");
										posi = sc.nextLine();
										xy = posi.split("-");
										x = Integer.parseInt(xy[0]);
										y = Integer.parseInt(xy[1]);
										ocupado = this.persona.getTablero().ocupado(x, y);
										while (ocupado) {
											System.out.println("\t" + "POSICION OCUPADA- Introduce la posición " + j
													+ " con el formato x-y");
											posi = sc.nextLine();
											xy = posi.split("-");
											x = Integer.parseInt(xy[0]);
											y = Integer.parseInt(xy[1]);
											ocupado = this.persona.getTablero().ocupado(x, y);
										}
										conse = false;
									}
								}

							}

							if (disparos == 1) {

								if (dosx == 1 && dosy == 0) {
									while ((y != yant + 1 && y != yant - 1) || x != xant) {
										System.out.println(
												"\t" + "Introduce coordenadas consecutivas HORIZONTALES por favor");
										posi = sc.nextLine();
										xy = posi.split("-");
										x = Integer.parseInt(xy[0]);
										y = Integer.parseInt(xy[1]);
										ocupado = this.persona.getTablero().ocupado(x, y);
										while (ocupado) {
											System.out.println("\t" + "POSICION OCUPADA- Introduce la posición " + j
													+ " con el formato x-y");
											posi = sc.nextLine();
											xy = posi.split("-");
											x = Integer.parseInt(xy[0]);
											y = Integer.parseInt(xy[1]);
											ocupado = this.persona.getTablero().ocupado(x, y);
										}

									}
								}
								if (dosy == 1 && dosx == 0) {

									while ((x != xant + 1 && x != xant - 1) || y != yant) {

										System.out.println(
												"\t" + "Introduce coordenadas VERTICALES consecutivas por favor");
										posi = sc.nextLine();
										xy = posi.split("-");
										x = Integer.parseInt(xy[0]);
										y = Integer.parseInt(xy[1]);
										ocupado = this.persona.getTablero().ocupado(x, y);
										while (ocupado) {
											System.out.println("\t" + "POSICION OCUPADA- Introduce la posición " + j
													+ " con el formato x-y");
											posi = sc.nextLine();
											xy = posi.split("-");
											x = Integer.parseInt(xy[0]);
											y = Integer.parseInt(xy[1]);
											ocupado = this.persona.getTablero().ocupado(x, y);
										}

									}
								}

							}

							x = Integer.parseInt(xy[0]);
							y = Integer.parseInt(xy[1]);

							cont = 1;
							if (cont == 1) {

								x = Integer.parseInt(xy[0]);
								y = Integer.parseInt(xy[1]);

								this.persona.getTablero().setPosicion(x, y, b);
								this.persona.getTablero().anadirBarco(b);
								System.out.println(persona.getTablero().toString());
							}
							xant = x;
							yant = y;
							disparos = 1;

						} catch (NumberFormatException e) {
							System.out.println("\t" + "Formato no válido: Introduce dos numeros separados por -");
							cont = 0;
						} catch (IndexOutOfBoundsException e) {
							System.out.println("\t" + "ERROR- (Número menor que 0 o mayor que 9)");
							cont = 0;
						}
						if (cont == 0) {
							j--;
						}
					}
					disparos = 0;
					dosx = 0;
					dosy = 0;
					conse = false;
				}
				out.writeBytes("ok\r\n");
				out.flush();

			}

			// cuando sea PONER:
			/**
			 * Se utiliza cuando se quieran elegir de manera automatica y aleatoria las
			 * posiciones de los barcos en el tablero. (En el caso de que el jugador sea un
			 * ordenador)
			 */
			if (peticion.startsWith("PONER")) {
				String p[] = peticion.split(" ");
				String id = p[1];
				this.ordenador = Servidor.encontrarJugador(id);
				Tablero t = new Tablero(10, 10);
				this.ordenador.setTablero(t);
				this.ordenador.getTablero().colocarBarcosOrdenador();
			}

			/**
			 * Devuelve un mensaje diciendo "si" al cliente (jugador) si resulta que ha
			 * ganado la partida, y diciendo "no" en caso contrario.
			 */
			// cuando sea GANA:
			else if (peticion.startsWith("GANA")) {
				String p[] = peticion.split(" ");
				String id = p[1];
				if (Servidor.encontrarJugador(id).getTablero().todosTocados()) {
					out.writeBytes("si" + "\r\n");
					out.flush();
				} else {
					out.writeBytes("no" + "\r\n");
					out.flush();
				}
			}
			/**
			 * Devuelve un mensaje al cliente según qué parte del tablero haya recibido el
			 * disparo. Si el disparo ha tocado agua (valor 0 en el tablero), devolverá
			 * '-AGUA-' en el mensaje. Si el disparo ha tocado un barco (valor del
			 * identificador del barco en el tablero) y aún no se han tocado todas las
			 * posiciones de ese barco, devolverá '-TOCADO-' en el mensaje. Si el disparo ha
			 * tocado un barco (valor del identificador del barco en el tablero) y ya se han
			 * tocado todas las posiciones de ese barco, devolverá '-HUNDIDO-' en el
			 * mensaje.
			 */
			// cuando sea DISPARA:
			else if (peticion.startsWith("DISPARA")) {
				String p[] = peticion.split(" ");
				String id = p[1];
				int x = Integer.parseInt(p[2]);
				int y = Integer.parseInt(p[3]);
				int d;
				if (id.equals("p")) {
					d = this.ordenador.getTablero().recibeDisparo(x, y);
				} else {
					d = this.persona.getTablero().recibeDisparo(x, y);
				}
				if (d == 0) {// agua
					out.writeBytes("-AGUA-" + "\r\n");
					out.flush();
				} else if (d == 1) {// tocado
					out.writeBytes("-TOCADO-" + "\r\n");
					out.flush();
				} else if (d == 2) {// tocado y hundido.
					out.writeBytes("-HUNDIDO-" + "\r\n");
					out.flush();
				} else {
					out.writeBytes("-Ya has disparado en esta posición-" + "\r\n");
					out.flush();
				}

				/**
				 * Almacena en un fichero un String con los tableros de los dos jugadores
				 * escritos. Posteriormente lanza un mensaje al cliente diciendo que lo ha
				 * escrito.
				 */
				// cuando sea MUESTRA:
			} else if (peticion.startsWith("MUESTRA")) {
				String p[] = peticion.split(" ");
				// String id = p[1];
				String res = "";
				String oponente = "o";
				String propio = "p";

				oponente = "Tablero oponente: " + "\n" + this.ordenador.getTablero().toStringSoloTocadas()
						+ "\n\nTablero propio:" + "\n";
				propio = this.persona.getTablero().toString();

				res = oponente + propio;
				PrintWriter pw = new PrintWriter(fichero);
				pw.println(res);
				pw.flush();
				pw.close();
				out.writeBytes("escrito" + "\r\n");
				out.flush();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
