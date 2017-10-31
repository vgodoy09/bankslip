package br.com.bankshipgeneration.bankship.utils;

public final class Relational {
	/**
	 * Retorna NULL se ambos os parâmetros forem iguais, ou se o primeiro parâmetro for NULL.
	 * Utiliza-se o método {@link Object#equals(Object)} para comparar a igualdade.
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
