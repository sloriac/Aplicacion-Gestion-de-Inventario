//Clase que almacena clientes, con sus metodos estandar
public class Cliente {

    //Atributos
    private int id;
    private String nombreCliente;
    private int prioridad;
    private ListaProductos carrito;

    //Constructor
    public Cliente(int id, String nombreCliente, int prioridad) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        setPrioridad(prioridad);
        this.carrito = new ListaProductos();
    }

    //Getters
    public int getId() {return id;}
    public String getNombreCliente() {return nombreCliente;}
    public int getPrioridad() {return prioridad;}
    public String getTipoPrioridad() {
        if (prioridad == 1) {return "Basico";}
        else if (prioridad == 2) {return "Afiliado";}
        else if (prioridad == 3) {return "Premium";}
        return "";
    }
    public ListaProductos getCarrito() {return carrito;}

    //Setters
    public void setNombreCliente(String nombreCliente) {this.nombreCliente = nombreCliente;}
    public void setPrioridad(int prioridad) {
        if (prioridad > 3 || prioridad < 1) {throw new IllegalArgumentException("La prioridad solo puede ser 1 (básico), 2 (afiliado) o 3 (premium).");}
        this.prioridad = prioridad;
    }
    public void agregarAlCarrito(Producto producto) {carrito.insertarAlFinal(producto);}


    //toString
    @Override
    public String toString() {return "\nid: " + id + "\nNombre: " + nombreCliente + "\nPrioridad: " + prioridad + " " + "(" + getTipoPrioridad() + ")" + "\n";}
}
