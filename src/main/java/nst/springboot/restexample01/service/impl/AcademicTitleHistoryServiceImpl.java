package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.converter.impl.AcademicTitleHistoryConverter;
import nst.springboot.restexample01.domain.AcademicTitle;
import nst.springboot.restexample01.domain.AcademicTitleHistory;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.domain.ScientificField;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import nst.springboot.restexample01.repository.AcademicTitleHistoryRepository;
import nst.springboot.restexample01.repository.AcademicTitleRepository;
import nst.springboot.restexample01.repository.MemberRepository;
import nst.springboot.restexample01.repository.ScientificFieldRepository;
import nst.springboot.restexample01.service.AcademicTitleHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {
    private final MemberRepository memberRepository;
    private final AcademicTitleRepository academicTitleRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;

    public AcademicTitleHistoryServiceImpl(
            MemberRepository memberRepository,
            AcademicTitleRepository academicTitleRepository,
            ScientificFieldRepository scientificFieldRepository,
            AcademicTitleHistoryConverter academicTitleHistoryConverter,
            AcademicTitleHistoryRepository academicTitleHistoryRepository
    ) {
        this.memberRepository = memberRepository;
        this.academicTitleRepository = academicTitleRepository;
        this.scientificFieldRepository = scientificFieldRepository;
        this.academicTitleHistoryConverter = academicTitleHistoryConverter;
        this.academicTitleHistoryRepository = academicTitleHistoryRepository;
    }

    @Override
    @Transactional
    public AcademicTitleHistoryDto save(AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {
        AcademicTitleHistory academicTitleHistory = academicTitleHistoryConverter.toEntity(academicTitleHistoryDto);

        academicTitleHistory.setMember(handleMember(academicTitleHistory));
        academicTitleHistory.setAcademicTitle(handleAcademicTitle(academicTitleHistory));
        academicTitleHistory.setScientificField(handleScientificField(academicTitleHistory));

        AcademicTitleHistory academicTitleHistory1 = academicTitleHistoryRepository.save(academicTitleHistory);
        return academicTitleHistoryConverter.toDto(academicTitleHistory1);
    }

    private Member handleMember(AcademicTitleHistory academicTitleHistory) {
        if (academicTitleHistory.getMember().getId() == null) {
            return memberRepository.save(academicTitleHistory.getMember());
        } else {
            Optional<Member> memberOptional = memberRepository.findById(academicTitleHistory.getMember().getId());
            return memberOptional.orElseGet(() -> memberRepository.save(academicTitleHistory.getMember()));
        }
    }

    private AcademicTitle handleAcademicTitle(AcademicTitleHistory academicTitleHistory) {
        if (academicTitleHistory.getAcademicTitle().getId() == null) {
            return academicTitleRepository.save(academicTitleHistory.getAcademicTitle());
        } else {
            Optional<AcademicTitle> academicTitleOptional = academicTitleRepository.findById(academicTitleHistory.getAcademicTitle().getId());
            return academicTitleOptional.orElseGet(() -> academicTitleRepository.save(academicTitleHistory.getAcademicTitle()));
        }
    }

    private ScientificField handleScientificField(AcademicTitleHistory academicTitleHistory) {
        if (academicTitleHistory.getScientificField().getId() == null) {
            return scientificFieldRepository.save(academicTitleHistory.getScientificField());
        } else {
            Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findById(academicTitleHistory.getScientificField().getId());
            return scientificFieldOptional.orElseGet(() -> scientificFieldRepository.save(academicTitleHistory.getScientificField()));
        }
    }


    @Override
    public List<AcademicTitleHistoryDto> getAll() {
        return academicTitleHistoryRepository
                .findAll()
                .stream().map(academicTitleHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AcademicTitleHistoryDto> getAll(Pageable pageable) {
        return academicTitleHistoryRepository
                .findAll(pageable).getContent()
                .stream().map(academicTitleHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<AcademicTitleHistory> academicTitleHistoryOptional = academicTitleHistoryRepository.findById(id);
        if (academicTitleHistoryOptional.isPresent()) {
            AcademicTitleHistory academicTitleHistory = academicTitleHistoryOptional.get();
            academicTitleHistoryRepository.delete(academicTitleHistory);
        } else {
            throw new Exception("Academic title history does not exist!");
        }

    }

    @Transactional
    @Override
    public AcademicTitleHistoryDto update(Long id, AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {
        Optional<AcademicTitleHistory> optionalAcademicTitle =academicTitleHistoryRepository.findById(id);
        if (optionalAcademicTitle.isPresent()) {
            academicTitleHistoryDto.setId(optionalAcademicTitle.get().getId());
            return academicTitleHistoryConverter.toDto(academicTitleHistoryRepository.save(academicTitleHistoryConverter.toEntity(academicTitleHistoryDto)));
        }
        throw new Exception("Academic title history with that id does not exist");
    }

    @Override
    public AcademicTitleHistoryDto findById(Long id) throws Exception {
        Optional<AcademicTitleHistory> academicTitleHistoryOptional = academicTitleHistoryRepository.findById(id);
        if (academicTitleHistoryOptional.isPresent()) {
            AcademicTitleHistory academicTitleHistory = academicTitleHistoryOptional.get();
            return academicTitleHistoryConverter.toDto(academicTitleHistory);
        } else {
            throw new Exception("Academic Title History does not exist!");
        }
    }
}
