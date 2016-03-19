package gestorvuelos;

import java.io.*;

/**
 * Clase Vuelo.
 * @author ramon
 */

public class Vuelo
{
    String ident,origen,destino,hsalida,hllegada;
    String [] asiento;
    int numP;
    private String s;
/**
     * Método constructor.
     * @param id identificador del vuelo.
     * @param orig ciudad de origen del vuelo.
     * @param dest ciudad de destino del vuelo.
     * @param hsal hora de salida del vuelo.
     * @param hlleg hora de llegada del vuelo.
     */   
public Vuelo(String id, String orig, String dest, String hsal, String hlleg)
{
    this.ident=id;
    this.origen=orig;
    this.destino=dest;
    this.hllegada=hlleg;
    this.hsalida=hsal;
    this.numP = 0;
    asiento= new String [51];
    //s=(ident+" "+origen+" "+destino+" "+hsalida+" "+hllegada);
    s=String.format("%10s %10s %10s %10s %10s",ident,origen,destino,hsalida,hllegada);
}

/**
 * Devuelve el identificador del vuelo.
 * @return identificador.
 */
public String getIdent()
{
    return ident;
}

/**
 * Devuelve el origen del vuelo.
 * @return origen.
 */
public String getOrigen()
{
    return origen;
}

/**
 * Devuelve el destino del vuelo.
 * @return destino.
 */
public String getDestino()
{
    return destino;
}

/**
 * Devuelve el numero de pasajeros del vuelo.
 * @return numero pasajeros.
 */
public int getNumPasajeros()
{
    return numP;
}

/**
 * Comprueba si quedan asientos libres.
 * @return devuelve true si quedan libres.
 */
public boolean hayLibres()
{
    if(numP<50)
        return true;
    else
        return false;
}

/**
 * Comprueba si un asiento está libre.
 * @param a número de asiento a comprobar.
 * @return devuelve true si está libre.
 */
public boolean estaLibre(int a)
{
    if(asiento[a]==null)
        return true;
    else
        return false;
}

/**
 * Reserva un asiento para un pasajero.
 * @param pas nombre del pasajero.
 * @param pref tipo de asiento preferido.
 */
public void reservar(String pas, char pref)
{
    int a=0;
    char t=pref;
    if(hayLibres()){
        if(t=='P'){
            a=asientoLibre(t);
            if(a>0){
                reservar(pas,a);
            }
            else{
                t='V';
                a=asientoLibre(t);
                reservar(pas,a);
            }
        }
        else if(t=='V'){
            a=asientoLibre(t);
            if(a>0){
                reservar(pas,a);
            }
            else{ 
                t='P';
                a=asientoLibre(t);
                reservar(pas,a);
            }
        }
        System.out.println("Su reserva:\n"+pas+" "+a+t+" "+s);
        //numP++;  
    }
    else
        System.out.println("El vuelo ya está completo.");
}

/**
 * Comprueba si existe un asiento libre del tipo preferido.
 * @param pref tipo de asiento preferido ('P' pasillo, 'V' ventanilla).
 * @return devuelve el primer asiento libre o -1 si no hay libres,
 *          0 si pref es distinto de 'P' o 'V'.
 */
private int asientoLibre(char pref)
{
    int a=0;
    if(pref=='P'){
        a=2;
        while(!estaLibre(a) || a>50){
            a+=2;
        }
        if(a>50) a=-1;

     }
    else if(pref=='V'){
        a=1;
        while(!estaLibre(a) || a>50){
            a+=2;
        }
        if(a>50) a=-1;
    }
    return a;
}

/**
 * Reserva un asiento para un pasajero.
 * @param pas nombre del pasajero.
 * @param as numero de asiento.
 */
public void reservar(String pas, int as)
{
    /* No hace ningun tipo de validacion de datos 
       puesto que este metodo se invoca en la lectura 
       de datos desde fichero (metodo leerVuelos de la 
       clase Aerolinea) y los datos se suponen correctos */
    asiento[as]=pas;
    numP++;
}  

/**
 * Cancela la reserva del asiento que le pases.
 * @param numasiento numero de asiento.
 */
public void cancelarReserva(int numasiento)
{
    if(numasiento>=1 && numasiento<=50){
        asiento[numasiento]=null;
    }
    else System.out.println("El asiento "+numasiento+" no es un asiento válido.");
    
}

/**
 * Devuelve los datos del vuelo en forma de cadena.
 * @return datos del vuelo.
 */
@Override
public String toString()
{
    return s;
}

/**
 * Guarda los datos del vuelo y las reservas en un fichero.
 * @param fich fichero.
 * @throws Exception 
 */
public void guardarVuelo(PrintWriter fich) throws Exception
{
    fich.println(s);
    fich.println(numP);
    for(int i=1;i<asiento.length;i++){
        if(!estaLibre(i)) fich.println(i+" "+asiento[i]);
    }
    fich.println("=== === === === === === === ===");
}

} // Vuelo