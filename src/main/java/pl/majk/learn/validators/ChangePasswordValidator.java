package pl.majk.learn.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.majk.learn.constants.AppDemoConstants;
import pl.majk.learn.entity.User;
import pl.majk.learn.utilities.AppdemoUtils;

public class ChangePasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        @SuppressWarnings("unused")
        User u = (User) o;

        ValidationUtils.rejectIfEmpty(errors, "newPassword", "error.userPassword.empty");
    }

    public void chcekPasssword(String newPass, Errors errors) {

        if(!newPass.equals(null)) {
            boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.PASSWORD_PATTERN, newPass);
            if(!isMatch) {
                errors.rejectValue("newPassword", "error.userPasswordIsNotMatch");
            }
        }
    }
}
