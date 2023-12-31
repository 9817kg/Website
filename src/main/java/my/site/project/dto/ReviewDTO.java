package my.site.project.dto;

import java.time.LocalDateTime;

import com.fullstack2.website.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewDTO {

   private Long rno;
   private String reviewer;
   private String text;
   private String password;
   private String content;
   
   private Product product;
   
   private Long itemcount;
   private LocalDateTime regDate, modDate;
}
