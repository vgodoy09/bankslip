package br.com.bankslipgeneration.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.bankshipgeneration.bankship.Boleto;
import br.com.bankshipgeneration.bankship.facade.Facade;
import br.com.bankshipgeneration.bankship.facade.FacadeSystem;

@Path("/bankship")
public class BankShipService {

	@PostConstruct
	private void init() {
		
	}
	
	@POST
	@Path("/generation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public byte[] generationBankship(List<Object[]> list) {
		Facade fc = new FacadeSystem();
		List<Boleto> listBankShip = new ArrayList<Boleto>();
		list.stream().forEach(o -> listBankShip.add(fc.getBankShip(o)));
		return fc.getBankShipInByte(listBankShip);
	}
}
