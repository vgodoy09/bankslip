package br.com.bankshipgeneration.bankship.facade;

import java.util.List;

import br.com.bankshipgeneration.bankship.Boleto;

public interface Facade {
	public Boleto getBankShip(Object[] obj);
	public byte[] getBankShipInByte(List<Boleto> listBankShip);
}
