package ec.edu.espol;
import java.util.ArrayList;


public class Jugador {
    private String nombre;
    private ArrayList<Carta> mano;

    public Jugador(String nombre){
        this.nombre = nombre;
    }

    public Carta jugarCarta(int index){
        Carta carta = mano.remove(index);
        return carta;
    }

    public void robarCarta(ArrayList<Carta> baraja, int m){
        for(int i = 0; i < m; i++)
        {
            Carta carta = baraja.remove(baraja.size() - 1);
            mano.add(carta);  
        }      
    }
 
    @Override
    public String toString(){
        StringBuilder str_final = new StringBuilder();
        str_final.append("Jugador: "+ nombre);
        
        int i =0;
        for(Carta c : mano){
            str_final.append("\n[" +  c.toString() + "] (" + i + ")");
            i++;
        }
        str_final.append("\n--------------------------");

        return str_final.toString();  
    }

    public void setMano(ArrayList<Carta> mano){
        this.mano = mano;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    
}
