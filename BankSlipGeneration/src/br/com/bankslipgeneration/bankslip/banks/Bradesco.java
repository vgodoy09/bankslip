package br.com.bankslipgeneration.bankslip.banks;

import static br.com.bankslipgeneration.bankslip.utils.StellaStringUtils.leftPadWithZeros;

import br.com.bankslipgeneration.bankslip.Banco;
import br.com.bankslipgeneration.bankslip.Beneficiario;
import br.com.bankslipgeneration.bankslip.Boleto;

public class Bradesco extends AbstractBanco implements Banco {
	
	private static final long serialVersionUID = 1L;


	private static final String NUMERO_BRADESCO = "237";

	private static final String DIGITO_NUMERO_BRADESCO = "2";

	@Override
	public String geraCodigoDeBarrasPara(Boleto boleto) {
		Beneficiario beneficiario = boleto.getBeneficiario();
		StringBuilder campoLivre = new StringBuilder();
		campoLivre.append(beneficiario.getAgenciaFormatada());
		campoLivre.append(getCarteiraFormatado(beneficiario));
		campoLivre.append(getNossoNumeroFormatado(beneficiario));
		campoLivre.append(getCodigoBeneficiarioFormatado(beneficiario));
		campoLivre.append("0");
		return new CodigoDeBarrasBuilder(boleto).comCampoLivre(campoLivre);
	}

	@Override
	public String getNumeroFormatadoComDigito() {
		StringBuilder builder = new StringBuilder();
		builder.append(getNumeroFormatado()).append("-");
		return builder.append(DIGITO_NUMERO_BRADESCO).toString();
	}

	@Override
	public String getNumeroFormatado() {
		return NUMERO_BRADESCO;
	}

	@Override
	public java.net.URL getImage() {
		String arquivo = "/br/com/caelum/stella/boleto/img/%s.png";
		String imagem = String.format(arquivo, getNumeroFormatado());
		return getClass().getResource(imagem);
	}

	@Override
	public String getCodigoBeneficiarioFormatado(Beneficiario beneficiario) {
		return leftPadWithZeros(beneficiario.getCodigoBeneficiario(), 7);
	}

	@Override
	public String getCarteiraFormatado(Beneficiario beneficiario) {	
		return leftPadWithZeros(beneficiario.getCarteira(), 2);
	}

	@Override
	public String getNossoNumeroFormatado(Beneficiario beneficiario) {
		return leftPadWithZeros(beneficiario.getNossoNumero(), 11);
	}

	@Override
	public String getNossoNumeroECodigoDocumento(Boleto boleto) {
		Beneficiario beneficiario = boleto.getBeneficiario();
		StringBuilder builder = new StringBuilder().append(leftPadWithZeros(beneficiario.getCarteira(),2));
		builder.append("/").append(getNossoNumeroFormatado(beneficiario));
		return builder.append(getDigitoNossoNumero(beneficiario)).toString();
	}

	private String getDigitoNossoNumero(Beneficiario beneficiario) {
		return beneficiario.getDigitoNossoNumero() != null 
			&& !beneficiario.getDigitoNossoNumero().isEmpty() 
				? "-" + beneficiario.getDigitoNossoNumero() : "";
	}

	@Override
	public String getAgenciaECodigoBeneficiario(Beneficiario beneficiario) {
		// TODO Auto-generated method stub
		return null;
	}
}
