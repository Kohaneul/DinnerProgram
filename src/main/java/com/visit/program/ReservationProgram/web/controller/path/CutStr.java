package com.visit.program.ReservationProgram.web.controller.path;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Slf4j
public abstract class CutStr {

    public static void ex(String message, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ex1(message,request,response);
    }



    private static void ex1(String message, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referURL = request.getHeader("REFERER");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
            int i = referURL.lastIndexOf("/")+1;
            referURL ="/dinner/info/"+referURL.substring(i);
        out.println("<script>alert('"+message+"'); location.href='"+referURL+"';</script>");
        out.flush();
    }


}
