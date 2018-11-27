<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 11/27/2018
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload One File</title>
</head>
<body>


<h3>Upload One File:</h3>

<!-- MyUploadForm -->
<form method="POST" action="/import/uploadFile.html" enctype="multipart/form-data">
    File to upload: <input type="file" name="file"><br />
    Name: <input type="text" name="name"><br /> <br />
    <input type="submit" value="Upload"> Press here to upload the file!
</form>

</body>

</html>