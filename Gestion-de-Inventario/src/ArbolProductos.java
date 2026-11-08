//Arbol binario de busqueda que almacena el inventario de la tienda
public class ArbolProductos {
    //Atributos
    private NodoArbol raiz;

    //Constructor
    public ArbolProductos() {raiz = null;}

    //Getter
    private NodoArbol getRaiz() {return raiz;}

    //Setter
    private void setRaiz(NodoArbol raiz) {this.raiz = raiz;}

    //Operaciones
    public boolean estaVacio() {return raiz == null;}

    public void insertar(Producto producto) {
        if (producto == null) {
            System.out.println("El producto no puede ser null");
            return;
        }
        //Primero se construye el nodo que se va a insertar - ya
        //Si esta vacio se inserta el nodo nuevo en la raiz con setRaiz - ya
        //Si no, se declaran las variables temp y padreTemp e inician ambas en la raiz - ya
        //En un ciclo se hacen los movimientos necesarios con padreTemp y temp mientras que temp != null - ya
        //Detro del ciclo, cada vez que temp se mueve se verifica si llego a un nodo cuya clave es igual a la clave del nodo nuevo - ya
        //Si se cumple se da un mensaje y se retorna prematuramente (se encontro el numero que se queria insertar) - ya
        //Cuando el ciclo termina, se cumple que temp == null, se insertar el nodo nuevo como hijo de padreTemp
        String nombreProducto = producto.getNombre();

        //Constructor
        NodoArbol nodo = new NodoArbol(producto);
        if (estaVacio()) {
            setRaiz(nodo);
            return;
        }
        NodoArbol temp = raiz;
        NodoArbol padreTemp = temp;
        while (temp != null) {
            padreTemp = temp;
            if ((temp.getClave().compareToIgnoreCase(nombreProducto)) > 0) {temp = temp.getIzquierda();}
            else if ((temp.getClave().compareToIgnoreCase(nombreProducto)) < 0) {temp = temp.getDerecha();}
            else{
                System.out.println("El producto ya esta en el inventario.\n");
                return;
            }
        }
        if ((padreTemp.getClave().compareToIgnoreCase(nombreProducto)) > 0) {padreTemp.setIzquierda(nodo);}
        else {padreTemp.setDerecha(nodo);}
    }

    private NodoArbol buscarNodo(String nombre) {
        if (estaVacio()) {
            System.out.println("El arbol esta vacio");
            return null;
        }
        NodoArbol temp = raiz;
        while (temp != null) {
            if ((temp.getClave().compareToIgnoreCase(nombre)) > 0) {temp = temp.getIzquierda();}
            else if ((temp.getClave().compareToIgnoreCase(nombre) ) < 0) {temp = temp.getDerecha();}
            else {return temp;}
        }
        System.out.println("La clave no existe en el arbol");
        return null;
    }

    public Producto buscar(String nombre) {
        NodoArbol nodoBuscar = buscarNodo(nombre);
        if (nodoBuscar != null) {return nodoBuscar.getProducto();}
        else {return null;}
    }

    private NodoArbol getPadre(String nombre) {
        NodoArbol temp = raiz;
        NodoArbol padreTemp = temp;
        while (temp != null && temp.getClave().compareToIgnoreCase(nombre) != 0) {
            padreTemp = temp;
            if ((temp.getClave().compareToIgnoreCase(nombre) ) < 0) {temp = temp.getDerecha();}
            else {temp = temp.getIzquierda();}
        }
        if (temp == null) {return null;}
        return padreTemp;
    }
    private NodoArbol getSucesor(NodoArbol nodoBorrar) {
        NodoArbol padreSucesor = nodoBorrar;
        NodoArbol sucesor = nodoBorrar;
        NodoArbol temporal = nodoBorrar.getDerecha();
        while (temporal != null) {
            padreSucesor = sucesor;
            sucesor = temporal;
            temporal = temporal.getIzquierda();
        }
        if (sucesor != nodoBorrar.getDerecha()) {
            padreSucesor.setIzquierda(sucesor.getDerecha());
            sucesor.setDerecha(nodoBorrar.getDerecha());
        }
        return sucesor;
    }

    public Producto eliminar (String nombre) {
        //* = Acomodarle el hijo izquierdo del nodo que va a reemplazar


        //Si está vacío, se da un mensaje explicativo y se retorna null
        if (estaVacio()) {
            System.out.println("El arbol esta vacio");
            return null;
        }
        //Se busca el nodo a partir de la clave recibida
        NodoArbol nodoEliminar = buscarNodo(nombre);
        if (nodoEliminar == null) {return null;}
        //Si el nodo no está, se retorna null, esto ya lo hace la funcion de buscar

        //Si es la raíz, se determina el caso exacto:
        if (nodoEliminar == raiz) {
            //Si no tiene hijos, se pone null en la raíz
            if (raiz.getDerecha() == null && raiz.getIzquierda() == null) {raiz = null;}
            //Si tiene sólo un hijo, ese hijo se pone en la raíz
            else if (raiz.getDerecha() == null) {setRaiz(raiz.getIzquierda());}
            else if (raiz.getIzquierda() == null) {setRaiz(raiz.getDerecha());}
            //Si tiene los dos, se pone su sucesor* en la raíz
            else {
                NodoArbol sucesor = getSucesor(nodoEliminar);
                setRaiz(sucesor);
                sucesor.setIzquierda(nodoEliminar.getIzquierda());
            }
            //Se retorna el nodo encontrado
            return nodoEliminar.getProducto();
        }

        //Se busca el padre del nodo a partir de la clave recibida
        NodoArbol padre = getPadre(nombre);
        boolean esHijoIzquierdo = (padre.getIzquierda() == nodoEliminar);
        //Si no tiene hijos, se pone null a su padre por el lado correspondiente
        if (nodoEliminar.getIzquierda() == null && nodoEliminar.getDerecha() == null) {
            if (esHijoIzquierdo) {padre.setIzquierda(null);}
            else {padre.setDerecha(null);}
        }
        //Si tiene sólo un hijo, se pone ese hijo a su padre por el lado correspondiente
        else if (nodoEliminar.getIzquierda() == null) {
            if (esHijoIzquierdo) {padre.setIzquierda(nodoEliminar.getDerecha());}
            else {padre.setDerecha(nodoEliminar.getDerecha());}
        }
        else if (nodoEliminar.getDerecha() == null) {
            if (esHijoIzquierdo) {padre.setIzquierda(nodoEliminar.getIzquierda());}
            else {padre.setDerecha(nodoEliminar.getIzquierda());}
        }
        //Si tiene los dos, se pone su sucesor* a su padre por el lado correspondiente
        else {
            NodoArbol sucesor = getSucesor(nodoEliminar);

            if (esHijoIzquierdo) {padre.setIzquierda(sucesor);}
            else {padre.setDerecha(sucesor);}
            //el sucesor conserva los hijos del nodo eliminado
            sucesor.setIzquierda(nodoEliminar.getIzquierda());
        }
        //Se retorna el nodo encontrado
        return nodoEliminar.getProducto();
    }

    private void  enOrdenRecursivo(NodoArbol subraiz) {
        if (subraiz != null) {
            enOrdenRecursivo(subraiz.getIzquierda());
            System.out.print(subraiz.getProducto() + " ");
            enOrdenRecursivo(subraiz.getDerecha());
        }
    }

    public void enOrden() {
        if (raiz == null) {
            System.out.println("El inventario esta vacio");
            return;
        }
        enOrdenRecursivo(raiz);
        System.out.println("\n");
    }

    //AGREGAR PREORDEN Y POSTORDEN
    private void preOrdenRecursivo(NodoArbol subraiz) {
        if (subraiz != null) {
            System.out.print(subraiz.getProducto() + " ");
            preOrdenRecursivo(subraiz.getIzquierda());
            preOrdenRecursivo(subraiz.getDerecha());
        }
    }

    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println("\n");
    }

    private void postOrdenRecursivo(NodoArbol subraiz) {
        if (subraiz != null) {
            postOrdenRecursivo(subraiz.getIzquierda());
            postOrdenRecursivo(subraiz.getDerecha());
            System.out.print(subraiz.getProducto() + " ");
        }
    }

    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println("\n");
    }

    private class NodoArbol {

        //Atributos
        private Producto producto;
        private NodoArbol izquierda;
        private NodoArbol derecha;

        //Constructor
        public NodoArbol(Producto producto) {
            this.producto = producto;
            this.derecha = null;
            this.izquierda = null;
        }

        //Getters
        public String getClave() {return producto.getNombre();}
        public Producto getProducto() {return producto;}
        public NodoArbol getDerecha() {return derecha;}
        public NodoArbol getIzquierda() {return izquierda;}

        //Setters
        public void setProducto(Producto producto) {this.producto = producto;}
        public void setDerecha(NodoArbol derecha) {this.derecha = derecha;}
        public void setIzquierda(NodoArbol izquierda) {this.izquierda = izquierda;}

        //toString
        @Override
        public String toString() {
            return "\nProducto: " + producto + "\n";
        }
    }
}
