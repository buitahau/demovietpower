<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="<c:url value="/logout"/>">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<!-- Core plugin JavaScript-->
<script src="<c:url value="/static/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Page level plugin JavaScript-->
<script src="<c:url value="/static/vendor/chart.js/Chart.min.js"/>"></script>
<script src="<c:url value="/static/vendor/datatables/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/static/vendor/datatables/dataTables.bootstrap4.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/static/js/sb-admin.min.js"/>"></script>

<script type="text/javascript">
    $(document).ready(function () {
        var currentURL = window.location.href;

        if (currentURL.indexOf('/admin/company/') > -1) {
            setActivePanel('/admin/company/');
        } else if (currentURL.indexOf('/admin/machine/') > -1) {
            setActivePanel('/admin/machine/');
        } else if (currentURL.indexOf('/admin/user/') > -1) {
            setActivePanel('/admin/user/');
        } else if (currentURL.indexOf('/admin/collection/') > -1) {
            setActivePanel('/admin/collection/');
        } else if (currentURL.indexOf('/admin/formula/') > -1) {
            setActivePanel('/admin/formula/');
        } else if(currentURL.indexOf('/admin/import/') > -1){
            setActivePanel('/admin/import/');
        }
    });

    function setActivePanel(targetLink) {
        $('.sidebar.navbar-nav').find(' > .nav-item').each(function () {
            if ($(this).find('.nav-link').attr("href").indexOf(targetLink) > -1) {
                $(this).addClass("active");
            }
        })
    }
</script>