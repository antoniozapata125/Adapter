/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter.Modelo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Emmanuel Zapata
 */
public class alumno {
     String id;
    String Nombres;
    String Apellidos;
    String Correo;

    public alumno() {
        super();
    }

    public alumno(String ID, String Nombres, String Apellidos, String Correo) {
        super();
        this.id = ID;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Correo = Correo;
    }

    public String getCarnet() {
        return id;
    }

    @XmlAttribute
    public void setCarnet(String ID) {
        this.id = ID;
    }

    public String getNombres() {
        return Nombres;
    }

    @XmlAttribute
    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    @XmlElement
    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

}
