package com.ssafy.enjoytrip.map.repository;

import com.ssafy.enjoytrip.map.Entity.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MapRepository extends JpaRepository<AttractionInfo, Long> {

    Optional<List<AttractionInfo>> findAllBySidoCodeAndContentTypeId(int sidoCode, int contentTypeId);

    Optional<List<AttractionInfo>> findAllByTitleContainsAndSidoCode(String title, int sidoCode);

}
