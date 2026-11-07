public class Nodo {
    //Atributos
    private Producto producto;
    private Nodo siguiente;

    //Constructor
    public Nodo(Producto producto) {
        this.producto = producto;
        siguiente = null;
    }

    //Getter
    public Nodo getSiguiente() {return siguiente;}
    public Producto getProducto() {return producto;}
    //Setter
    public void setSiguiente(Nodo siguiente) {this.siguiente = siguiente;}
    public void setProducto(Producto producto) {this.producto = producto;}
    //toString
    @Override
    public String toString() {return "\nProducto: " + producto + "\n";}

}
