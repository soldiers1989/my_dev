package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.ApartmentDepositContract;

/**
 * apartment_deposit_contract EntityQuery
 * @author robot
 * @version 2018-02-02
 */
public class ApartmentDepositContractQuery extends Query {

	private static final long serialVersionUID = 1L;

	public ApartmentDepositContractQuery(){
		this.apartmentDepositContract = new ApartmentDepositContract();
	}
	
	private ApartmentDepositContract apartmentDepositContract;

	public ApartmentDepositContract getApartmentDepositContract() {
		return apartmentDepositContract;
	}

	public void setApartmentDepositContract(ApartmentDepositContract apartmentDepositContract) {
		this.apartmentDepositContract = apartmentDepositContract;
	}

}