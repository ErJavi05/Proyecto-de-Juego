package Clases_del_Juego;

public class Mago extends Personajes {
    private double mana;
    private double manaMaximo;

    public Mago(double vida, double ataque, double defenza, double mana) {
        super(vida, ataque, defenza);
        this.mana = mana;
        this.manaMaximo = mana;
    }

    @Override
    public void atacar(Enemigos enemigo) {
        if (this.mana >= 10) {
            double danio = this.ataque * 1.5;
            mostrarMensaje("¡Hechizo arcano! (" + danio + " de daño)");
            enemigo.recibirDanio(danio);
            this.mana -= 10;
        } else {
            mostrarMensaje("¡Golpe con bastón! (" + (this.ataque * 0.5) + " de daño)");
            enemigo.recibirDanio(this.ataque * 0.5);
        }
    }

    @Override
    public void habilidadEspecial(Enemigos enemigo) {
        if (this.mana >= 30) {
            double danio = this.ataque * 3;
            mostrarMensaje("¡Bola de fuego! (" + danio + " de daño)");
            enemigo.recibirDanio(danio);
            this.mana -= 30;
        } else {
            mostrarMensaje("No tienes suficiente maná (necesitas 30)");
        }
    }

    @Override
    public String mostrarHabilidades() {
        String stats = "\n=== ESTADÍSTICAS DEL MAGO ===" +
            "\nVida: " + this.vida +
            "\nAtaque: " + this.ataque +
            "\nDefensa: " + this.defenza +
            "\nManá: " + this.mana + "/" + this.manaMaximo;
        mostrarMensaje(stats);
        return stats;
    }

    @Override
    public String historiaPersonaje() {
        String historia = "\n=== HISTORIA DEL MAGO ===" +
            "\nFerdinand, mago de la corte real, escuchó historias sobre un tomo de hechizos antiguos." +
            "\nReuniendo pistas logró encontrar su ubicación aproximada: la mazmorra del Gran Rey." +
            "\nCon ansias de poder y conocimiento decidió ir a buscarlo.";
        mostrarMensaje(historia);
        return historia;
    }

    public void regenerarMana() {
        this.mana = Math.min(this.manaMaximo, this.mana + 5);
        mostrarMensaje("Maná regenerado: " + this.mana + "/" + this.manaMaximo);
    }

    public double getMana() { return mana; }
    public double getManaMaximo() { return manaMaximo; }
}