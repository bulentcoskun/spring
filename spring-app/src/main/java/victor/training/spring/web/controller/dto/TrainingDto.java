package victor.training.spring.web.controller.dto;


import victor.training.spring.web.entity.ContractType;

import javax.validation.constraints.Size;

public class TrainingDto {
	public Long id;
	@Size(min = 3, max = 50)
//	@ValidTrainingName
	public String name;
	public ContractType level;
	public Long teacherId;
	public String teacherName;
	public String startDate;
	public String description;
}