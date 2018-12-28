package pl.martyna.lotto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.martyna.lotto.dto.Settings;
import pl.martyna.lotto.services.Draw;
import pl.martyna.lotto.exceptions.IllegalValueException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Spring Controller
 * @author Martyna Drabinska
 * @version 0.1
 */
@Controller
public class HomeController {

    /** Service responsible for lotto simulation */
    private final Draw draw;

    /**
     * default constructor
     * @param draw service Draw
     */
    @Autowired
HomeController(Draw draw){
    this.draw = draw;
}

    /**
     * Returns view name of main page
     * @return view name of main page
     */
    @RequestMapping("/")
    public String showMainPage(){
    return "index";
    }

    /**
     * Returns view name of page with drawn results
     * @param minCookie cookie which contains minimum value of drawn results
     * @param maxCookie cookie which contains maximum value of drawn results
     * @param quantityCookie cookie which contains quantity of drawn results
     * @param modelMap  Spring ModelMap
     * @return view name of page with results
     */
    @RequestMapping("/draw")
    public String showResults(@CookieValue(value="min", defaultValue ="1") int minCookie, @CookieValue(value = "max", defaultValue = "49") int maxCookie,
                              @CookieValue(value = "quantity", defaultValue = "5") int quantityCookie, ModelMap modelMap){
        modelMap.addAttribute("results", draw.randomResults());
        modelMap.addAttribute("min", minCookie);
        modelMap.addAttribute("max", maxCookie);
        modelMap.addAttribute("quantity", quantityCookie);
        return "draw";
    }


    /**
     * Returns view name of page with form responsible for changing settings
     * @param hasIllegalValues contains information if alert about illegal values should appear in view
     * @param modelMap Spring ModelMap
     * @return view name of page with form responsible for changing settings
     */
    @RequestMapping("/settings")
    public String showSettingsForm( @RequestParam(value = "hasIllegalValues", required = false, defaultValue = "false") boolean
            hasIllegalValues, ModelMap modelMap){
        modelMap.addAttribute("hasIllegalValues", hasIllegalValues);
        Settings settings = new Settings();
        modelMap.addAttribute("settings", settings);
        return "settings";
    }

    /**
     * Handles submitted form
     * @param settings DTO object which contains data from submitted form
     * @param result contains information about settings validation
     * @param response Http response
     * @return view name
     * @throws IllegalValueException  if for passed arguments service can't carry out a simulation
     */
    @RequestMapping("/settings-form-check")
    public String handleForm(@ModelAttribute("settings") @Valid Settings settings, BindingResult result, HttpServletResponse response)
                            throws IllegalValueException {

        if (result.hasErrors()) {
            return "settings";
        }
        else {
            Cookie minCookie = new Cookie("min", Integer.toString(settings.getMin()));
            Cookie maxCookie = new Cookie("max", Integer.toString(settings.getMax()));
            Cookie quantityCookie = new Cookie("quantity", Integer.toString(settings.getQuantity()));
            response.addCookie(minCookie);
            response.addCookie(maxCookie);
            response.addCookie(quantityCookie);
            draw.changeSettings(settings.getMin(), settings.getMax(), settings.getQuantity());
            return "index";
        }
    }

    /**
     * Handles IllegalValueException
     * @param model Spring Model
     * @return redirect for changing settings
     */
    @ExceptionHandler(value = IllegalValueException.class)
    public String handle(Model model){
        boolean hasIllegalValues = true;
        model.addAttribute("hasIllegalValues", hasIllegalValues);
        return "redirect:/settings";
    }

    /**
     * Shows history of drawings
     * @param modelMap Spring ModelMap
     * @return view name of page with history of drawings
     */
    @RequestMapping("/history")
    public String showHistory(ModelMap modelMap){
        modelMap.addAttribute("history", draw.getHistory());
        return "history";
    }

}
