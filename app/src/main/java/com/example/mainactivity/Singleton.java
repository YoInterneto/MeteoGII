package com.example.mainactivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Singleton {
    private static Singleton instance;
    private String identificadorEstacion;
    private String[] headerMainActivity = {"Identificador", "Ubicación", "Temperatura", "Humedad", "Presión Atmosférica"};
    private ArrayList<String> stations = new ArrayList<>();
    private int counterStations;
    private boolean endConnectionThreadMainActivity;
    private boolean endConnectionThreadStationActivity;

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private Singleton()
    {

        this.identificadorEstacion = "";
        this.counterStations = 0;
        this.endConnectionThreadMainActivity = false;
        this.endConnectionThreadStationActivity = false;
    }

    public String getIdentificadorEstacion() {
        return identificadorEstacion;
    }

    public void setIdentificadorEstacion(String identificadorEstacion) {
        this.identificadorEstacion = identificadorEstacion;
    }

    public String[] getHeaderMainActivity() {
        return headerMainActivity;
    }

    public void setHeaderMainActivity(String[] headerMainActivity) {
        this.headerMainActivity = headerMainActivity;
    }

    public String[] getStations()
    {
        return Arrays.copyOf(this.stations.toArray(), this.stations.toArray().length, String[].class);
    }
    public void setStations(String[] stations)
    {
        this.stations.addAll(Arrays.asList(stations));
    }

    public void addStation(String identificadorEstacion)
    {
        this.stations.add(identificadorEstacion);
    }

    public void resetStations()
    {
        this.stations = new ArrayList<>();
    }

    /*public void addOneStation()
    {
        this.counterStations++;
    }*/
    /*public void resetCounterStations()
    {
        this.counterStations = 0;
    }*/
    public int getCounterStations()
    {
        return this.counterStations;
    }

    public void setCounterStations(int counterStations)
    {
        this.counterStations = counterStations;
    }

    public boolean isEndConnectionThreadMainActivity() {
        return endConnectionThreadMainActivity;
    }

    public void setEndConnectionThreadMainActivity(boolean endConnectionThreadMainActivity) {
        this.endConnectionThreadMainActivity = endConnectionThreadMainActivity;
    }

    public boolean isEndConnectionThreadStationActivity() {
        return endConnectionThreadStationActivity;
    }

    public void setEndConnectionThreadStationActivity(boolean endConnectionThreadStationActivity) {
        this.endConnectionThreadStationActivity = endConnectionThreadStationActivity;
    }
}
