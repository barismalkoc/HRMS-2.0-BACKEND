package bbm.humanrmsystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bbm.humanrmsystem.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	
	
	@Query("SELECT e FROM Employer e WHERE e.emailVerificationCode = ?1")
	public Employer findByVerificationCode(String code);
	

}
