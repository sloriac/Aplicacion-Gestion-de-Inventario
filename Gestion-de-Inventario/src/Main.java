import java.util.ArrayList;
import java.util.Scanner;

//Rutina main que contiene el menu y permite la ejecucion del programa

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Tienda tienda = new Tienda();

    public static void main(String[] args) {
        menu(); //Punto de entrada del programa, llamada al menu que permite acceder al resto de opciones
    }

    //Metodo que hace que el programa no se caiga si se digitan letras en vez de numeros
    public static int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            int numeroI;
            try {
                numeroI = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero.");
                continue;
            }
            if (numeroI < min || numeroI > max) {
                System.out.println("El entero debe estar entre " + min + " y " + max + "\n");
            } else {
                return numeroI;
            }
        }
    }

    //Lee numeros doubles y no se cae si se ingresan letras
    public static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            double numeroD;
            try {
                numeroD = Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero.");
                continue;
            }
            if (numeroD <= 0) {
                System.out.println("El numero debe ser mayor a cero. \n");
            } else {
                return numeroD;
            }
        }
    }

    // MENÚ PRINCIPAL
    public static void menu() {

        int opcion;

        do {
            System.out.println();
            System.out.println("");
            System.out.println("   **** SISTEMA DE GESTIÓN DE INVENTARIO **** ");
            System.out.println("");
            System.out.println("   ----- INVENTARIO -----");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Modificar producto");
            System.out.println("3. Agregar imagen a producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Ver inventario");
            System.out.println("   ----- CLIENTES -----");
            System.out.println("6. Registrar cliente");
            System.out.println("7. Atender al siguiente cliente");
            System.out.println("8. Ver cola de clientes");
            System.out.println("");
            System.out.println("9. Salir del programa");
            System.out.println("");

            opcion = leerEntero("Seleccione una opcion: ", 1, 9);

            switch (opcion) {

                case 1:
                    insertarProducto();
                    break;

                case 2:
                    modificarProducto();
                    break;

                case 3:
                    agregarImagen();
                    break;

                case 4:
                    eliminarProducto();
                    break;

                case 5:
                    tienda.listarInventario();
                    break;

                case 6:
                    registrarCliente();
                    break;

                case 7:
                    atenderCliente();
                    break;

                case 8:
                    tienda.listarCola();
                    break;

                case 9:
                    System.out.println();
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 9);

        scanner.close();
    }

    // INSERTAR PRODUCTO
    public static void insertarProducto() {

        System.out.println();
        System.out.println("**** REGISTRO DE PRODUCTO ****");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        double precio = leerDouble("Precio: ");

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Fecha de vencimiento (escriba N/A si no aplica): ");
        String fechaVencimiento = scanner.nextLine();

        if (fechaVencimiento.equalsIgnoreCase("N/A")) {
            fechaVencimiento = null;
        }

        //Maximo generico
        int cantidad = leerEntero("Cantidad: ", 1, 10000);

        ArrayList<String> listaImagenes = new ArrayList<>();

        System.out.print("¿Desea agregar una imagen? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Ruta de la imagen: ");
            String rutaImagen = scanner.nextLine();
            listaImagenes.add(rutaImagen);
        }

        Producto producto = new Producto(
                nombre,
                precio,
                categoria,
                fechaVencimiento,
                cantidad,
                listaImagenes
        );

        tienda.agregarProducto(producto);
    }

    //Metodo que permite registrar un cliente
    public static void registrarCliente() {
        if (tienda.inventarioVacio()) {
            return;
        }
        //Pedir datos del cliente
        int idCliente = leerEntero("Ingrese el ID del cliente: ", 1, 100000); //Rango generico
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        int prioridadCliente = leerEntero("Digite la prioridad del cliente (1 Basico, 2 Afiliado, 3 Premium): ", 1, 3);
        //Crear el cliente con los datos pedidos
        Cliente cliente = new Cliente(
                idCliente,
                nombreCliente,
                prioridadCliente
        );

        //Llenar el carrito del cliente
        String respuesta;
        do { //Muestra el inventario por primera vez para el cliente vea que y cuanto queda
            tienda.listarInventario();
            System.out.print("Digite el nombre del producto a agregar al carrito: ");
            String nombreProducto = scanner.nextLine();
            int cantidadProducto = leerEntero("Digite la cantidad que desea agregar al carrito: ", 1, 10000);
            //Agregar al carrito
            if (tienda.agregarAlCarrito(cliente, nombreProducto, cantidadProducto)) {
                System.out.println("Producto agregado al carrito.");
            }
            //Consultar si desea agregar otro producto
            System.out.print("Desea agregar otro producto? (s/n): ");
            respuesta = scanner.nextLine().strip();
        } while (respuesta.equalsIgnoreCase("s"));
        if (cliente.getCarrito().estaVacia()) {
            System.out.println("El cliente no se registro, ya que no compro nada.");
        } else {
            tienda.registrarCliente(cliente);
            System.out.println("Cliente registrado con exito");
        }
    }

    //Atender al cliente con factura y ver la cola
    public static void atenderCliente() {
        if (tienda.colaVacia()) {return;}
        else {tienda.atenderSiguiente();}
    }

    // MODIFICAR PRODUCTO
    public static void modificarProducto() {

        System.out.println();
        System.out.println("**** MODIFICAR PRODUCTO ****");
        if (tienda.inventarioVacio()) {return;}

        System.out.print("Nombre del producto que desea modificar: ");
        String nombreActual = scanner.nextLine();

        double nuevoPrecio = leerDouble("Nuevo precio: ");

        System.out.print("Nueva categoría: ");
        String nuevaCategoria = scanner.nextLine();

        System.out.print("Nueva fecha de vencimiento (N/A si no aplica): ");
        String nuevaFechaVencimiento = scanner.nextLine();

        if (nuevaFechaVencimiento.equalsIgnoreCase("N/A")) {
            nuevaFechaVencimiento = null;
        }

        int nuevaCantidad = leerEntero("Nueva cantidad: ", 0, 100000);

        boolean modificado = tienda.modificarProducto(
                nombreActual,
                nuevoPrecio,
                nuevaCategoria,
                nuevaFechaVencimiento,
                nuevaCantidad
        );

        if (modificado) {
            System.out.println();
            System.out.println("Producto modificado correctamente.");
        }
    }

    // AGREGAR IMAGEN
    public static void agregarImagen() {

        System.out.println();
        System.out.println("**** AGREGAR IMAGEN ****");
        if (tienda.inventarioVacio()) {
            return;
        }

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ruta de la imagen: ");
        String rutaImagen = scanner.nextLine();

        boolean agregada = tienda.agregarImagenProducto(
                nombre,
                rutaImagen
        );

        if (agregada) {
            System.out.println();
            System.out.println("Imagen agregada correctamente.");
        }
    }

    // ELIMINAR PRODUCTO
    public static void eliminarProducto() {

        System.out.println();
        System.out.println("**** ELIMINAR PRODUCTO ****");
        if (tienda.inventarioVacio()) {return;}

        System.out.print("Nombre del producto que desea eliminar: ");
        String nombre = scanner.nextLine();

        Producto eliminado = tienda.eliminarProducto(nombre);

        if (eliminado != null) {
            System.out.println();
            System.out.println("Producto eliminado correctamente.");
            System.out.println("Producto eliminado: " + eliminado.getNombre());
        }
    }
}