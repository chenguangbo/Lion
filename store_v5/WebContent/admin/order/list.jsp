<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript">

		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="5%">
										序号
									</td>
									<td align="center" width="25%">
										订单编号
									</td>
									<td align="center" width="5%">
										订单金额
									</td>
									<td align="center" width="5%">
										收货人
									</td>
									<td align="center" width="5%">
										订单状态
									</td>
									<td align="center" width="55%">
										订单详情
									</td>
								</tr>
							<c:forEach items="${orders}" var="o" varStatus="status">	
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${status.count}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="25%">
												${o.oid}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${o.total}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${o.name}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
													<!-- 1=未付款、2=发货、3=已发货、4=订单完成 -->
                                                 <c:if test="${o.state==1}">未付款</c:if>  													
                                                 <c:if test="${o.state==2}">
                                                 	<a href="${pageContext.request.contextPath}/AdminOrderServlet?method=sendGood&oid=${o.oid}">发货</a>
                                                 </c:if>  													
                                                 <c:if test="${o.state==3}">已发货</c:if>  													
                                                 <c:if test="${o.state==4}">订单完成</c:if>  													
											</td>
											<td align="center" style="HEIGHT: 22px">
												<input type="button" value="订单详情" id="${o.oid}" class="myBtn"/>
												<table border="1" width="100%">
												  <!-- 
													<tr><th>商品</th><th>名称</th><th>数量</th><th>小计</th></tr>
													<tr><td><img src="/store_v5/products/1/c_0001.jpg" width="50px"/></td><td>AA</td><td>1</td><td>222</td></tr>
													<tr><td><img src="/store_v5/products/1/c_0001.jpg" width="50px"/></td><td>AA</td><td>1</td><td>222</td></tr>
													<tr><td><img src="/store_v5/products/1/c_0001.jpg" width="50px"/></td><td>AA</td><td>1</td><td>222</td></tr>
												  -->
												</table>
											</td>
							
										</tr>
								 </c:forEach>
							</table>
						</td>
					</tr>
					<tr align="center">
						<td colspan="7">
							
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
<script>
$(function(){
	$(".myBtn").click(function(){
		//alert("DDDD");
		//获取当前订单id
		//alert(this.id);
		//alert($(this).attr("id"));
		//alert($(this).prop("id"));
		//如果我们获取到的某个对象是通过JQUERY的形式,那么加一个前缀$,以后使用这个对象的时候,知道这是一个jquery,调用的就是jquery API
		var $btn=$(this);
		
		//获取按钮文字
		///alert($btn.val());
		if("订单详情"==$btn.val()){
			$.post("/store_v5/AdminOrderServlet",{"method":"findOrderByOid","oid":this.id},function(dt){
				
				$btn.next("table").html("");
				$btn.next("table").append("<tr><th>商品</th><th>名称</th><th>数量</th><th>小计</th></tr>");
				
				$.each(dt,function(i,obj){
					//alert(i+obj);
					//火狐的控制台打印信息
					//console.log(i+"<====>"+obj);
					//alert($(this).next().attr("border"));//错误  时刻注意:this,谁调用我,谁就是this
					//测试是否可以通过next()获取到按钮的下一个元素
					//alert($btn.next("table").attr("border"));
					var tr="<tr><td><img src='/store_v5/"+obj.product.pimage+"' width='50px'/></td><td>"+obj.product.pname+"</td><td>"+obj.quantity+"</td><td>"+obj.total+"</td></tr>";
					$btn.next().append(tr);				
				})
				$btn.prop("value","关闭");
				
			},"json");
			
		}else{
			
			$btn.next("table").html("");
			$btn.val("订单详情");
		}
		
		

		
		
	});
});
</script>	
</HTML>






