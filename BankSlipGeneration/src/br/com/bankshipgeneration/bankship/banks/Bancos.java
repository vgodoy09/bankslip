package br.com.bankshipgeneration.bankship.banks;

import br.com.bankshipgeneration.bankship.Banco;
import br.com.bankshipgeneration.bankship.exception.BancoNaoSuportadoException;

public enum Bancos {
	
	BANCO_DO_BRASIL("001", "Banco do Brasil") {
		@Override
		public Banco getBanco() {
			return new BancoDoBrasil();
		}
	},
	ITAU("341", "Itaú") {
		@Override
		public Banco getBanco() {
			return new Itau();
		}
	},
	BRADESCO("237", "Bradesco") {
		@Override
		public Banco getBanco() {
			return new Bradesco();
		}
	};

	private final String numeroDoBanco;
	private final String nomeDoBanco;

	/**
	 * Obtém o número do banco
	 * 
	 * @return numero do banco
	 */
	public String getNumeroDoBanco() {
		return numeroDoBanco;
	}
	
	public String getNomeDoBanco() {
        return nomeDoBanco;
    }
	

	private Bancos(String numeroDoBanco, String nomeDoBanco) {
		this.numeroDoBanco = numeroDoBanco;
        this.nomeDoBanco = nomeDoBanco;
	}


	/**
	 * Obtém a instancia do banco para ser utilizada na geração dos boletos
	 * 
	 * @return Banco para geração dos boletos
	 */
	public abstract Banco getBanco();

	/**
	 * Obtém o Banco com base no número
	 * 
	 * @param numero
	 *            Número do banco com 3 dígitos.
	 * @return Banco correspondente ao número informado.
	 * @throws BancoNaoSuportadoException
	 *             se o número informado não corresponder a nenhum dos bancos
	 *             suportados.
	 */
	public static Banco getPorNumero(String numero) {
		for (Bancos b : Bancos.values()) {
			if (b.getNumeroDoBanco().equals(numero))
				return b.getBanco();
		}
		throw new BancoNaoSuportadoException(numero);
	}
}
