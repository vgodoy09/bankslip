package br.com.bankslipgeneration.bankslip;

import org.junit.Assert;
import org.junit.Test;

import br.com.bankslipgeneration.bankslip.Endereco;

public class EnderecoTest {
	@Test
	public void deveImprimirEnderecoCompleto() {
		
	    Endereco end = Endereco.novoEndereco()
	    		.comLogradouro("RODOVIA SC 401, KM 1 - EDIFÍCIO CELTA")
	    		.comBairro("PARQTEC ALFA")
	    		.comCep("88030-000")
	    		.comCidade("FLORIANÓPOLIS")
	    		.comUf("SC");
	    
	    Assert.assertEquals("RODOVIA SC 401, KM 1 - EDIFÍCIO CELTA, "
	    		+ "PARQTEC ALFA 88030-000 - FLORIANÓPOLIS - SC", end.getEnderecoCompleto());
	    
	}
	
	@Test
	public void deveImprimirVazioSeEnderecoNaoPreenchido() {
		
	    Endereco end = Endereco.novoEndereco();
	    
	    Assert.assertEquals("", end.getEnderecoCompleto());
	    
	}
	
	@Test
	public void deveImprimirEnderecoSemLogradouro() {
		
	    Endereco end = Endereco.novoEndereco()
	    		.comBairro("PARQTEC ALFA")
	    		.comCep("88030-000")
	    		.comCidade("FLORIANÓPOLIS")
	    		.comUf("SC");
	    
	    Assert.assertEquals("PARQTEC ALFA 88030-000 - FLORIANÓPOLIS - SC", end.getEnderecoCompleto());
	    
	}
	
	@Test
	public void deveImprimirEnderecoSemCep() {
		
	    Endereco end = Endereco.novoEndereco()
	    		.comLogradouro("RODOVIA SC 401, KM 1 - EDIFÍCIO CELTA")
	    		.comBairro("PARQTEC ALFA")
	    		.comCidade("FLORIANÓPOLIS")
	    		.comUf("SC");
	    
	    Assert.assertEquals("RODOVIA SC 401, KM 1 - EDIFÍCIO CELTA, " +
	    		"PARQTEC ALFA FLORIANÓPOLIS - SC", end.getEnderecoCompleto());
	    
	}
}
