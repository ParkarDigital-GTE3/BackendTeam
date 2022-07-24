package com.AspasCabAdmin.Admin.Service;

import com.AspasCabAdmin.Admin.Model.Admin;

public interface AdminAuthService {

	boolean emailValidation(String email);

	Admin fetchAdminByEmail(String emailId);

	Admin createAdmin(Admin adminDetails);

}
