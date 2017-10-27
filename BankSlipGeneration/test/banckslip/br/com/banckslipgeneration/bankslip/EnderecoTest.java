package br.com.banckslipgeneration.bankslip;

import org.junit.Assert;
import org.junit.Test;

public class EnderecoTest {
	@Test
	public void deveImprimirEnderecoCompleto() {
		
	    Endereco end = Endereco.novoEndereco()
	    		.comLogradouro("RODOVIA SC 401, KM 1 - EDIF�CIO CELTA")
	    		.comBairro("PARQTEC ALFA")
	    		.comCep("88030-000")
	    		.comCidade("FLORIAN�POLIS")
	    		.comUf("SC");
	    
	    Assert.assertEquals("RODOVIA SC 401, KM 1 - EDIF�CIO CELTA, "
	    		+ "PARQTEC ALFA 88030-000 - FLORIAN�POLIS - SC", end.getEnderecoCompleto());
	    
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
	    		.comCidade("FLORIAN�POLIS")
	    		.comUf("SC");
	    
	    Assert.assertEquals("PARQTEC ALFA 88030-000 - FLORIAN�POLIS - SC", end.getEnderecoCompleto());
	    
	}
	
	@Test
	public void deveImprimirEnderecoSemCep() {
		
	    Endereco end = Endereco.novoEndereco()
	    		.comLogradouro("RODOVIA SC 401, KM 1 - EDIF�CIO CELTA")
	    		.comBairro("PARQTEC ALFA")
	    		.comCidade("FLORIAN�POLIS")
	    		.comUf("SC");
	    
	    Assert.assertEquals("RODOVIA SC 401, KM 1 - EDIF�CIO CELTA, " +
	    		"PARQTEC ALFA FLORIAN�POLIS - SC", end.getEnderecoCompleto());
	    
	}
}
