package ec.edu.espol;

public class Carta {
    protected int numero;
    protected TipoColor color;

    public Carta(int numero, TipoColor color){
        this.numero = numero;
        this.color = color;
    }

    public Carta(TipoColor color){
        this.color = color;
    }


    public TipoColor getColor(){
        return this.color;
    }

    public int getNumero(){
        return numero;
    }

    @Override
    public String toString(){
        return "Color: " + color + " - Numero: " + numero;
    }
}
