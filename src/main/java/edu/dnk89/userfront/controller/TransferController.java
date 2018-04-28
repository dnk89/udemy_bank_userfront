package edu.dnk89.userfront.controller;

import edu.dnk89.userfront.domain.User;
import edu.dnk89.userfront.services.TransactionService;
import edu.dnk89.userfront.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
    public String betweenAccounts(Model model) {
        model.addAttribute("transferFrom", "");
        model.addAttribute("transferTo", "");
        model.addAttribute("amount", "");

        return "betweenAccounts";
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String betweenAccounts(@ModelAttribute("transferFrom") String transferFrom,
                                  @ModelAttribute("transferTo") String transferTo,
                                  @ModelAttribute("amount") String amount,
                                  Principal principal) throws Exception {

        User user = userService.findByUsername(principal.getName());
        transactionService.transferBetweenAccounts(transferFrom, transferTo, amount, user.getPrimaryAccount(), user.getSavingsAccount());

        return "redirect:/userFront";
    }
}
