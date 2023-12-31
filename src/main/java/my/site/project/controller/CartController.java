package my.site.project.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.site.project.dto.MemberJoinDto;
import my.site.project.repository.CartRepository;
import my.site.project.repository.MemberQuery;
import my.site.project.service.MemberService;
import my.site.project.entity.Cart;
import my.site.project.oauth2.UserProfile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
public class CartController {

    private final MemberService memberService;
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    MemberQuery query;

    @GetMapping("/cart")
    public String cartView(@AuthenticationPrincipal User user, Model model, HttpSession session) {
        Object dtoObject = session.getAttribute("dto");
        if (dtoObject instanceof MemberJoinDto) {
            MemberJoinDto dto = (MemberJoinDto) dtoObject;

            List<Cart> carts = cartRepository.findAllByMember(dto.getId());
            model.addAttribute("dto", dto);
            model.addAttribute("list", carts);
            session.setAttribute("dto", dto);
            return "cart";
        } else if (dtoObject instanceof UserProfile) {
            UserProfile userProfile = (UserProfile) dtoObject;

            List<Cart> carts = cartRepository.findAllByMember(userProfile.getId());
            model.addAttribute("dto", userProfile);
            model.addAttribute("list", carts);
            session.setAttribute("dto", userProfile);

            return "cart";
        }
        return "cart";

    }

    @PostMapping("/cart")
    public String Cart(Cart cart, @AuthenticationPrincipal User user, HttpSession session) {
        Object dtoObject = session.getAttribute("dto");
        System.out.println(cart.getProduct().getPrice());
        if (dtoObject instanceof MemberJoinDto) {
            MemberJoinDto dto = (MemberJoinDto) dtoObject;
            cart.setMember(dto.getId());
            cartRepository.save(cart);
            return "redirect:/my/cart";

        } else if (dtoObject instanceof UserProfile) {
            UserProfile userProfile = (UserProfile) dtoObject;

            cart.setMember(userProfile.getId());
            System.err.println(cart.getMember());
            cartRepository.save(cart);
            return "redirect:/my/cart";

        }

        return "redirect:/my/cart";

    }
    
    

}
