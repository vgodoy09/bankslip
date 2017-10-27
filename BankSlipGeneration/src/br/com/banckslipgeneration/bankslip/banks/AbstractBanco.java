package br.com.banckslipgeneration.bankslip.banks;

import br.com.banckslipgeneration.bankslip.Banco;
import br.com.banckslipgeneration.bankslip.Beneficiario;
import br.com.banckslipgeneration.bankslip.Boleto;
import br.com.banckslipgeneration.bankslip.banks.generation.GeradorDeDigito;
import br.com.banckslipgeneration.bankslip.banks.generation.GeradorDeDigitoPadrao;
import static br.com.banckslipgeneration.bankslip.utils.StellaStringUtils.*;

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
