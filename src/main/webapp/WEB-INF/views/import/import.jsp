<%--
  Created by IntelliJ IDEA.
  User: HauKute
  Date: 11/27/2018
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Import</title>
    <meta charset="utf-8">
</head>
<body>
<div class="row">
    <div class="col-md-12 d-flex align-items-stretch grid-margin">
        <div class="row flex-grow">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Import</h4>
                        <form method="post" enctype="multipart/form-data" action="<c:url value="/admin/import/savefile"/>" class="">
                            <div class="form-group">
                                <label>File upload. Please upload file excel (xls, xlsx).</label>
                                <div class="input-group col-xs-12">
                                    <input type="file" name="file" class="form-control file-upload-info" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" >
                                </div>
                            </div>
                            <button id="submitBtn" type="submit" class="btn btn-success mr-2">Upload</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
       <c:if test="${success}">
            bootoast.toast({
                message: 'Upload success.',
                type: 'success',
                position: 'top-right'
            });
        </c:if>
        <c:if test="${error}">
            bootoast.toast({
                message: 'Upload error.',
                type: 'danger',
                position: 'top-right'
            });
        </c:if>

        $("#submitBtn").click(function(){
            $("#loader_container").removeClass("hide_loader").addClass("show_loader");
            $(".container-scroller").addClass("fuzzy");
        })
    });
</script>
</body>
</html>
