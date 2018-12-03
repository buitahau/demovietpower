<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 11/27/2018
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<body>
<h1>Single File Upload</h1>
<form method="post" enctype="multipart/form-data" action="/import/savefile.html">
    Upload File: <input type="file" name="file">
    <br /><br />
    Description: <input type="text" name="desc"/>
    <br/><br/><input type="submit" value="Upload">
</form>
</body>
</html>
