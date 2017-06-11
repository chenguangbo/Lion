<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<div style="width:100%;margin:0 auto;margin-top:50px; " align="center">
						第${pageBean.pageNumber}/${pageBean.totalPage}页&nbsp;&nbsp;
						总记录数:${pageBean.totalRecord} &nbsp;每页显示:${pageBean.pageSize}&nbsp;&nbsp;
						<c:if test="${pageBean.pageNumber > 1}">
						   <a href="${pageContext.request.contextPath}/${pageBean.url}&pageNumber=1">首页</a> | &nbsp;
						   <a href="${pageContext.request.contextPath}/${pageBean.url}&pageNumber=${pageBean.pageNumber-1}">上一页</a> | &nbsp;
						</c:if>
						&nbsp;
						<c:forEach var="i" begin="1" end="${pageBean.totalPage}">
							<c:if test="${pageBean.pageNumber==i}">${i}</c:if>
							<c:if test="${pageBean.pageNumber!=i}">
								<a href="${pageContext.request.contextPath}/${pageBean.url}&pageNumber=${i}">${i}</a>
							</c:if>
						</c:forEach>
						&nbsp;
						<c:if test="${pageBean.pageNumber lt pageBean.totalPage }">
						   <a href="${pageContext.request.contextPath}/${pageBean.url}&pageNumber=${pageBean.pageNumber+1}">下一页</a> | &nbsp;
						   <a href="${pageContext.request.contextPath}/${pageBean.url}&pageNumber=${pageBean.totalPage}">尾页</a> | &nbsp;
						</c:if>
</div>