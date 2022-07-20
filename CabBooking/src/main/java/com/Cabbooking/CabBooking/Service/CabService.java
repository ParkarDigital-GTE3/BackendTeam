package com.Cabbooking.CabBooking.Service;

import java.util.List;

import com.Cabbooking.CabBooking.Model.CabDetails;

public interface CabService {
	
	CabDetails createCab(CabDetails cabDetails);
	
	CabDetails fetchCabByRegistrationNo(String cabRegistrationNo);
	
	CabDetails fetchCabById(long cab_id);

	List<CabDetails> findAllCabs();

	CabDetails updateCab(CabDetails fetchCab);


}
