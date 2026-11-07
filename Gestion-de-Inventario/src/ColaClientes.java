import java.util.Enumeration;

public class ColaClientes {
    //Clase NodoCola
    private class NodoCola {
        //Atributos
        private Cliente cliente;
        private NodoCola siguiente;
        //Constructor
        public NodoCola(Cliente cliente) {
            this.cliente = cliente;
            this.siguiente = null;
        }
        //Getters
        public Cliente getCliente() {return cliente;}
        public NodoCola getSiguiente() {return siguiente;}
        //Setters
        public void setCliente(Cliente cliente) {this.cliente = cliente;}
        public void setSiguiente(NodoCola siguiente) {this.siguiente = siguiente;}
        //toString
        @Override
        public String toString() {return "\nCliente: " + cliente + "\n";}
    }

    //Atributos
    private NodoCola frente;
    private NodoCola fin;
    private int tamano;
    //Constructor
    public ColaClientes() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }
    //Getters
    public int getTamano() {return  tamano;}
    //Operaciones
    public boolean estaVacia() {return frente == null;}
    public void encolar(Cliente cliente) {
        if (cliente == null) {throw new IllegalArgumentException("El cliente no puede ser null.");}
        NodoCola nuevo = new NodoCola(cliente);

        //Insercion al inicio por prioridad
        if (estaVacia() || cliente.getPrioridad() > frente.getCliente().getPrioridad()) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
            if (fin == null) {fin = nuevo;}
            tamano++;
            return;
        }
        //Insercion en el medio por prioridad
        NodoCola anterior = frente;
        NodoCola actual = frente.getSiguiente();
        while (actual != null && actual.getCliente().getPrioridad() >= cliente.getPrioridad()) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        //Insercion al final o en medio si no hay prioridad
        if (actual == null) {
            //Si no hay nadie con menor prioridad se coloca al final
            fin.setSiguiente(nuevo);
            fin = nuevo;
        } else {
            //Insercion en el medio
            nuevo.setSiguiente(actual);
            anterior.setSiguiente(nuevo);
        }
        tamano++;
    }
    public Cliente atender() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia, no hay clientes por atender.");
            return null;
        }
        Cliente clienteAtender = frente.getCliente();
        frente = frente.getSiguiente();
        if (frente == null) {fin = frente;}
        tamano--;
        return clienteAtender;
    }
    public void listar() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia, no hay clientes a listar.");
            return;
        }
        System.out.println("\n========== COLA DE CLIENTES ==========");
        System.out.printf("%-4s %6s %-20s %4s %-10s %9s%n",
                "N°", "ID", "Nombre", "Pri.", "Tipo", "Productos");
        System.out.println("-------------------------------------------------------------");
        int ciclo = 1;
        NodoCola actual = frente;
        while (actual != null) {
            Cliente cliente = actual.getCliente();
            System.out.printf("%-4d %6d %-20s %4d %-10s %9d%n",
                    ciclo, cliente.getId(), cliente.getNombreCliente(), cliente.getPrioridad(),
                    cliente.getTipoPrioridad(), cliente.getCarrito().getTamano());
            ciclo++;
            actual = actual.getSiguiente();
        }
    }
}
