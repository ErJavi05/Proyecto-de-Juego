package Clases_del_Juego;

public class JefeZona extends Enemigos {
    private double aumentarAtaque;
    private double traspasarDefenza;

    public JefeZona(double vida, double defenza, double ataque, double aumentarAtaque, double traspasarDefenza) {
        super(vida, defenza, ataque);
        this.aumentarAtaque = aumentarAtaque;
        this.traspasarDefenza = traspasarDefenza;
    }

    @Override
    public void atacar(Personajes jugador) {
        double danio = this.ataque * (1 + this.aumentarAtaque/100);
        mostrarMensaje("¡El jefe lanza un ataque devastador! (" + danio + " de daño)");
        
        // Ignora parte de la defensa
        double danioFinal = danio * (1 - (jugador.getDefenza() * (1 - traspasarDefenza/100)) / 100);
        jugador.recibirDanio(danioFinal);
    }
    /*
    public double getAumentarAtaque() { return aumentarAtaque; }
    public double getTraspasarDefenza() { return traspasarDefenza; }
    */
}