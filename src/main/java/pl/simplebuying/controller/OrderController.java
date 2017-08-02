package pl.simplebuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.simplebuying.model.Order;
import pl.simplebuying.model.User;
import pl.simplebuying.service.OrderService;

@Controller
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/summary")
	public String summaryOfPurchase(Model model, @SessionAttribute User user, @ModelAttribute Order order, RedirectAttributes redirectAttribute) {
		if (orderService.checkUserAddress(user.getAddress())) {
			orderService.setOrder(order);
			String date = orderService.getDateAsString();
			model.addAttribute("date", date);
			return "summary";
		} else {
			redirectAttribute.addFlashAttribute("message", "Musisz uzupełnic swój adres w celu złożenia zamówienia");
			return "redirect:/shoppingcart";
		}
	}

	@GetMapping("/summary/confirm")
	public String confirmationOfPurchase(@SessionAttribute User user, RedirectAttributes redirectAttribute) {
		if(orderService.saveOrder(user)) {
			return "redirect:/";
		} else {
			redirectAttribute.addFlashAttribute("message", "Przedmiot który chcesz kupić jest już niedostępny lub został wyprzedany");
			return "redirect:/shoppingcart";
		}
		
	}
	
}
