<%--
  Created by IntelliJ IDEA.
  User: zerocho
  Date: 2017-06-09
  Time: 오후 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="kr.ac.korea.db.model.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.ac.korea.db.service.MovieService" %>
<% MovieService movieService = new MovieService(); %>
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

        <%
            // limit, offset
            List<Movie> movieList = movieService.getMovieList(20, 0);
            for (Movie movie : movieList) {
        %>
        <tr>
            <td><%=movie.getTitle()%></td>
            <td><%=movie.getGenre()%></td>
            <td><%=movie.getRuntime()%></td>
            <td><%=movie.getDirector()%></td>
            <td><%=movie.getPlaydate().toString()%></td>
        </tr>
        <%
            }

            // movie id, 제목, 감독, 장르, rating, 날짜(string), 런타임
            movieService.addMovie(
                    "제목",
                    "감독",
                    "장르",
                    15,
                    "2017-06-11",
                    120);
            // id에 해당하는 movie update
            movieService.updateMovie(
                    20,
                    "테스트",
                    "감독테스트",
                    "장르테스트",
                    19,
                    "2017-06-12",
                    180);

            // id에 해당하는 movie delete
            movieService.deleteMovie(10);
        %>
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
