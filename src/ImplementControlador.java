import Modelo.ArregloAleatorio;
import Modelo.Primos;
import Modelo.PrimosES;
import Modelo.PrimosFJ;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ForkJoinPool;

public class ImplementControlador extends UnicastRemoteObject implements Controlador {
    private ArregloAleatorio arreglo;
    private long inicio;
    private long fin;
    protected ImplementControlador() throws RemoteException {
        super();
        this.arreglo = new ArregloAleatorio();
    }

    public void guardar(int largo) {
        arreglo.arregloAleatorio(largo);
    }

    public String imprimir() {
        return arreglo.imprimirArreglo();
    }

    public String buscarSecuencial() {
        String primosOrdenados;
        this.inicio = System.currentTimeMillis();
        Primos primos = new Primos(arreglo.getArreglo());
        primosOrdenados = primos.getPrimos();
        this.fin = System.currentTimeMillis();
        return primosOrdenados;
    }

    public String buscarForkJoin() {
        String primosOrdenados;
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        this.inicio = System.currentTimeMillis();
        primosOrdenados = pool.invoke(new PrimosFJ(arreglo.getArreglo()));
        this.fin = System.currentTimeMillis();
        return primosOrdenados;
    }

    public String buscarExecutor() {
        String primosOrdenados;
        this.inicio = System.currentTimeMillis();
        PrimosES primos = new PrimosES(arreglo.getArreglo());
        primos.calcularPrimos();
        primosOrdenados = primos.getPrimos();
        this.fin = System.currentTimeMillis();
        return primosOrdenados;
    }

    public long getTiempo() {
        return fin - inicio;
    }
}
