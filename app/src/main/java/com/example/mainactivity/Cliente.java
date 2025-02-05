package com.example.mainactivity;

import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.concurrent.Callable;


public class Cliente implements Callable<String> {

    Socket cliente;

    DataInputStream entrada;
    DataOutputStream salida;

    String mensaje, respuesta, typeMessage;

    private String idConexion, idConexion2;

    public Cliente(String idConexion, String idConexion2, String typeMesage){
        this.idConexion = idConexion;
        this.idConexion2 = idConexion2;
        this.typeMessage = typeMesage;
        this.respuesta = "NULL";
    }

    public Cliente(String id, String typeMesage){
        this.idConexion = id;
        this.typeMessage = typeMesage;
        this.respuesta = "NULL";
    }

    public Cliente(String typeMesage){
        this.idConexion = "";
        this.typeMessage = typeMesage;
        this.respuesta = "NULL";
    }



    @Override
    public String call(){

        try{
            //Log.e("HEY","CONNECTION");
            cliente = new Socket("weatherubicuastation.duckdns.org", 8080);
            //Log.e("HEY","CONNECTION2");

            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            if(typeMessage.equals("Stations")) {
                mensaje = "Stations";
            }

            //Refresh-idEstacion se especifica el id de la estacion que se quiere consultar y devuelve TODOS los datos
            //idConexion tiene que ser idEstacion
            else if(typeMessage.equals("Refresh")){

                mensaje = "Refresh-"+idConexion;
            }
            //Refresh-numeroEstaciones se especifica cuantas estaciones tenemos siendo el id de las estaciones de 1 a n, es decir
            //si hay 5 estaciones hara las consultas para la estacion 1,2,3,4,5 devolviendo TODOS los datos
            //idConexion tiene que ser numero de estaciones que tenemos
            else if(typeMessage.equals("RefreshAll")){
                mensaje = "RefreshAll-"+idConexion;

            }
            //RefreshTable-numeroEstaciones se especifica cuantas estaciones tenemos siendo el id de las estaciones de 1 a n, es decir
            //si hay 5 estaciones hara las consultas para la estacion 1,2,3,4,5 devolviendo unicamente ID, ubicacion, temperatura, humedad y presion
            //idConexion tiene que ser numero de estaciones que tenemos
            else if(typeMessage.equals("RefreshTable")){
                mensaje = "RefreshTable-"+idConexion;

            }
            //Notify-idEstacion se especifica el id de la estacion y nos devuelve un string con las alertas de esa estacion
            //idConexion tiene que ser idEstacion
            else if(typeMessage.equals("Notify")){
                mensaje = "Notify-"+idConexion;

            }//NotifyAll nos devuelve todas las alertas de las estaciones
            else if(typeMessage.equals("NotifyAll")){
                mensaje = "NotifyAll";
            }
            //Weather-valorLuz-valorLluvia devuelve la imagen que hay que poner en el TextView Tiempo
            //Los dos valores de lluvia y luz seran los resultantes de un refresh o refrehall no hay que hacer otra consulta
            //cogerias directamente las posiciones 7 (luz) y 6(lluvia) de la consulta del refesh y las pasas como parametros para
            //devolver la alerta y pasarla como contenido de la notificacion
            else if(typeMessage.equals("Weather")){
                mensaje = "Weather-"+idConexion+"-"+idConexion2;

            }
            //Graph-idEstacion-columna devuelve un string con token1 = dia-hora y token2 = valor de la columna en esa hora y dia
            //idConexion = idEstacion que se quiere consultar y idConexion2 = nombre de la columna que se quiere consultar
            else if(typeMessage.equals("Graph")){
                mensaje = "Graph-"+idConexion+"-"+idConexion2;
            }

            salida.writeUTF(mensaje);

            respuesta = entrada.readUTF();
            System.out.println(respuesta);

            entrada.close();
            salida.close();
            cliente.close();

        }catch(IOException e){

            System.out.println("Error: "+e.getMessage());
        }

        return respuesta;
    }
}