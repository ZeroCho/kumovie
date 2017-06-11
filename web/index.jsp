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
    Connection conn = null; // DriverManager를 저장할 변수 선언
    try {
        String url = "jdbc:mysql://localhost:3306/movie_reservation_system?useSSL=false"; // 사용하려는 데이터베이스명을 포함한 URL 기술
        String id = "kumovie";  // 사용자 계정
        String pw = "kumovie1!";  // 사용자 계정의 패스워드

        Class.forName("com.mysql.jdbc.Driver"); // 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
        conn = DriverManager.getConnection(url, id, pw);  // DriverManager 객체로부터 Connection 객체를 얻어온다.
        out.println("제대로 연결되었습니다."); // 커넥션이 제대로 연결되면 수행된다.
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
    ResultSet rs = stmt.executeQuery("select * from schedule NATURAL JOIN movie NATURAL JOIN theater NATURAL JOIN movie_type"); // movie 테이블의 모든 데이터를 가져오는 쿼리 실행 후 결과 저장
%>
<div class="container">
    <div>
        <div class="page-header">
            <h1>상영 목록</h1>
        </div>
        <button class="btn btn-default">이전</button>
        <button class="btn btn-default pull-right">다음</button>
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
                    <th><input type="checkbox" data-value="<%= rs.getInt("schedule_id") %>"></th>
                    <th><%= rs.getInt("schedule_id") %></th>
                    <th><%= rs.getString("title") %></th>
                    <th><%= rs.getString("director") %></th>
                    <th><%= rs.getString("genre") %></th>
                    <th><%= rs.getDate("date") %></th>
                    <th><%= rs.getTime("time") %></th>
                    <th><%= rs.getInt("seat_num") %></th>
                    <th><%= rs.getInt("price") %></th>
                </tr>
            <%
                }
            %>
        </table>
        <button class="btn btn-success movie-menu">영화 검색</button>
        <form class="hidden movie-menu">
            <div class="form-group">
                <label for="search-name" class="col-sm-2 control-label">영화명</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="search-name" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="search-type" class="col-sm-2 control-label">종류</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="search-type" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="search-time" class="col-sm-2 control-label">상영시간</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="search-time" class="form-control">
                </div>
            </div>
            <button class="btn btn-inverse">검색</button>
        </form>
        <button class="btn btn-primary movie-menu">영화 추가</button>
        <form class="hidden movie-menu" action="addMovie.jsp">
            <div class="form-group">
                <label for="add-title" class="col-sm-2 control-label">영화명</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="add-title" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-director" class="col-sm-2 control-label">영화감독</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="add-director" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-genre" class="col-sm-2 control-label">장르</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="add-genre" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-rating" class="col-sm-2 control-label">등급</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="add-rating" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-playdate" class="col-sm-2 control-label">개봉일</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="add-playdate" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="add-runtime" class="col-sm-2 control-label">상영시간</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="add-runtime" class="form-control">
                </div>
            </div>
            <button class="btn btn-inverse">추가</button>
        </form>
        <button class="btn btn-warning movie-menu">영화 수정</button>
        <form class="hidden movie-menu" action="updateMovie.jsp">
            <div class="form-group">
                <label for="update-id" class="col-sm-2 control-label">영화 아이디</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="update-id" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-title" class="col-sm-2 control-label">영화명</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="update-title" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-director" class="col-sm-2 control-label">영화감독</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="update-director" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-genre" class="col-sm-2 control-label">장르</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="update-genre" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-rating" class="col-sm-2 control-label">등급</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="update-rating" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-playdate" class="col-sm-2 control-label">개봉일</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="update-playdate" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="update-runtime" class="col-sm-2 control-label">상영시간</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="update-runtime" class="form-control">
                </div>
            </div>
            <button class="btn btn-inverse">수정</button>
        </form>
        <button class="btn btn-danger movie-menu">영화 제거</button>
        <form class="hidden movie-menu" action="removeMovie.jsp">
            <div class="form-group">
                <label for="remove-id" class="col-sm-2 control-label">영화 아이디</label>
                <div class="col-sm-10">
                    <input type="text" name="search-name" id="remove-id" class="form-control">
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
    <div class="page-header">
        <h1>예매내역</h1>
    </div>
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
<script>
    $('button.movie-menu').on('click', function () {
        var index = $('button.movie-menu').index(this);
        console.log($('button.movie-menu'), $(this), index);
        $('form.movie-menu').addClass('hidden');
        $('form.movie-menu').eq(index).toggleClass('hidden');
    })
</script>
</body>
</html>
<%
    } catch (Exception e) { // 예외가 발생하면 예외 상황을 처리한다.
        e.printStackTrace(); // 에러 출력
    }
%>
