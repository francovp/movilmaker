/**
 * 
 */
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author Franco
 *
 */
@SuppressWarnings("unused")
public class XML {
	private String nombre_archivo;
	private ArrayList <String> key;
	private ArrayList <String> value;
	
	public XML(){
		key = new ArrayList<String>();
        value = new ArrayList<String>();
	}
	
	public boolean ingresarClienteXML (Compania e, Cliente c){
		nombre_archivo = "cliente" + e.mostrarClientes().size();
		
		key.add("rut"); value.add(c.getRut());
        key.add("idCompania"); value.add(c.getIdCompania());
        key.add("nombre1"); value.add(c.getNombre1());
        key.add("nombre2"); value.add(c.getNombre2());
        key.add("apellido1"); value.add(c.getApellido1());
        key.add("apellido2"); value.add(c.getApellido2());
        key.add("email"); value.add(c.getEmail());
        key.add("fonoCel"); value.add(Integer.toString(c.getFonoCel()));
        key.add("fonoFijo"); value.add(Integer.toString(c.getFonoFijo()));
        key.add("direccion1"); value.add(c.getDireccion1());
        key.add("direccion2"); value.add(c.getDireccion2());
        key.add("deuda"); value.add(Integer.toString(c.getDeuda()));
        
        try { 
            generate(nombre_archivo, key, value);
            return true;
        } catch (Exception e1) {
        	System.err.println("Cliente no se pudo escribir en el archivo XML.\n"
					+ "\nDetalles de la excepción:");
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
        }
	}
	
	public boolean ingresarContratoXML (Compania e, Cliente c, Contrato contr){
		nombre_archivo = "cliente" + e.mostrarClientes().size() + "_contrato" + c.getContratos().size();
 
        key.add("rutCliente"); value.add(contr.getRutCliente());
        key.add("idContrato"); value.add(Integer.toString(contr.getIdContrato()));
        key.add("idPlan"); value.add(Integer.toString(contr.getIdPlan()));
        key.add("idEquipo"); value.add(Integer.toString(contr.getIdEquipo()));
        key.add("valorTotal"); value.add(Integer.toString(contr.getValorTotal()));
        key.add("valorCuota"); value.add(Integer.toString(contr.getValorCuota()));
        key.add("cuotas"); value.add(Integer.toString(contr.getCuotas()));
        key.add("fechaInicio"); value.add(contr.getFechaInicio());
        key.add("fechaTermino"); value.add(contr.getFechaTermino());
        
        try { 
            generate(nombre_archivo, key, value);
            return true;
        } catch (Exception e1) {
        	System.err.println("Contrato no se pudo escribir en el archivo XML.\n"
					+ "\nDetalles de la excepción:");
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
        }
	}
	
	public boolean ingresarCompaniaXML (Compania e){
		nombre_archivo = "compania";
 
        key.add("nombre"); value.add(e.getNombre());
        key.add("rut"); value.add(e.getRut());
        
        try { 
            generate(nombre_archivo, key, value);
            return true;
        } catch (Exception e1) {
        	System.err.println("Compañia no se pudo escribir en el archivo XML.\n"
					+ "\nDetalles de la excepción:");
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
        }
	}
 
    public void generate(String name, ArrayList<String> key,ArrayList<String> value) throws Exception{
 
        if(key.isEmpty() || value.isEmpty() || key.size()!=value.size()){
            System.err.println("ERROR empty ArrayList");
            return;
        }else{
 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");
 
            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada key creamos un item que contendrá la key y el value
            for(int i=0; i<key.size();i++){
                //Item Node
                Element itemNode = document.createElement("ITEM"); 
                //Key Node
                Element keyNode = document.createElement("KEY"); 
                Text nodeKeyValue = document.createTextNode(key.get(i));
                keyNode.appendChild(nodeKeyValue);      
                //Value Node
                Element valueNode = document.createElement("VALUE"); 
                Text nodeValueValue = document.createTextNode(value.get(i));                
                valueNode.appendChild(nodeValueValue);
                //append keyNode and valueNode to itemNode
                itemNode.appendChild(keyNode);
                itemNode.appendChild(valueNode);
                //append itemNode to raiz
                raiz.appendChild(itemNode); //pegamos el elemento a la raiz "Documento"
            }                
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File("datosXML\\"+name+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
    }
 
}
