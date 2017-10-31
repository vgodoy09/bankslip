package br.com.bankslipgeneration.bankslip;

import java.io.Serializable;
import java.net.URL;

import br.com.bankslipgeneration.bankslip.banks.generation.GeradorDeDigito;

public interface Banco extends Serializable {
	
	/**
	 * Retorna o n�mero desse banco, formatado com 3 d�gitos
	 * 
	 * @return numero formatado
	 */
	String getNumeroFormatado();

	/**
	 * Pega a URL com a imagem de um banco
	 * 
	 * @return logo do banco
	 */
	URL getImage();

	/**
	 * Gera o c�digo de barras para determinado boleto
	 * @param boleto boleto
	 * @return c�digo de barras (texto)
	 */
	String geraCodigoDeBarrasPara(Boleto boleto);

	String getCodigoBeneficiarioFormatado(Beneficiario beneficiario);

	String getCarteiraFormatado(Beneficiario beneficiario);

	String getNossoNumeroFormatado(Beneficiario beneficiario);

	String getAgenciaECodigoBeneficiario(Beneficiario beneficiario);

	String getNumeroFormatadoComDigito();

	GeradorDeDigito getGeradorDeDigito();

	String getNossoNumeroECodigoDocumento(Boleto boleto);

}
