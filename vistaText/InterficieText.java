/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.vistaText;

import domino.model.Fitxa;
import domino.model.Joc;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ALUMNEDAM
 */
public class InterficieText {

    Joc joc;
    Scanner lector = new Scanner(System.in);
    int uiTorn = 1;

    public InterficieText(Joc joc) {
        this.joc = joc;
    }

    public void mostrarJugadorITorn(int torn) {
        System.out.println("Torn " + (uiTorn) + "\tJugador: " + joc.getJugadors()[torn].getNom());
        uiTorn++;
    }

    public void mostrarTauler(ArrayDeque<Fitxa> tauler) {
        StringBuilder sb = new StringBuilder();
        System.out.println("TAULER:");
        for (Fitxa fitxa : tauler) {
            sb.append("{").append(fitxa.getValors()[0]).append(" | ").append(fitxa.getValors()[1]).append("}");
        }
        System.out.println(sb);
    }

    public void mostrarFitxesJugador(List<Fitxa> fitxesJugador) {
        StringBuilder sb = new StringBuilder();
        System.out.println("FITXES JUGADOR:");
        for (Fitxa fitxa : fitxesJugador) {
            sb.append("{").append(fitxa.getValors()[0]).append(" | ").append(fitxa.getValors()[1]).append("}");
        }
        System.out.println(sb);
    }

    public int mostrarMenu1() {
        System.out.println("\nTRIAR JUGADA: "
                + "\n1- Colocar una fitxa"
                + "\n2- Colocar dos dobles"
                + "\n3- Passar");
        return demanarOpcio();
    }

    public Fitxa mostrarMenu2(int mode) {
        switch (mode) {
            case 1:
                System.out.println("INTRODUIR POSICIÓ FITXA: (0 per a passar)");
                break;
            case 2:
                System.out.println("INTRODUIR POSICIÓ PRIMERA FITXA DOBLE: (0 per a passar)");
                break;
            case 3:
                System.out.println("INTRODUIR POSICIÓ SEGONA FITXA DOBLE: (0 per a passar)");
                break;
        }

        return demanarPosicio(joc.getJugadors()[joc.getTorn() % 4].getFitxes());
    }

    public boolean mostrarMenu3() {
        System.out.println("\nTRIAR EXTREM: "
                + "\nL- Esquerra"
                + "\nR- Dreta");
        return demanarExtrem();
    }

    public void mostrarGuayador() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nEl guanyador es: ").append(joc.getGuanyador().getNom());
        System.out.println(sb);
    }

    //Entrada de dades
    public String demanarNom(int i) {
        System.out.println("");
        System.out.println("Introdueix el nom del jugador " + (i + 1) + ".");
        return lector.nextLine();
    }

    public int demanarOpcio() {
        System.out.println("");
        int opcio;

        while (true) {
            if (lector.hasNextInt()) {
                opcio = Integer.parseInt(lector.nextLine());
                if (opcio >= 1 && opcio <= 3) {
                    break;
                } else {
                    System.out.println("Numero no valid");
                    lector.nextLine();
                }

            } else {
                System.out.println("Numero no valid");
                lector.nextLine();
            }
        }
        return opcio;
    }

    public Fitxa demanarPosicio(List<Fitxa> fitxesJugador) {
        System.out.println("");
        int opcio;

        while (true) {
            if (lector.hasNextInt()) {
                opcio = Integer.parseInt(lector.nextLine());
                //ESTO PUEDE PETAR!!!!
                if (opcio == 0) {
                    return null;
                } else if (opcio >= 1 && opcio <= fitxesJugador.size()) {
                    break;
                } else {
                    System.out.println("Numero no valid");
                    lector.nextLine();
                }

            } else {
                System.out.println("Numero no valid");
                lector.nextLine();
            }
        }
        return fitxesJugador.get(opcio - 1);
    }

    public boolean demanarExtrem() {
        System.out.println("");
        
        Character extrem = lector.nextLine().charAt(0);

        while (true) {
            switch (Character.toUpperCase(extrem)) {
                case 'L':
                    return true;
                case 'R':
                    return false;
                default:
                    System.out.println("Extrem no valid");
                    lector.nextLine();
                    break;
            }
        }
    }
}
