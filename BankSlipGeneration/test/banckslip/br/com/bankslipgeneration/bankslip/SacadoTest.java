package br.com.bankslipgeneration.bankslip;

import org.junit.Assert;
import org.junit.Test;

import br.com.bankslipgeneration.bankslip.Sacado;

@SuppressWarnings("deprecation")
public class SacadoTest {
	private Sacado sacado = Sacado.novoSacado();

	@Test
	public void attributesShouldNotBeNull() {
		Assert.assertNotNull(sacado.getBairro());
		Assert.assertNotNull(sacado.getCep());
		Assert.assertNotNull(sacado.getCidade());
		Assert.assertNotNull(sacado.getCpf());
		Assert.assertNotNull(sacado.getEndereco());
		Assert.assertNotNull(sacado.getNome());
		Assert.assertNotNull(sacado.getUf());
	}
}
