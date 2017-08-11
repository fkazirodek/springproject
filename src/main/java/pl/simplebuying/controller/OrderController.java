package pl.simplebuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.simplebuying.model.Order;
import pl.simplebuying.model.Payment;
import pl.simplebuying.model.User;
import pl.simplebuying.service.OrderService;

@Controller
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/summary")
	public String summaryOfPurchase(@SessionAttribute User user, 
									@ModelAttribute Order order, 
								    @ModelAttribute Payment payment, 
								    Model model,
									RedirectAttributes redirectAttribute) {
		if (!user.isEnabled()) {
			redirectAttribute.addFlashAttribute("message",
					"Musisz aktywować konto w celu złozenia zamówienia(wejdz w link wysłany w wiadomości email)");
			return "redirect:/shoppingcart";
		}
		if (orderService.checkUserAddress(user.getAddress())) {
			orderService.createOrder(order, payment, user);
			model.addAttribute("order", order);
			return "summary";
		} else {
			redirectAttribute.addFlashAttribute("message", "Musisz uzupełnic swój adres w celu złożenia zamówienia");
			return "redirect:/shoppingcart";
		}
	}

	@GetMapping("/summary/confirm")
	public String confirmationOfPurchase(@SessionAttribute User user, RedirectAttributes redirectAttribute) {
		if (orderService.checkAndSaveOrder(user)) {
			return "success_order";
		} else {
			redirectAttribute.addFlashAttribute("message",
					"Przedmiot który chcesz kupić jest już niedostępny lub został wyprzedany");
			return "redirect:/shoppingcart";
		}
	}

}
