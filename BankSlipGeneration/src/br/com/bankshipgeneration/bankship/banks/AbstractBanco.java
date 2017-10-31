package br.com.bankshipgeneration.bankship.banks;

import static br.com.bankshipgeneration.bankship.utils.StellaStringUtils.*;

import br.com.bankshipgeneration.bankship.Banco;
import br.com.bankshipgeneration.bankship.Beneficiario;
import br.com.bankshipgeneration.bankship.Boleto;
import br.com.bankshipgeneration.bankship.banks.generation.GeradorDeDigito;
import br.com.bankshipgeneration.bankship.banks.generation.GeradorDeDigitoPadrao;

public abstract class AbstractBanco implements Banco {
	private static final long serialVersionUID = 1L;

	protected final GeradorDeDigito geradorDeDigito = new GeradorDeDigitoPadrao();

	@Override
	public GeradorDeDigito getGeradorDeDigito() {
		return geradorDeDigito;
	}

	@Override
	public String getNossoNumeroECodigoDocumento(Boleto boleto) {
		Beneficiario beneficiario = boleto.getBeneficiario();
		StringBuilder builder = new StringBuilder().append(beneficiario.getCarteira());
		builder.append("/").append(getNossoNumeroFormatado(beneficiario));
		return builder.toString();
	}

	@Override
	public String getAgenciaECodigoBeneficiario(Beneficiario beneficiario) {
		StringBuilder builder = new StringBuilder();
		builder.append(beneficiario.getAgenciaFormatada());
		builder.append(prefixNotNullStringOrDefault(beneficiario.getDigitoAgencia(), "", "-"));
		builder.append("/");
		builder.append(getCodigoBeneficiarioFormatado(beneficiario));
		builder.append(prefixNotNullStringOrDefault(beneficiario.getDigitoCodigoBeneficiario(), "", "-"));	
		return builder.toString();
	}
}
