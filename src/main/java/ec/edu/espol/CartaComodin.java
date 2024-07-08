package ec.edu.espol;

public class CartaComodin extends Carta{
    private TipoComodin caracter;

    public CartaComodin(TipoColor color, TipoComodin caracter){
        super(color);

        this.caracter = caracter;
    }

    public void setColor(TipoColor color){
        this.color = color;
    }

    @Override
    public String toString(){
        return "Color: " + color + " - Comodin: " + caracter.getSimbolo();
    }

    public TipoComodin getCaracter() {
        return caracter;
    }

}
