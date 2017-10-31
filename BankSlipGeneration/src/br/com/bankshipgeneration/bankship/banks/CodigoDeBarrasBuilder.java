package br.com.bankshipgeneration.bankship.banks;

import br.com.bankshipgeneration.bankship.Banco;
import br.com.bankshipgeneration.bankship.Boleto;
import br.com.bankshipgeneration.bankship.exception.CriacaoBoletoException;

public class CodigoDeBarrasBuilder {
	private StringBuilder codigoDeBarras;
	private Banco banco;

	/**
	 * Cria um CodigoDeBarrasBuilder com os primeiros 18 digitos 
	 * preenchidos de acordo com as normas da carta circular 2926 
	 * do banco central do Brasil. O documento est� dispon�vel 
	 * para consulta em br.com.caelum.stella.boleto.doc.
	 * 
	 * @param boleto para o qual ser� gerado o c�digo de barras.
	 */
	CodigoDeBarrasBuilder(Boleto boleto) {
		this.banco = boleto.getBanco();
		this.codigoDeBarras = new StringBuilder(44);
		this.codigoDeBarras.append(banco.getNumeroFormatado());		
		int codigoEspecieMoeda = boleto.getCodigoEspecieMoeda();
		this.codigoDeBarras.append(String.valueOf(codigoEspecieMoeda));
		this.codigoDeBarras.append(boleto.getFatorVencimento());
		this.codigoDeBarras.append(boleto.getValorFormatado());
	}

	/**
	 * @param campoLivre preparado pelo banco de acordo com suas regras
	 * @return o c�digo de barras j� com o digito verificador geral.
	 * @throws CriacaoBoletoException se c�digo tenha mais de 44 digitos.
	 */
	public String comCampoLivre(StringBuilder campoLivre) {
		this.codigoDeBarras.append(campoLivre);
		String trecho = this.codigoDeBarras.toString();
		int digito = banco.getGeradorDeDigito().geraDigitoMod11(trecho);
		this.codigoDeBarras.insert(4, digito);
		validaTamahoDoCodigoDeBarrasCompletoGerado();
		return this.codigoDeBarras.toString();
	}

	/**
	 * J� valida neste ponto para n�o ter que repetir esse c�digo sempre
	 */
	private void validaTamahoDoCodigoDeBarrasCompletoGerado() {
		if (this.codigoDeBarras.toString().length() != 44) {
			throw new CriacaoBoletoException("Erro na gera��o do c�digo " +
				"de barras. N�mero de digitos diferente de 44. Verifique " +
				"se todos os dados foram preenchidos corretamente.");
		}
	}
}
