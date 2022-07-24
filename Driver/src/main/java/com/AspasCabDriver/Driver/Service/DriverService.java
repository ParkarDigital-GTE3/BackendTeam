package com.AspasCabDriver.Driver.Service;

import java.util.List;

import com.AspasCabDriver.Driver.Model.CabDriver;



public interface DriverService {

	List<CabDriver> findAllByActivationStatus();

	CabDriver updateDriverRegistration(CabDriver driver);

	List<CabDriver> fetchAllDrivers();

	Long countDriver();

	CabDriver updateDriver(CabDriver fetchDriver);

	CabDriver getById(long driverId);

	CabDriver fetchDriverByEmail(String driverEmail);

}
