package Clases_del_Juego;

public class NoMuerto extends Enemigos {
    private double envenenar;

    public NoMuerto(double vida, double defenza, double ataque, double envenenar) {
        super(vida, defenza, ataque);
        this.envenenar = envenenar;
    }

    @Override
    public void atacar(Personajes jugador) {
        double danio = this.ataque;
        mostrarMensaje("¡El no-muerto ataca con sus garras!");
        jugador.recibirDanio(danio);
        
        if (Math.random() * 100 < this.envenenar) {
            mostrarMensaje("¡Has sido envenenado! (Pierdes 5 de vida por 3 turnos)");
            // Lógica de veneno (implementar según necesidad)
        }
    }

    public double getEnvenenar() { return envenenar; }
}