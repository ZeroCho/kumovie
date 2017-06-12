<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kr.ac.korea.db.service.ReservationService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setCharacterEncoding("utf-8");    // 한글처리용
%>
<html>
<head>
    <title>영화예매시스템</title>
</head>
<body>
<%
    ReservationService service = new ReservationService();

    try {
        service.addReservation(
                request.getParameter("userId"),
                request.getParameter("pw"),
                Integer.parseInt(request.getParameter("scheduleId"))
        );
    } catch (Exception e) {

    }


    response.sendRedirect("index.jsp");
%>
</body>
</html>