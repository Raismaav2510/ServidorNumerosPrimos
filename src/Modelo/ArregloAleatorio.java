package Modelo;

import java.util.Arrays;

public class ArregloAleatorio {
    private int arreglo[];

    public void arregloAleatorio(int limite) {
        int valor = 0;
        boolean existe;
        arreglo = new int[limite];
        for (int i = 0; i < arreglo.length; i++) {
            existe = false;
            valor = (int) ((Math.random() * limite * 100) + 2);
            for (int j = 0; j < i; j++) {
                if (arreglo[j] == valor) {
                    existe = true;
                    i--;
                    break;
                }
            }

            if (!existe){
                arreglo[i] = valor;
            }
        }
        ordenar(0, arreglo.length - 1);
    }

    private void ordenar(int izquierda, int derecha) {
        if (izquierda < derecha) {
            int centro = (izquierda + derecha)/2;
            ordenar(izquierda, centro);
            ordenar(centro + 1, derecha);
            unir(izquierda, centro, derecha);
        }
    }

    private void unir(int izquierda, int centro, int derecha) {
        int indexL = 0;
        int indexR = 0;
        int index = izquierda;
        int lengthL = centro - izquierda + 1;
        int lengthR = derecha - centro;
        int arrayL[] = new int [lengthL];
        int arrayR[] = new int [lengthR];

        for (int i = 0; i < lengthL; i++)
            arrayL[i] = arreglo[izquierda + i];

        for (int i = 0; i < lengthR; i++)
            arrayR[i] = arreglo[centro + 1 + i];

        while (indexL < lengthL && indexR < lengthR) {
            if (arrayL[indexL] <= arrayR[indexR]) {
                arreglo[index] = arrayL[indexL];
                indexL++;
            } else {
                arreglo[index] = arrayR[indexR];
                indexR++;
            }
            index++;
        }

        while (indexL < lengthL) {
            arreglo[index] = arrayL[indexL];
            indexL++;
            index++;
        }

        while (indexR < lengthR) {
            arreglo[index] = arrayR[indexR];
            indexR++;
            index++;
        }
    }

    public String imprimirArreglo() {
        String mensaje = "";
        for (int i = 0; i < arreglo.length; i++) {
            mensaje += arreglo[i] + "\n";
        }
        return mensaje;
    }

    public int[] getArreglo() {
        return Arrays.copyOf(this.arreglo, this.arreglo.length);
    }
}
