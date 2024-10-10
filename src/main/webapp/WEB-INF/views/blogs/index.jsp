<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actBlo" value="${ForwardConst.ACT_BLOG.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <div id="top_image">
            <img src=".../images/top_image.jpg">
            <img src="../../../images/top_image.jpg" alt="トップ画像">
            <% String contextPath = request.getContextPath(); %> <img src="<%=contextPath%>/images/top_image.jpg" alt="トップ画像">

        </div>
        <div>
        <%=contextPath%>
        </div>

        <div id="main_content">
            <article>
            main content
            </article>

            <aside>
                <div id="profile">
                <h3 id="sub-title">プロフィール</h3>
                </div>

                <div id="category">
                <h3 id="sub-title">カテゴリー</h3>
                </div>

                <div id="archive">
                <h3 id="sub-title">アーカイブ</h3>
                </div>
            </aside>
        </div>

    </c:param>
</c:import>
