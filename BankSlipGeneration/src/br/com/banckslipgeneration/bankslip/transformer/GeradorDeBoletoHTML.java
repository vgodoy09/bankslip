package br.com.banckslipgeneration.bankslip.transformer;

import java.io.File;
import java.io.InputStream;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import br.com.banckslipgeneration.bankslip.Boleto;
import br.com.banckslipgeneration.bankslip.exception.GeracaoBoletoException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

/**
 * Gerador de boletos em HTML
 * 
 * @author Mario Amaral <a href="github.com/mariofts">Github</a>
 * 
 */
@SuppressWarnings("deprecation")
public class GeradorDeBoletoHTML extends GeradorDeBoleto {
	private JRHtmlExporter exporter = new JRHtmlExporter();

	{
		exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, 1.3F);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "stella-boleto?image=");
		exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
	}
	
	/**
	 * Contr�i um gerador de boletos com o template padr�o.
	 * 
	 * @param boletos boleto a serem gerados.
	 */
	public GeradorDeBoletoHTML(Boleto... boletos) {
		super(boletos);
	}
	
	/**
	 * Cria um gerador de boletos que usa um template customizado.
	 * 
	 * @param template o template (.jasper) a ser usado (obrigat�rio).
	 * @param parametros parametros extras para o relat�rio( opcional).
	 * @param boletos boletos.
	 */
	public GeradorDeBoletoHTML(InputStream template,  Map<String, Object> parametros, Boleto... boletos) {
		super(template, parametros, boletos);
	}
	
	/**
	 * Seta propriedades para o gerador de html do jasper.
	 * 
	 * @param parameter propriedade aser setada.
	 * @param value valor da propriedade.
	 */
	public void setJasperParameter(JRExporterParameter parameter, Object value){
		exporter.setParameter(parameter, value);
	}

	/**
	 * Gera um boleto em HTML, e grava no caminho informado.
	 * 
	 * @param arquivo caminho do arquivo.
	 */
	public void geraHTML(String arquivo) {
		try {
			JasperExportManager.exportReportToHtmlFile(geraRelatorio(), arquivo);
		} catch (JRException e) {
			throw new GeracaoBoletoException(e);
		}
	}

	/**
	 * Gera um boleto em HTML, e grava no caminho informado.
	 * 
	 * @param arquivo caminho do arquivo.
	 */
	public void geraHTML(File arquivo) {
		geraHTML(arquivo.getAbsolutePath());
	}

	/**
	 * Gera o boleto no formato HTML, e escreve o conte�do do HTML no Writer informado.
	 * 
	 * @param writer local para grava��o do arquivo
	 * @param request requisi��o
	 */
	public void geraHTML(Writer writer, HttpServletRequest request) {
		try {
			JRHtmlExporter exporter = getHtmlExporter(request);
			exporter.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER, writer);
			exporter.exportReport();	
		} catch (JRException e) {
			throw new GeracaoBoletoException(e);
		}
	}

	/**
	 * Obt�m o JRExporter para HTML. 
	 * 
	 * @param request requisi��o.
	 * @return exporter do Jasper configurado.
	 */
	protected JRHtmlExporter getHtmlExporter(HttpServletRequest request) {
		JasperPrint relatorio = geraRelatorio();

		exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, relatorio);
		request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, relatorio);

		return exporter;
	}
}
