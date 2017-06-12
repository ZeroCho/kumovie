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
        service.updateSchedule(
                Integer.parseInt(request.getParameter("update-scheduleid")),
                Integer.parseInt(request.getParameter("update-movieid")),
                Integer.parseInt(request.getParameter("update-theaterid")),
                request.getParameter("update-date"),
                request.getParameter("update-time"),
                request.getParameter("update-type")
        );
    } catch (Exception e) {

    }


    response.sendRedirect("index.jsp");
%>
</body>
</html>