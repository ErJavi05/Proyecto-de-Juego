package Clases_del_Juego;

public class Arquero extends Personajes {
    private double critico;
    private int flechasRapidas;

    public Arquero(double vida, double ataque, double defenza, double critico) {
        super(vida, ataque, defenza);
        this.critico = critico;
        this.flechasRapidas = 0;
    }

    @Override
    public void atacar(Enemigos enemigo) {
        double danio = this.ataque;
        boolean esCritico = Math.random() * 100 < this.critico;
        
        if (esCritico) {
            danio *= 2;
            mostrarMensaje("¡Disparo crítico! (" + danio + " de daño)");
        } else {
            mostrarMensaje("¡Flecha precisa! (" + danio + " de daño)");
        }
        
        enemigo.recibirDanio(danio);
        
        if (Math.random() < 0.3) {
            this.flechasRapidas++;
            mostrarMensaje("Flechas rápidas acumuladas: " + this.flechasRapidas + "/5");
        }
    }

    @Override
    public void habilidadEspecial(Enemigos enemigo) {
        if (this.flechasRapidas >= 5) {
            mostrarMensaje("¡Lluvia de flechas!");
            for (int i = 0; i < 3; i++) {
                atacar(enemigo);
            }
            this.flechasRapidas = 0;
        } else {
            mostrarMensaje("Necesitas 5 flechas rápidas (tienes " + this.flechasRapidas + ")");
        }
    }

    @Override
    public String mostrarHabilidades() {
        String stats = "\n=== ESTADÍSTICAS DEL ARQUERO ===" +
            "\nVida: " + this.vida +
            "\nAtaque: " + this.ataque +
            "\nDefensa: " + this.defenza +
            "\nProb. crítico: " + this.critico + "%" +
            "\nFlechas rápidas: " + this.flechasRapidas + "/5";
        mostrarMensaje(stats);
        return stats;
    }

    @Override
    public String historiaPersonaje() {
        String historia = "\n=== HISTORIA DEL ARQUERO ===" +
            "\nMax, siendo un elfo del gran bosque era diferente a sus compatriotas." +
            "\nAnsiaba la libertad como ningún otro." +
            "\nUn día decidió salir de su hogar y explorar el mundo." +
            "\nEn su camino escuchó historias sobre una mazmorra fuera del continente.";
        mostrarMensaje(historia);
        return historia;
    }

    public double getCritico() { return critico; }
    public int getFlechasRapidas() { return flechasRapidas; }
}