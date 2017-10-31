package br.com.bankslipgeneration.bankslip.transformer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import br.com.bankslipgeneration.bankslip.Banco;
import br.com.bankslipgeneration.bankslip.Beneficiario;
import br.com.bankslipgeneration.bankslip.Boleto;
import br.com.bankslipgeneration.bankslip.Datas;
import br.com.bankslipgeneration.bankslip.Endereco;
import br.com.bankslipgeneration.bankslip.Pagador;
import br.com.bankslipgeneration.bankslip.banks.BancoDoBrasil;
import br.com.bankslipgeneration.bankslip.transformer.GeradorDeBoleto;

public class BoletoTransformerIntegrationTest {
	private Boleto boleto;

	@Before
	public void setUp() {

		Locale.setDefault(new Locale("pt", "br"));

		apagaArquivosGerados();

		Datas datas = Datas.novasDatas().comDocumento(4, 5, 2008).comProcessamento(4, 5, 2008)
				.comVencimento(2, 5, 2008);
		Beneficiario beneficiario = Beneficiario.novoBeneficiario().comNomeBeneficiario("Caue").comAgencia("1824").comDigitoAgencia("4")
				.comCodigoBeneficiario("76000").comNumeroConvenio("1207113").comDigitoCodigoBeneficiario("5").comCarteira("18")
				.comNossoNumero("9000206");

		Endereco endereco = new Endereco("Av dos testes, 111 apto 333", "Bairro Teste", "01234-111", "S�o Paulo", "SP");

		Pagador pagador = Pagador.novoPagador().comNome("Fulano da Silva").comDocumento("111.222.333-12")
				.comEndereco(endereco);

		String[] descricoes = { "descricao 1", "descricao 2", "descricao 3", "descricao 4", "descricao 5" };

		String[] locaisDePagamento = { "local 1", "local 2" };

		String[] instrucoes = { "instrucao 1", "instrucao 2", "instrucao 3", "instrucao 4", "instrucao 5" };

		Banco banco = new BancoDoBrasil();

		boleto = Boleto.novoBoleto().comBanco(banco).comDatas(datas).comDescricoes(descricoes).comBeneficiario(beneficiario)
				.comPagador(pagador).comValorBoleto("40.00").comNumeroDoDocumento("4323").comInstrucoes(instrucoes)
				.comLocaisDePagamento(locaisDePagamento);

		GeradorDeBoleto generator = new GeradorDeBoleto(boleto);

		generator.geraPDF("arquivo.pdf");
		generator.geraPNG("arquivo.png");

	}

	@Test
	public void testPDFWriterGeneration() {
		assertTrue(new File("arquivo.pdf").exists());
	}

	@Test
	public void testPDFWriterEscreveValorCorreto() throws IOException {
		PDFTextStripper stripper = new PDFTextStripper();

		PDDocument document = PDDocument.load(new File("arquivo.pdf"));
		String text = stripper.getText(document);
		document.close();
		assertTrue(text.contains("40,00"));
	}

	@Test
	public void testPDFWriterEscreveLinhaDigitavelCorreta() throws IOException {
		PDFTextStripper stripper = new PDFTextStripper();

		PDDocument document = PDDocument.load(new File("arquivo.pdf"));
		String text = stripper.getText(document);
		document.close();

		assertTrue(text.contains("00190.00009  01207.113000  09000.206186  5  38600000004000"));
	}

	@Test
	public void testPNGWriteGeneration() {
		assertTrue(new File("arquivo.png").exists());
	}

    @Test
    public void testByteArrayGeneration() {
        GeradorDeBoleto geradorDeBoleto = new GeradorDeBoleto(this.boleto);
        assertNotNull(geradorDeBoleto.geraPDF());
        assertNotNull(geradorDeBoleto.geraPNG());
    }

	@After
	public void apagaArquivosGerados() {
		final File pngFile = new File("arquivo.png");
		final File pdfFile = new File("arquivo.pdf");
		apagaArquivoSeExistir(pngFile);
		apagaArquivoSeExistir(pdfFile);
	}

	private void apagaArquivoSeExistir(final File pngFile) {
		if (pngFile.exists()) {
			pngFile.delete();
		}
	}
	
}
