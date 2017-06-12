package univel.br.CadastroSimples;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {
	
	private static final String JASPER =
			"C:\\Users\\Andre Tosetto\\JaspersoftWorkspaceV2\\MyReports\\Blank_A4.jasper";
	
	public void imprimir(){
		JasperPrint jasperprintPDF = getPrint();
		Locale local = Locale.getDefault();
		JasperViewer.viewReport(jasperprintPDF,false,local);
	}
	
	public void exportar(){
		JasperPrint jasperPrintPDF = getPrint();
		
		SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd)_HH-mm-ss");
		String data = sdf.format(new Date());
		String nomePDF = "Relatorio_Contato_" + data + ".pdf";
		
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrintPDF,nomePDF);
			Desktop.getDesktop().open(new File(nomePDF));
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private JasperPrint getPrint() {
		Connection con = ConexaoDB.getInstance().getConnection();
		try {
			return JasperFillManager.fillReport(JASPER, null,con);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
