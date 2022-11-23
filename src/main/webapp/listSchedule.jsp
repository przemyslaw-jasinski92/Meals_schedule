<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/headerLogged.jsp" %>

<div class="m-4 p-3 width-medium">
    <c:if test="${not empty message}">
        <div class="dashboard-alerts alert-light"><h2>${message}</h2></div>
    </c:if>
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">LISTA PLANÓW</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href='<c:url value="/app/plan/add"/>' class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj
                    plan</a>
            </div>
        </div>

        <div class="schedules-content">
            <table class="table border-bottom">
                <thead>
                <tr class="d-flex">
                    <th class="col-1">ID</th>
                    <th class="col-2">NAZWA</th>
                    <th class="col-7">OPIS</th>
                    <th class="col-2 center">AKCJE</th>
                </tr>
                </thead>
                <tbody class="text-color-lighter">
                <c:forEach items="${plans}" var="plan">
                    <tr class="d-flex">
                        <td class="col-1">${plan.id}</td>
                        <td class="col-2">${plan.name}</td>
                        <td class="col-7">${plan.description}</td>
                        <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                            <a href='<c:url value="/app/plan/delete?id=${plan.id}"/>'
                               class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            <a href='<c:url value="/app/plan/details?id=${plan.id}"/>'
                               class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            <a href='<c:url value="/app/plan/edit?id=${plan.id}"/>'
                               class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
</section>

<%@ include file="/appFooter.jsp" %>
