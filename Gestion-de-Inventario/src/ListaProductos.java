public class ListaProductos {

    //  ATRIBUTOS

    private Nodo cabeza;   // Primer nodo de la lista (null si está vacía)
    private int tamano;    // Cantidad de nodos en la lista

    //  CONSTRUCTOR

    public ListaProductos() {
        this.cabeza = null;
        this.tamano = 0;
    }

    //  CONSULTAS BÁSICAS


    public boolean estaVacia() {
        return cabeza == null;
    }


    public int getTamano() {
        return tamano;
    }

    //  INSERCIONES

    public void insertarAlInicio(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        Nodo nuevo = new Nodo(producto);
        // El orden de estas dos líneas es importante:
        nuevo.siguiente = cabeza;  // 1. el nuevo apunta a la antigua cabeza
        cabeza = nuevo;            // 2. el nuevo pasa a ser la cabeza
        tamano++;
    }


    public void insertarAlFinal(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        Nodo nuevo = new Nodo(producto);

        if (cabeza == null) {
            // Lista vacía: el nuevo nodo es la cabeza
            cabeza = nuevo;
        } else {
            // Se recorre hasta el último nodo (el que no tiene siguiente)
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamano++;
    }

        // MODIFICACIÓN DE PRODUCTOS

    public boolean modificarProducto(String nombreActual, String nuevoNombre,
            double nuevoPrecio, String nuevaCategoria,
            String nuevaFechaVencimiento, int nuevaCantidad) {

        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return false;
        }

        Nodo actual = cabeza;

        while (actual != null) {
            if (nombreActual.equals(actual.producto.getNombre())) {

                actual.producto.setNombre(nuevoNombre);
                actual.producto.setPrecio(nuevoPrecio);
                actual.producto.setCategoria(nuevaCategoria);
                actual.producto.setFechaVencimiento(nuevaFechaVencimiento);
                actual.producto.setCantidad(nuevaCantidad);

                return true;
            }

            actual = actual.siguiente;
        }

        System.out.println("El producto no existe.");
        return false;
    }


    // AGREGAR IMAGEN A UN PRODUCTO

    public boolean agregarImagenProducto(String nombre, String rutaImagen) {

        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return false;
        }

        Nodo actual = cabeza;

        while (actual != null) {
            if (nombre.equals(actual.producto.getNombre())) {

                actual.producto.agregarImagen(rutaImagen);

                return true;
            }

            actual = actual.siguiente;
        }

        System.out.println("El producto no existe.");
        return false;
    }


    // ELIMINACIÓN DE PRODUCTOS

    public Producto eliminarProducto(String nombre) {

        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return null;
        }

        Nodo anterior = null;
        Nodo actual = cabeza;

        while (actual != null) {

            if (nombre.equals(actual.producto.getNombre())) {

                if (actual == cabeza) {
                    cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }

                tamano--;

                return actual.producto;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        System.out.println("El producto no existe.");
        return null;
    }

    //  REPORTE DE COSTOS


    public double calcularCostoTotalLista() {
        double total = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            total += actual.producto.calcularCostoTotal();
            actual = actual.siguiente;
        }
        return total;
    }


    public void imprimirReporte() {
        if (estaVacia()) {
            System.out.println("No hay productos registrados en el inventario.");
            return;
        }

        System.out.println("\n========== REPORTE DE COSTOS ==========");
        System.out.printf("%-4s %-20s %8s %12s %14s%n",
                "N°", "Producto", "Cant.", "Precio", "Costo total");
        System.out.println("-------------------------------------------------------------");

        int numero = 1;
        Nodo actual = cabeza;
        while (actual != null) {
            Producto p = actual.producto;
            System.out.printf("%-4d %-20s %8d %,12.2f %,14.2f%n",
                    numero, p.getNombre(), p.getCantidad(),
                    p.getPrecio(), p.calcularCostoTotal());
            numero++;
            actual = actual.siguiente;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("TOTAL ACUMULADO: ₡%,.2f%n", calcularCostoTotalLista());
        System.out.println("=============================================================");
    }
}