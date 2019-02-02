package pl.majk.learn.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.majk.learn.constants.AppDemoConstants;
import pl.majk.learn.entity.User;
import pl.majk.learn.utilities.AppdemoUtils;

public class UserRegisterValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    User u = (User) o;

        ValidationUtils.rejectIfEmpty(errors, "name", "error.userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.userLastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "error.userEmail.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.userPassword.empty");

        if(!u.getEmail().equals(null)) {
            boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.EMAIL_PATTERN, u.getEmail());
            if (!isMatch) {
                errors.rejectValue("email", "error.userEmailIsNotMatch");
            }
        }
        if(!u.getPassword().equals(null)) {
            boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.PASSWORD_PATTERN, u.getPassword());
            if (!isMatch) {
                errors.rejectValue("password", "error.userPasswordIsNotMatch");
            }
        }

    }

    public void validateEmailExist(User user, Errors errors){

        if(user != null) {

            errors.rejectValue("email", "error.userEmailExist");
        }
    }

}
