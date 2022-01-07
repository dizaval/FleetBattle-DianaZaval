package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

	public Gestiona(Socket s, Jugador persona, Jugador ordenador) {
		this.persona = persona;
		this.ordenador = ordenador;
		this.socket = s;
	}

	public void run() {
		try (DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
			String peticion = in.readLine();
			System.out.println(Thread.currentThread().getName());


			// cuando sea ELIGE:
			if (peticion.startsWith("ELIGE")) {

				String p []=peticion.split(" ");
				String id=p[1];
				this.persona= Servidor.encontrarJugador(id);
				Tablero t =new Tablero(10,10);
				this.persona.setTablero(t);
				
				ArrayList<Barco> l = new ArrayList<Barco>();
				Barco b1 = new Barco(0, 0, 4, "portaaviones", "b1");
				Barco b2 = new Barco(0, 0, 3, "acorazado", "b2");
				Barco b3 = new Barco(0, 0, 3, "acorazado",  "b3");
				Barco b4 = new Barco(0, 0, 2, "buque","b4");
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
				for (int i = 0; i < l.size(); i++) {
					b = l.get(i); // ya tendrá asignada la lista de barcos.
					//persona.muestraTableroPropio();
					System.out.println("Elige las posiciones del barco " + b.getId() + "(" + b.getTipo() + ")");
					int tam = b.getCasillas();
					for (int j = 1; j < tam + 1; j++) {
						System.out.println("\t" + "Introduce la posición " + j + " con el formato x-y");
						posi = sc.nextLine();
						xy = posi.split("-");
						try {
							x = Integer.parseInt(xy[0]);
							y = Integer.parseInt(xy[1]);
							boolean ocupado = this.persona.getTablero().ocupado(x, y);
							while (ocupado) {
								System.out.println("\t" + "POSICION OCUPADA- Introduce la posición " + j + " con el formato x-y");
								posi = sc.nextLine();
								xy = posi.split("-");
								x = Integer.parseInt(xy[0]);
								y = Integer.parseInt(xy[1]);
								ocupado = this.persona.getTablero().ocupado(x, y);
							}

							x = Integer.parseInt(xy[0]);
							y = Integer.parseInt(xy[1]);

							this.persona.getTablero().setPosicion(x, y, b);
							this.persona.getTablero().anadirBarco(b);
							System.out.println(persona.getTablero().toString());
							
						} catch (NumberFormatException e) {
							System.out.println("Formato no válido: Introduce dos numeros separados por -");
						}
						
					}
				}
				out.writeBytes("ok\r\n");
				out.flush();
				System.out.println("Posiciones persona acabadas");
			}
			
			
			// cuando sea PONER:
			else if (peticion.startsWith("PONER")) {
				System.out.println("poniendo ordenador");
				String p []=peticion.split(" ");
				String id=p[1];		
				this.ordenador= Servidor.encontrarJugador(id);
				Tablero t =new Tablero(10,10);
				this.ordenador.setTablero(t);
				this.ordenador.getTablero().colocarBarcosOrdenador();
				System.out.println(this.ordenador.getTablero().toString());
				//out.writeBytes("Posiciones ordenador colocadas\r\n");
				//out.flush();
			}
			
			
			
			//cuando sea GANA:
			else if (peticion.startsWith("GANA")) {
				System.out.println("entrando en gana");
				String p []=peticion.split(" ");
				String id=p[1];			
				System.out.println(Servidor.encontrarJugador(id).getId());
				if(Servidor.encontrarJugador(id).getTablero().todosTocados()) {
					System.out.println(Servidor.encontrarJugador(id).getId());
					out.writeBytes("si"+"\r\n");
					out.flush();
				}else {
					out.writeBytes("no"+"\r\n");
					out.flush();
				}
			}
			
			else if(peticion.startsWith("DISPARA")) {
				System.out.println("entrando en dispara");
				String p []=peticion.split(" ");
				String id=p[1];
				int  x =Integer.parseInt(p[2]);
				int  y =Integer.parseInt(p[3]);
				int d;
				if(id.equals("p")) {
					d =this.ordenador.getTablero().recibeDisparo(x, y);
				}else {
					d= this.persona.getTablero().recibeDisparo(x, y);
				}
				if(d==0) {//agua
					out.writeBytes("agua"+"\r\n");
					out.flush();
				}else if(d==1) {//tocado
					out.writeBytes("tocado"+"\r\n");
					out.flush();
				}else if(d==2) {//tocado y hundido.
					out.writeBytes("hundido"+"\r\n");
					out.flush();
				}
				System.out.println("terminando dispara");
			}
			else if(peticion.startsWith("MUESTRA")) { //muestra ambos tableros, primero el del oponente con las tocadas y luego el propio
				System.out.println("entrando en muestra");
				String p []=peticion.split(" ");
				String id=p[1];
				String res ="";
				String oponente ="";
				String propio ="";
						
				if(id.equals(persona.getId())) { //es de la persona
					oponente ="Tablero oponente: "+"\n"+ this.ordenador.getTablero().toStringSoloTocadas()+ "\n\nTablero propio:"+"\n";
					propio=this.persona.getTablero().toString();
					
				}else { //es del ordenador
					oponente ="Tablero oponente: "+"\n"+ this.persona.getTablero().toStringSoloTocadas()+ "\n\nTablero propio:"+"\n";
					propio=this.ordenador.getTablero().toString();
				}
				res = oponente+propio;
				out.writeBytes(res+"\r\n");
				out.flush();
							
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
