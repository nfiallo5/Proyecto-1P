package ec.edu.espol;
import java.util.ArrayList;
import java.util.Collections;

public class Utilitaria {
    public static ArrayList<Carta> crearBaraja(){
        ArrayList<Carta> baraja = new ArrayList<>();

        //Cartas Regulares
        for(int i = 0; i < 10; i++){
            baraja.add(new Carta(i, TipoColor.R));
            baraja.add(new Carta(i, TipoColor.V));
            baraja.add(new Carta(i, TipoColor.A));
            baraja.add(new Carta(i, TipoColor.Z));
        }

        //Cartas Comodin de R-A-V-Z
        for(int i=0; i<4; i++){
           baraja.add(new CartaComodin(TipoColor.values()[i], TipoComodin.values()[i]));
           baraja.add(new CartaComodin(TipoColor.values()[i], TipoComodin.values()[i]));
        }

        //Cartas Comodin de N
        for(int i=2; i<5; i++){
            baraja.add(new CartaComodin(TipoColor.N, TipoComodin.values()[i]));
            baraja.add(new CartaComodin(TipoColor.N, TipoComodin.values()[i]));
        }

        Collections.shuffle(baraja);
        return baraja;
    }


    public static ArrayList<Carta> crearManoJugador(ArrayList<Carta> baraja){
        ArrayList<Carta> mano = new ArrayList<>();
        
        for(int i=0; i<7; i++)
        {
            Carta carta_j = baraja.remove((int) (Math.random()*baraja.size()));
            mano.add(carta_j);
        }

        return mano;
    }

    public static ArrayList<Carta> crearMesa(ArrayList<Carta> baraja){
        ArrayList<Carta> mesa = new ArrayList<>();
        boolean flag = true;

        while(flag)
        {
            int max = baraja.size()-1;
            int min = 0;
            int range = max - min + 1;
            Carta carta1 = baraja.remove((int) (Math.random()*range)+min);
            if(!(carta1 instanceof CartaComodin))
            {
                flag = false;
                mesa.add(carta1);
            }
        }
        return mesa;
    }

    
}
