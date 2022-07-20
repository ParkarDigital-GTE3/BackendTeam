package com.Cabbooking.CabBooking.Service;

import java.util.List;

import com.Cabbooking.CabBooking.Model.CabDriver;

public interface DriverService {

	List<CabDriver> findAllByActivationStatus();

	CabDriver updateDriverRegistration(CabDriver driver);

	List<CabDriver> fetchAllDrivers();

	Long countDriver();

	CabDriver updateDriver(CabDriver fetchDriver);

	CabDriver getById(long driverId);

	CabDriver fetchDriverByEmail(String driverEmail);

}
