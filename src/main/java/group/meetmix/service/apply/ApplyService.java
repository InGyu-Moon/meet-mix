package group.meetmix.service.apply;

import group.meetmix.data.dto.ApplyDto;

import java.util.List;

public interface ApplyService {
    public void saveApply(ApplyDto applyDto);
    public List<ApplyDto> findAllApplies(Long id);


}
