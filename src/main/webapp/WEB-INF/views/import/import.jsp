<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 11/27/2018
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>

<html>

<body>
<h1>Spring MVC file upload example</h1>

<form method="POST" action="/import/upload.html" enctype="multipart/form-data">
    <input type="file" name="file" /><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>
