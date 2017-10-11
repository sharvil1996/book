<div class="footer">
	<div class="row footerrow" style="text-align: center;">
		<div class="cmd4" style="display: inline-block; padding: 0;">
			<a href="index.jsp" class="footerlogo"> <img
				src="res/imgs/berry.svg">
				<div class="texts">
					<span class="darktext">book</span><br> <span class="lighttext">berries</span>
				</div>
			</a>
		</div>
		<div class="cmd8"
			style="font-size: 18px; text-align: center; color: white; overflow: hidden; display: inline-block;">
			<span style="font-size: 28px;">Contact Us</span><br> <span>Mobile
				: +91-91730 74933</span>&nbsp;&nbsp;&nbsp;&nbsp; <span>Email :
				support@bookberries.co.in</span>
		</div>
	</div>
</div>
<%@page import="com.dao.ProductDAO"%>
<span id="bookIdProvider" style="display: none;"><%=new ProductDAO().listOfProductId()%></span>
<span id="bookNameProvider" style="display: none;"><%=new ProductDAO().listOfProductName()%></span>
<!-- <script src="res/js/jq.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<!-- <script src="res/js/angular.js"></script> --> 
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script src="res/js/code.js"></script>
<script src="res/js/hash.js"></script>
<script src="res/js/search.js"></script>