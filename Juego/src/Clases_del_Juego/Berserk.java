package Clases_del_Juego;

public class Berserk extends Personajes{

    private double rabia;

    public Berserk(double vida, double ataque, double defenza, double rabia) {
        super(vida, ataque, defenza);
        this.rabia = rabia;
    }

    @Override
    public void atacar(Enemigos enemigo) {
    }

    @Override
    public void habilidadEspecial(Enemigos enemigo) {

    }

    @Override
    public String mostrarHabilidades() {
        return "";
    }

    @Override
    public String historiaPersonaje() {
        return "";
    }
}
