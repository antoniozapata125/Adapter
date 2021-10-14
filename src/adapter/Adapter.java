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
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Emmanuel Zapata
 */
public class Adapter {

     static String id;
    static String Nombres;
    static String Apellidos;
    static String Correo;
    static int opcion = 0;

    static Scanner scn = new Scanner(System.in);
    static ArrayList<alumno> LisAlumno = new ArrayList();

    static alumno alumno;
    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void agregarAlumno() {

        System.out.println("Por favor ingrese los datos solicitados:");

        System.out.println("   Ingrese su Id: ");
        System.out.print(" -");
        id = scn.next();

        System.out.println("   Ingrese su Nombre: ");
        System.out.print(" -");
        Nombres = scn.next();

        System.out.println("   Ingrese su Apellido: ");
        System.out.print(" -");
        Apellidos = scn.next();

        System.out.println("   Ingrese su Correo electronico: ");
        System.out.print(" -");
        Correo = scn.next();

        alumno = new alumno(id, Nombres, Apellidos, Correo);
        
        menuPrincipal();
    }

    public static void menuPrincipal() {
        do {

            System.out.println(" ");
            System.out.println("\t *** Bienvenido ***");
            System.out.println("");
            System.out.println("\t *** Menú *** ");
            System.out.println("");
            System.out.println("Ingrese el numero que desees realizar ");
            System.out.println("");
            System.out.println("1. Ingresar los datos Alumnos");
            System.out.println("2. Quiere ver los datos en XML");
            System.out.println("3. Quiere ver los datos en JSON");
            System.out.println("4. Salir");
            System.out.println("");
            System.out.print(" ** Seleccione la Opción que desees ** :");
            opcion = scn.nextInt();

            switch (opcion) {

                case 1:

                    agregarAlumno();

                    break;

                case 2:

                    System.out.println("\t---Datos XML---");
                    objectToXML(alumno);
                    documento();
                    System.out.println("Se ha creado un nuevo archivo XML en la carpeta del projecto ");
                    

                    break;

                case 3:

                    System.out.println("\t---Datos JSON---");

            }

        } while (opcion != 4);
    }

    public static void listObjectTOXML(LisAlumno LisAlumno) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(LisAlumno.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(LisAlumno, sw);

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

    public static void documento() {
        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            DOMImplementation implementation = builder.getDOMImplementation();

            
            Document documento = implementation.createDocument(null, "AlumnosXML", null);
            documento.setXmlVersion("1.0");

            
            Element alumnosxml = documento.createElement("alumnosxml");
            Element alumnoxml = documento.createElement("alumnoxml");

            
           // Id del estudiante
            Element carnet = documento.createElement("ID");
            Text textCarnet = documento.createTextNode(alumno.getCarnet());
            carnet.appendChild(textCarnet);
            alumnoxml.appendChild(carnet);
            
            // Nombres del estudiante
            Element nombres = documento.createElement("Nombres");
            Text textNombres = documento.createTextNode(alumno.getNombres());
            nombres.appendChild(textNombres);
            alumnoxml.appendChild(nombres);

            // Apellidos del estudiante
            Element apellidos = documento.createElement("Apellidos");
            Text textApellidos = documento.createTextNode(alumno.getApellidos());
            apellidos.appendChild(textApellidos);
            alumnoxml.appendChild(apellidos);

            // Correo del estudiante
            Element correo = documento.createElement("Correos");
            Text textCorreo = documento.createTextNode(alumno.getCorreo());
            correo.appendChild(textCorreo);
            alumnoxml.appendChild(correo);

            
            alumnosxml.appendChild(alumnoxml);

            
            documento.getDocumentElement().appendChild(alumnosxml);

            
            Source source = new DOMSource(documento);
            
            Result result = new StreamResult(new File("AlumnosXML.xml"));

            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }

    } 
    }
    

