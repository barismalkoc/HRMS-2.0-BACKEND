package bbm.humanrmsystem.entities.concretes;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "job_advertisement")
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","JobAdvertisement"})
public class JobAdvertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "job_description")
	private String jobDescription;
	
	@Column(name = "min_salary")
	private int minSalary;
	
	@Column(name = "max_salary")
	private int maxSalary;
	
	@Column(name = "number_of_open_positions")
	private int numberOfOpenPosition;
	
	@Column(name = "application_deadline")
	private String applicationDeadline;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "release_date")
	private String releaseDate;
	
	@Column(name ="job_position_name")
	private String jobPositionName;
	
	@Column(name ="city_name")
	private String cityName;
	
	@Column(name ="employer_name")
	private String employerName;
	
	@Column(name ="hrms_staff_confirmation")
	private boolean hrmsStaffConfirmation;
	
	@Column(name ="type_of_work")
	private String typeOfWork;
	
	@Column(name ="part_or_full_time")
	private String partOrFullTime;
	
	
	
	
	
}
