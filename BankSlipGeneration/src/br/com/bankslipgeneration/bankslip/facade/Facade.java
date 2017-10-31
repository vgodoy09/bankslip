package br.com.bankslipgeneration.bankslip.facade;

import java.util.List;

import br.com.bankslipgeneration.bankslip.Boleto;

public interface Facade {
	public Boleto getBankSlip(Object[] obj);
	public byte[] getBankSlipInByte(List<Boleto> listBankSlip);
}
