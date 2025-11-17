package com.vehiculos.models;

import com.vehiculos.enums.TipoMotor;
import com.vehiculos.enums.TipoCombustible;

public class Motor {
    private TipoMotor tipo;
    private TipoCombustible combustible;
    private int cantidadCombustible;
    private int revoluciones;
    private double temperatura;
    private boolean encendido;
    private long consumoTotal;

    public Motor(TipoMotor tipo, TipoCombustible combustible, int cantidadCombustible) {
        this.tipo = tipo;
        this.combustible = combustible;
        this.cantidadCombustible = cantidadCombustible;
        this.revoluciones = 0;
        this.temperatura = 20.0;
        this.encendido = false;
        this.consumoTotal = 0;
    }

    // ===== SOBRECARGA 1
    public void encender() {
        if (cantidadCombustible > 0) {
            this.encendido = true;
            this.revoluciones = 500;
            this.temperatura = 45.0;
            System.out.println(" Motor " + tipo.getNombre() + " encendido (" + revoluciones + " RPM)");
        } else {
            System.out.println(" Motor sin combustible. No se puede encender.");
        }
    }

    // ===== SOBRECARGA 2
    public void acelerar(int intensidad) {
        if (!encendido) {
            System.out.println("Motor apagado. Enciéndelo primero.");
            return;
        }
        
        int nuevoRPM = Math.min(revoluciones + intensidad, tipo.getRevolucionesMax());
        revoluciones = nuevoRPM;
        temperatura += intensidad * 0.5;
        cantidadCombustible -= (intensidad / 100.0);
        consumoTotal += (intensidad / 100.0);
        
        System.out.printf(" Acelerando: %d RPM |   %.1f°C |  %.1fL%n", 
                         revoluciones, temperatura, cantidadCombustible);
    }

    // ===== SOBRECARGA 3
    public void acelerar(String modo) {
        switch(modo.toLowerCase()) {
            case "suave":
                acelerar(300);
                break;
            case "normal":
                acelerar(600);
                break;
            case "deportivo":
                acelerar(1200);
                temperatura += 15;
                break;
            default:
                System.out.println(" Modo desconocido");
        }
    }

    // ===== SOBRECARGA 4
    public void acelerar(int intensidad, int duracionSegundos) {
        System.out.println(" Aceleración prolongada por " + duracionSegundos + "s:");
        for (int i = 0; i < duracionSegundos; i++) {
            acelerar(intensidad);
        }
    }

    public void apagar() {
        if (encendido) {
            encendido = false;
            revoluciones = 0;
            temperatura -= 5;
            System.out.println(" Motor apagado");
        }
    }

    public void diagnostico() {
        System.out.println("\n🔍 DIAGNÓSTICO DEL MOTOR");
        System.out.println("├─ Tipo: " + tipo.getNombre());
        System.out.println("├─ Caballos: " + tipo.getCaballos() + " HP");
        System.out.println("├─ Revoluciones: " + revoluciones + "/" + tipo.getRevolucionesMax() + " RPM");
        System.out.println("├─ Temperatura: " + String.format("%.1f", temperatura) + "°C");
        System.out.println("├─ Combustible: " + String.format("%.1f", cantidadCombustible) + "L");
        System.out.println("├─ Consumo Total: " + String.format("%.2f", consumoTotal) + "L");
        System.out.println("└─ Estado: " + (encendido ? " ENCENDIDO" : " APAGADO"));
    }

    public int getRevolucioes() { return revoluciones; }
    public boolean isEncendido() { return encendido; }
    public double getTemperatura() { return temperatura; }
    public double getCombustible() { return cantidadCombustible; }
}