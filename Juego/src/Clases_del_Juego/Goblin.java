package Clases_del_Juego;

public class Goblin extends Enemigos {
    private double rabia;

    public Goblin(double vida, double defenza, double ataque, double rabia) {
        super(vida, defenza, ataque);
        this.rabia = rabia;
    }

    @Override
    public void atacar(Personajes jugador) {
        double danio = this.ataque;
        mostrarMensaje("¡El goblin ataca con su garrote!");
        jugador.recibirDanio(danio);
    }

    public double getRabia() { return rabia; }
}