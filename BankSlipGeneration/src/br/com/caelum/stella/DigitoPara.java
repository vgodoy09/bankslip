package br.com.caelum.stella;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DigitoPara {
	private LinkedList<Integer> numero;
	private List<Integer> multiplicadores = new ArrayList<Integer>();
	private boolean complementar;
	private int modulo;
	private boolean somarIndividual;
	private Map<Integer,String> substituicoes;

	/**
	 * Cria o objeto a ser preenchido com interface fluente e armazena o trecho num�rico
	 * em uma lista de algarismos. Isso � necess�rio porque a linha digitada pode ser 
	 * muito maior do que um int suporta.
	 * 
	 * @param trecho Refere-se � linha num�rica sobre a qual o d�gito deve ser calculado
	 */
	public DigitoPara(String trecho) {
		comMultiplicadoresDeAte(2, 9);
		mod(11);
		substituicoes = new HashMap<Integer, String>();
		this.numero = new LinkedList<Integer>();
		char[] digitos = trecho.toCharArray();
		for (char digito : digitos) {
			this.numero.add(Character.getNumericValue(digito));
		}
		Collections.reverse(numero);
	}

	/**
	 * Para multiplicadores (ou pesos) sequenciais e em ordem crescente, esse m�todo permite 
	 * criar a lista de multiplicadores que ser� usada ciclicamente, caso o n�mero base seja
	 * maior do que a sequ�ncia de multiplicadores. Por padr�o os multiplicadores s�o iniciados
	 * de 2 a 9. No momento em que voc� inserir outro valor este default ser� sobrescrito.
	 * 
	 * @param inicio Primeiro n�mero do intervalo sequencial de multiplicadores
	 * @param fim �ltimo n�mero do intervalo sequencial de multiplicadores
	 * @return this
	 */
	public DigitoPara comMultiplicadoresDeAte(int inicio, int fim) {
		this.multiplicadores.clear();
		for (int i = inicio; i <= fim; i++) {
			multiplicadores.add(i);
		}
		return this;
	}

	/**
	 * H� documentos em que os multiplicadores n�o usam todos os n�meros de um intervalo 
	 * ou alteram sua ordem. Nesses casos, a lista de multiplicadores pode ser passada 
	 * atrav�s de varargs.
	 * 
	 * @param multiplicadoresEmOrdem Sequ�ncia de inteiros com os multiplicadores em ordem
	 * @return this
	 */
	public DigitoPara comMultiplicadores(Integer... multiplicadoresEmOrdem) {
		this.multiplicadores = Arrays.asList(multiplicadoresEmOrdem);
		return this;
	}
	
	/**
	 * � comum que os geradores de d�gito precisem do complementar do m�dulo em vez
	 * do m�dulo em s�. Ent�o, a chamada desse m�todo habilita a flag que � usada 
	 * no m�todo mod para decidir se o resultado devolvido � o m�dulo puro ou seu 
	 * complementar.
	 * 
	 * @return this
	 */
	public DigitoPara complementarAoModulo() {
		this.complementar = true;
		return this;
	}

	public DigitoPara trocandoPorSeEncontrar(String substituto, Integer... i) {
		for (Integer integer : i) {
			substituicoes.put(integer, substituto);
		}
		return this;
	}

	/**
	 * @param modulo Inteiro pelo qual o resto ser� tirado e tamb�m seu complementar.
	 * 			O valor padr�o � 11.
	 * 
	 * @return this
	 */
	public DigitoPara mod(int modulo) {
		this.modulo = modulo;
		return this;
	}
	
	/**
	 * Indica se ao calcular o m�dulo, se a soma dos resultados da multiplica��o deve ser
	 * considerado digito a d�gito.
	 * 
	 * Ex: 2 X 9 = 18, ir� somar 9 (1 + 8) inv�s de 18 ao total.
	 * 
	 * @return this
	 */
	public DigitoPara somandoIndividualmente(){
		this.somarIndividual = true;
		return this;
	}
	
	/**
	 * Faz a soma geral das multiplica��es dos algarismos pelos multiplicadores, tira o 
	 * m�dulo e devolve seu complementar.
	 * 
	 * @return String o d�gito vindo do m�dulo com o n�mero passado e configura��es extra.
	 */
	public String calcula() {
		int soma = 0;
		int multiplicadorDaVez = 0;
		for (int algarismo : numero) {
			int multiplicador = multiplicadores.get(multiplicadorDaVez);
			int total = algarismo * multiplicador;
			soma += somarIndividual ? somaDigitos(total) : total;
			multiplicadorDaVez = proximoMultiplicador(multiplicadorDaVez);
		}
		int resultado = soma % modulo;
		if (complementar)
			resultado = modulo - resultado;
		
		if (substituicoes.containsKey(resultado)) {
			return substituicoes.get(resultado);
		}
		return String.valueOf(resultado);
	}
	
	
	/**
	 * soma os d�gitos do n�mero (at� 2)
	 * 
	 * Ex: 18 => 9 (1+8), 12 => 3 (1+2)
	 * 
	 * @param total
	 * @return
	 */
	private int somaDigitos(int total) {
		return (total / 10) + (total % 10);
	}

	/**
	 * Devolve o pr�ximo multiplicador a ser usado, isto �, a pr�xima posi��o da lista de
	 * multiplicadores ou, se chegar ao fim da lista, a primeira posi��o, novamente.
	 *  
	 * @param multiplicadorDaVez Essa � a posi��o do �ltimo multiplicador usado.
	 * @return pr�ximo multiplicador
	 */
	private int proximoMultiplicador(int multiplicadorDaVez) {
		multiplicadorDaVez++;
		if (multiplicadorDaVez == multiplicadores.size())
			multiplicadorDaVez = 0;
		return multiplicadorDaVez;
	}

	/**
	 * Adiciona um d�gito no final do trecho num�rico.
	 *  
	 * @param digito � o d�gito a ser adicionado.
	 * @return this
	 */
	public DigitoPara addDigito(String digito) {
		this.numero.addFirst(Integer.valueOf(digito));
		return this;
	}
}
