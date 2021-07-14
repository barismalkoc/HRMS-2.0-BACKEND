package bbm.humanrmsystem.business.abstracts;

import java.util.List;



import bbm.humanrmsystem.core.utilities.results.DataResult;
import bbm.humanrmsystem.core.utilities.results.Result;

import bbm.humanrmsystem.entities.concretes.Employer;

public interface EmployerService {
	
	public Result register(Employer employer, String siteURL);
	public DataResult<List<Employer>> getAll();
	
	public Result updateIsActive(int id , boolean isAvtice);
	public void sendVerificationEmail(Employer employer, String siteURL);
	
	public Result updateEmailVerification(String emailVerificationCode);

}
