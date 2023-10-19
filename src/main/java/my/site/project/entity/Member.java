package my.site.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.crypto.password.PasswordEncoder;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) 
    private String email;
    private String password;
    private String name;
    
    // 주소
   
    private String postalCode;
    private String addressBasic;
    private String addressRest;
  
    private String phone;
    private String mobile;
    private String birth;
    private String role;
    private String provider;
    @Column(name = "join_date")
    private String joinDate;
    
    
    
    
 // 회원가입 시 목록 추가할 곳
   /* public static Member createMember(
	    String email, 
	    String password,
	    String name,
	    String postalCode, 
	    String addressBasic, 
	    String addressRest,
	    String phone, 
	    String mobile,
	    String birth,
	    String role,
	    String provider, 
	    PasswordEncoder passwordEncoder) {
        return new Member(null,email, passwordEncoder.encode(password), name, postalCode, addressBasic, addressRest, phone, mobile, birth,"Member","일반회원" );
    }*/
    public static Member createMember(
	    String email, 
	    String password,
	    String name,
	    String postalCode, 
	    String addressBasic, 
	    String addressRest,
	    String phone, 
	    String mobile,
	    String birth,
	    
	    PasswordEncoder passwordEncoder) {
	    Member member = new Member();
	    member.setEmail(email);
	    member.setPassword(passwordEncoder.encode(password));
	    member.setName(name);
	    member.setPostalCode(postalCode);
	    member.setAddressBasic(addressBasic);
	    member.setAddressRest(addressRest);
	    member.setPhone(phone);
	    member.setMobile(mobile);
	    member.setBirth(birth);
	    member.setRole("ADMIN");
	    member.setProvider("관리자");
	    
	   LocalDateTime joinDate = LocalDateTime.now();

	    // 날짜와 시간을 원하는 형식으로 포맷합니다.
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String formattedDateTime = joinDate.format(formatter);
	    member.setJoinDate(formattedDateTime); // 현재 날짜 설정
	    
	    return member;
	}   
    // 사용자의 이름이나 이메일을 업데이트하는 메소드
    public Member updateUser(String name, String email, String birth, String mobile) {
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.mobile = mobile;
        
        return this;
    }
    public Member updateUserGoogle(String name, String email) {
	this.name = name;
	this.email = email;
	
	
	return this;
    }

   
}
