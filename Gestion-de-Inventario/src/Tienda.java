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
    public void listarCola() {
        cola.listar();
    }

    private void imprimirFactura(Cliente cliente) {
        System.out.println("\n========== FACTURA DE LA COMPRA ==========");
        System.out.println("----------- DATOS DEL CLIENTE -----------");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getNombreCliente());
        System.out.println("Prioridad: " + cliente.getPrioridad() + " Tipo de Prioridad: " + cliente.getTipoPrioridad());
        cliente.getCarrito().imprimirReporte("DETALLE DE LA COMPRA");
    }
}
