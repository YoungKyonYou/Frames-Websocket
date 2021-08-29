package org.morgorithm.websocket.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.morgorithm.websocket.dto.RealTimeStatusDTO;
import org.morgorithm.websocket.entity.Facility;
import org.morgorithm.websocket.entity.Status;
import org.morgorithm.websocket.repository.FacilityRepository;
import org.morgorithm.websocket.repository.StatusRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.*;


@Service
@Log4j2
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final FacilityRepository facilityRepository;
    private final StatusRepository statusRepository;
    private Long latestStatusNum = 0L; // 마지막으로 읽어온 로그 넘버
    @Override
    public RealTimeStatusDTO getFacilityStatus() {
        RealTimeStatusDTO realTimeStatusDTO = new RealTimeStatusDTO();

        List<Facility> facilities = facilityRepository.findAll();
        int maxBno = facilities.stream().map(Facility::getBno).max(Long::compareTo).get().intValue();

        final int BUILDING_NUM = facilities.size();

        int[] in = new int[BUILDING_NUM];
        int[] out = new int[BUILDING_NUM];
        int[] bno = new int[maxBno+1];
        String[] bName = new String[BUILDING_NUM];

        // 초기화
        for (int i = 0; i < facilities.size(); i++) {
            Facility facility = facilities.get(i);
            in[i] = 0;
            out[i] = 0;
            bno[facility.getBno().intValue()] = i;
            bName[i] = facility.getBuilding();
        }

        int total = 0;
        LocalDateTime startDatetime = LocalDateTime.now().minusDays(1L).with(LocalTime.of(23, 59));
        LocalDateTime endDatetime = LocalDateTime.now();
        List<Object[]> result = statusRepository.getFacilityInInfoOneDay(startDatetime, endDatetime);
        List<Status> statusList = statusRepository.findRecentStatusList(latestStatusNum); // 실시간 출입현황 가져오기
        if (statusList.size() > 0)
            latestStatusNum = statusList.get(0).getStatusnum();



       // System.out.println("test getFacilityStatus result.size():" + result.size());
        //System.out.println(statusList);

        for (int i = 0; i < result.size(); i++) {
            int _bno = ((Long) result.get(i)[2]).intValue();
            int index = bno[_bno];
            if ((Boolean) result.get(i)[3]) {
                in[index] = ((Long) (result.get(i)[1])).intValue();
            } else
                out[index] = ((Long) (result.get(i)[1])).intValue();
            total += ((Long) (result.get(i)[1])).intValue();
        }
        realTimeStatusDTO = realTimeStatusDTO.builder().in(in).out(out).total(total).bName(bName).statusList(statusList).build();
        return realTimeStatusDTO;
    }

}
