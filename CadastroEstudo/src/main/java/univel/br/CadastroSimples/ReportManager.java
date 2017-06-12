package univel.br.CadastroSimples;

import java.sql.Connection;
import java.util.Locale;

import net.sf.jasperreports.engine.JRException;
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

	private JasperPrint getPrint() {
		Connection con = ConexaoDB.getInstance().getConnection();
		try {
			return JasperFillManager.fillReport(JASPER, null,con);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
