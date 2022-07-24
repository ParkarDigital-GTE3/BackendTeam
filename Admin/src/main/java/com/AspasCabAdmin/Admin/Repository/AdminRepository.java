package com.AspasCabAdmin.Admin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AspasCabAdmin.Admin.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.email=?1")
	Admin getAdminByEmail(String email);
	

}
