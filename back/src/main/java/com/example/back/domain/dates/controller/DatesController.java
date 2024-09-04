package com.example.back.domain.dates.controller;

import static com.example.back.util.response.SuccessResponseEntity.getResponseEntity;
import com.example.back.domain.dates.service.DatesService;
import com.example.back.util.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DatesController {
    private final DatesService datesService;

    // 해당하는 날짜의 data가 존재하면 그냥 가져오고, 없으면 만들어서 가져온다.
    // 추후 Dates에 member의 id도 추가하여 memberId도 and 연산으로 진행 해야한다.

    // 즉 우선 날짜로 해당 Dates 테이블의 PK를 찾아온다
    public ResponseEntity<?> getDates() {
        return getResponseEntity(SuccessCode.OK);
    }




}
