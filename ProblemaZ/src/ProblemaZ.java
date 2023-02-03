import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProblemaZ {

    private static int[] respuesta;

    private static String ordenFlips = "";

    public ProblemaZ() {

    }

    public static void main(String[] args) throws Exception {
        ProblemaZ instancia = new ProblemaZ();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int casos = Integer.parseInt(line);
        line = br.readLine();
        for (int i = 0; i < casos && line != null && line.length() > 0 && !"0".equals(line); i++) {
            final String[] dataStr = line.split(" ");
            final int[] numeros = Arrays.stream(dataStr).mapToInt(f -> Integer.parseInt(f)).toArray();
            determinarFlip(numeros);
            if(ordenFlips.equals(""))
            {
                ordenFlips = "ORDENADO";
            }

            System.out.println(ordenFlips + "\n");
            ordenFlips = "";
            line = br.readLine();
        }

    }



    public static final int[] flip(int[] numeros, int idFlip) {

        ordenFlips += " "+ idFlip;
        int[] respuestas = new int[numeros.length];
        //primero copiamos el arreglo en respuesta
        for (int i = 0; i < respuestas.length; i++) {
            respuestas[i] = numeros[i];
        }

        int posicionFinal = numeros.length - 1;

        for (int i = idFlip; i < numeros.length; i++) {
            respuestas[i] = numeros[posicionFinal];
            posicionFinal--;
        }
        return respuestas;
    }

    public final boolean estaCorrecto(int[] numeros) {
        int valorEsperado = numeros.length;
        boolean termino = true;
        for (int i = 0; i < numeros.length && termino == true; i++) {
            if (numeros[i] != valorEsperado) {
                termino = false;
                valorEsperado--;
            }
        }
        return termino;
    }

    public static void determinarFlip(int[] numeros)
    {
        //mire uno por uno si estan en su posicion.
        int[] respuestas = new int[numeros.length];
        respuestas = numeros.clone();
        //verificamos
        boolean encontrado = false;
        for (int buscado = numeros.length; buscado > 0 ; buscado--)
        {
            for( int actual= 0; actual< numeros.length && encontrado == false; actual++)
            {
                if (respuestas[actual] == buscado && Math.abs(numeros.length - buscado) == actual)
                {
                    encontrado=true;

                } else if (respuestas[actual] == buscado && actual != respuestas.length-1)
                {
                    respuestas = flip(respuestas, actual).clone();

                    respuestas = flip(respuestas, Math.abs(respuestas.length - buscado)).clone();
                    encontrado=true;
                } else if (respuestas[actual] == buscado ) {
                    respuestas = flip(respuestas, respuestas.length - buscado);
                }
            }
           encontrado=false;

        }

    }



}