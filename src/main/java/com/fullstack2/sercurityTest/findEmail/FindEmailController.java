package com.fullstack2.sercurityTest.findEmail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullstack2.sercurityTest.repository.MemberQuery;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FindEmailController {

    private final MemberQuery query;
    private FindEmailDTO dto = new FindEmailDTO();
    
    @GetMapping("/findEmail")
    public String findEmailPage() {
       
       return "findEmail"; 
    }
    
    @PostMapping("/findEmail")
    public String findEmailPagePost(@RequestParam("mobile") String mobile
	    ,RedirectAttributes redirectAttributes) {
	String ph = query.selectMobile(mobile);
	if (ph != null) {
	    String email = query.selectMobilebyEmail(mobile);
	    dto.setEmail(email);
	    
	    return "redirect:/resultFindEmail";  
	}else if (ph == null) {
	    redirectAttributes.addAttribute("message","err");
	    return "redirect:/findEmail";
	}
	return ph;
	
	
	
    }
    
    @GetMapping("/resultFindEmail")
    public String resEmail(Model model) {
	model.addAttribute("dto",dto);
       
       return "resultFindEmail"; 
    }
}
