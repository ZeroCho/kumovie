<%@ page import="kr.ac.korea.db.service.ScheduleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>영화예매시스템</title>
</head>
<body>
<%
    ScheduleService service = new ScheduleService();

    try {
        service.addSchedule(
                Integer.parseInt(request.getParameter("add-movieid")),
                Integer.parseInt(request.getParameter("add-theaterid")),
                request.getParameter("add-date"),
                request.getParameter("add-time"),
                request.getParameter("add-type")
        );
    } catch (Exception e) {

    }

    response.sendRedirect("index.jsp");
%>
</body>
</html>