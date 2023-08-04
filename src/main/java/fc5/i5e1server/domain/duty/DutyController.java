package fc5.i5e1server.domain.duty;

import fc5.i5e1server.common.APIDataResponse;
import fc5.i5e1server.domain.annual.AnnualCreateReqDTO;
import fc5.i5e1server.domain.model.Annual;
import fc5.i5e1server.domain.model.Duty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duty")
public class DutyController {

    private final DutyService dutyService;

    public DutyController(DutyService dutyService) {
        this.dutyService = dutyService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<APIDataResponse<List<DutyPageDTO>>> getMyPageAnnual(@PathVariable Long userId) {
        return APIDataResponse.of(HttpStatus.OK, "마이페이지 당직 조회 성공", dutyService.getDuty(userId));
    }
    @PostMapping
    public ResponseEntity<APIDataResponse<Duty>> createDuty(
            @RequestBody DutyCreateReqDTO dutyCreateReqDTO,
            @RequestParam("memberId") Long memberId) {

        Duty duty = dutyService.createDuty(dutyCreateReqDTO , memberId);
        return APIDataResponse.empty(HttpStatus.CREATED, "당직 신청 성공");
    }

    @PutMapping("/{dutyId}")
    public ResponseEntity<APIDataResponse<Duty>> updateDuty(@RequestBody DutyUpdateReqDTO dutyUpdateReqDTO, @PathVariable Long dutyId) {
        Duty duty = dutyService.updateDuty(dutyUpdateReqDTO, dutyId);
        return APIDataResponse.of(HttpStatus.OK, "당직 수정 성공", duty);
    }
}