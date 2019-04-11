package pl.martyna.lotto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.martyna.lotto.dto.Settings;
import pl.martyna.lotto.service.DrawService;
import pl.martyna.lotto.service.SimulationService;
import pl.martyna.lotto.service.SimulationServiceImp;
import pl.martyna.lotto.exceptions.IllegalValueException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Set;

@Controller
public class HomeController {

    private final SimulationService simulationService;
    private final DrawService drawService;

    @Autowired
    HomeController(SimulationServiceImp simulationService, DrawService drawService){
      this.simulationService = simulationService;
      this.drawService = drawService;
}

    @RequestMapping("/")
    public String showMainPage(){
    return "index";
    }

    @RequestMapping("/draw")
    public String showResults(@CookieValue(value="min", defaultValue ="1") int minCookie, @CookieValue(value = "max", defaultValue = "49") int maxCookie,
                              @CookieValue(value = "quantity", defaultValue = "5") int quantityCookie, ModelMap modelMap){

        Set<Integer> results = simulationService.randomResults();

        modelMap.addAttribute("results", results);
        modelMap.addAttribute("min", minCookie);
        modelMap.addAttribute("max", maxCookie);
        modelMap.addAttribute("quantity", quantityCookie);
        drawService.saveDraw(results);

        return "draw";
    }

    @RequestMapping("/settings")
    public String showSettingsForm( @RequestParam(value = "hasIllegalValues", required = false, defaultValue = "false") boolean
            hasIllegalValues, ModelMap modelMap){
        modelMap.addAttribute("hasIllegalValues", hasIllegalValues);
        Settings settings = new Settings();
        modelMap.addAttribute("settings", settings);

        return "settings";
    }

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
            simulationService.changeSettings(settings.getMin(), settings.getMax(), settings.getQuantity());

            return "index";
        }
    }

    @ExceptionHandler(value = IllegalValueException.class)
    public String handle(Model model){
        boolean hasIllegalValues = true;
        model.addAttribute("hasIllegalValues", hasIllegalValues);

        return "redirect:/settings";
    }

    @RequestMapping("/history")
    public String showHistory(ModelMap modelMap){
        modelMap.addAttribute("history", drawService.getHistory());
        return "history";
    }

}
