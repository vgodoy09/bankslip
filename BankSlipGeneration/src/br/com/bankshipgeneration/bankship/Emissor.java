package br.com.bankshipgeneration.bankship;

import static br.com.bankshipgeneration.bankship.utils.StellaStringUtils.*;

import java.io.Serializable;

/**
 * Bean que representa os dados do emissor de um boleto 
 * bancario. � um bean gen�rico, ou seja, o uso dos campos 
 * varia de acordo com o banco pelo qual ser� gerado o boleto.
 * 
 * @author Paulo Silveira, Caue Guerra e David Paniz
 * @deprecated use {@link Beneficiario}
 */
@Deprecated
public class Emissor implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agencia;
	private String digitoAgencia;
	private String contaCorrente;
	private String carteira;
	private String numeroConvenio;
	private String nossoNumero;
	private String cedente;
	private String digitoContaCorrente;
	private String digitoNossoNumero;
	private String codigoOperacao;
	private String codigoFornecidoPelaAgencia;
	private String endereco;

	private Emissor() {
	}

	/**
	 * @return um novo Emissor
	 */
	public static Emissor novoEmissor() {
		return new Emissor();
	}

	/**
	 * @param endereco, que ser� associado ao emissor
	 * @return este emissor
	 */
	public Emissor comEndereco(String endereco) {
		this.endereco = endereco;
		return this;
	}

	/**
	 * @return o endere�o do Emissor
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @return n�mero da agencia sem o digito
	 */
	public String getAgencia() {
		return this.agencia;
	}

	/**
	 * @param agencia, que dever� ser informada
	 * <strong>sem</strong> o digito verificador
	 * @deprecated Agora deve ser String. 
	 * @return este emissor
	 */
	public Emissor comAgencia(int agencia) {
		this.agencia = String.valueOf(agencia);
		return this;
	}
	
	/**
	 * @param agencia, que dever� ser informada
	 * <strong>sem</strong> o digito verificador
	 * 
	 * @return este emissor
	 */
	public Emissor comAgencia(String agencia) {
		this.agencia = agencia;
		return this;
	}


	/**
	 * @return n�mero da conta corrente sem o digito
	 */
	public String getContaCorrente() {
		return this.contaCorrente;
	}

	/**
	 * @param contaCorrente, que dever� ser informada
	 * <strong>sem</strong> o digito verificador.
	 * @deprecated deve ser String agora 
	 * @return este emissor
	 */
	public Emissor comContaCorrente(long contaCorrente) {
		this.contaCorrente = String.valueOf(contaCorrente);
		return this;
	}
	
	/**
	 * @param contaCorrente, que dever� ser informada
	 * <strong>sem</strong> o digito verificador.
	 * 
	 * @return este emissor
	 */
	public Emissor comContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
		return this;
	}

	/**
	 * @return carteira <br> Valor informado pelo 
	 * banco para identifica��o do tipo de boleto
	 */
	public String getCarteira() {
		return this.carteira;
	}

	/**
	 * @param carteira <br> Valor informado pelo 
	 * banco para identifica��o do tipo de boleto
	 * @deprecated deve ser String agora 
	 * @return este emissor
	 */
	public Emissor comCarteira(int carteira) {
		this.carteira = String.valueOf(carteira);
		return this;
	}
	
	/**
	 * @param carteira <br> Valor informado pelo 
	 * banco para identifica��o do tipo de boleto
	 * 
	 * @return este emissor
	 */
	public Emissor comCarteira(String carteira) {
		this.carteira = carteira;
		return this;
	}

	/**
	 * @return n�mero do conv�nio <br> Valor que identifica 
	 * um emissor junto ao seu banco para associar seus boletos
	 */
	public String getNumeroConvenio() {
		return this.numeroConvenio;
	}

	/**
	 * @param numConvenio <br> Valor que identifica um 
	 * emissor junto ao seu banco para associar seus boletos
	 * @deprecated deve ser String agora 
	 * @return este emissor
	 */
	public Emissor comNumeroConvenio(long numConvenio) {
		this.numeroConvenio = String.valueOf(numConvenio);
		return this;
	}
	
	/**
	 * @param numConvenio <br> Valor que identifica um 
	 * emissor junto ao seu banco para associar seus boletos
	 * 
	 * @return este emissor
	 */
	public Emissor comNumeroConvenio(String numConvenio) {
		this.numeroConvenio = numConvenio;
		return this;
	}

	/**
	 * @return nosso n�mero <br> Valor que o cedente escolhe 
	 * para manter controle sobre seus boletos. Esse valor serve 
	 * para o cedente identificar quais boletos foram pagos ou n�o.
	 * Recomenda-se o uso de n�meros sequenciais, na gera��o de 
	 * diversos boletos, para facilitar a identifica��o dos pagos
	 */
	public String getNossoNumero() {
		return this.nossoNumero;
	}

	/**
	 * @param nossoNumero <br> Valor que o cedente escolhe 
	 * para manter controle sobre seus boletos. Esse valor serve 
	 * para o cedente identificar quais boletos foram pagos ou n�o.
	 * Recomenda-se o uso de n�meros sequenciais, na gera��o de 
	 * diversos boletos, para facilitar a identifica��o dos pagos
	 * @deprecated deve ser String agora 
	 * @return este emissor
	 */
	public Emissor comNossoNumero(long nossoNumero) {
		this.nossoNumero =  String.valueOf(nossoNumero);
		return this;
	}

	/**
	 * @param nossoNumero <br> Valor que o cedente escolhe 
	 * para manter controle sobre seus boletos. Esse valor serve 
	 * para o cedente identificar quais boletos foram pagos ou n�o.
	 * Recomenda-se o uso de n�meros sequenciais, na gera��o de 
	 * diversos boletos, para facilitar a identifica��o dos pagos
	 * 
	 * @return este emissor
	 */
	public Emissor comNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
		return this;
	}

	
	/**
	 * @return cedente deste emissor (nome fornecido para boleto)
	 */
	public String getCedente() {
		return this.cedente;
	}

	/**
	 * @param cedente, que ser� associado a este emissor.
	 * @return este emissor
	 */
	public Emissor comCedente(String cedente) {
		this.cedente = cedente;
		return this;
	}

	/**
	 * @return digito verificador (DV) da conta corrente
	 */
	public String getDigitoContaCorrente() {
		return this.digitoContaCorrente;
	}

	/**
	 * @param digito - verificador (DV) da conta corrente
	 * @deprecated deve ser String agora
	 * @return este emissor
	 */
	public Emissor comDigitoContaCorrente(char digito) {
		this.digitoContaCorrente =  String.valueOf(digito);
		return this;
	}
	
	/**
	 * @param digito - verificador (DV) da conta corrente
	 * @return este emissor
	 */
	public Emissor comDigitoContaCorrente(String digito) {
		this.digitoContaCorrente = digito;
		return this;
	}


	/**
	 * @return digito verificador (DV) da agencia
	 */
	public String getDigitoAgencia() {
		return this.digitoAgencia;
	}

	/**
	 * @param digito - verificador (DV) da agencia
	 * @deprecated deve ser String agora
	 * @return este emissor
	 */
	public Emissor comDigitoAgencia(char digito) {
		this.digitoAgencia =  String.valueOf(digito);
		return this;
	}
	
	/**
	 * @param digito - verificador (DV) da agencia
	 * @return este emissor
	 */
	public Emissor comDigitoAgencia(String digito) {
		this.digitoAgencia = digito;
		return this;
	}

	/**
	 * @return agencia formatada com 4 digitos
	 * Para o valor de agencia 123 retorna a String 0123
	 */
	public String getAgenciaFormatado() {
		return leftPadWithZeros(agencia, 4);
	}

	/**
	 * @return c�digo de opera��o do emissor
	 */
	public String getCodigoOperacao() {
		return this.codigoOperacao;
	}

	/**
	 * @param codigoOperacao, que ser� associado ao emissor
	 * @deprecated deve ser String agora
	 * @return este emissor
	 */
	public Emissor comCodigoOperacao(int codigoOperacao) {
		this.codigoOperacao =  String.valueOf(codigoOperacao);
		return this;
	}
	
	/**
	 * @param codigoOperacao, que ser� associado ao emissor
	 * @return este emissor
	 */
	public Emissor comCodigoOperacao(String codigoOperacao) {
		this.codigoOperacao = codigoOperacao;
		return this;
	}

	/**
	 * @return c�digo fornecido pela ag�ncia do emissor.
	 */
	public String getCodigoFornecidoPelaAgencia() {
		return this.codigoFornecidoPelaAgencia;
	}

	/**
	 * @param codigoFornecidoPelaAgencia, que ser� associado ao emissor
	 * @deprecated Deve ser com String agora
	 * @return este emissor
	 */
	public Emissor comCodigoFornecidoPelaAgencia(int codigoFornecidoPelaAgencia) {
		this.codigoFornecidoPelaAgencia =  String.valueOf(codigoFornecidoPelaAgencia);
		return this;
	}

	/**
	 * @param codigoFornecidoPelaAgencia, que ser� associado ao emissor
	 * @return este emissor
	 */
	public Emissor comCodigoFornecidoPelaAgencia(String codigoFornecidoPelaAgencia) {
		this.codigoFornecidoPelaAgencia = codigoFornecidoPelaAgencia;
		return this;
	}
	
	/**
	 * @param digitoNossoNumero, que ser� associado ao emissor
	 * @return este emissor
	 */
	public Emissor comDigitoNossoNumero(String digitoNossoNumero) {
		this.digitoNossoNumero = digitoNossoNumero;
		return this;
	}

	/**
	 * @return digito verificador do nosso n�mero associado ao emissor
	 */
	public String getDigitoNossoNumero() {
		return this.digitoNossoNumero;
	}
}
