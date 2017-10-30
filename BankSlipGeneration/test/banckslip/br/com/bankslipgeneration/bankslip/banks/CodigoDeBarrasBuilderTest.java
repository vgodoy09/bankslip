package br.com.bankslipgeneration.bankslip.banks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.bankslipgeneration.bankslip.Boleto;
import br.com.bankslipgeneration.bankslip.Datas;
import br.com.bankslipgeneration.bankslip.banks.CodigoDeBarrasBuilder;
import br.com.bankslipgeneration.bankslip.exception.CriacaoBoletoException;

public class CodigoDeBarrasBuilderTest {
	private CodigoDeBarrasBuilder codigo;
	private Boleto boleto;
	private StringBuilder campoLivre;

	@Before
	public void setUp() {
//		this.campoLivre = new StringBuilder("0000000000000000000000000");
//		Datas datas = Datas.novasDatas().comVencimento(01, 04, 2013);
//		this.boleto = Boleto.novoBoleto()
//			.comDatas(datas).comBanco(new Itau()).comValorBoleto(2680.16);
//		this.codigo = new CodigoDeBarrasBuilder(boleto);
	}
	
	@Test
	public void deveGerarOCodigoFixoDoBoletoQuandoCriado() throws Exception {
//		String codigoFixo = codigo.comCampoLivre(campoLivre).substring(0, 19);
//		assertEquals("3419256550000268016", codigoFixo);
//		assertThat(codigoFixo, endsWith(boleto.getValorFormatado()));
//		String numeroDoBancoFormatado = boleto.getBanco().getNumeroFormatado();
//		assertThat(codigoFixo, startsWith(numeroDoBancoFormatado));
	}
	
	@Test
	public void deveInserirODigitoVerificadorGeralNa5Casa() throws Exception {
		assertEquals("2", codigo.comCampoLivre(campoLivre).substring(4, 5));
	}
	
	@Test (expected=CriacaoBoletoException.class)
	public void deveLancarExceptionSeOCodigoGeradoForDiferenteDe44Digitos(){
		codigo.comCampoLivre(new StringBuilder("012345678910111213141516"));
	}
}
