<%--
  Created by IntelliJ IDEA.
  User: zerocho
  Date: 2017-06-09
  Time: 오후 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="static java.lang.Integer.parseInt" %>
<%
    int currentPage = 1; // 현재 페이지를 1로
    if (request.getParameter("page") != null && request.getParameter("page") != "") { // 현재 페이지 값이 주소 쿼리스트링에 있으면
        currentPage = parseInt(request.getParameter("page")); // 그 값을 현재 페이지로
    }
    Connection conn = null; // DriverManager를 저장할 변수 선언
    try {
        String url = "jdbc:mysql://localhost:3306/movie_reservation_system?useSSL=false"; // 사용하려는 데이터베이스명을 포함한 URL 기술
        String id = "kumovie";  // 사용자 계정
        String pw = "kumovie1!";  // 사용자 계정의 패스워드

        Class.forName("com.mysql.jdbc.Driver"); // 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
        conn = DriverManager.getConnection(url, id, pw);  // DriverManager 객체로부터 Connection 객체를 얻어온다.
%>
<html>
<head>
    <title>영화예매시스템</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
</head>
<body>
<%
    Statement stmt = conn.createStatement(); // 쿼리를 입력할 수 있는 변수 선언
    ResultSet rs = stmt.executeQuery("SELECT * FROM schedule NATURAL JOIN movie NATURAL JOIN theater NATURAL JOIN movie_type ORDER BY schedule_id ASC LIMIT 4 OFFSET " + (currentPage - 1) * 4); // schedule 테이블의 모든 데이터를 4개씩 끊어서 가져오는 쿼리 실행 후 결과 저장

%>
<div class="container">
    <div>
        <div class="page-header">
            <h1>상영 목록</h1>
        </div>
        <button class="btn btn-default schedule-prev">이전</button>
        <button class="btn btn-default pull-right schedule-next">다음</button>
        <table class="table table-bordered">
            <tr>
                <th>선택</th>
                <th>스케줄 아이디</th>
                <th>영화제목</th>
                <th>감독</th>
                <th>장르</th>
                <th>날짜</th>
                <th>시간</th>
                <th>총 좌석수</th>
                <th>가격</th>
            </tr>
            <%
                while (rs.next()) {
            %>
            <tr>
                <td><input type="checkbox" data-value="<%= rs.getInt("schedule_id") %>"></td>
                <td><%= rs.getInt("schedule_id") %>
                </td>
                <td><%= rs.getString("title") %>
                </td>
                <td><%= rs.getString("director") %>
                </td>
                <td><%= rs.getString("genre") %>
                </td>
                <td><%= rs.getDate("date") %>
                </td>
                <td><%= rs.getTime("time") %>
                </td>
                <td><%= rs.getInt("seat_num") %>
                </td>
                <td><%= rs.getInt("price") %>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <button class="btn btn-success schedule-menu">스케줄 검색</button>
        <form class="hidden schedule-menu" action="searchSchedule.jsp">
            <div class="form-group">
                <label for="search-name" class="col-sm-2 control-label">영화명</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="search-name" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="search-type" class="col-sm-2 control-label">종류</label>
                <div class="col-sm-10">
                    <input type="text" name="search-type" id="search-type" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="search-date" class="col-sm-2 control-label">상영날짜</label>
                <div class="col-sm-10">
                    <input type="text" name="search-date" id="search-date" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="search-time" class="col-sm-2 control-label">상영시간</label>
                <div class="col-sm-10">
                    <input type="text" name="search-time" id="search-time" class="form-control">
                </div>
            </div>
            <button class="btn btn-inverse">검색</button>
        </form>
        <button class="btn btn-primary schedule-menu">스케줄 추가</button>
        <form class="hidden schedule-menu" method="post" action="addSchedule.jsp">
            <div class="form-group">
                <label for="add-title" class="col-sm-2 control-label">영화 ID</label>
                <div class="col-sm-10">
                    <input type="text" name="add-movieid" id="add-title" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-director" class="col-sm-2 control-label">상영관</label>
                <div class="col-sm-10">
                    <input type="text" name="add-theaterid" id="add-director" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-genre" class="col-sm-2 control-label">날짜</label>
                <div class="col-sm-10">
                    <input type="text" name="add-date" id="add-genre" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-rating" class="col-sm-2 control-label">시간</label>
                <div class="col-sm-10">
                    <input type="text" name="add-time" id="add-rating" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-playdate" class="col-sm-2 control-label">영화종류</label>
                <div class="col-sm-10">
                    <input type="text" name="add-type" id="add-playdate" class="form-control">
                </div>
            </div>
            <button class="btn btn-inverse">추가</button>
        </form>
        <button class="btn btn-warning schedule-menu">스케줄 수정</button>
        <form class="hidden schedule-menu" method="post" action="updateSchedule.jsp">
            <div class="form-group">
                <label for="update-id" class="col-sm-2 control-label">스케줄 ID</label>
                <div class="col-sm-10">
                    <input type="text" name="update-scheduleid" id="update-id" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-title" class="col-sm-2 control-label">영화 ID</label>
                <div class="col-sm-10">
                    <input type="text" name="update-movieid" id="update-title" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-director" class="col-sm-2 control-label">상영관 ID</label>
                <div class="col-sm-10">
                    <input type="text" name="update-theaterid" id="update-director" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-genre" class="col-sm-2 control-label">날짜</label>
                <div class="col-sm-10">
                    <input type="text" name="update-date" id="update-genre" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-rating" class="col-sm-2 control-label">시간</label>
                <div class="col-sm-10">
                    <input type="text" name="update-time" id="update-rating" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-playdate" class="col-sm-2 control-label">영화종류</label>
                <div class="col-sm-10">
                    <input type="text" name="update-type" id="update-playdate" class="form-control">
                </div>
            </div>
            <button class="btn btn-inverse">수정</button>
        </form>
        <button class="btn btn-danger schedule-menu">스케줄 제거</button>
        <form class="hidden schedule-menu" method="post" action="removeSchedule.jsp">
            <div class="form-group">
                <label for="remove-id" class="col-sm-2 control-label">스케줄 아이디</label>
                <div class="col-sm-10">
                    <input type="text" name="remove-scheduleid" id="remove-id" class="form-control">
                </div>
            </div>
            <button class="btn btn-inverse">제거</button>
        </form>
    </div>
    <div class="divider"></div>
    <form action="reserve.jsp" method="post" class="form-horizontal">
        <div class="page-header">
            <h1>예매하기</h1>
        </div>
        <div class="form-group">
            <label for="userId" class="col-sm-2 control-label">아이디</label>
            <div class="col-sm-10">
                <input type="text" name="userId" id="userId" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="pw" class="col-sm-2 control-label">비밀번호</label>
            <div class="col-sm-10">
                <input type="password" name="pw" id="pw" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="scheduleId" class="col-sm-2 control-label">스케줄 ID</label>
            <div class="col-sm-10">
                <input type="text" name="scheduleId" id="scheduleId" class="form-control">
            </div>
        </div>
        <div class="form-group text-center">
            <button type="submit" class="btn btn-primary">예매</button>
        </div>
    </form>
    <div class="page-header">
        <h1>예매내역</h1>
    </div>
    <%
        stmt = conn.createStatement(); // 쿼리를 입력할 수 있는 변수 재선언
        rs = stmt.executeQuery("SELECT * FROM reservation NATURAL JOIN schedule NATURAL JOIN customer NATURAL JOIN movie"); // reservation 테이블의 모든 데이터를 가져오는 쿼리 실행 후 결과 저장
    %>
    <table class="table table-bordered">
        <tr>
            <th>예매자</th>
            <th>영화제목</th>
            <th>날짜</th>
            <th>시간</th>
            <th>좌석번호</th>
        </tr>
        <%
            while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("name")%>
            </td>
            <td><%= rs.getString("title")%>
            </td>
            <td><%= rs.getDate("date")%>
            </td>
            <td><%= rs.getTime("time")%>
            </td>
            <td><%= rs.getInt("reservation_order")%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<script
        src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script>
    $('button.schedule-menu').on('click', function () {
        var index = $('button.schedule-menu').index(this);
        console.log($('button.schedule-menu'), $(this), index);
        $('form.schedule-menu').addClass('hidden');
        $('form.schedule-menu').eq(index).toggleClass('hidden');
    });

    var currentPage = parseInt("<%= currentPage %>", 10) || 1;
    $('.schedule-prev').on('click', function() { // 스케줄 테이블 이전 4개 보기
        if (currentPage === 1) { // 이미 첫 페이지면
            return false;
        }
        location.href="/index.jsp?page=" + (currentPage - 1);
    });
    $('.schedule-next').on('click', function() { // 스케줄 테이블 다음 4개 보기
        location.href="/index.jsp?page=" + (currentPage + 1);
    });
</script>
</body>
</html>
<%
    } catch (Exception e) { // 예외가 발생하면 예외 상황을 처리한다.
        e.printStackTrace(); // 에러 출력
    }
%>
