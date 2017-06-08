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
            <div class="row page-header">
                <h1>Events</h1>
                <a href="${pageContext.request.contextPath}/addEventForm"
                   class="btn btn-default add-button" id="add-event-button"
                   type="button">
                    Schedule Event
                </a>
                <a href="${pageContext.request.contextPath}/searchEventsPage"
                   class="btn btn-default add-button" id="add-event-button"
                   type="button">
                    Search Events
                </a>
            </div>

            <!-- Begin Event Search Results Div -->
            <div class="row">
                <div id="resultsTable">
                    <table class="table table-responsive">
                        <tr>
                            <th width="10%">Date</th>
                            <th width="20%">Host</th>
                            <th width="30%">Theme</th>
                            <th width="15%">Movie</th>
                            <th width="15%">Location</th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="currentEvent" items="${upcomingEventList}">
                            <tr>
                                <td>
                                    <c:out value="${currentEvent.event_date}"/>
                                </td>
                                <td>
                                    <c:out value="${currentEvent.member.first_name} ${currentEvent.member.last_name}"/>
                                </td>
                                <td>
                                    <c:out value="${currentEvent.theme}"/>
                                </td>
                                <td>
                                    <c:out value="${currentEvent.movie_name}"/>
                                </td>
                                <td>
                                    <c:out value="${currentEvent.location}"/>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/updateEventForm?event_id=${currentEvent.event_id}">
                                        Update
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table> 
                </div>
            </div>
            <!-- End Begin Event Search Results Div -->

            <div class="row">
                <div id="pastEventsTable">
                    <table class="table table-responsive">
                        <caption class="text-center">
                            Past Events
                        </caption>
                        <tr>
                            <th width="10%">Date</th>
                            <th width="20%">Host</th>
                            <th width="30%">Theme</th>
                            <th width="15%">Movie</th>
                            <th width="15%">Location</th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="currentEvent" items="${pastEventList}">
                            <tr>
                                <td>
                                    <c:out value="${currentEvent.event_date}"/>
                                </td>
                                <td>
                                    <c:out value="${currentEvent.member.first_name} ${currentEvent.member.last_name}"/>
                                </td>
                                <td>
                                    <c:out value="${currentEvent.theme}"/>
                                </td>
                                <td>
                                    <c:out value="${currentEvent.movie_name}"/>
                                </td>
                                <td>
                                    <c:out value="${currentEvent.location}"/>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/updateEventForm?event_id=${currentEvent.event_id}">
                                        Update
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table> 
                </div>

            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/movieClub.js"></script>
    </body>
</html>
