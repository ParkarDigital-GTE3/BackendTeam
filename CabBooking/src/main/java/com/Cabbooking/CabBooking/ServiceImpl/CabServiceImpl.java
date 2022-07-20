package com.Cabbooking.CabBooking.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Repository.CabRepository;
import com.Cabbooking.CabBooking.Service.CabService;

@Service
public class CabServiceImpl implements CabService{

	@Autowired
	CabRepository cabRepository;
	
	// Create Cab
	@Override
	public CabDetails createCab(CabDetails cabDetails) {
		
		return cabRepository.save(cabDetails);
	}

	// Fetch Cab By Registration No
	@Override
	public CabDetails fetchCabByRegistrationNo(String cabRegistrationNo) {

		return cabRepository.fetchCabByRegistrationNo(cabRegistrationNo);
	}

	// Fetch By id
	@Override
	public CabDetails fetchCabById(long cab_id) {
		return cabRepository.fetchCabById(cab_id);
	}

	@Override
	public List<CabDetails> findAllCabs() {

		return cabRepository.findAll();
	}

	@Override
	public CabDetails updateCab(CabDetails fetchCab) {
	
		return cabRepository.save(fetchCab);
	}
	

}
