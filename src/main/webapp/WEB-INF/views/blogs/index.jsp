<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actBlo" value="${ForwardConst.ACT_BLOG.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <div id="top_image">
            <% String contextPath = request.getContextPath(); %> <img src="<%=contextPath%>/images/top_image.jpg" alt="トップ画像">
        </div>

        <div id="main_content">
            <article>
            <c:import url="/WEB-INF/views/blogs/show.jsp">
            </c:import>
            </article>

            <aside>
            <div id="new_article">
                <p><a href="<c:url value='?action=${actBlo}&command=${commNew}' />">coming soon...</a></p>
            </div>
                <div id="profile">
                <h3 id="sub_title">プロフィール</h3>
                <div id="profile_img">
                    <% String contextPath2 = request.getContextPath(); %> <img src="<%=contextPath2%>/images/profile_icon.jpg" alt="プロフィール画像">
                </div>
                <p>テキストテキストテキストテキストテキストテキストテキスト
                テキストテキストテキストテキストテキストテキストテキスト
                テキストテキストテキストテキストテキストテキストテキスト</p>
                </div>

                <div id="category">
                <h3 id="sub_title">カテゴリー</h3>
                <p>coming soon...</p>
                </div>

                <div id="archive">
                <h3 id="sub_title">アーカイブ</h3>
                <p>coming soon...</p>
                </div>
            </aside>
        </div>

    </c:param>
</c:import>
