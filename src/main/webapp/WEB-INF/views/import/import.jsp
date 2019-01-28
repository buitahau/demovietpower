<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Import</title>
    <meta charset="utf-8">
</head>
<body>

<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/"/>">Home</a>
    </li>

    <li class="breadcrumb-item active">Import Metadata</li>
</ol>

<div class="row">
    <div class="col-12">
        <form method="post" enctype="multipart/form-data" action="<c:url value="/admin/import/savefile"/>" class="">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    Import
                </div>

                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="form-group">
                                <label>File upload. Please upload file excel (xls, xlsx).</label>
                                <input type="file" name="file" class="form-control file-upload-info" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                            </div>
                        </div>
                    </div>

                    <button id="submitBtn" type="submit" class="btn btn-success mr-2">Upload</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
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

        $("#submitBtn").click(function () {
            $("#loader_container").removeClass("hide_loader").addClass("show_loader");
            $(".container-scroller").addClass("fuzzy");
        });
    });
</script>
</body>
</html>
