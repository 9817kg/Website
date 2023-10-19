package my.site.project.service;

import my.site.project.dto.NoticeDTO;
import my.site.project.dto.NoticePageRequestDTO;
import my.site.project.dto.NoticePageResultDTO;
import my.site.project.entity.Notice;

public interface NoticeService {

	
	Long notice(NoticeDTO dto);
	
	NoticePageResultDTO<NoticeDTO, Notice> getList(NoticePageRequestDTO pageRequestDTO);
	
	default Notice dtoToEntity(NoticeDTO dto) {
		
		Notice notice = Notice.builder()
				.nno(dto.getNno())
				.admin(dto.getAdmin())
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		return notice;
	}
	
	default NoticeDTO entityToDTO(Notice notice) {
		
		NoticeDTO dto = NoticeDTO.builder()
				.nno(notice.getNno())
				.admin(notice.getAdmin())
				.title(notice.getTitle())
				.content(notice.getContent())
				.regDate(notice.getRegDate())
				.modDate(notice.getModDate())
				.build();
		
		return dto;
	}
}
