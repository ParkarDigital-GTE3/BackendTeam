package com.AspasCabDriver.Driver.Service;

import java.util.List;

import com.AspasCabDriver.Driver.Model.CabDetails;


public interface CabService {
	
	CabDetails createCab(CabDetails cabDetails);
	
	CabDetails fetchCabByRegistrationNo(String cabRegistrationNo);
	
	CabDetails fetchCabById(long cab_id);

	List<CabDetails> findAllCabs();

	CabDetails updateCab(CabDetails fetchCab);

	CabDetails fetchCabByLicenseNo(String LicenseNo);

	CabDetails fetchCabByInsuranceNo(String cabInsuranceNo);

	CabDetails fetchCabByEmail(String driverEmail);


}
