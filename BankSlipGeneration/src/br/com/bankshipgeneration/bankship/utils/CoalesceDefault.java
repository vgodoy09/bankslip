package br.com.bankshipgeneration.bankship.utils;

/**
 * Implementação padrão de um Coalesce
 * @author eduardo.silva
 *
 * @param <T>
 */
public final class CoalesceDefault<T> implements Coalesce<T> {

	private final T[] listToChoose;

	public CoalesceDefault(T... listToChoose) {
		this.listToChoose = listToChoose;
	}

	@Override
	public T choose() {

		T selected = null;
		
		for(T t : listToChoose) {
			if (t != null) {
				return t;
			}
		}
		
		return selected;
	}
}