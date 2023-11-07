import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class HomeCinemaPreferences {
    // Ejercicio #27:
    private String username;
    private boolean darkModePreferred;

    @Deprecated
    public HomeCinemaPreferences() throws FileNotFoundException {
        String file ="assets\\cinemaPrefs.txt";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
//            String firstLine = bufferedReader.readLine();
//            String secondLine = bufferedReader.readLine();
            String newLine = null;
            do{
                newLine = bufferedReader.readLine();
                System.out.println(newLine);
                parseLine(newLine);
            }while (newLine != null);
            reader.close();
//        } catch (FileNotFoundException e) {
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // Ejercicio #28:
    private void parseLine(String line) {
        if (line == null) { return; }
        String[] separatedString = line.split("=");
        String firstHalf = separatedString[0];
        String secondHalf = separatedString[1];
        if (firstHalf.equals("username")) { this.username = secondHalf; }
        if (firstHalf.equals("prefersDarkMode")) { this.darkModePreferred = Boolean.parseBoolean(secondHalf); }
    }
    public String getUsername() { return username; }
    public boolean isDarkModePreferred() { return darkModePreferred; }

    // Ejercicio #29:
    public void save() {
        String file ="assets\\cinemaPrefs.txt";
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("username=");
            bufferedWriter.write(this.username);
            bufferedWriter.newLine();

            if (this.darkModePreferred) {
                bufferedWriter.write("prefersDarkMode=true");
            } else {
                bufferedWriter.write("prefersDarkMode=false");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setUsername(String username) { this.username = username; }
    public void setDarkModePreferred(boolean darkModePreferred) { this.darkModePreferred = darkModePreferred; }

    // Ejercicio #30:
    public void saveExampleXML() throws ParserConfigurationException, TransformerException {
        // Necesario para crear un Document = Documento XML.
        DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory1.newDocumentBuilder();
        Document xmlDocument = builder.newDocument();
        // Nodo raíz:
        Element rootNode = xmlDocument.createElement("Student");
        xmlDocument.appendChild(rootNode);
        // Nodo hijo 1:
        Element node1 = xmlDocument.createElement("Name");
        Text node1Content = xmlDocument.createTextNode("Pepe Depura");
        node1.appendChild(node1Content);
        rootNode.appendChild(node1);
        // Nodo hijo 2:
        Element node2 = xmlDocument.createElement("IsLearning");
        Text node2Content = xmlDocument.createTextNode("true");
        node2.appendChild(node2Content);
        rootNode.appendChild(node2);
        // Para guardarlo en el disco duro:
        TransformerFactory factory2 = TransformerFactory.newInstance();
        Transformer transformer = factory2.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource dom = new DOMSource(xmlDocument);
        StreamResult outputStream = new StreamResult(new File("assets\\example.xml"));

        transformer.transform(dom, outputStream);
    }

    public void saveAsXML() throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factories1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factories1.newDocumentBuilder();
        Document DocumentoXML2 = builder.newDocument();
        //
        Element nodoRaiz = DocumentoXML2.createElement("Preferences");
        DocumentoXML2.appendChild(nodoRaiz);
        //
        Element nodo1 = DocumentoXML2.createElement("Username");
        Text contenidoNodo1 = DocumentoXML2.createTextNode(getUsername());
        nodo1.appendChild(contenidoNodo1);
        nodoRaiz.appendChild(nodo1);
        //
        Element nodo2 = DocumentoXML2.createElement("PrefersDarkMode");
        Text contenidoNodo2 = DocumentoXML2.createTextNode(String.valueOf(isDarkModePreferred()));
        nodo2.appendChild(contenidoNodo2);
        nodoRaiz.appendChild(nodo2);
        //
        TransformerFactory factories2 = TransformerFactory.newInstance();
        Transformer transformer = factories2.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource dom = new DOMSource(DocumentoXML2);
        StreamResult outputStream = new StreamResult(new File("assets\\cinemaPrefs.xml"));

        transformer.transform(dom, outputStream);
    }

    // Ejercicio #31:
    private void initializeFromXML() {
        // Método privado requerido para inicializar las preferencias desde un XML:
        Document xmlDocument;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse("assets\\cinemaPrefs.xml");
            // Comenzamos la recuperación de nodos, primero el raíz:
            Element rootElement = xmlDocument.getDocumentElement();
            // Nodos hijos:
            NodeList childNodes = rootElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node child = childNodes.item(i);
                parseNode(child);
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
    // Comenzamos el parse de las líneas:
    private void parseNode(Node node) {
        if (node.getNodeName().equals("Username")) {
            this.username = node.getTextContent();
        }
        if (node.getNodeName().equals("PrefersDarkMode")) {
            this.darkModePreferred = Boolean.parseBoolean(node.getTextContent());
        }
    }
    private void initializeFromTXT() {
        String file ="assets\\cinemaPrefs.txt";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
//            String firstLine = bufferedReader.readLine();
//            String secondLine = bufferedReader.readLine();
            String newLine = null;
            do{
                newLine = bufferedReader.readLine();
                System.out.println(newLine);
                parseLine(newLine);
            }while (newLine != null);
            reader.close();
//        } catch (FileNotFoundException e) {
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public HomeCinemaPreferences(boolean readXML) {
        if (readXML) {
            initializeFromXML();
        } else {
            initializeFromTXT();
        }
    }

    // Ejercicio #32:
    public void saveExampleJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Pepe Depura");
        jsonObject.put("isLearning", true);

        try {
            FileWriter writer = new FileWriter("assets\\example.json");
            jsonObject.write(writer, 2, 0); // Los números indican la identación del resultado, espacios en blanco que mejoran la legibilidad.
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveAsJSON(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("username", String.valueOf(getUsername()));
        jsonObject1.put("prefersDarkMode", isDarkModePreferred());

        try {
            FileWriter writer2 = new FileWriter("assets\\cinemaPrefs.json");
            jsonObject1.write(writer2, 2, 0); // Los números indican la identación del resultado, espacios en blanco que mejoran la legibilidad.
            writer2.flush();
            writer2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Ejercicio #33:
    private void initializeFromJSON(){
        try {
            JSONTokener tokener = new JSONTokener(new FileReader("assets\\cinemaPrefs.json"));

            JSONObject jsonObject = new JSONObject(tokener);

            String fileUsername = jsonObject.getString("username");
            boolean fileDarkMode = jsonObject.getBoolean("prefersDarkMode");

            this.username = fileUsername;
            this.darkModePreferred = fileDarkMode;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public HomeCinemaPreferences(HomeCinemaPreferencesMode mode){
//        if(mode == HomeCinemaPreferencesMode.MODE_TXT){
//            initializeFromTXT();
//        }else if(mode == HomeCinemaPreferencesMode.MODE_XML ){
//            initializeFromXML();
//        } else if (mode == HomeCinemaPreferencesMode.MODE_JSON) {
//            initializeFromJSON();
//        }
//
        //# Añado la otra opción para futuras referencias ^^^^

        switch (mode) {
            case MODE_TXT -> initializeFromTXT();
            case MODE_XML -> initializeFromXML();
            case MODE_JSON -> initializeFromJSON();
        }

    }

}
