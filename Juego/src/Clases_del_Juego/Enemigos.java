package Clases_del_Juego;

public abstract class Enemigos {
    protected double vida;
    protected double defenza;
    protected double ataque;
    
    public Enemigos(double vida, double defenza, double ataque) {
        this.vida = vida;
        this.defenza = defenza;
        this.ataque = ataque;
    }

    public abstract void atacar(Personajes jugador);
    
    public void recibirDanio(double cantidad) {
        this.vida = Math.max(0, this.vida - cantidad);
        mostrarMensaje("El enemigo recibe " + cantidad + " de daño. Vida restante: " + this.vida);
    }

    protected void mostrarMensaje(String mensaje) {
        if (Juego.areaTexto != null) {
            Juego.areaTexto.append(mensaje + "\n");
        } else {
            System.out.println(mensaje);
        }
    }

    public double getVida() { return vida; }
    public double getDefenza() { return defenza; }
    public double getAtaque() { return ataque; }
}