package truong.capgemini.usermanagement.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import truong.capgemini.usermanagement.entity.UserEntity;

public class UserValidator implements Validator{
	
	private EmailValidator validator = EmailValidator.getInstance();
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return arg0 == UserEntity.class;
	}

	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		UserEntity entity = (UserEntity) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "NotEmpty.applicantForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "email", "NotEmpty.applicantForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "phone", "NotEmpty.applicantForm.phone");
		
		if(!validator.isValid(entity.getEmail())){
			arg1.rejectValue("Email", "Pattern.applicantForm.email");
		}
	}

}
