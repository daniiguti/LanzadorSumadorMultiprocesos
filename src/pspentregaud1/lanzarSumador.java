/**
 * HECHO POR:
 * Daniel Gutiérrez Baena
 * David Ruíz Ruíz
 */

package pspentregaud1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class lanzarSumador {
    //Donde vamos a guardar los resultados de cada fichero
    public static String FICH_RESULTADO = "resul";
    public static String EXTENSION = ".txt";
    
    public static void main(String[] args) {
        
        //Array donde tenemos todos los nombres de los archivos
        String[] misFiles = new String[5];
        
        //Nuestros archivos, dados por el enunciado
        String Fich_Informatica = "informatica.txt";
        String Fich_Gerencia = "gerencia.txt";
        String Fich__Contabilidad = "contabilidad.txt";
        String Fich_Comercio = "comercio.txt";
        String Fich_rrhh = "rrhh.txt";
        
        //Añadimos al array los archivos
        misFiles[0] = Fich_Informatica;
        misFiles[1] = Fich_Gerencia;
        misFiles[2] = Fich__Contabilidad;
        misFiles[3] = Fich_Comercio;
        misFiles[4] = Fich_rrhh;
        
        //Recorremos el array
        for(int i = 0; i < misFiles.length; i++){
            //variable de los nombres de los ficheros resultados
            String auxFichResultado = FICH_RESULTADO + "" + (i+1) + "" + EXTENSION;
            
            //llamamos a la funcion lanzarSumador
            lanzarSumador(misFiles[i], auxFichResultado);
        }
        
        //Calculamos la suma total, llamando a la funcion encargada de hacer esto
        //pasandole el numero de archivos que tiene el array
        //puesto que es lo que va a hacer
        int sumaTotal = getSumaTotal(misFiles.length);
        
        //Mostramos la suma total (en este caso 126)
        System.out.println("La suma total es: " + sumaTotal);
    }
    
    /**
     * 
     * @param fileSumar
     * //Metodo lanzarSumador, le pasamos por parametro, el nombre del archivo que vamos
     * //a sumar y el nombre del archivo donde se guardará el resultado
     * @param fileResultado 
     */
    public static void lanzarSumador(String fileSumar, String fileResultado){
        
        //Nos creamos el comando que va a sumar
        String comando = "pspentregaud1.Sumador";
        
        //Fichero del ejecutable
        File directorioSumador = new File("C:\\Users\\Usuario\\Documents\\NetBeansProjectsProgServ\\PSPEntregaUD1\\build\\classes\\pspentregaud1");
        
        //Fichero donde vamos a guardar los resultados de los ficheros
        File fichResultado = new File(fileResultado);
        
        //ProcessBuilder para el paralelismo
        ProcessBuilder pb;
        pb = new ProcessBuilder("java",
                            comando,
                            //Argumentos requeridos en Sumador
                        fileSumar);
        
        pb.directory(directorioSumador);
        pb.directory(new File(System.getProperty("java.class.path")));
        //Le decimos que la salida la guarde en el fichero resultado
        pb.redirectOutput(fichResultado);       
        
        //Excepciones que puede dar
        try {
            
            //Guardamos en el proceso el comienzo del processBuilder hay que inicializarlo aqui
            //puesto que son distintas variables, sino se pisan
            Process process = pb.start();
            
            //Le decimos al programa que espere a que acabe el subproceso
            process.waitFor();
            
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException");
        } catch (IOException ex) {
            System.out.println("Error de IOException");
        }
    }
    
    /**
     * 
     * @param numArchivos
     * //método para calcular la suma total, le pasamos cuantos archivos va a leer
     * //y devuelve un entero(la suma total de estos archivos)
     * @return 
     */
    public static int getSumaTotal(int numArchivos){
        int sumaTotal = 0;
        
        for(int i = 0; i < numArchivos; i++){
            String auxFichResultado = FICH_RESULTADO + "" + (i+1) + "" + EXTENSION;
            sumaTotal = sumaTotal + getResultadoFichero(auxFichResultado);
        }
        
        return sumaTotal;
    }

    /**
     * 
     * @param nombreFichero
     * //metodo para leer el número de un fichero, usando un BufferedReader
     * //nos devuelve el número leido
     * @return 
     */
    private static int getResultadoFichero(String nombreFichero){
        String fraseLeida = "";
        int numFichero = 0;
        FileReader fr = null;
        BufferedReader br = null;
        File miFile = new File(nombreFichero);
        try {
            //Archivos necesarios para leer los ficheros
            fr = new FileReader(miFile);
            br = new BufferedReader(fr);
            do{
                fraseLeida = br.readLine();
                if(fraseLeida != null){
                    numFichero = Integer.parseInt(fraseLeida);
                }
            }while(fraseLeida != null);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }finally{
            try {
                fr.close();
                br.close();
            } catch (IOException ex) {
                System.out.println("IOException");
            }            
        }
               
        return numFichero;
    }
}
