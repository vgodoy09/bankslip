package br.com.bankshipgeneration.bankship.transformer;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.bankshipgeneration.bankship.Banco;
import br.com.bankshipgeneration.bankship.Beneficiario;
import br.com.bankshipgeneration.bankship.Boleto;
import br.com.bankshipgeneration.bankship.Datas;
import br.com.bankshipgeneration.bankship.Endereco;
import br.com.bankshipgeneration.bankship.Pagador;
import br.com.bankshipgeneration.bankship.banks.BancoDoBrasil;
import br.com.bankshipgeneration.bankship.transformer.GeradorDeBoletoHTML;

public class BoletoHTMLTransformerIntegrationTest {
private static final String FILE_NAME = "arquivo.html";
	
	
	@After
	public void removeFiles(){
		delete(new File(FILE_NAME));
		delete(new File(FILE_NAME + "_files"));
	}
	
	private boolean delete(File file) {
		if (file.isDirectory()) {
			String[] children = file.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = delete(new File(file, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		return file.delete();
	}
	
	@Before
	public void setUp() throws Exception {

		Locale.setDefault(new Locale("pt", "br"));

		Boleto boleto;
		Datas datas = Datas.novasDatas().comDocumento(4, 5, 2008).comProcessamento(4, 5, 2008)
				.comVencimento(2, 5, 2008);
		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
				.comNomeBeneficiario("Caue").comAgencia("1824").comDigitoAgencia("4")
				.comCodigoBeneficiario("76000").comNumeroConvenio("1207113")
				.comDigitoCodigoBeneficiario("5").comCarteira("18")
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

		GeradorDeBoletoHTML gerador = new GeradorDeBoletoHTML(boleto);
		gerador.geraHTML(FILE_NAME);
	}

	@Test
	public void testHTMLWriterGeneration() {
		assertTrue(new File(FILE_NAME).exists());
	}

	@Test
	public void testHTMLWriterEscreveValorCorreto() {
		assertTrue(lerArquivo().contains("40,00"));
	}

	@Test
	public void testHTMLWriterEscreveLinhaDigitavelCorreta() {
		assertTrue(lerArquivo().contains("00190.00009 01207.113000 09000.206186 5 38600000004000"));
	}
	
	private String lerArquivo() {
		try {
			@SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
			int c = 0;
			StringBuffer boleto = new StringBuffer();
			while ((c = fileInputStream.read()) != -1) {
				boleto.append((char) c);
			}
			return boleto.toString().replaceAll("&nbsp;", "");
		} catch (FileNotFoundException fileNotFoundException) {
			throw new RuntimeException(fileNotFoundException);
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}
}
