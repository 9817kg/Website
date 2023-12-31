package  my.site.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import   my.site.project.dto.MemberJoinDto;
import   my.site.project.dto.NoticeDTO;
import   my.site.project.dto.NoticePageRequestDTO;
import   my.site.project.dto.NoticePageResultDTO;
import   my.site.project.dto.QnADTO;
import   my.site.project.dto.QnAPageRequestDTO;
import   my.site.project.dto.QnAPageResultDTO;
import   my.site.project.dto.ReviewDTO;
import   my.site.project.dto.ReviewPageRequestDTO;
import   my.site.project.dto.ReviewPageResultDTO;
import  my.site.project.entity.Member;
import  my.site.project.entity.Notice;
import  my.site.project.entity.Product;
import  my.site.project.entity.QnA;
import  my.site.project.entity.Review;
import  my.site.project.oauth2.UserProfile;
import  my.site.project.service.MemberService;
import  my.site.project.service.NoticeService;
import  my.site.project.service.Product_Service;
import  my.site.project.service.QnAService;
import  my.site.project.service.ReviewService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class UserController {

    private final ReviewService reviewService;
    private final Product_Service productService;
    private final MemberService memberService;
    private final QnAService qnaService;
    private final NoticeService noticeService;
    
    
   
 
    @GetMapping("/sns")
    public String snsPage(HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "sns";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "sns"; // online-store.html 페이지로 이동
	}
	return "sns";
    }

    @GetMapping("/")
    public String home() {
	return "home"; // 홈 페이지
    }
    
    @GetMapping("/product_list")
    public String product_list() {
	
	return "product_list";
    }
   
  
    @GetMapping("/qna_manage")
    public String qna_manage() {
	
	return "qna_manage";
    }
   
    @GetMapping("/user_list")
    public String user_list(HttpSession session, Model model) {
            // 전체 회원 목록을 가져온다
            List<Member> members = memberService.getAllMembers();
            model.addAttribute("members", members);
            return "user_list";
    }
    @DeleteMapping("/deleteMember/{memberId}")
    @ResponseBody
    public String deleteMember(@PathVariable Long memberId) {
        try {
            // 회원을 삭제하는 비즈니스 로직 수행
            memberService.deleteMemberById(memberId);
            return "success";
        } catch (Exception e) {
            System.err.println("삭제 중 예외 발생 : " + e.getMessage());
            return "error";
        }
    }
    
    
    
    @GetMapping("/main")
    public String main() {
	return "main"; // 메인 페이지
    }

    @GetMapping("/aboutus")
    public String aboutUs(HttpSession session, Model model) {
	// About Us 페이지 로직 처리
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "aboutus";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "aboutus"; // online-store.html 페이지로 이동
	}
	return "aboutus";
    }

    @GetMapping("/onlinestore")
    public String onlineStore(HttpSession session, Model model) {
	// Online Store 페이지 로직 처리
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "onlinestore";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "onlinestore"; // online-store.html 페이지로 이동
	}
	return "onlinestore";

    }

    @GetMapping("/collection")
    public String collection(HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "collection";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "collection"; // online-store.html 페이지로 이동
	}
	return "collection";
    }

    @GetMapping("/orderhistory")
    public String orderhistoryPage() {
	// SNS 페이지 로직 처리
	return "orderhistory"; // manageacc.html 페이지로 이동
    }

    @GetMapping("/points")
    public String pointsPage() {
	// SNS 페이지 로직 처리
	return "points"; // manageacc.html 페이지로 이동
    }

    @GetMapping("/manageposts")
    public String managepostscPage() {
	// SNS 페이지 로직 처리
	return "manageposts"; // manageacc.html 페이지로 이동
    }

    @GetMapping("/cart2")
    public String cart2Page(HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "cart2";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "cart2";
	}
	return "cart2";
    }

    @GetMapping("/notice")
    public String notice(@ModelAttribute("noticePageRequestDTO") NoticePageRequestDTO noticePageRequestDTO,
          HttpSession session, Model model) {
   Object dtoObject = session.getAttribute("dto");

   if (dtoObject instanceof MemberJoinDto) {
      NoticePageResultDTO<NoticeDTO, Notice> noticeResult = noticeService.getList(noticePageRequestDTO);
       MemberJoinDto dto = (MemberJoinDto) dtoObject;
       
       model.addAttribute("noticeResult", noticeResult);
       model.addAttribute("dto", dto);
       return "notice";

   } else if (dtoObject instanceof UserProfile) {
      NoticePageResultDTO<NoticeDTO, Notice> noticeResult = noticeService.getList(noticePageRequestDTO);
       UserProfile userProfile = (UserProfile) dtoObject;
       
       model.addAttribute("noticeResult", noticeResult);
       model.addAttribute("dto", userProfile);
       return "notice";
   }
   return "notice";
 }
    @GetMapping("/qna")
    public String qna(@ModelAttribute("qnaPageRequestDTO") QnAPageRequestDTO qnaPageRequestDTO,
    		HttpSession session, Model model) {
    	Object dtoObject = session.getAttribute("dto");

    	   if (dtoObject instanceof MemberJoinDto) {
    		QnAPageResultDTO<QnADTO, QnA> qnaResult = qnaService.getList(qnaPageRequestDTO);
    	       MemberJoinDto dto = (MemberJoinDto) dtoObject;
    	       
    	       model.addAttribute("dto", dto);
    	       model.addAttribute("qnaResult", qnaResult);
    	       return "qna";

    	   } else if (dtoObject instanceof UserProfile) {
    	       UserProfile userProfile = (UserProfile) dtoObject;
    	       QnAPageResultDTO<QnADTO, QnA> qnaResult = qnaService.getList(qnaPageRequestDTO);
    	       
    	       model.addAttribute("dto", userProfile);
    	       model.addAttribute("qnaResult", qnaResult);
    	       return "qna";
    	   }
    	   return "qna";
    }


    
    @GetMapping("/community")
    public String community(HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "community";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "community";
	}
	return "community";
    }

    @GetMapping("/review")
    public String review(@ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO,
	    HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);

	    model.addAttribute("reviewResult", reviewResult);
	    model.addAttribute("dto", dto);

	    return "review";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);

	    model.addAttribute("reviewResult", reviewResult);
	    model.addAttribute("dto", userProfile);

	    return "review";

	}
	else {
	    ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);
	    model.addAttribute("reviewResult", reviewResult);
	    return "review";
	}
	

    }

    // 벨트 제품 리스트로 맵핑belt
    @GetMapping(value = "/product/{id}")
    public String Product(HttpSession session, Model model, @PathVariable("id") String id) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    List<Product> entity = productService.Category_item_All(id);
	    model.addAttribute("dto", dto);
	    model.addAttribute("list", entity);
	    return "product";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    List<Product> entity = productService.Category_item_All(id);
	    model.addAttribute("dto", userProfile);
	    model.addAttribute("list", entity);
	    return "product";

	}else {
	    List<Product> entity = productService.Category_item_All(id);
	    model.addAttribute("list", entity);
	    return "product";
	}
	
    }

    @GetMapping(value = "/productdetail/{itemcount}")
    public String beltdetail(@PathVariable Long itemcount, @ModelAttribute("Product") Product product,
       @ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO, HttpSession session,
       Model model) {
   Object dtoObject = session.getAttribute("dto");
   if (dtoObject instanceof MemberJoinDto) {
       MemberJoinDto dto = (MemberJoinDto) dtoObject;
       ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);
       Optional<Product> productOptional = productService.SelectONE(product.getItemcount());
      
       model.addAttribute("dto", dto);
       model.addAttribute("itemcount", itemcount); // 모델에 itemcount를 추가
       model.addAttribute("reviewResult", reviewResult);
       model.addAttribute("Product", productOptional.get());
       return "/productdetail";

   } else if (dtoObject instanceof UserProfile) {
       UserProfile userProfile = (UserProfile) dtoObject;
       ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);
       Optional<Product> productOptional = productService.SelectONE(product.getItemcount());
       model.addAttribute("dto", userProfile);

       model.addAttribute("reviewResult", reviewResult);
       model.addAttribute("Product", productOptional.get());

       return "/productdetail";

   }
   return "/productdetail";
}
    
    @GetMapping("/product_reviews")
    public String product_reviews(@ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO,
    							  @Param("rno") Long rno, Model model) {
       ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);

       model.addAttribute("reviewResult", reviewResult);

       return "/product_reviews";
    }
    
    @GetMapping("/noticemanage")
    public String noticeManage(@ModelAttribute("reviewPageRequestDTO") NoticePageRequestDTO noticePageRequestDTO,
                         Model model) {
       NoticePageResultDTO<NoticeDTO, Notice> noticeResult = noticeService.getList(noticePageRequestDTO);
       
       model.addAttribute("noticeResult", noticeResult);
       
       return "/noticemanage";
    }
    
    
}

