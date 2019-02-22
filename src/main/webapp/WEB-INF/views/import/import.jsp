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

<c:if test="${hasErrors}">
    <div class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        Have some error in import file. Please revise them before update.
    </div>

    <c:if test="${not empty colorantErrors}">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    Colourant Errors  (Sheet 1)
                </div>

                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                    <%--<th>#</th>--%>
                                <th style="width: 50px;">Row</th>
                                <th>Errors</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${colorantErrors}" var="colourant">
                                <tr>
                                        <%--<td>${colourant.row}</td>--%>
                                    <td>${colourant.row}</td>
                                    <td>${colourant.errors}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:if>

    <c:if test="${not empty productBaseErrors}">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <i class="fas fa-chart-area"></i>
                        Product Base Errors (Sheet 2)
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                        <%--<th>#</th>--%>
                                    <th style="width: 50px;">Row</th>
                                    <th>Errors</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${productBaseErrors}" var="pb">
                                    <tr>
                                            <%--<td>${pb.row}</td>--%>
                                        <td>${pb.row}</td>
                                        <td>${pb.errors}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>


    <c:if test="${not empty productBaseCanErrors}">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <i class="fas fa-chart-area"></i>
                        Product Base Can Errors (Sheet 3)
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                        <%--<th>#</th>--%>
                                    <th style="width: 50px;">Row</th>
                                    <th>Errors</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${productBaseCanErrors}" var="pbc">
                                    <tr>
                                            <%--<td>${pb.row}</td>--%>
                                        <td>${pbc.row}</td>
                                        <td>${pbc.errors}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty formulaErrors}">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <i class="fas fa-chart-area"></i>
                        Formula Errors (Sheet 4)
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                        <%--<th>#</th>--%>
                                    <th style="width: 50px;">Row</th>
                                    <th>Errors</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${formulaErrors}" var="formula">
                                    <tr>
                                            <%--<td>${pb.row}</td>--%>
                                        <td>${formula.row}</td>
                                        <td>${formula.errors}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:if>

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
