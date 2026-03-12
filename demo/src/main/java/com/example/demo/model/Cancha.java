package com.example.demo.model;

public class Cancha {

    private Long id;
    private String nombre;
    private String tipo;
    private String estado;
    private double precioPorHora;

    public Cancha() {}

    public Cancha(Long id, String nombre, String tipo, String estado, double precioPorHora) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.precioPorHora = precioPorHora;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getPrecioPorHora() { return precioPorHora; }
    public void setPrecioPorHora(double precioPorHora) { this.precioPorHora = precioPorHora; }
}
