package domino.model;

public class Fitxa {

    private int[] valors;

    public Fitxa(int[] valors) {
        this.valors = valors;
    }

    public int[] getValors() {
        return valors;
    }
    
    public int getPrimerValor(){
        int valor;
        
        if(valors[0] < valors[1]){
            valor = valors[0];
        }else{
            valor = valors[1];
        }
        return valor;
    }
    
    public int getSegonValor(){
         int valor;
        
        if(valors[0] > valors[1]){
            valor = valors[0];
        }else{
            valor = valors[1];
        }
        return valor;
    }

    /**
     * Es permuten els valors de la fitxa.
     */
    public void canviarOrientacio() {
        int aux = valors[0];
        valors[0] = valors[1];
        valors[1] = aux;
    }

    @Override
    public String toString() {
        return "Fitxa{" + "valors={" + valors[0] + ", " + valors[1] + '}';
    }

}
