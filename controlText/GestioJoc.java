/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.controlText;

import domino.model.Fitxa;
import domino.model.Joc;
import domino.model.Jugada;
import domino.vistaText.InterficieText;

/**
 *
 * @author ALUMNEDAM
 */
public class GestioJoc {

    Joc joc = new Joc(4, 28, 7);
    InterficieText ui = new InterficieText(joc);
    
    private final int modeFitxa1 = 1;
    private final int modeFitxa2 = 2;
    private final int modeFitxa3 = 3;
    

    public void iniciarPartida() {
        //Inicia el joc
        joc.iniciar(introduirNoms(joc.NUMJUGADORS));

        realitzarPrimeraJugada();

        while (joc.isFinalitzat() == false) {
            realitzarJugada();
        }

        ui.mostrarGuayador();
    }

    public String[] introduirNoms(int numJugadors) {
        String[] noms = new String[numJugadors];
        for (int i = 0; i < numJugadors; i++) {
            noms[i] = ui.demanarNom(i);
        }
        return noms;
    }

    public void realitzarPrimeraJugada() {
        ui.mostrarJugadorITorn(joc.getTorn());
        Jugada jug = new Jugada(joc);
        jug.inicial();
        joc.actualitzarEstat();
        ui.mostrarTauler(joc.getFitxesJugades());
        System.out.println("\n");
    }

    public void realitzarJugada() {
        ui.mostrarJugadorITorn(joc.getTorn());
        ui.mostrarTauler(joc.getFitxesJugades());
        ui.mostrarFitxesJugador(joc.getJugadors()[joc.getTorn()].getFitxes());
        menu1();

    }

    public void menu1() {
        int opcio = ui.mostrarMenu1();
        Jugada jug = new Jugada(joc);
        switch (opcio) {
            case 1:
                menuColocarUnaFitxa(jug);
                break;
            case 2:
                menuFitxesDobles(jug);
                break;
            case 3:
                jug.passar();
                joc.actualitzarEstat();
                break;
            default:
                System.out.println("");

        }
    }

    private void menuColocarUnaFitxa(Jugada jug) {

        Fitxa fitxa;
        boolean extrem;

        while (true) {
            fitxa = ui.mostrarMenu2(modeFitxa1);
            if (fitxa == null) {
                jug.passar();
                joc.actualitzarEstat();
                break;
            }
            extrem = ui.mostrarMenu3();
            boolean correcte = jug.colocarUnaFitxa(fitxa, extrem);
            if (correcte) {
                break;
            }
            System.out.println("No es pot colocar!");
        }
        joc.actualitzarEstat();

    }

    private void menuFitxesDobles(Jugada jug) {

        boolean correcte;

        Fitxa fitxa1;
        boolean extrem1;
        Fitxa fitxa2;
        boolean extrem2;

        while (true) {
            //FITXA1
            fitxa1 = ui.mostrarMenu2(modeFitxa2);
            if (fitxa1 == null) {
                jug.passar();
                joc.actualitzarEstat();
                break;
            }
            extrem1 = ui.mostrarMenu3();

            //FITXA2
            fitxa2 = ui.mostrarMenu2(modeFitxa3);
            if (fitxa2 == null) {
                jug.passar();
                joc.actualitzarEstat();
                break;
            }
            extrem2 = ui.mostrarMenu3();

            correcte = jug.colocarDosDobles(fitxa1, extrem1, fitxa2, extrem2);
            if(correcte){
                break;
            }
            System.out.println("No es pot colocar");
        }

        joc.actualitzarEstat();

    }
}
