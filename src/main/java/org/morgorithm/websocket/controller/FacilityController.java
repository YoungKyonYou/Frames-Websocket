package org.morgorithm.websocket.controller;


import org.morgorithm.websocket.dto.EventDTO;
import org.morgorithm.websocket.dto.RealTimeStatusDTO;
import org.morgorithm.websocket.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

//Controller의 메소드는 한 Client에게서 message를 수신한 다음 다른 Client에게 Broadcast합니다.
@Controller
public class FacilityController {
    @Autowired
    StatusService statusService;
    @Autowired
    SimpMessagingTemplate template;

    // "/app"로 시작하는 대상이 있는 클라이언트에서 보낸 모든 메시지는
    // @MessageMapping 어노테이션이 달린 메서드로 라우팅 됩니다.
    // 예를 들어 "/app/chat.sendMessage"인 메시지는 sendMessage()로 라우팅 되며
    // "/app/chat.addUser"인 메시지는 addUser()로 라우팅된다.
    @MessageMapping("/status.sendMessage")
    @SendTo("/topic/public")
    @Scheduled(fixedDelay=3000)
    public void sendMessage() {
        // System.out.println("sendMessage method 11111");
        RealTimeStatusDTO dto = statusService.getFacilityStatus();
//        System.out.println("FacilityController");
        template.convertAndSend("/topic/public", dto);
       // System.out.println("sendMessage method! 22222");
    }

    /*@MessageMapping("/status.sendMessage2")
    @SendTo("/topic/public2")
    public EventDTO sendMessage2(@Payload EventDTO eventDTO) {
        EventDTO dto = statusService.getEventInfo();
        return dto;
    }*/
}
