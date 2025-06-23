package Clases_del_Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class Juego {
    // Componentes Swing
    private static JFrame ventana;
    public static JTextArea areaTexto;
    private static JPanel panelBotones;
    
    // Elementos del juego
    static Personajes jugador;
    static Enemigos enemigo;


    public static void main(String[] args) {iniciarInterfazGrafica();}

    // ========== CONFIGURACIÓN SWING ==========
    private static void iniciarInterfazGrafica() {
        ventana = new JFrame("Juego de Aventuras");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);

        // Área de texto
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Arial black", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaTexto);

        // Panel de botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        // Redirigir System.out
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                areaTexto.append(String.valueOf((char) b));
                areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
            }
        }));

        ventana.add(scroll, BorderLayout.CENTER);
        ventana.add(panelBotones, BorderLayout.SOUTH);
        ventana.setVisible(true);

        // Iniciar juego
        menuInicio();
    }

    private static void actualizarBotones(String[] textos, ActionListener[] acciones) {
        panelBotones.removeAll();
        for (int i = 0; i < textos.length; i++) {
            JButton boton = new JButton(textos[i]);
            boton.addActionListener(acciones[i]);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelBotones.add(boton);
        }
        ventana.revalidate();
    }

    // ========== MÉTODOS DEL JUEGO ADAPTADOS A SWING ==========
    public static void menuInicio() {
        String[] opciones = {"Nueva Partida", "Salir"};
        ActionListener[] acciones = {
            e -> seleccionDePersonajes(),
            e -> System.exit(0)
        };
        areaTexto.append("=== MENÚ PRINCIPAL ===\n");
        actualizarBotones(opciones, acciones);
    }

    public static void seleccionDePersonajes() {
        String[] opciones = {"Guerrero", "Arquero", "Mago", "Volver"};
        ActionListener[] acciones = {
            e -> { jugador = new Guerrero(100, 20, 15, 0); confirmarEleccion(); },
            e -> { jugador = new Arquero(80, 18, 12, 15); confirmarEleccion(); },
            e -> { jugador = new Mago(70, 15, 10, 80); confirmarEleccion(); },
            e -> menuInicio()
        };
        areaTexto.append("\n=== SELECCIÓN DE PERSONAJE ===\n");
        actualizarBotones(opciones, acciones);
    }

    public static void confirmarEleccion() {
        jugador.mostrarHabilidades();
        jugador.historiaPersonaje();

        String[] opciones = {"Sí, empezar aventura", "No, volver a selección"};
        ActionListener[] acciones = {
            e -> iniciarCombate(),
            e -> seleccionDePersonajes()
        };
        areaTexto.append("\n¿Confirmas tu elección?\n");
        actualizarBotones(opciones, acciones);
    }

    public static void iniciarCombate() {
        int tipoEnemigo = (int)(Math.random() * 3) + 1;
        switch (tipoEnemigo) {
            case 1:
                enemigo = new Goblin(60, 10, 12, 0);
                areaTexto.append("\n¡Un Goblin aparece frente a ti!\n");
                break;
            case 2:
                enemigo = new NoMuerto(80, 14, 8, 0);
                areaTexto.append("\n¡Un No-muerto surge de las sombras!\n");
                break;
            case 3:
                enemigo = new JefeZona(120, 20, 25, 0, 0);
                areaTexto.append("\n¡El Jefe de Zona te desafía!\n");
                break;
        }
        menuCombate();
    }

    public static void menuCombate() {
        if (jugador.getVida() <= 0 || enemigo.getVida() <= 0) {
            if (jugador.getVida() <= 0) {
                areaTexto.append("\n¡Has sido derrotado!\n");
            }
            menuInicio();
            return;
        }

        String[] opciones = {"Atacar", "Habilidad Especial", "Usar objeto"};
        ActionListener[] acciones = {
            e -> { jugador.atacar(enemigo); turnoEnemigo(); },
            e -> { jugador.habilidadEspecial(enemigo); turnoEnemigo(); },
            e -> { 
                jugador.setVida(jugador.getVida() + 30); 
                areaTexto.append("\nUsas una poción curativa (+30 vida)\n"); 
                turnoEnemigo(); 
            }
        };
        actualizarBotones(opciones, acciones);
    }

    private static void turnoEnemigo() {
        if (enemigo.getVida() <= 0) {
            areaTexto.append("\n¡Has derrotado al enemigo!\n");
            menuInicio();
            return;
        }
        enemigo.atacar(jugador);
        if (jugador instanceof Mago) {
            ((Mago)jugador).regenerarMana();
        }
        menuCombate();
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    private static void mostrarMensaje(String mensaje) {
        areaTexto.append(mensaje + "\n");
    }

    //Metodos que se usaran en un futturo//

    /*
     * En esta zona estara la historia principal 
     * 
     * public void historia(){
     *  system.out.println("La Era de los dioses antiguos , Astor era un paraiso primordial donde los Dioses Antiguos goberdaban en Armonia.
     *                      \n Estas deidades , conocidas como los Progenitores moldearon la tierra , los mares y las criaturas que la habitaban.
     *                      \n Su paz termino cuando Zalathor , EL Devorador de Eones , un dios caído de un plano lejano llegó a Astor.")
     * }
     * 
     * ademas que se agregara el metodo para el mapa y el minimapa
     * 
     *      *Aqui se podria crear una clase Mapa la cual cambia dependiendo de las diferentes zonas de la mazmorra 
     *                        
     *          public void mapa(){
     *              static ArrayList<> mapa = new ArrayList<>;    
     *          }
     * 
     * se presentara una presentación con una historia fluida 
     * 
     *  (Se intentara encontrar alguna manera de que se puedan importar videos con banda sonora a la aplicacion)
     * 
     *  por lo que he visto necesitamos usar librerias externas para poder lograr eso
     * 
     * ------------------------------------------------------------------------------
     * tutorial para aprender los comandos graficos 
     * eso se veracuando consiga mas megas xq lo que tengo ahora no da para eso
     * 
     * 
     * ------------------------------------------------------------------------------------------------------------------------------------------------
     * ademas necesito encontrar la manera de poder crear un modelado para el personaje 
     * obviamente se usara modelado 2D y no tendra tantas animaciones pero ese modelado se importara para el ataque ya que pienso hacer un sistema estilo pokemón.
     * 
     * 
     * --------------------------------------------------------------------------------------------------------------------------------------------------------------
     * necesito ademas arreglar algunas cosas en lo que respecta al codigo como por ejemplo ponerle limite al uso de posiones 
     * 
     * 
     * 
     * --------------------------------------------------------------------------------------------------------------------------
     * ademas de que debo añadir mas objetos y cambier algunas cosas en el balance del juego ya que los enemigos son muy debiles 
     * 
     * 
     * 
     * ---------------------------------------------------------------------------------------------------
     * debo continuar tambien desarrollando ademas el menu principal ya que es demasiado soso ya que no es agradable a la vista y no comple con las reglas de oro
     * 
     * 
     * 
     * -----------------------------------------------------------------------------------
     * de ser posible añadir mas personajes y ojala poder crear una interfaz de eleccion de estadisticas
     * 
     * 
     * 
     * 
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * nuevo set de estadisticas las cuales agregadas serían
     * 
     * -carisma (interferiria con las relaciones de los npc dandonos posibles descuentos y ademas ya que quiero que el juego sea lo mas libre posible afectaroia tambien en diversas acciones con npc  y enemigos la cuales ya demen saber ;) )
     * -suerte (interfiere directamente con el chance de golpes criticos y la posibilidad de objetos raros)
     * -aguante (seria como tal un limite de acciones por turno y el peso que podemos aguantar)
     * -fe (para la futura clase clerigo sera como el mana)
     * -karma(dependiendo de nuestras acciones el karma aumenta o disminuye , esta estadistica sera uno de los requisitos para los distintos finales por ejemplo si terminas la aventura con karma negativo desbloquearia en final malo neutral y lo mismo pasaria con el karma positivo)
     * 
     * 
     * 
     * 
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * metodos para la creacion de varios finales alternos 
     * 
     * sería una serie de condicionales las cuales van a estar anidadas a cada accion que elijamos mediente boolean ejemplo (ESTA TAREA SE PUEDE CONCIDERAR PARCIALMENTE LISTA YA QUE SE VERIFICO QUE ESTA SERIE DE CONDICIONALES FUNCIONAN)
     * 
     * usuario elije matar npc (true)
     *      npc muere(ture)
     * usuario elige matar npc(false)
     *      npc ataca(true)
     * usuario se defiende(true)
     *      npc muere(false)
     * usuario se defiende(false)
     *      npc hace ataque exitoso(true)
     * usuario muere(true)
     * 
     * y así de esta manera serían las condiciones para poder desbloquear finales alternos
     * 
     * 
     */

    /*
     * public class Clerigo{
     * 
     *   private double fe;//esta estadistiva va a ser variable ya que va a depender del nivel de karma del clerigo y ademas va a ser la unica que puede ser negativa
     * 
     *   public void(metodos constructores y gethers and sethers)
     * 
     * }
     * 
     * public class Assassin{
     *  
     *   private double sigilo;// aqui jugaremos un poco con la prespectiva del enemigo ya que como tal no se va a poder presenciar en la interfaz pero esta estadistica va  tener una cierta proporcionalidad con la estadistica critico
     *   private double critico;// aqui la aparicion del critical-hit va a ser diferente que del aruqero ya que solo se va  apoder usar una vez por batalla ya que despues de eso el enemigo se dara cuenta de nuestra existencia
     *                         // a menos que se cumplan ciertas condiciones y podamos disminuir la percepcion del enemigo y aprovechar ese chance pero eso lo veremos despues 
     * 
     *   public void(metodos constructores y gethers and sethers)
     * }
     * 
     * 
     */

    /*
     * public void personajesAsset(){
     * 
     *  JPanel personajes = new JPanel();
     *  ImageIcon image = new ImageIcon("Aqui estará la direccion de equipo de las assets para las imagenes de los personajes sean arqueros , magos , guerrero , berserk  y asssassin");
     *  personajes.setBound(50 , 50 , 80 , 40);
     *  personajer.image;
     *  personajes.setColor(Color.BLACK);
     * }
     * 
     * public void enemiAsset(){
     * 
     *  JPanel enemigos = new JPanel();
     *  ImageIcon image = new ImageIcon("Aqui estará la direccion de equipo de las assets para las imagenes de los personajes sean arqueros , magos , guerrero , berserk  y asssassin");
     *  enemigos.setBounds(80 , 50 , 80 , 40);
     *  enemigo.image;
     * 
     * }
     */





}