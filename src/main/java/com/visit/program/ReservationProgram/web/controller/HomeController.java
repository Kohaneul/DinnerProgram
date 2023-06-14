package com.visit.program.ReservationProgram.web.controller;

import com.visit.program.ReservationProgram.domain.dao.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String home(){return "해당 페이지에 접근할 수 없습니다. 다시 접속해주세요";}

    @ModelAttribute(name="renewDate")
    public String renewDate(){
        return  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss"));
    }


    private void AccessIdNull(HttpSession session,String uuid){
        if(session.getAttribute(SessionConst.ACCESS_ID)==null){
            session.setAttribute(SessionConst.ACCESS_ID, uuid);
        }
    }
















}
