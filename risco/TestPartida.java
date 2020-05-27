package risco;

import utiles.*;
import java.io.File;
import java.util.Scanner;

/**
 * TestPartida es la clase que interactúa directamente con el usuario.
 * Aqui se crea una partida nueva y se imprimen por pantalla toda la
 * información necesaria para el desarrollo de la partida, como las
 * opciones que tiene el jugador o mostrar el tablero del juego con
 * las puntuaciones.
 * 
 */
public class TestPartida {

  private static Menu menu = new Menu("Indique la opción que quiere realizar:",
      new String[] { "Risco", "Trece", "Escalera Mayor", "Escalera Menor ", "Escalera par", "Escalera impar", "Trio",
          "Seis", "Cinco", "Cuatro", "Tres", "Dos", "Ases", "Ver mi historial" });

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    int nJugadores;
    String nombreTmp;
    File archivoTmp; // Representación de un archivo, para ver si existe
    String esONoEs; // Para aceptar la confirmación de un jugador sobre su persona
    boolean apto = true;

    // Recojo el número de jugadores, y se los paso a Partida

    nJugadores = Teclado.leerEntero("Indica cuantos jugadores sois: ");
    // Crear una partida
    Partida partida = new Partida(nJugadores);
    for (int i = 1; i <= nJugadores; i++) {
      do {
        apto = true;
        nombreTmp = Teclado.leerCadena("Introduce el nombre del jugador " + i + ": ");
        archivoTmp = new File("risco_" + nombreTmp + ".txt");
        if (archivoTmp.exists()) {
          System.out.print("\nEste jugador ya ha jugado anteriormente, ¿realmente eres él? [S/N]: ");
          esONoEs = sc.nextLine().toUpperCase();
          if (!esONoEs.equals("S")) {
            apto = false;
          }
        }
      } while (!apto);
      partida.crearJugadores(i, nombreTmp);
    }

    // Para recorrer las 13 rondas
    for (int i = 1; i <= 13; i++) {

      /**
       * Recorremos todos los jugadores durante cada ronda
       */
      for (int j = 1; j <= nJugadores; j++) {
        Jugador player = partida.jugadores.get(partida.jugadores.indexOf(new Jugador(j)));

        System.out.print("\n\nTurno " + i + " del jugador "
            + partida.jugadores.get(partida.jugadores.indexOf(new Jugador(j))).getNombre());
        System.out.println("\nEn esta tirada obtienes:\n"
            + Partida.tirarDados(partida.jugadores.get(partida.jugadores.indexOf(new Jugador(j)))));

        String cambioDado = Teclado.leerCadena("\n¿Quieres cambiar algún dado? [S/N]: ").toUpperCase();
        if (cambioDado.equals("S")) {
          int nDadosCambiar = Teclado.leerEntero("¿Cuántos dados quieres cambiar?: ");
          System.out.println(player.dadosJugador.cambiarDados(nDadosCambiar));
        }

        boolean casillaOcupada;
        int n;
        /**
         * Bucle do while para no perder el turno de la partida si indica una casilla
         * que está ocupada.
         */
        do {
         
          
          casillaOcupada = false;
          
          switch (menu.gestionar()) {
          case 1:
            n=Partida.Risco(player);
            if (n == 0) {
              System.out.println("Ha obtenido 0 puntos");
            } else if (n == 1) {
              System.out.println("Has completado la casilla Risco: 50 puntos");
            } else {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            }
            break;

          case 2:
            n=Partida.Trece(player);
            if (n== 0) {
              System.out.println("Ha obtenido 0 puntos");
            } else if (n == 1) {
              System.out.println("Casilla Trece completada: 26 ptos");
            } else {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            }
            break;

          case 3:
            n=Partida.EscaleraMayor(player) ;
            if (n == 0) {
              System.out.println("Ha obtenido 0 puntos");
            } else if (n == 1) {
              System.out.println("Esta combinación es una Escalera Mayor, 20 ptos");
            } else {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            }

            break;

          case 4:
            n=Partida.EscaleraMenor(player) ;
            if (n == 0) {
              System.out.println("Ha obtenido 0 puntos");
            } else if (n == 1) {
              System.out.println("Esta combinación es una Escalera Menor, 20 ptos");
            } else {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            }
            break;

          case 5:
            n=Partida.EscaleraPar(player);
            if (n== 0) {
              System.out.println("Ha obtenido 0 puntos");
            } else if (n == 1) {
              System.out.println("Esta combinación es una Escalera Par, 20 ptos");
            } else {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            }
            break;

          case 6:
            n=Partida.EscaleraImpar(player) ;
            if (n == 0) {
              System.out.println("Ha obtenido 0 puntos");
            } else if (n == 1) {
              System.out.println("Esta combinación es una Escalera Impar, 20 ptos");
            } else {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            }

            break;

          case 7:
            n= Partida.Trio(player);
            if (n == 0) {
              System.out.println("Ha obtenido 0 puntos");
            } else if (n == 1) {
              System.out.println("Casilla Trío completada, 25 ptos");
            } else {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            }
            break;

          case 8:
            n=Partida.numero(player, 6);
            if (n == 50) {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            } else {
              System.out.println("Casilla seises completada con " + n);
            }
            break;

          case 9:
            n=Partida.numero(player, 5);
            if (n == 50) {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            } else {
              System.out.println("Casilla cincos completada con " + n);

            }

            break;

          case 10:
            n=Partida.numero(player, 4);
            if (n == 50) {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            } else {
              System.out.println("Casilla cuatros completada con " + n);

            }
            break;

          case 11:
            n= Partida.numero(player, 3);
            if (n == 50) {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            } else {
              System.out.println("Casilla treses completada con " + n);
            }
            break;

          case 12:
            n=Partida.numero(player, 2);
            if (n == 50) {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;
            } else {
              System.out.println("Casilla doses completada con " + n);
            }

            break;

          case 13:
            n=Partida.numero(player, 1);
            if (n == 50) {
              System.out.println("Casilla ocupada indique una casilla vacia");
              casillaOcupada = true;

            } else {
              System.out.println("Casilla Ases completada con " + n);
            }

            break;

          case 14:
            Historial history = new Historial(player);
            System.out.println(history);
            casillaOcupada = true; // Aunque no era su fin, uso casillaVacia para que no pierda el turno al ver el
                                   // historial
            break;

          default:
            System.out.println("Opción no valida.");
            casillaOcupada = true;
            break;
          }

        } while (casillaOcupada);

        partida.muestraTablero();
        System.out.println();
        System.out.print("\nPulsa Intro para seguir o e para exportar tus datos actuales[Intro/e]: ");

        String exporto = sc.nextLine().toUpperCase();
        if (exporto.equals("E")) {
          int pos = partida.posicion(player);
          player.guardaDatos(nJugadores, pos);
        }
      }
    }
    sc.close();
  }

}
