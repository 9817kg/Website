package  my.site.project.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;



@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Data
public abstract class BaseEntity {

	//방명록에 작성되는 글들의 날짜값을 자동 지정하도록 컬럼 설정함
	@CreatedDate//해당 필드 즉 컬럼에 날짜값 자동 반영하도록 선언
	@Column(name = "regDate", updatable = false)//DB에 regdate 컬럼 생성하도록 선언 및,
	//값이 처음 insert 이후엔 자동 update 불가 하도록 설정
	private LocalDateTime regDate;
	
	
	@CreatedDate
	@Column(name="modDate")//수정일 컬럼 설정.. 자동으로 insert, update 되어짐
	private LocalDateTime modDate;
}
