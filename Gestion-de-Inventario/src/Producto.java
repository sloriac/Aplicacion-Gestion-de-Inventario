//Clase producto, contiene atributos, constructor, getters, setters

import java.util.ArrayList;

public class Producto {
    //Atributos
    String nombre;
    double precio;
    String categoria;
    String fechaVencimiento = null; //Comienza en null, ya que no es obligatorio que tenga fecha de vencimiento, solo si aplica
    int cantidad; //Unidades del producto
    ArrayList<String> listaImagenes; //Almacena la ruta de las imagenes

    //METODOS
    //Constructor
    public Producto (String nombre, double precio, String categoria, String fechaVencimiento, int cantidad, ArrayList<String> listaImagenes) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList();
    }
    //Getter
    public String getNombre() {return nombre;}
    public double getPrecio() {return precio;}
    public String getCategoria() {return categoria;}
    public String getFechaVencimiento() {return fechaVencimiento;}
    public int getCantidad() {return cantidad;}
    public ArrayList<String> getListaImagenes() {return listaImagenes;}
    //Setter
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPrecio(double precio) {this.precio = precio;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setFechaVencimiento(String fechaVencimiento) {this.fechaVencimiento = fechaVencimiento;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setListaImagenes(ArrayList<String> listaImagenes) {this.listaImagenes = listaImagenes;}
    //Metodo que agrega una ruta a listaimagenes
    public void agregarImagen(String rutaImagen) {listaImagenes.add(rutaImagen);}

    //Realiza un calculo con precio y cantidad, devuelve un double del precio
    public double calcularCostoTotal() {return precio * cantidad;}

    //toString()
    public String toString() {return "\nNombre: " + nombre + "\nPrecio: " + precio + "\nCategoria: " + categoria + "\nFecha de Vencimiento: " + fechaVencimiento + "\nCantidad: " + cantidad + "\nImagenes: " + listaImagenes + "\n";}
}
