package br.com.bankshipgeneration.teste;

import static br.com.bankshipgeneration.bankship.utils.Relational.nullIf;

import java.util.Calendar;
import java.util.Date;

import br.com.bankshipgeneration.bankship.Banco;
import br.com.bankshipgeneration.bankship.Beneficiario;
import br.com.bankshipgeneration.bankship.Boleto;
import br.com.bankshipgeneration.bankship.Datas;
import br.com.bankshipgeneration.bankship.Endereco;
import br.com.bankshipgeneration.bankship.NossoNumero;
import br.com.bankshipgeneration.bankship.Pagador;
import br.com.bankshipgeneration.bankship.banks.Bradesco;
import br.com.bankshipgeneration.bankship.transformer.GeradorDeBoleto;
import br.com.bankshipgeneration.bankship.utils.CoalesceDefault;

public class BoletosTeste {

	public static void main(String[] args) {
		Date dataDocumento = new Date();
		Calendar documentDate = Calendar.getInstance();
		documentDate.setTime(dataDocumento);
		
		Datas datas = Datas.novasDatas().comDocumento(documentDate.get(Calendar.DAY_OF_MONTH), documentDate.get(Calendar.MONTH), documentDate.get(Calendar.YEAR))
                .comProcessamento(30, 10, 2017).comVencimento(01, 11, 2017);

		
		Endereco enderecoBeneficiario =  Endereco.novoEndereco()
				.comLogradouro("Av dos testes, 111 apto 333")
				.comComplemento("")
				.comCep(new CoalesceDefault<String>(nullIf("01234111", ""), "000000").choose())
				.comCidade(new CoalesceDefault<String>("São Paulo", "").choose())
				.comUf(new CoalesceDefault<String>("SP", "").choose())
				.comBairro(new CoalesceDefault<String>("Bairro Teste", "").choose());
				
		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
				.comAgencia("3373")
				.comDigitoAgencia("1")
				.comCarteira("26")
				.comCodigoBeneficiario("41777")//Conta corrente
				.comDigitoCodigoBeneficiario("7")
				.comDigitoNossoNumero(NossoNumero.getDigitoBradesco("3099243", "26"))
				.comEndereco(enderecoBeneficiario)
				.comNomeBeneficiario("REDE NOVO TEMPO DE COMUNICAÇÃO")
				.comNossoNumero("3099243")
				.comNumeroConvenio("1234567")
				.comDocumento("10987654321"); 

		Endereco enderecoPagador =  Endereco.novoEndereco()
				.comLogradouro("Rua Manoel Martins Sanches, 41")
				.comComplemento("")
				.comCep(new CoalesceDefault<String>(nullIf("08770510", ""), "000000").choose())
				.comCidade(new CoalesceDefault<String>("Mogi das Cruzes", "").choose())
				.comUf(new CoalesceDefault<String>("SP", "").choose())
				.comBairro(new CoalesceDefault<String>("Jardim Aracy", "").choose());
		
        Pagador pagador = Pagador.novoPagador()
        	.comPerson_id(928987)
            .comNome("Victor Prado de Godoy")
            .comDocumento("40691463859")
            .comEndereco(enderecoPagador);

        Banco banco = new Bradesco();

        Boleto boleto = Boleto.novoBoleto()
            .comBanco(banco)
            .comDatas(datas)
            .comBeneficiario(beneficiario)
            .comPagador(pagador)
            .comValorBoleto("1.00")
            .comLocaisDePagamento("Pagar preferencialmente em agências Bradesco")
            .comNumeroDoDocumento("3099243")
            .comMensagem(1, "Instruções ( texto de responsabilidade do Cedente)")
            .comMensagem(2, "VALORES EM REAIS")
            .comMensagem(3, "JUROS / MORA POR DIA: 0,00")
            .comMensagem(4, "Instruções (texto de responsabilidade do cedente)")
            .comMensagem(5, "Este boleto pode ser pago em qualquer agência bancária ou casa lotérica até a data de vencimento.")
            .comMensagem(6, "Após a data de vencimento o boleto deverá ser pago somente no Banco Bradesco, no prazo de 60 dias.")
            .comMensagem(7, "Se desejar pagar um valor maior do que o do boleto, favor preencher o valor desejado no campo “Outros acréscimos”. " + 
            		"Se desejar pagar um valor menor, favor preencher o valor a ser descontado no campo “Outras deduções”.")
            .comMensagem(8, "Doação: Pagamento Facultativo");

		GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

        // Para gerar um boleto em PDF
        gerador.geraPDF("BoletoBradesco.pdf");

        // Para gerar um array de bytes a partir de um PDF
        @SuppressWarnings("unused")
        byte[] bPDF = gerador.geraPDF();

	}

}
