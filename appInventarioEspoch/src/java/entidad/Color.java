/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Joel
 */
public class Color {
  private int id_color;
private String nombre_color;

    public Color() {
    }

    public Color(int id_color, String nombre_color) {
        this.id_color = id_color;
        this.nombre_color = nombre_color;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public String getNombre_color() {
        return nombre_color;
    }

    public void setNombre_color(String nombre_color) {
        this.nombre_color = nombre_color;
    }



}
