package gestorvuelos;

import java.util.Scanner;

/**
 * Clase GestorVuelos.
 * @author ramon
 */

public class GestorVuelos {
    
    /** 
     * Muestra un menu con las opciones y devuelve la elegida.
     * @return opcion.
     */
    public static int menu(){
        Scanner scan=new Scanner(System.in);
        int m;
        String teclado;
        do{
            System.out.println("*********** MENÚ PRINCIPAL ***********");
            System.out.println("        1. Mostrar vuelos       ");
            System.out.println("        2. Reservar             ");
            System.out.println("        3. Cancelar             ");
            System.out.println("        0. Terminar             ");
            System.out.println("**************************************");
            System.out.println("Elige una opción:");
            
            teclado=scan.nextLine();
            if(isNumeric(teclado)){
                m=Integer.parseInt(teclado);
            }
            else{
                System.out.println("Elige una opción correcta!");
                m=4;
            }
        }while(m>3 || m<0);
        
        return m;
    }
    
    /**
     * Comprueba si una cadena es numerica.
     * @param str cadena.
     * @return devuelve true si es numerica.
     */
    private static boolean isNumeric(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
    public static void mostrarTodos(Aerolinea listaC[]){
        System.out.println(" AEROLÍNEA    IDVUELO    ORIGEN    DESTINO    HSALIDA    HLLEGADA    LIBRES ");
        System.out.println("-----------------------------------------------------------------------------");
        for(int i=0;i<listaC.length;i++){
            System.out.println(i);
            System.out.println(listaC[i].getNombre());
            System.out.println(listaC[i].toString());
        }
    }
    /**
     * Dados un identificador de vuelo (@code i}, un origen {@code o} y un 
     * destino {@code o}, busca un vuelo con esos datos en las aerolineas 
     * {@code listaC}. Si lo encuentra, devuelve dicho vuelo, si no, 
     * {@code null}.
     * @param listaC
     * @param i
     * @param o
     * @param d
     * @return 
     */
    public static Vuelo encontrarVuelo(Aerolinea listaC[], String i,
    String o, String d){
        Vuelo v=null;
        for(int k=0;k<listaC.length;k++){
            if(listaC[k].buscarVuelo(i)!=null)
                if(listaC[k].buscarVuelo(i).getOrigen().equals(o) && listaC[k].buscarVuelo(i).getDestino().equals(d))
                    v=listaC[k].buscarVuelo(i);
        }
        if(v!=null)
            return v;
        else
            return null;
    }
    
    /**
     * Dado un identificador de vuelo (@code i}, busca un vuelo con esos datos 
     * en las aerolineas {@code listaC}. Si lo encuentra, devuelve dicho vuelo, 
     * si no, {@code null}.
     * @param listaC
     * @param i
     * @return 
     */
    public static Vuelo encontrarVuelo(Aerolinea listaC[], String i){
        Vuelo v=null;
        for(int k=0;k<listaC.length;k++){
            if(listaC[k].buscarVuelo(i)!=null)
                v=listaC[k].buscarVuelo(i);
        }
        if(v!=null)
            return v;
        else
            return null;
    }
    
    public static void main(String args[]) throws Exception{
        Aerolinea [] listaC=new Aerolinea[3];
        listaC[0]=new Aerolinea("Alitalia");
        listaC[1]=new Aerolinea("AirFrance");
        listaC[2]=new Aerolinea("Iberia");
        int m;
        
        do{
            m=menu();
            switch(m){
                case 0:
                    break;
                case 1:
                    mostrarTodos(listaC);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    m=0;
                    break;
            }
        
        }while(m!=0);
        
    }
}