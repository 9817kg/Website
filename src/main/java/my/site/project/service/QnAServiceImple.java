package my.site.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import my.site.project.dto.QnADTO;
import my.site.project.dto.QnAPageRequestDTO;
import my.site.project.dto.QnAPageResultDTO;
import my.site.project.entity.QnA;
import my.site.project.repository.QnARepository;
import my.site.project.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnAServiceImple implements QnAService {

	private final QnARepository qnaRepository;
	
	@Override
	public Long qna(QnADTO dto) {
		QnA qna = dtoToEntity(dto);
		qnaRepository.save(qna);
		return qna.getQno();
	}

	@Override
	public QnAPageResultDTO<QnADTO, QnA> getList(QnAPageRequestDTO pageRequestDTO) {
		// Page<Review> 타입으로 반환하도록 수정
        Page<QnA> result = qnaRepository.getQnAPaged(pageRequestDTO.getPageable(Sort.by("qno").descending()));
        return new QnAPageResultDTO<>(result, this::entityToDTO);
	}

}
