//Clase que almacena y conecta las estructuras codificadas para el correcto funcionamiento del flujo de la tienda
public class Tienda {
    //Atributos
    private ArbolProductos inventario;
    private ColaClientes cola;
    //Constructor
    public Tienda() {
        this.inventario = new ArbolProductos();
        this.cola = new ColaClientes();
    }
    //Operaciones
    public void agregarProducto(Producto producto) {
        inventario.insertar(producto);
    }
    public boolean agregarAlCarrito(Cliente cliente, String nombreProducto, int cantidad) {
        Producto producto = inventario.buscar(nombreProducto);
        if (producto == null ) {
            return false;
        }
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return false;
        } else if (cantidad > producto.getCantidad()) {
            System.out.println("La cantidad seleccionada es mayor a la que se encuentra en el inventario.");
            return false;
        }
        Producto copia = new Producto(producto.getNombre(), producto.getPrecio(), producto.getCategoria(), producto.getFechaVencimiento(), cantidad, null);
        cliente.agregarAlCarrito(copia);
        producto.setCantidad(producto.getCantidad()-cantidad);

        return true;
    }
    public void registrarCliente(Cliente cliente) {
        cola.encolar(cliente);
    }
    public Cliente atenderSiguiente() {
        Cliente clienteAtender = cola.atender();
        if (clienteAtender == null) {
            return null;
        }
        imprimirFactura(clienteAtender);
        return clienteAtender;
    }
    //Lista la cola de clientes a atender
    public void listarCola() {
        cola.listar();
    }
    //Lista el inventario de toda la tienda
    public void listarInventario() {inventario.enOrden();}
    //Dice si no hay productos
    public boolean inventarioVacio() {
        if (inventario.estaVacio()) {
            System.out.println("El inventario esta vacio.");
            return true;
        }
        return false;
    }
    //Dice si no hay clientes
    public boolean colaVacia() {
        if (cola.estaVacia()) {
            System.out.println("La cola esta vacia, no hay clientes por atender.");
            return true;
        }
        return false;
    }
    //Elimina un producto, verifica que no este vacio, si existe lo elimina
    public Producto eliminarProducto(String nombre) {
        if (inventario.estaVacio()) {
            System.out.println("El inventario esta vacio.");
            return null;
        }
        Producto productoEliminar = inventario.eliminar(nombre);
        return productoEliminar;
    }

    private void imprimirFactura(Cliente cliente) {
        System.out.println("\n========== FACTURA DE LA COMPRA ==========");
        System.out.println("----------- DATOS DEL CLIENTE -----------");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getNombreCliente());
        System.out.println("Prioridad: " + cliente.getPrioridad() + " Tipo de Prioridad: " + cliente.getTipoPrioridad());
        cliente.getCarrito().imprimirReporte("DETALLE DE LA COMPRA");
    }

    //Permite agregar una imagen a un producto
    public boolean agregarImagenProducto(String nombre, String rutaImagen) {
        Producto producto = inventario.buscar(nombre);
        if (producto != null) {
            producto.agregarImagen(rutaImagen);
            return true;
        } else {
            return false;
        }
    }

    //Permite modificar los atributos de un producto existente, el nombre no se modifica porque es la clave que permite buscarlo
    public boolean modificarProducto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        Producto producto = inventario.buscar(nombre);
        if (producto == null) {
            return false;
        } else {
            producto.setPrecio(precio);
            producto.setCategoria(categoria);
            producto.setFechaVencimiento(fechaVencimiento);
            producto.setCantidad(cantidad);
            return true;
        }

    }
}
