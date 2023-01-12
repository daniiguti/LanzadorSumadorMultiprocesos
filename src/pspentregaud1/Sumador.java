package pspentregaud1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sumador {
    
    public static void main(String[] args) {
        //Nos creamos el fichero con el parámetro pasado
        File myFile = new File(args[0] + "");
        
        //Llamamos a la funcion que suma los numeros de un archivo
        //y lo guardamos en una variable 
        int sumaFichero = sumar(myFile);
        
        //Mostramos la variable
        System.out.println(sumaFichero);
        
        //
        System.out.flush();
    }
    
    //Variable que suma las lineas de un archivo, le pasamos por parámetro el puntero
    //que apunta a nuestro archivo, devolvemos la suma total
    public static int sumar(File f){
        
        //Clases necesarias para leer el fichero
        FileReader fr = null;
        BufferedReader br = null;
        
        //Variable que vamos a devolver
        int sumaTotal = 0;
        
        //Variable que va a ir leyendo las lineas del archivo 
        String lineaLeida = "";
        
        //Capturamos excepcion, puesto que es de las que hay que controlar si o si
        //ya que el programador no controla que exista un fichero o no
        try {
            
            //Inicializamos las clases necesarias para leer el fichero
            fr = new FileReader(f);
            br  = new BufferedReader(fr);
     
            //Bucle para recorrer lina a linea el fichero (con un BufferedReader),
            //ya que nos dice que hay una cantidad en cada linea
            do{
                
                //Leemos la linea con el metodo readLine()
                lineaLeida = br.readLine();
                
                //Comprobamos que lo que ha leido no sea nulo
                if(lineaLeida != null){ 
                    
                    //sino es nulo, convertimos lo leido a int
                    //y lo sumamos a la variable donde vamos sumando todas las lineas
                    sumaTotal = sumaTotal + Integer.parseInt(lineaLeida);
                }
                
            //Cuando el readLine() nos devuelva null significa que ha llegado al fin
            //y ha acabado de leer
            }while(lineaLeida != null);
        
        //Excepciones
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error de IOException");
            
        //finally para que se cierran los flujos de datos si o si    
        } finally{
            try {
                //Cerramos los flujos de datos
                fr.close();
                br.close();
            } catch (IOException ex) {
                System.out.println("Error de IOException");
            }            
        }
        
        //Devolvemos la suma total
        return sumaTotal;
    }
}
