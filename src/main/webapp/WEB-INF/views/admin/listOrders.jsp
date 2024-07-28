<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="admin_header.jsp" />

<div class="container">
	<h1>List Orders</h1>

	<table>
		<tr>
			<th>S.N.</th>
			<th>Order Date</th>
			<th>Shipping Information</th>
			<th>Order Total (Rs.)</th>
			<th>Order Status</th>
		</tr>
		<c:forEach var="order" items="${orders}" varStatus="status">
			<tr>
				<td class="serial-number">${status.index + 1}</td>
				<td>${order.orderDate}</td>
				<td>${order.shippingInformation}</td>
				<td>${order.getAmount()}</td>
				<td>${order.orderStatus}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<jsp:include page="admin_footer.jsp" />
