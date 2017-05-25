/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.control;

import domino.model.Fitxa;
import domino.model.Joc;
import domino.model.Torn;
import domino.vista.VistaText;
import domino.vista.VistaSwing;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALUMNEDAM
 */
public class ControlText {

    Joc joc = new Joc(4, 28, 7);
    VistaText vt = new VistaText(joc);
    VistaSwing vs;

    public ControlText(VistaSwing vs) {
        this.vs = vs;
        this.vs.setJoc(joc);
    }

    
    private int fichita = 1;

    

    public void iniciarPartida() {
        //Inicia el joc
        
        joc.iniciar(introduirNoms(joc.NUMJUGADORS));

//        
        realitzarPrimeraJugada();

        //while (!joc.isFinalitzat()) {
            //realitzarJugada();
            realitzarJugadaUI();
        //}
//
//        vt.ganador();
    }

        public void realitzarPrimeraJugada() {
        
        try {
            Torn t = new Torn(joc);
            vs.setUiTorn(joc.getTorn());
            
            vt.mostrarJugador(joc.getTorn());
            
            vs.mostrarJugador(joc.getTorn());
            
            System.out.println("Turno" + joc.getTorn());
            
            
            
            
            //t.inicial();
            t.inicialVista();
            vt.mostrarTablero(joc.getFitxesJugades());
            vs.mostrarTablero(joc.getFitxesJugades());
            
            joc.actualitzarEstat();
            
           
            
            
        } catch (IOException ex) {
            System.out.println("Algo falla : " + ex);
        }
    }
    
    public String[] introduirNoms(int numJugadors) {
        String[] noms = new String[numJugadors];
        
            noms[0] = vs.pedirNombre(0);
            noms[1] = "IA 1";
            noms[2] = "IA 2";
            noms[3] = "IA 3";
            //noms[i] = vt.pedirNombre(i);
        
        
        return noms;
    }



    public void realitzarJugada() {
        vt.mostrarJugador(joc.getTorn());
        vt.mostrarTablero(joc.getFitxesJugades());
        vt.mostrarFitxesJugador(joc.getJugadors()[joc.getTorn()].getFitxes());
        menu1();

    }
    
    public void realitzarJugadaUI() {
        System.out.println("holita");
    }
    
    
    

    public void menu1() {
        int opcio = vt.mostrarMenu1();
        Torn tirador = new Torn(joc);
        switch (opcio) {
            case 1:
                menuColocarUnaFitxa(tirador);
                break;
            case 2:
                menuFitxesDobles(tirador);
                break;
            case 3:
                tirador.passar();
                joc.actualitzarEstat();
                break;
            default:
                System.out.println("");

        }
    }

    private void menuColocarUnaFitxa(Torn jug) {


        while (true) {
             Fitxa fitxa = vt.mostrarMenuTirada(fichita);
            if (fitxa == null) {
                jug.passar();
                joc.actualitzarEstat();
                break;
            }
            boolean extremo = vt.mostrarMenuTablero();
            boolean sePuede = jug.colocarUnaFitxa(fitxa, extremo);
            if (sePuede) {
                break;
            }
            System.out.println("No es pot colocar!");
        }
        joc.actualitzarEstat();

    }

    private void menuFitxesDobles(Torn t) {

        
        //No el tinc fet
        //Encara me'l he de mirar millor

    }
}
