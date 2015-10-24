	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.pdf.PdfWriter;
	import java.io.*;
	
public class DocumentoPdf {
	
	
	// CONSTRUCTOR
	public DocumentoPdf(){	
	}
	
	/**
	 * Imprime un Reporte en pdf
	 * @param datosEmpresa
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void generarDocumento(Compania datosEmpresa)throws FileNotFoundException, DocumentException{
		Document documento = new Document();
		int idPlan,idEquipo;	// Compararan ids de cada contrato de x cliente con las ids almacenadas en Compania
		PdfWriter.getInstance(documento,new FileOutputStream("Reporte.pdf"));	
		documento.open();	// ABRE DOCUMENTO
		
		documento.add(new Paragraph("Documento emitido por compañia "+datosEmpresa.getNombre()));
		documento.add(new Paragraph("\nInformación de contrato de cada cliente en la empresa :"));
		
		// RECORRE CADA CLIENTE E IMPRIME EN PDF SUS DATOS PERSONALES
		for (int i=0;i<datosEmpresa.getListaClientes().size();i++){
		Cliente c = datosEmpresa.getListaClientes().get(i);
		documento.add(new Paragraph("\n\n- Cliente :                        "
		+c.getNombre1()+" "+c.getNombre2()+" "+c.getApellido1()+" "+c.getApellido2()));
		
		// RECORRE CONTRATOS DE CLIENTE Y OBTIENE VALOR DE idPLAN Y idEQUIPO DE CADA CONTRATO
		for (int j=0;j<c.getContratos().size();j++){
			idPlan=c.getContratos().get(j).getIdPlan();
			idEquipo=c.getContratos().get(j).getIdEquipo();
			//imprime en pdf id contrato y valor total a pagar de cada cliente
			documento.add(new Paragraph("- ID Contrato :                 "
			+c.getContratos().get(j).getIdContrato()));
			documento.add(new Paragraph("- Valor total :                   $"
			+c.getContratos().get(j).getValorTotal()));

			// RECORRE PLANES EN COMPANIA E IMPRIME EL PLAN EN PDF
			for (int k = 0; k<datosEmpresa.getPlanes().size();k++){
			if (datosEmpresa.getPlanes().get(k).getIdPlan()==idPlan){	
				documento.add(new Paragraph("- Plan contratado :          "
			+datosEmpresa.getPlanes().get(k).getNombrePlan()));
				}
			}
			
			// RECORRE EQUIPOS EN COMPANIA E IMPRIME EL EQUIPO EN PDF
			for (int k = 0; k<datosEmpresa.getMoviles().size();k++){
				if(datosEmpresa.getMoviles().get(k).getIdEquipo()==idEquipo){
					documento.add(new Paragraph("- Equipo contratado :      "
				+datosEmpresa.getMoviles().get(k).getNombreEquipo()));
					}
				}
			}
		}
		documento.close();	// SE CIERRA EL DOCUMENTO
	}
}