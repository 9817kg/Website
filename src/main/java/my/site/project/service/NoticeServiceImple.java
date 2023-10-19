package my.site.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import my.site.project.dto.NoticeDTO;
import my.site.project.dto.NoticePageRequestDTO;
import my.site.project.dto.NoticePageResultDTO;
import my.site.project.entity.Notice;
import my.site.project.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService {

	private final NoticeRepository noticeRepository;
	
	@Override
	public Long notice(NoticeDTO dto) {
		Notice notice = dtoToEntity(dto);
		noticeRepository.save(notice);
		return notice.getNno();		
	}

	@Override
	public NoticePageResultDTO<NoticeDTO, Notice> getList(NoticePageRequestDTO pageRequestDTO) {
		
		Page<Notice> result = noticeRepository.getNoticePaged(pageRequestDTO.getPageable(Sort.by("nno").descending()));
		return new NoticePageResultDTO<>(result, this::entityToDTO);
	}

}
