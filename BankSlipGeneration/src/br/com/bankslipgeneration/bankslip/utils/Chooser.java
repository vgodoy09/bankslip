package br.com.bankslipgeneration.bankslip.utils;

/**
 * Escolhedor de objectos
 * @author eduardo.silva
 * @param <T>
 */
public interface Chooser<T> {
	
	/**
	 * Escolhe um item
	 * @return
	 */
	T choose();
}