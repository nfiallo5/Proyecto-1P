package ec.edu.espol;

public enum TipoComodin {
    REVERSO("^"),
    BLOQUEO("&"),
    MAS_4("+4"),
    MAS_2("+2"),
    CAMBIO_COLOR("%");

    private String simbolo;

    private TipoComodin(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return this.simbolo;
    }
    
}
