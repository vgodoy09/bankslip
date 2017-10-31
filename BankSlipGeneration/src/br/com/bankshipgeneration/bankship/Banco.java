package br.com.bankshipgeneration.bankship;

import java.io.Serializable;
import java.net.URL;

import br.com.bankshipgeneration.bankship.banks.generation.GeradorDeDigito;

public interface Banco extends Serializable {
	
	/**
	 * Retorna o número desse banco, formatado com 3 dígitos
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
	 * Gera o código de barras para determinado boleto
	 * @param boleto boleto
	 * @return código de barras (texto)
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
