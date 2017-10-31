package br.com.bankslipgeneration.bankslip.transformer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import br.com.bankslipgeneration.bankslip.Boleto;
import br.com.bankslipgeneration.bankslip.exception.GeracaoBoletoException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;

/**
 * Gerador de boletos em HTML
 * 
 * @author Mario Amaral <a href="github.com/mariofts">Github</a>
 * 
 */
public class GeradorDeBoletoHTML extends GeradorDeBoleto {
	private HtmlExporter  exporter = new HtmlExporter();

	{
		SimpleHtmlReportConfiguration reportExportConfiguration = new SimpleHtmlReportConfiguration();
		reportExportConfiguration.setWhitePageBackground(false);
		reportExportConfiguration.setRemoveEmptySpaceBetweenRows(true);
		exporter.setConfiguration(reportExportConfiguration);
	}
	
	/**
	 * Contrói um gerador de boletos com o template padrão.
	 * 
	 * @param boletos boleto a serem gerados.
	 */
	public GeradorDeBoletoHTML(Boleto... boletos) {
		super(boletos);
	}
	
	/**
	 * Cria um gerador de boletos que usa um template customizado.
	 * 
	 * @param template o template (.jasper) a ser usado (obrigatório).
	 * @param parametros parametros extras para o relatório( opcional).
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
//	public void setJasperParameter(JRExporterParameter parameter, Object value){
//		exporter.setParameter(parameter, value);
//	}

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
	 * Gera o boleto no formato HTML, e escreve o conteúdo do HTML no Writer informado.
	 * 
	 * @param writer local para gravação do arquivo
	 * @param request requisição
	 */
	public void geraHTML(Writer writer, HttpServletRequest request, String rootPath) {
		try {
			HtmlExporter exporter = getHtmlExporter(request);
			
			SimpleHtmlExporterOutput exporterOutput;
			//exporterOutput = new SimpleHtmlExporterOutput(response.getOutputStream());
			String outputFile = rootPath + "WEB-INF\\pages\\MyReportOutput" + request.getSession().getId() + ".jsp";
			exporterOutput = new SimpleHtmlExporterOutput(outputFile);
			exporterOutput.setImageHandler(new WebHtmlResourceHandler("image?image={0}"));
			exporter.setExporterOutput(exporterOutput);
			exporter.exportReport();	
		} catch (JRException e) {
			throw new GeracaoBoletoException(e);
		}
	}

	/**
	 * Obtém o JRExporter para HTML. 
	 * 
	 * @param request requisição.
	 * @return exporter do Jasper configurado.
	 */
	protected HtmlExporter getHtmlExporter(HttpServletRequest request) {
		JasperPrint relatorio = geraRelatorio();

		exporter.setExporterInput(new SimpleExporterInput(relatorio));
		request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, relatorio);

		return exporter;
	}
	
	public byte[] exportHtml(final JasperPrint print) throws IOException, JRException {
		JasperPrint relatorio = geraRelatorio();
	    final ByteArrayOutputStream out = new ByteArrayOutputStream();

	    exporter.setConfiguration(createHtmlConfiguration());
	    exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
	    exporter.setExporterInput(new SimpleExporterInput(relatorio));
	    exporter.exportReport();

	    return out.toByteArray();
	}

	private HtmlExporterConfiguration createHtmlConfiguration() throws IOException {
	    SimpleHtmlExporterConfiguration shec = new SimpleHtmlExporterConfiguration();
	    shec.setHtmlHeader(getHtmlHeader());
	    shec.setHtmlFooter(getHtmlFooter());
	    return shec;
	}

	private String getHtmlHeader() {
	    StringBuffer sb = new StringBuffer();
	    sb.append("<html>");
	    sb.append("<head>");
	    sb.append("  <title></title>");
	    sb.append("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
	    sb.append("  <style type=\"text/css\">");
	    sb.append("    a {text-decoration: none}");
	    sb.append("  </style>");
	    sb.append("</head>");
	    sb.append("<body text=\"#000000\" link=\"#000000\" alink=\"#000000\" vlink=\"#000000\">");
	    sb.append("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
	    sb.append("<tr><td align=\"left\">");

	    return sb.toString();
	}

	private String getHtmlFooter() {
		return null;
	}
}
