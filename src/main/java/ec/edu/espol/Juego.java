package ec.edu.espol;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Jugador jugador = new Jugador("jugador");
    private Jugador maquina = new Jugador("maquina");
    private ArrayList<Carta> baraja;
    private ArrayList<Carta> mesa; 
    

    public Juego(){
        baraja = new ArrayList<>();
        mesa = new ArrayList<>();
    }

    
    public void iniciarJuego(){
        baraja = Utilitaria.crearBaraja();
        mesa = Utilitaria.crearMesa(baraja); 

        jugador.setMano(Utilitaria.crearManoJugador(baraja));
        maquina.setMano(Utilitaria.crearManoJugador(baraja));
    }

    public void jugar(){
        iniciarJuego();
        estadoActual();
        boolean flag =true;

        while (flag){
            turnojugador();
            if(jugador.getMano().size() == 0)
            {
                System.out.println("Jugador gano!");
                  flag = false;  
            }
                
            if(flag)
            {
                juegoMaquina();
                if(maquina.getMano().size() == 0)
                {
                    System.out.println("Maquina gano!");
                    flag = false;
                }      
            }
            if(flag)
            {
                if(jugador.getMano().size() == 1 || maquina.getMano().size() == 1)
                    System.out.println("UNO!");
            }
        }
        
    }

    public void estadoActual() {
        System.out.println("Ultima carta jugada: " + mesa.get(mesa.size()-1));
        System.out.println(jugador.toString()); 
        System.out.println(maquina.toString()); 
    }


    public boolean repetirturno(Carta carta){
        if(carta instanceof CartaComodin)
        {
            CartaComodin c_cm= (CartaComodin)carta;
            return c_cm.getCaracter()==TipoComodin.BLOQUEO || c_cm.getCaracter()==TipoComodin.REVERSO;
        }
        return false;
    }

    public void turnojugador() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while(flag)
        {   
            
            System.out.println("Ingrese el indice de la carta a jugar, o ingresar '-1' para robar carta: ");
            int index = Integer.parseInt(sc.nextLine());

            if(index >= 0)
            {
                Carta probar = jugador.getMano().get(index);               
                if(validarCarta(probar))
                {
                    Carta cartajugada = jugador.jugarCarta(index);
                    if(cartajugada instanceof CartaComodin)
                    {
                        CartaComodin comodinjugado = (CartaComodin)cartajugada;
                        if(comodinjugado.getCaracter() == TipoComodin.MAS_2)
                            maquina.robarCarta(baraja, 2);
                        else if(comodinjugado.getCaracter() == TipoComodin.MAS_4)
                            maquina.robarCarta(baraja, 4);
                        if(comodinjugado.getColor() == TipoColor.N)
                        {
                            System.out.println("Escoger el color a cambiar (R, A, V, Z): ");
                            String color = sc.nextLine().toUpperCase();

                            comodinjugado.setColor(TipoColor.valueOf(color));
                            mesa.add(comodinjugado);
                            flag = false;
                            
                        }
                    }
                    
                    if(flag)
                    {
                        mesa.add(cartajugada);
                        flag = false; 
                    }
                }
            } 
            else if(index == -1){
                jugador.robarCarta(baraja,1);
                flag = false;
            }
        }
        estadoActual();
        Carta ultimacarta = mesa.get(mesa.size()-1);
        if(repetirturno(ultimacarta))
            turnojugador();
        
    }

    public void juegoMaquina() {
        for(int i =0; i < maquina.getMano().size(); i++)
        {
            Carta carta = maquina.getMano().get(i); 
            if(validarCarta(carta))
            {
                if(carta instanceof CartaComodin)
                {
                    CartaComodin c_m = (CartaComodin)carta;
                    if(c_m.getCaracter() == TipoComodin.MAS_2)
                        jugador.robarCarta(baraja, 2);
                    else if(c_m.getCaracter() == TipoComodin.MAS_4)
                        jugador.robarCarta(baraja, 4);

                    if(c_m.getColor()==TipoColor.N)
                    {
                        Random random = new Random();
                        TipoColor nuevo_color = TipoColor.values()[random.nextInt(3)];
                        c_m.setColor(nuevo_color);

                        Carta cartajugada = maquina.jugarCarta(i);
                        mesa.add(c_m);
                        break;
                    }
                }
                    
                Carta cartajugada = maquina.jugarCarta(i);
                mesa.add(cartajugada);
                break;
            }
            if(i == maquina.getMano().size()-1)
            {
                maquina.robarCarta(baraja,1);
                break;
            }  
        }
        estadoActual();
        if(repetirturno(mesa.get(mesa.size()-1)))
            juegoMaquina();    
    }

    public boolean validarCarta(Carta carta){
        Carta ultimacarta = mesa.get(mesa.size()-1);
        if(!(carta instanceof CartaComodin) && !(ultimacarta instanceof CartaComodin))
        {
            return carta.getColor() == ultimacarta.getColor() || carta.getNumero() == ultimacarta.getNumero() || carta.getColor()==TipoColor.N;
        }
        return carta.getColor() == ultimacarta.getColor() || carta.getColor()==TipoColor.N;
    } 

    public ArrayList<Carta> getBaraja() {
        return baraja;
    }

    public ArrayList<Carta> getMesa() {
        return mesa;
    }

    

}
