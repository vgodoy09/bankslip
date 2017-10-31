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
	ITAU("341", "Ita�") {
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
	 * Obt�m o n�mero do banco
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
	 * Obt�m a instancia do banco para ser utilizada na gera��o dos boletos
	 * 
	 * @return Banco para gera��o dos boletos
	 */
	public abstract Banco getBanco();

	/**
	 * Obt�m o Banco com base no n�mero
	 * 
	 * @param numero
	 *            N�mero do banco com 3 d�gitos.
	 * @return Banco correspondente ao n�mero informado.
	 * @throws BancoNaoSuportadoException
	 *             se o n�mero informado n�o corresponder a nenhum dos bancos
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
