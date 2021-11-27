/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import adapter.Modelo.LisAlumno;
import adapter.Modelo.alumno;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Emmanuel Zapata
 */
public class Adapter {

  
    static String carnet;
    static String nombres;
    static String apellidos;
    static String correo;
    static int opcion = 0;

    static Scanner scn = new Scanner(System.in);
    static ArrayList<alumno> lisAlumno = new ArrayList();

    static alumno alumno;

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void agregarAlumno() {

        System.out.println("Por favor ingrese los datos solicitados:");

        System.out.println("   Ingrese carnet: ");
        System.out.print(" -");
        carnet = scn.next();

        System.out.println("   Ingrese nombre: ");
        System.out.print(" -");
        nombres = scn.next();

        System.out.println("   Ingrese apellido: ");
        System.out.print(" -");
        apellidos = scn.next();

        System.out.println("   Ingrese correo electronico: ");
        System.out.print(" -");
        correo = scn.next();

        alumno = new alumno(carnet, nombres, apellidos, correo);
        lisAlumno.add(alumno);

        menuPrincipal();
    }

    public static void menuPrincipal() {
        do {

            System.out.println(" ");
            System.out.println("\t ---Menú para registrar datos---");
            System.out.println("Escriba el número de la acción a realizar");
            System.out.println("1. Ingresar datos Alumnos");
            System.out.println("2. Mostrar datos en XML");
            System.out.println("3. Mostrar datos en JSON");
            System.out.println("4. Salir");
            System.out.print("--Opción:");
            opcion = scn.nextInt();

            switch (opcion) {

                case 1:

                    agregarAlumno();

                    break;

                case 2:

                    System.out.println("\t---Datos XML---");
                    objectToXML(alumno);
                    documentoXML();
                    //listObjectTOXML(listaAlumno);

                    break;

                case 3:

                    System.out.println("\t---Datos JSON---");
                    Json(lisAlumno);

            }

        } while (opcion != 4);
    }

    public static void listObjectTOXML(LisAlumno listaAlumnos) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(LisAlumno.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(listaAlumnos, sw);

            String xmlData = sw.toString();

            System.out.println(xmlData);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void objectToXML(alumno alumno) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(alumno.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(alumno, sw);

            String xmlData = sw.toString();

            System.out.println(xmlData);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static void documentoXML() {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();

            Document documento = implementation.createDocument(null, "alumnoxml", null);
            documento.setXmlVersion("1.0");

            Element alumnosxml = documento.createElement("alumnosxml");
            Element alumnoxml = documento.createElement("alumnoxml");

            // carnet
            Element carnet = documento.createElement("carnet");
            Text textCarnet = documento.createTextNode(alumno.getCarnet());
            carnet.appendChild(textCarnet);
            alumnoxml.appendChild(carnet);

            // nombres
            Element nombres = documento.createElement("nombres");
            Text textNombres = documento.createTextNode(alumno.getNombres());
            nombres.appendChild(textNombres);
            alumnoxml.appendChild(nombres);

            // apellidos
            Element apellidos = documento.createElement("apellidos");
            Text textApellidos = documento.createTextNode(alumno.getApellidos());
            apellidos.appendChild(textApellidos);
            alumnoxml.appendChild(apellidos);

            // Precio
            Element correo = documento.createElement("correo");
            Text textCorreo = documento.createTextNode(alumno.getCorreo());
            correo.appendChild(textCorreo);
            alumnoxml.appendChild(correo);

            alumnosxml.appendChild(alumnoxml);

            documento.getDocumentElement().appendChild(alumnosxml);

            Source source = new DOMSource(documento);

            Result result = new StreamResult(new File("alumnoxml" + ".xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void Json(ArrayList<alumno> lisAlumno) {

        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();

        for (int i = 0; i < lisAlumno.size(); i++) {

            JSONObject obj1 = new JSONObject();
            obj1.put("carnet", lisAlumno.get(i).getCarnet());
            obj1.put("Nombre", lisAlumno.get(i).getNombres());
            obj1.put("Apellido", lisAlumno.get(i).getApellidos());
            obj1.put("Correo", lisAlumno.get(i).getCorreo());
            array.add(obj1);
        }

        obj.put("Nombres", array);

        System.out.println(obj);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos_persona.json"))) {
            bw.write(obj.toString());
            System.out.println("Fichero creado");
        } catch (IOException ex) {
            Logger.getLogger(Adapter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
