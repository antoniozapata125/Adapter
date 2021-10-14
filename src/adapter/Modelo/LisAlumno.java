/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter.Modelo;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Emmanuel Zapata
 */
public class LisAlumno {
     int idLista;
    ArrayList<alumno> LisAlumno;

    public LisAlumno() {
        super();
    }

    public int getIdLista() {
        return idLista;
    }

    @XmlAttribute
    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public ArrayList<alumno> getListaAlumno() {
        return  LisAlumno;
    }

    @XmlElement
    public void setListaAlumno(ArrayList<alumno> listaAlumno) {
        this.LisAlumno = LisAlumno;
    }

}
