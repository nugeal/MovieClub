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
            .edit-delete-link {
                font-size: 10px;
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
                <h1>Members</h1>
                <a href="${pageContext.request.contextPath}/addMemberForm"
                   class="btn btn-default add-button" id="add-member-button"
                   type="button">
                    Add Member
                </a>
            </div>
            <div class="row">
                <c:forEach var="currentMember" items="${memberList}">
                    <div class="col-md-3 text-center">
                        <p class="text-center member">
                            <c:out value="${currentMember.first_name} ${currentMember.last_name}"/>
                        </p>
                        <c:if test="${currentMember.member_image != null}">
                            <p>
                                <img src="${pageContext.request.contextPath}/photos/${currentMember.member_id}"
                                     class="center-block" height="150px" width="150px">
                            </p>   
                        </c:if>
                        <p class="edit-delete-link">
                            <a href="${pageContext.request.contextPath}/editMemberForm?member_id=${currentMember.member_id}">
                                Edit
                            </a>
                        </p>
                        <p class="edit-delete-link">
                            <a href="${pageContext.request.contextPath}/deleteMember?member_id=${currentMember.member_id}">
                                Delete
                            </a>
                        </p>
                    </div>

                </c:forEach>

            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/movieClub.js"></script>
    </body>
</html>
