package br.com.bankslipgeneration.bankslip.facade;

import static br.com.bankslipgeneration.bankslip.utils.Relational.nullIf;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.bankslipgeneration.bankslip.Banco;
import br.com.bankslipgeneration.bankslip.Beneficiario;
import br.com.bankslipgeneration.bankslip.Boleto;
import br.com.bankslipgeneration.bankslip.Datas;
import br.com.bankslipgeneration.bankslip.Endereco;
import br.com.bankslipgeneration.bankslip.NossoNumero;
import br.com.bankslipgeneration.bankslip.Pagador;
import br.com.bankslipgeneration.bankslip.banks.Bradesco;
import br.com.bankslipgeneration.bankslip.transformer.GeradorDeBoleto;
import br.com.bankslipgeneration.bankslip.utils.CoalesceDefault;

public class FacadeSystem implements Facade {

	@Override
	public Boleto getBankSlip(Object[] obj) {
		
		Date dataDocumento = new Date();
		Calendar documentDate = Calendar.getInstance();
		documentDate.setTime(dataDocumento);
		
		Datas datas = Datas.novasDatas().comDocumento(documentDate.get(Calendar.DAY_OF_MONTH), documentDate.get(Calendar.MONTH), documentDate.get(Calendar.YEAR))
                .comProcessamento(30, 10, 2017).comVencimento(01, 11, 2017);

		
		Endereco enderecoBeneficiario =  Endereco.novoEndereco()
				.comLogradouro("Av dos testes, 111 apto 333")
				.comComplemento("")
				.comCep(new CoalesceDefault<String>(nullIf("01234111", ""), "000000").choose())
				.comCidade(new CoalesceDefault<String>("S�o Paulo", "").choose())
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
				.comNomeBeneficiario("REDE NOVO TEMPO DE COMUNICA��O")
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
            .comLocaisDePagamento("Pagar preferencialmente em ag�ncias Bradesco")
            .comNumeroDoDocumento("3099243")
            .comMensagem(1, "Instru��es ( texto de responsabilidade do Cedente)")
            .comMensagem(2, "VALORES EM REAIS")
            .comMensagem(3, "JUROS / MORA POR DIA: 0,00")
            .comMensagem(4, "Instru��es (texto de responsabilidade do cedente)")
            .comMensagem(5, "Este boleto pode ser pago em qualquer ag�ncia banc�ria ou casa lot�rica at� a data de vencimento.")
            .comMensagem(6, "Ap�s a data de vencimento o boleto dever� ser pago somente no Banco Bradesco, no prazo de 60 dias.")
            .comMensagem(7, "Se desejar pagar um valor maior do que o do boleto, favor preencher o valor desejado no campo �Outros acr�scimos�. " + 
            		"Se desejar pagar um valor menor, favor preencher o valor a ser descontado no campo �Outras dedu��es�.")
            .comMensagem(8, "Doa��o: Pagamento Facultativo");
		
		return boleto;
	}

	@Override
	public byte[] getBankSlipInByte(List<Boleto> listBankShip) {
		GeradorDeBoleto gerador = new GeradorDeBoleto(listBankShip);

        // Para gerar um boleto em PDF
        gerador.geraPDF("Boleto.pdf");

        // Para gerar um array de bytes a partir de um PDF
        byte[] bPDF = gerador.geraPDF();

		return bPDF;
	}

}
