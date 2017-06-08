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
                <button id="date-range-search-button"
                        class="btn btn-default add-button" role="button">
                    Search by Date Range
                </button>
                <a id="member-search-button"
                   class="btn btn-default add-button" role="button"
                   onclick="showMemberSelectorForm()">
                    Search by Member
                </a>
            </div>

            <!-- Begin Member Selector Form-->
            <form id="member-selector-form" class="form-horizontal" role="form"
                  hidden>
                <div class="form-group">
                    <label for="select-member" class="col-sm-1 control-label">
                        Select Member:
                    </label>

                    <div class="col-sm-3">
                        <select class="form-control" placeholder="Member" 
                                id="search-member-selected" required>
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
                    <div class="col-md-1 col-sm-offset-1">
                        <button class="btn btn-default"
                           onclick="hideMemberSelectorForm()">
                            Cancel
                        </button>
                    </div>
                    <div class="col-md-1">
                        <button id="submit-member-search" type="button"
                                class="btn btn-default">
                            Search
                        </button>
                    </div>  
                </div>
            </form>
            <!-- End Member Selector Form -->

            <!-- Begin Event Search Results Div -->
            <div class="row">
                <div id="no-data-message"></div>
                
                <div id="eventResultsTable" hidden>
                    <table class="table table-responsive">
                        <tr>
                            <th width="10%">Date</th>
                            <th width="30%">Theme</th>
                            <th width="15%">Movie</th>
                            <th width="15%">Location</th>
                        </tr>
                        <tbody id="event-search-results"></tbody>
                    </table> 
                </div>
            </div>
            <!-- End Event Search Results Div -->

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/movieClub.js"></script>
    </body>
</html>
