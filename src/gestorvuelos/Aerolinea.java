package gestorvuelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clase Aerolinea.
 * @author ramon
 */

public class Aerolinea {
    String nom;
    Vuelo [] listaV;
    int numV;
    
    /**
     * Método constructor.
     * @param n nombre de la aerolinea.
     * @throws FileNotFoundException 
     */
    public Aerolinea(String n) throws FileNotFoundException{
        this.nom=n;
        this.listaV=new Vuelo[10];
        this.numV=0;
        leerVuelos();
    }
    
    /**
     * Lee los vuelos del fichero con el nombre de la aerolinea.
     * @throws FileNotFoundException 
     */
    private void leerVuelos() throws FileNotFoundException {
        Scanner scan=null;
        try{
            scan=new Scanner(new File(nom));
            String ident,origen,destino,hsalida,hllegada,nomPas;
            int numP,numAs;
            while(scan.hasNext()){
                ident=scan.next();
                origen=scan.next();
                destino=scan.next();
                hsalida=scan.next();
                hllegada=scan.next();
                numP=scan.nextInt();
                
                listaV[numV]=new Vuelo(ident,origen,destino,hsalida,hllegada);
                
                if(numP>0){
                    for(int i=0;i<numP;i++){
                        numAs=scan.nextInt();
                        nomPas=scan.nextLine();
                        listaV[numV].reservar(nomPas,numAs);
                    }
                }
                else scan.nextLine();
                scan.nextLine();
                numV++;
                
            }
        }catch(FileNotFoundException e){
            System.out.println("Error abriendo el fichero "+nom+".");
        }finally{
            if(scan!=null) scan.close();
        }
    }
    
    /**
     * Devuelve el nombre de la aerolinea.
     * @return nombre.
     */
    public String getNombre(){
        return nom;
    }
    
    /**
     * Busca entre los vuelo de una aerolinea el vuelo con el id especificado
     * y lo devuelve, si no existe devuelve null.
     * @param id identificador de vuelo.
     * @return devuelve el vuelo.
     */
    public Vuelo buscarVuelo(String id){
        Vuelo v=null;
        for(int i=0;i<listaV.length;i++){
            if(id.equals(listaV[i].getIdent())) v=listaV[i];
        }
        return v;
    }
    
    /**
     * Muestra por pantalla los vuelos con origen y destino especificados
     * que no esten completos.
     * @param o origen del vuelo.
     * @param d destino del vuelo.
     */
    public void mostrarVuelos(String o, String d){
         for(int i=0;i<listaV.length;i++){
             if(o.equals(listaV[i].getOrigen()) && d.equals(listaV[i].getDestino()) && listaV[i].hayLibres()){
                 System.out.println(nom+" "+listaV[i].toString()+" "+(50-listaV[i].getNumPasajeros()));                 
             }
         }
    }
    
    /**
     * Devuelve un string con todos los vuelos de la aerolinea.
     * @return devuelve el string.
     */
    @Override
    public String toString(){
        String s=new String();
        for(int i=0;i<listaV.length;i++){
            if(listaV[i]!=null){
                s+=(" "+nom+" "+listaV[i].toString()+" "+(50-listaV[i].getNumPasajeros())+"\n");
                //System.out.println(listaV[i].getIdent());
            }
        }
        return s;
    }
    
    /**
     * Guarda en un fichero con el nombre de la aerolinea todos los vuelos
     * de esta.
     * @throws Exception 
     */
    public void guardarAerolinea() throws Exception{
        PrintWriter pw=null;
        try{
            pw=new PrintWriter(nom);
            for(int i=0;i<listaV.length;i++){
                listaV[i].guardarVuelo(pw);
            }            
        }catch(Exception e){
            System.out.println("Error creando el fichero "+nom+".");
        }finally{
            if(pw!=null) pw.close();
        }
    }
}
