package com.ssafy.enjoytrip.map.service;

import com.ssafy.enjoytrip.map.Entity.AttractionInfo;
import com.ssafy.enjoytrip.map.dto.request.CheckAllRequestDto;
import com.ssafy.enjoytrip.map.dto.request.CheckNameRequestDto;
import com.ssafy.enjoytrip.map.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    @Transactional
    public List<AttractionInfo> checkAll(CheckAllRequestDto requestDto){
        return mapRepository.findAllBySidoCodeAndContentTypeId(requestDto.getRegion(), requestDto.getType()).orElseGet(ArrayList::new);
    }

    @Transactional
    public List<AttractionInfo> checkName(CheckNameRequestDto requestDto){
        return mapRepository.findAllByTitleContainsAndSidoCode(requestDto.getRegionName(), requestDto.getRegion()).orElseGet(ArrayList::new);
    }
}
