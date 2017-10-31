package br.com.bankshipgeneration.bankship.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

import br.com.bankshipgeneration.bankship.exception.BoletoException;

public class ValidacaoBoleto {

	/**
	 * Valida o campo baseado no numero de posicoes da string (varia de banco a banco)
	 * 
	 * @param numero
	 * @param tamanho - se for zero, nao valida o tamanho
	 * @param nomeCampo - caso queira informar na mensagem o nome do campo validado
	 * @return
	 * @throws BoletoException
	 */
	public static boolean validaNumeroTamanhoEObrigatoriedade(String numero, int tamanho, String nomeCampo) {
		boolean result = true;
		// numero do documento nao pode ser vazio
		if (numero == null || numero.equals("")){
			throw new RuntimeException();

		}
		// deve ser somente numerico
		if (!StringUtils.isNumeric(numero)){
			throw new RuntimeException();
		}
		// verifica o tamanho precisa ter (depende de cada banco)
		if (tamanho> 0 && numero.length() != tamanho ){
			throw new RuntimeException();
		}

		return result;
	}
	/**
	 * Valida o campo baseado no numero de posicoes da string (varia de banco a banco)
	 * 
	 * @param numero
	 * @param tamanho - se for zero, nao valida o tamanho
	 * @return
	 * @throws BoletoException
	 */
	public static boolean validaNumeroTamanhoEObrigatoriedade(String numero, int tamanho) throws BoletoException{
		return validaNumeroTamanhoEObrigatoriedade(numero, tamanho, null);
	}
	/**
	 * preenche a esqueda com zero
	 * @param numero
	 * @param total
	 * @return
	 * @throws BoletoException
	 */
	public static String preencheAEsquerdaComZero(String numero, int total) throws BoletoException{
		long n = 0;
		try {
			n = Long.parseLong(numero);	
		} catch (Exception e) {
			throw new RuntimeException();
		}


		return String.format("%0"+total+"d", n);
	}

	/**
	 * aplica o fator peso e retorna o resto de mod 11
	 * @param numero
	 * @param peso
	 * @param crescente
	 * @return
	 */
	public static String aplicaFatorPorPesoModulo11(String numero, short peso, boolean crescente){
		// quebra a string em array
		char[] quebra = numero.toCharArray();

		short pesoTmp = peso; 
		int somatoria = 0;

		if (crescente){
			pesoTmp = 2;
			// multiplica cada elemento pelo peso
			for (short i= (short)(quebra.length-1); i >= 0; i--){
				// a conta vai de peso a 2
				if (pesoTmp > peso)
					pesoTmp = 2;
				// faz a multiplicacao pelo peso e faz a somatoria
				somatoria = somatoria + (Integer.parseInt(String.valueOf(quebra[i])) * pesoTmp);
				pesoTmp++;
			}
		}else{
			// multiplica cada elemento pelo peso
			for (short i= (short)(quebra.length-1); i >= 0; i--){
				// a conta vai de peso a 2
				if (pesoTmp < 2)
					pesoTmp = peso;
				// faz a multiplicacao pelo peso e faz a somatoria
				somatoria = somatoria + (Integer.parseInt(String.valueOf(quebra[i])) * pesoTmp);
				pesoTmp--;
			}
		}
		// pega o resto
		String resto = String.valueOf(somatoria % 11);
		return resto;
	}


	/**
	 * cria o fator de vencimento
	 * @param vencimento
	 * @return
	 * @throws BoletoException
	 */
	public static int fatorDeVencimento(Date vencimento) throws BoletoException{
		int result = 0;
		try {
			if (vencimento == null)
				return result;

			GregorianCalendar dataBase2 = new GregorianCalendar(1997, Calendar.OCTOBER, 7);
			GregorianCalendar vencimento2 = new GregorianCalendar();
			vencimento2.setTime(vencimento);	    

			long diferenca = vencimento2.getTimeInMillis() - dataBase2.getTimeInMillis();
			long diferencaDias = diferenca/(24*60*60*1000);

			result = (int) diferencaDias;

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return result;
	}

	/**
	 * pega a data em formato juliano
	 * @param vencimento
	 * @return
	 */
	public static String formataDataJuliano(Date vencimento){
		String result = "0000";
		if (vencimento == null)
			return result;

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(vencimento); 
		return gc.get(Calendar.DAY_OF_YEAR)+ String.valueOf(gc.get(Calendar.YEAR)).substring(3,4);		
	}

}
