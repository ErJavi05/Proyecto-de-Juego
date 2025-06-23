package Clases_del_Juego;

public class Guerrero extends Personajes {
    private double furia;

    public Guerrero(double vida, double ataque, double defenza, double furia) {
        super(vida, ataque, defenza);
        this.furia = furia;
    }
    
    @Override
    public void atacar(Enemigos enemigo) {
        double danio = this.ataque * (1 + this.furia/100);
        mostrarMensaje("¡Golpe con espada! (" + danio + " de daño)");
        enemigo.recibirDanio(danio);
    }
    
    @Override
    public void habilidadEspecial(Enemigos enemigo) {
        mostrarMensaje("¡Furia desatada! Ataque aumentado al máximo");
        this.furia = 100;
    }
    
    @Override
    public String mostrarHabilidades() {
        String stats = "\n=== ESTADÍSTICAS DEL GUERRERO ===" +
            "\nVida: " + this.vida +
            "\nAtaque: " + this.ataque +
            "\nDefensa: " + this.defenza +
            "\nFuria: " + this.furia + "/100";
        mostrarMensaje(stats);
        return stats;
    }
    
    @Override
    public String historiaPersonaje() {
        String historia = "\n=== HISTORIA DEL GUERRERO ===" +
            "\nPaul, era un aldeano el cual en busca de aventuras se unió al gremio de aventureros." +
            "\nTeniendo grandes dotes por el manejo de la espada decidió hacerse un guerrero." +
            "\nLo último que se supo de él fue que partió en busca de una mazmorra en las afueras del continente Astor.";
        mostrarMensaje(historia);
        return historia;
    }
    
    public void acumularFuria(double danioRecibido) {
        this.furia = Math.min(100, this.furia + danioRecibido * 0.5);
    }
    
    public double getFuria() { return furia; }
}