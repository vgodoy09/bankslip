package br.com.bankslipgeneration.bankslip.banks;

import static br.com.bankslipgeneration.bankslip.utils.StellaStringUtils.*;

import br.com.bankslipgeneration.bankslip.Banco;
import br.com.bankslipgeneration.bankslip.Beneficiario;
import br.com.bankslipgeneration.bankslip.Boleto;
import br.com.bankslipgeneration.bankslip.banks.generation.GeradorDeDigito;
import br.com.bankslipgeneration.bankslip.banks.generation.GeradorDeDigitoPadrao;

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
