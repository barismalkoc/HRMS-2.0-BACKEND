package bbm.humanrmsystem.business.concretes;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import bbm.humanrmsystem.business.abstracts.EmployerService;
import bbm.humanrmsystem.core.utilities.abstracts.EmailCheckService;
import bbm.humanrmsystem.core.utilities.results.DataResult;
import bbm.humanrmsystem.core.utilities.results.ErrorDataResult;
import bbm.humanrmsystem.core.utilities.results.ErrorResult;
import bbm.humanrmsystem.core.utilities.results.Result;
import bbm.humanrmsystem.core.utilities.results.SuccessDataResult;
import bbm.humanrmsystem.core.utilities.results.SuccessResult;
import bbm.humanrmsystem.dataAccess.abstracts.EmployerDao;
import bbm.humanrmsystem.entities.concretes.Employer;
import net.bytebuddy.utility.RandomString;

@Service
public class EmployerManager implements EmployerService {
	
	

	private EmployerDao employerDao;
	private EmailCheckService emailCheckService;
	private JavaMailSender mailSender;

	@Autowired
	public EmployerManager(EmployerDao employerDao, EmailCheckService emailCheckService, JavaMailSender mailSender) {
		super();
		this.employerDao = employerDao;
		this.emailCheckService = emailCheckService;
		this.mailSender = mailSender;
	}

	@Override
	public Result register(Employer employer, String siteURL) {

		Result result = new SuccessDataResult<Employer>(employer, "Kayit basarili.");

		if (emailCheckService.emailIsItUsed(employer.getEmail())) {
			result = new ErrorDataResult<Employer>(employer, "Email sisteme kayitli.");
			return result;
		}  else {
			this.sendVerificationEmail(employer, siteURL);
			this.employerDao.save(employer);
		}
		return result;
	}
	


	public void sendVerificationEmail(Employer employer, String siteURL) {
		String randomCodeforEmail = RandomString.make(64);
		employer.setEmailVerificationCode(randomCodeforEmail);
		String subject = "Please verify your registration";
		String senderName = "BBM Company";
		String mailContent = "<p>Dear " + employer.getCompanyName() + ",</p>";
		mailContent += "<p>Please click the link below to verify to your registration:</p>";

		String verifyURL = siteURL + "/verify?emailVerificationCode=" + employer.getEmailVerificationCode() + "&id=" + employer.getId()	 ;

		mailContent += "<h3><a href=\"" + verifyURL + "\">VERIFY</a></h3>";
		mailContent += "<p> The BBM Team</p>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom("birlucky963@gmail.com", senderName);
			helper.setTo(employer.getEmail());
			helper.setSubject(subject);
			helper.setText(mailContent, true);

			mailSender.send(message);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public DataResult<List<Employer>> getAll() {

		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Data getirildi.");
	}

	@Override
	public Result updateIsActive(int id, boolean isAvtice) {

		Employer employerIsActive = this.employerDao.findById(id).get();
		employerIsActive.setStaffVerification(isAvtice);
		this.employerDao.save(employerIsActive);
		return new SuccessResult("Aktif hale getirildi.");
	}

	@Override
	public Result updateEmailVerification(String emailVerificationCode) {

		Employer employer = this.employerDao.findByVerificationCode(emailVerificationCode);
		
		if(employer == null || employer.isEmailVerification()) {
			return new ErrorResult("Aktiflik yapilamadi.");
		}else {
			employer.setEmailVerification(true);
			this.employerDao.save(employer);
			return new SuccessResult("Aktif hale getirildi.");
		}

	}

}
