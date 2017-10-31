package br.com.bankslipgeneration.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.bankslipgeneration.bankslip.Boleto;
import br.com.bankslipgeneration.bankslip.facade.Facade;
import br.com.bankslipgeneration.bankslip.facade.FacadeSystem;

@Path("/bankslip")
public class BankShipService {

	@PostConstruct
	private void init() {
		
	}
	
	@POST
	@Path("/generation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object[] generationBankslip(List<Object[]> list) {
		Facade fc = new FacadeSystem();
		List<Boleto> listBankShip = new ArrayList<Boleto>();
		list.stream().forEach(o -> listBankShip.add(fc.getBankSlip(o)));
		return new Object[] {fc.getBankSlipInByte(listBankShip)};
	}
}
