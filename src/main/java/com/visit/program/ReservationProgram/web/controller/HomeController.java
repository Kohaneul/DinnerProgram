package com.visit.program.ReservationProgram.web.controller;

import com.visit.program.ReservationProgram.domain.dao.Reservation;
import com.visit.program.ReservationProgram.domain.dao.session.SessionConst;
import com.visit.program.ReservationProgram.domain.dto.ReservationDTO;
import com.visit.program.ReservationProgram.domain.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * 석식, 방문예약 별 Controller 클래스
 * Repository -> Service -> *Controller
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final ReservationService reservationService;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "해당 페이지에 접근할 수 없습니다. 다시 접속해주세요";
    }


    @ModelAttribute(name = "renewDate")
    public String renewDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss"));
    }

    @GetMapping("/reservation/info/all/rapigen_employee")
    public String viewEmployees(HttpSession session) {
        session.removeAttribute(SessionConst.ADMIN_ID);
        session.removeAttribute(SessionConst.DINNER_PROGRAM);
        String uri = "redirect:/reservation/info/save";
        AccessIdNull(session, UUID.randomUUID().toString());
        return uri;
    }

    private void AccessIdNull(HttpSession session, String uuid) {
        if (session.getAttribute(SessionConst.ACCESS_ID) == null) {
            session.setAttribute(SessionConst.ACCESS_ID, uuid);
        }
    }


    @GetMapping("/reservation/info/all/rapigen_security")
    public String viewSecurity(@ModelAttribute("reservationDTO") ReservationDTO reservationDTO, HttpSession session, Model model) {
        String url = "redirect:/reservation/info/all";
        session.removeAttribute(SessionConst.EMPLOYEE_ID);

        List<Reservation> reservations = reservationService.findAllDTO(reservationDTO);
        AccessIdNull(session, UUID.randomUUID().toString() + "security");
        model.addAttribute("reservations", reservations);
        return url;
    }

    @GetMapping("/reservation")
    public String redirectReservation() {
        return "redirect:/reservation/info/all";
    }


    @GetMapping("/reservation/info/all")
    public String viewAll(Model model, @ModelAttribute("reservationDTO") ReservationDTO reservationDTO, HttpSession session) {
        List<Reservation> reservations = null;
        String url = "visit/All_Emp";
        session.removeAttribute(SessionConst.LOGIN_SUCCESS);
        if (session.getAttribute(SessionConst.ACCESS_ID).toString().contains("security")) {
            url = "visit/All_Sec";
            reservations = reservationService.findAllDTO2(reservationDTO);
        } else {
            reservations = reservationService.findAllDTO(reservationDTO);
        }
        model.addAttribute("reservations", reservations);
        return url;
    }


}
