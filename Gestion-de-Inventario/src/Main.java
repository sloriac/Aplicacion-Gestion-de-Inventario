import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ListaProductos listaProductos = new ListaProductos();

    public static void main(String[] args) {
        menu();
    }

    // MENÚ PRINCIPAL
    public static void menu() {

        int opcion;

        do {
            System.out.println();
            System.out.println("");
            System.out.println("   ****SISTEMA DE GESTIÓN DE INVENTARIO**** ");
            System.out.println("");
            System.out.println("1. Insertar producto al inicio");
            System.out.println("2. Insertar producto al final");
            System.out.println("3. Modificar producto");
            System.out.println("4. Agregar imagen a producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Mostrar reporte de costos");
            System.out.println("7. Salir");
            System.out.println("");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    insertarProducto(true);
                    break;

                case 2:
                    insertarProducto(false);
                    break;

                case 3:
                    modificarProducto();
                    break;

                case 4:
                    agregarImagen();
                    break;

                case 5:
                    eliminarProducto();
                    break;

                case 6:
                    mostrarReporte();
                    break;

                case 7:
                    System.out.println();
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 7);

        scanner.close();
    }

    // INSERTAR PRODUCTO
    public static void insertarProducto(boolean alInicio) {

        System.out.println();
        System.out.println("**** REGISTRO DE PRODUCTO ****");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Fecha de vencimiento (escriba N/A si no aplica): ");
        String fechaVencimiento = scanner.nextLine();

        if (fechaVencimiento.equalsIgnoreCase("N/A")) {
            fechaVencimiento = null;
        }

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

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

        if (alInicio) {
            listaProductos.insertarAlInicio(producto);
            System.out.println();
            System.out.println("Producto agregado al inicio correctamente.");
        } else {
            listaProductos.insertarAlFinal(producto);
            System.out.println();
            System.out.println("Producto agregado al final correctamente.");
        }
    }

    // MODIFICAR PRODUCTO
    public static void modificarProducto() {

        System.out.println();
        System.out.println("**** MODIFICAR PRODUCTO ****");

        System.out.print("Nombre del producto que desea modificar: ");
        String nombreActual = scanner.nextLine();

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Nuevo precio: ");
        double nuevoPrecio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Nueva categoría: ");
        String nuevaCategoria = scanner.nextLine();

        System.out.print("Nueva fecha de vencimiento (N/A si no aplica): ");
        String nuevaFechaVencimiento = scanner.nextLine();

        if (nuevaFechaVencimiento.equalsIgnoreCase("N/A")) {
            nuevaFechaVencimiento = null;
        }

        System.out.print("Nueva cantidad: ");
        int nuevaCantidad = scanner.nextInt();
        scanner.nextLine();

        boolean modificado = listaProductos.modificarProducto(
                nombreActual,
                nuevoNombre,
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

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ruta de la imagen: ");
        String rutaImagen = scanner.nextLine();

        boolean agregada = listaProductos.agregarImagenProducto(
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

        System.out.print("Nombre del producto que desea eliminar: ");
        String nombre = scanner.nextLine();

        Producto eliminado = listaProductos.eliminarProducto(nombre);

        if (eliminado != null) {
            System.out.println();
            System.out.println("Producto eliminado correctamente.");
            System.out.println("Producto eliminado: " + eliminado.getNombre());
        }
    }

    // MOSTRAR REPORTE
    public static void mostrarReporte() {
        listaProductos.imprimirReporte();
    }


}