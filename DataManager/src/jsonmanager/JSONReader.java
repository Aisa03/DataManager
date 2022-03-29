/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author AA
 */
public class JSONReader {
    
    public static final String JSON_FILE="libri.json";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException { //uso il comando throws per gestire l'eccezione nel caso in cui non trovassi il file

        Libro libri[]; //dichiaro un array della classe libri
        
        InputStream input = new FileInputStream(JSON_FILE); //serve a convertire in byte il contenuto del json
        
        JsonReader jsonReader = Json.createReader(input);
        
        JsonObject jsonObject = jsonReader.readObject();
        
        jsonReader.close();
        
        JsonObject innerJsonObject = jsonObject.getJsonObject("libreria");
        
        JsonArray jsonArray = innerJsonObject.getJsonArray("libri");
        
        libri = new Libro[jsonArray.size()]; //creo un nuovo array della classe libri
        
        int index = 0; // inizializzo l'indice
        
        for (JsonValue element : jsonArray) {
            Libro libro = new Libro(); //creo un nuovo libro da aggiungere poi all'array per ogni elemento del Json
            //setto tutti gli attributi della classe libro con i valori contenuti all'interno del file Json
            libro.setGenere(element.asJsonObject().getString("genere")); 
            libro.setTitolo(element.asJsonObject().getString("titolo"));
            libro.setAutore(element.asJsonObject().getString("autore"));
            libro.setPrezzo((float) element.asJsonObject().getJsonNumber("prezzo").doubleValue());
            
            libri[index++] = libro;   //riempo l'array con i libri creati     
        }
        
        for (index=0; index<libri.length; index++) //stampo tutti i libri generati dal precedente ciclo
            System.out.println(libri[index]);
       
    }
    
}
