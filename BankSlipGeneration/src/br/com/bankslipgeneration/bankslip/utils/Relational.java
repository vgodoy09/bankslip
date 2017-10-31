package br.com.bankslipgeneration.bankslip.utils;

public final class Relational {
	/**
	 * Retorna NULL se ambos os par�metros forem iguais, ou se o primeiro par�metro for NULL.
	 * Utiliza-se o m�todo {@link Object#equals(Object)} para comparar a igualdade.
	 * @param result
	 * @param equal
	 * @return
	 */
	public static final <T> T nullIf(T result, T equal) {
		if (result == null) {
			return null;
		}
		return result.equals(equal) ? null : result;
	}
}
