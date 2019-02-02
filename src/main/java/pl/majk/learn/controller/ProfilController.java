package pl.majk.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.majk.learn.entity.User;
import pl.majk.learn.services.UserService;
import pl.majk.learn.utilities.UserUtilities;
import pl.majk.learn.validators.ChangePasswordValidator;
import pl.majk.learn.validators.EditUserProfileValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Locale;

@Controller
public class ProfilController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GET
    @RequestMapping(value = "/profil")
    public String showUserProfilePage(Model model) {

        String userName = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(userName);
        int nrRoli = user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);
        model.addAttribute("user", user);

        return "profil";
    }

    @GET
    @RequestMapping(value = "/editpassword")
    public String editUserPassword(Model model) {

        String userName = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(userName);
        model.addAttribute("user", user);
        return "editPassword";
    }

    @POST
    @RequestMapping(value = "/updatepass")
    public String changeUserPassword(User user, BindingResult result, Model model, Locale locale) {
        String returnPage = null;

        new ChangePasswordValidator().validate(user, result);

        new ChangePasswordValidator().chcekPasssword(user.getNewPassword(), result);

        if(result.hasErrors()) {
            returnPage = "editPassword";
        } else {
            userService.updateUserPassword(user.getNewPassword(), user.getEmail());
            returnPage = "editPassword";
            model.addAttribute("message", messageSource.getMessage("passwordChange.success", null, locale));
        }
        return returnPage;
    }

    @GET
    @RequestMapping(value = "/editprofil")
    public String changeUserData(Model model) {

        String userName = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(userName);
        model.addAttribute("user", user);
        return "editProfile";
    }

    @POST
    @RequestMapping(value = "/updateprofil")
    public String changeUserDataAction(User user, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        new EditUserProfileValidator().validate(user, result);
        if (result.hasErrors()) {
            returnPage = "editProfile";
        } else {
            userService.updateUserProfile(user.getName(), user.getLastName(), user.getEmail(), user.getId());
            model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
            returnPage = "afterEdit";
        }
        return returnPage;
    }
}
