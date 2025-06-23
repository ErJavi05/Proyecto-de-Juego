package Clases_del_Juego;

public abstract class Personajes {
    protected double vida;
    protected double ataque;
    protected double defenza;
    
    public Personajes(double vida, double ataque, double defenza) {
        this.vida = vida;
        this.ataque = ataque;
        this.defenza = defenza;
    }

    public abstract void atacar(Enemigos enemigo);
    public abstract void habilidadEspecial(Enemigos enemigo);
    public abstract String mostrarHabilidades();
    public abstract String historiaPersonaje();
    
    public void recibirDanio(double cantidad) {
        this.vida = Math.max(0, this.vida - cantidad);
        mostrarMensaje("Recibes " + cantidad + " de daño. Vida restante: " + this.vida);
    }
    
    protected void mostrarMensaje(String mensaje) {
        if (Juego.areaTexto != null) {
            Juego.areaTexto.append(mensaje + "\n");
        } else {
            System.out.println(mensaje);
        }
    }
    
    // Getters y setters
    public double getVida() { return vida; }
    public double getAtaque() { return ataque; }
    public double getDefenza() { return defenza; }
    public void setVida(double vida) { this.vida = vida; }
    public void setAtaque(double ataque) { this.ataque = ataque; }
    public void setDefenza(double defenza) { this.defenza = defenza; }
}