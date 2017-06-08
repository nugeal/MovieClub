<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
    <head>
        <title>Film Society</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/movieClubStyle.css" rel="stylesheet">
        <style>
            body {
                height: 100%;
                background-color: #e6edea;
                background-position: center;
                background-attachment: fixed;
                background-repeat: no-repeat;
                background-size: cover;
            }
            #main {
                padding-left: 30px;
                padding-right: 30px;
            }
        </style>
    </head>
    <body>
        <div class="navbar-wrapper">
            <div class="container">
                <nav class="navbar navbar-inverse navbar-static-top">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed"
                                    data-toggle="collapse" data-target="#navbar"
                                    aria-expanded="false" aria-controls="navbar">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Film Society</a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse" aria-expanded="false"
                             style="height:1px;">
                            <ul class="nav navbar-nav">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/displayMembersPage">Members</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/displayEventsPage">Events</a>
                                </li>
                            </ul>
                        </div>
                    </div>

                </nav>
            </div>
        </div>
        <div id= "main" class="container"> 
            <div class="row">
                <h2>Schedule Event</h2>
            </div>

            <!-- Begin Add Member Div -->
            <div class="col-md-6">
                <div id="add-event-form">
                    <form class="form-horizontal" role="form" 
                          modelAttribute="event" action="addEvent" 
                          method="POST">
                        <div class="form-group">
                            <label for="add-event-date" class="col-md-3 control-label">
                                Date:
                            </label>

                            <div class="col-md-6">
                                <input type="date" class="form-control" 
                                       path="event_date"
                                       name="event_date"
                                       placeholder="Date" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-member" class="col-md-3 control-label">
                                Host:
                            </label>

                            <div class="col-md-6">
                                <select class="form-control" placeholder="Member" name="member" required>
                                    <option value="">Select Member</option>
                                        <c:forEach var="currentMember" items="${memberList}">
                                            <option>
                                                <c:out value="${currentMember.member_id} - ${currentMember.first_name} ${currentMember.last_name}"/>
                                            </option>
                                        </c:forEach>
                                </select> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-event-theme" class="col-md-3 control-label">
                                Theme:
                            </label>

                            <div class="col-md-6">
                                <input type="text" class="form-control" 
                                       name="theme"
                                       placeholder="Theme"
                                       maxlength="40"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-movie" class="col-md-3 control-label">
                                Movie:
                            </label>

                            <div class="col-md-6">
                                <input type="text" class="form-control" 
                                       name="movie_name"
                                       placeholder="Movie"
                                       maxlength="40"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location" class="col-md-3 control-label">
                                Location:
                            </label>

                            <div class="col-md-6">
                                <input type="text" class="form-control" 
                                       name="location"
                                       placeholder="Location"
                                       maxlength="70"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-2 col-md-offset-3">
                                <a class="btn btn-default" type="button"
                                   href="${pageContext.request.contextPath}/displayEventsPage">
                                    Cancel
                                </a>
                            </div>
                            <div class="col-md-1">
                                <button type="submit"
                                        class="btn btn-default">
                                    Create Event
                                </button>
                            </div>  
                        </div>
                    </form>
                </div>
            </div>
            <!-- End Add Member Div -->

        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/movieClub.js"></script>
</body>
</html>
