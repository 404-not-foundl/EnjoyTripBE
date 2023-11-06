package com.ssafy.enjoytrip.map.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.map.Entity.AttractionInfo;
import com.ssafy.enjoytrip.map.dto.request.CheckAllRequestDto;
import com.ssafy.enjoytrip.map.dto.request.CheckNameRequestDto;
import com.ssafy.enjoytrip.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @GetMapping("/all")
    @ResponseBody
    public ApiResponseDto<List<AttractionInfo>> checkAll(@RequestBody CheckAllRequestDto requestDto){
        return ResponseUtil.ok(mapService.checkAll(requestDto));
    }

    @GetMapping("/name")
    @ResponseBody
    public ApiResponseDto<List<AttractionInfo>> checkName(@RequestBody CheckNameRequestDto requestDto){
        return ResponseUtil.ok(mapService.checkName(requestDto));
    }
}
