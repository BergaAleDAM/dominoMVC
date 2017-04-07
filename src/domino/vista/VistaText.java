/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.vista;

import domino.model.Fitxa;
import domino.model.Joc;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ALUMNEDAM
 */
public class VistaText {

    Joc joc;
    Scanner lector = new Scanner(System.in);
    int uiTorn = 1;

    public VistaText(Joc joc) {
        this.joc = joc;
    }

    public void mostrarJugador(int torn) {
        System.out.println("Torn " + (uiTorn) + "\tJugador: " + joc.getJugadors()[torn].getNom());
        uiTorn++;
    }
    
    public String pedirNombre(int i) {
        System.out.println("\n Pon nombre a Jugador " + (i + 1) + ".");
        return lector.nextLine();
    }

    public void mostrarTablero(ArrayDeque<Fitxa> tablero) {
        
     StringBuilder sb = new StringBuilder();
        for (Fitxa f : tablero) {
            sb.append(" - ");
            sb.append(f.getValors()[0]);
            sb.append("·");
            sb.append(f.getValors()[1]);
            sb.append(" - ");
        }
        System.out.println(sb);
    }

    public void mostrarFitxesJugador(List<Fitxa> Mano) {
        StringBuilder sb = new StringBuilder();
        System.out.println("Mano jugador:");
        for (Fitxa f : Mano) {
            sb.append(" - ");
            sb.append(f.getValors()[0]);
            sb.append("·");
            sb.append(f.getValors()[1]);
            sb.append(" - ");
        }
        System.out.println(sb);
    }

    public int mostrarMenu1() {
        System.out.println("\nESCOGER JUGADA: "
                + "\n1- Colocar una ficha"
                + "\n2- Colocar dos dobles"
                + "\n3- Passar");
        return pedirOpcion();
    }

    public Fitxa mostrarMenuTirada(int mode) {
        switch (mode) {
            case 0:
                System.out.println("0-> Passar");
            case 1:
                System.out.println("Introducir Posicion ficha:");
                break;
            case 2:
                System.out.println("Introducir posicion primera ficha doble:");
                break;
            case 3:
                System.out.println("Introducir posicion segunda ficha doble: ");
                break;
        }
        return demanarPosicio(joc.getJugadors()[joc.getTorn() % 4].getFitxes());
    }

    public boolean mostrarMenuTablero() {
        
        System.out.println("\nEscojer extremo: "
                + "\nL- Izqierda"
                + "\nR- Derecha");
        return pedirExtremo();
    }



    public int pedirOpcion() {

        int opcio;
        while (true) {
            //System.out.println("\n");
            if (!lector.hasNextInt()) {
                System.out.println("No se puede");
                lector.next();

            } else {
                //String op =lector.nextLine();
                opcio = Integer.parseInt(lector.next());
                System.out.println(opcio);
                if (  opcio < 4 && opcio > 0) {
                    break;
                } else {
                    System.out.println("No se puede");
                    lector.nextLine();
                }
                
            }
        }
        return opcio;
    }

    public Fitxa demanarPosicio(List<Fitxa> mano) {

        int opcion;

        while (true) {
            if (lector.hasNextInt()) {
                opcion = Integer.parseInt(lector.next());
                if (opcion == 0) {
                    return null;
                } else if (opcion > 0 && opcion <= mano.size()) {
                    break;
                } else {
                    System.out.println("Ponlo bien");
                    lector.nextLine();
                }

            } else {
                System.out.println("Ponlo bien");
                lector.next();
            }
        }
        return mano.get(opcion - 1);
    }

    
        public void ganador() {
            
        StringBuilder sb = new StringBuilder();

        sb.append("\nGana ->: ").append(joc.getGuanyador().getNom());
        System.out.println(sb);
    }
        
            public boolean pedirExtremo() {
        
        
        Character e = lector.next().charAt(0);

        while (true) {
            switch (Character.toUpperCase(e)) {
                case 'L':
                    return true;
                case 'R':
                    return false;
                default:
                    System.out.println("O te has equivocado o lo he hecho mal");
                    lector.nextLine();
                    break;
            }
        }
    }
    
}
