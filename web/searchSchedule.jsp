<%@ page import="kr.ac.korea.db.service.ScheduleService" %>
<%@ page import="kr.ac.korea.db.model.Schedule" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>영화예매시스템</title>
</head>
<body>
<%
    ScheduleService service = new ScheduleService();

    List<Schedule> searchResult = service.searchSchedule(
            request.getParameter("search-name"),
            request.getParameter("search-type"),
            request.getParameter("search-date"),
            request.getParameter("search-time")
    );
    out.println(request.getParameter("search-name"));

%>
<table class="table table-bordered">
    <tr>
        <th>스케줄 아이디</th>
        <th>영화제목</th>
        <th>감독</th>
        <th>장르</th>
        <th>날짜</th>
        <th>시간</th>
        <th>상영관</th>
        <th>영화종류</th>
        <th>가격</th>
    </tr>
    <%
        for (Schedule s : searchResult) {
    %>
    <tr>
        <td><%= s.getScheduleId() %>
        </td>
        <td><%= s.getMovie().getMovieId() %>
        </td>
        <td><%= s.getMovie().getDirector() %>
        </td>
        <td><%= s.getMovie().getGenre() %>
        </td>
        <td><%= s.getDate().toString() %>
        </td>
        <td><%= s.getTime().toString() %>
        </td>
        <td><%= s.getTheater().getSeatNum() %>
        </td>
        <td><%= s.getMovieType().getType() %>
        </td>
        <td><%= s.getMovieType().getPrice() %>
        </td>
    </tr>
    <%
        }
    %>
</table>

<script
        src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>