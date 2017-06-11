<%@ page import="kr.ac.korea.db.service.ScheduleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>영화예매시스템</title>
</head>
<body>
<%
    ScheduleService service = new ScheduleService();

    service.deleteSchedule(
            Integer.parseInt(request.getParameter("remove-scheduleid"))
    );

    response.sendRedirect("index.jsp");
%>
</body>
</html>