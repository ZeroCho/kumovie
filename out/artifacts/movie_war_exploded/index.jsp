<%--
  Created by IntelliJ IDEA.
  User: zerocho
  Date: 2017-06-09
  Time: 오후 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%
    Connection conn = null;
    try {
        String url = "jdbc:mysql://localhost:3306/jdbcTest";        // 사용하려는 데이터베이스명을 포함한 URL 기술
        String id = "testid";                                                    // 사용자 계정
        String pw = "testpw";                                                // 사용자 계정의 패스워드

        Class.forName("com.mysql.jdbc.Driver");                       // 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
        conn = DriverManager.getConnection(url, id, pw);              // DriverManager 객체로부터 Connection 객체를 얻어온다.
        out.println("제대로 연결되었습니다.");                            // 커넥션이 제대로 연결되면 수행된다.

    } catch (Exception e) {                                                    // 예외가 발생하면 예외 상황을 처리한다.
        e.printStackTrace();
    }
%>
<html>
<head>
    <title>영화예매시스템</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <table class="table table-bordered">
        <tr>
            <th>영화제목</th>
            <th>종류</th>
            <th>상영시간</th>
            <th>남은 좌석수</th>
            <th>가격</th>
        </tr>
    </table>
    <form action="reserve.jsp" method="post" class="form-horizontal">
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
            <label for="title" class="col-sm-2 control-label">영화제목</label>
            <div class="col-sm-10">
                <input type="text" name="title" id="title" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="time" class="col-sm-2 control-label">상영시간</label>
            <div class="col-sm-10">
                <input type="text" name="time" id="time" class="form-control">
            </div>
        </div>
        <div class="form-group text-center">
            <button type="submit" class="btn btn-primary">예매</button>
        </div>
    </form>
    <table class="table table-bordered">
        <tr>
            <th>예매자</th>
            <th>고객등급</th>
            <th>영화제목</th>
            <th>상영시간</th>
            <th>좌석번호</th>
        </tr>
    </table>
</div>
<script
        src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>
