package br.com.bankshipgeneration.bankship;

import java.io.Serializable;

public class Pagador implements Serializable {
	private static final long serialVersionUID = 1L;

    private String nome = "";
    private String documento = "";
    private Integer person_id;
    private Endereco endereco = Endereco.novoEndereco();

    private Pagador() {
    }

    /**
     * Cria um novo pagador
     * 
     * @return this
     */
    public static Pagador novoPagador() {
        return new Pagador();
    }

    /**
     * Devolve o nome do pagador
     * 
     * @return nome do pagador
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Associa um nome ao pagador
     * 
     * @param nome nome do pagador
     * @return this
     */
    public Pagador comNome(String nome) {
        this.nome = nome;
        return this;
    }

    /**
     * Devolve o CPF/CNPJ do pagador
     * 
     * @return this
     */
    public String getDocumento() {
        return this.documento;
    }

    /**
     * Associa um CPF/CNPJ ao pagador
     * 
     * @param documento do pagador
     * @return this
     */
    public Pagador comDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    /**
     * Devolve o endere�o do pagador
     * 
     * @return endere�o do pagador
     */
    public Endereco getEndereco() {
        return this.endereco;
    }

    /**
     * Associa um endere�o ao pagador
     * 
     * @param endereco do pagador
     * @return this
     */
    public Pagador comEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

	public Integer getPerson_id() {
		return person_id;
	}

	public Pagador comPerson_id(Integer person_id) {
		this.person_id = person_id;
		return this;
	}
}
