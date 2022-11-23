<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/headerLogged.jsp" %>

<div class="m-4 p-3 width-medium ">
    <c:if test="${not empty message}">
        <div class="dashboard-alerts alert-light"><h2>${message}</h2></div>
    </c:if>
    <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href='<c:url value="/app/plan/list"/>'
                   class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <div class="schedules-content-header">
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">${planDetailsInfo.name}</p>
                    </div>
                </div>
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">${planDetailsInfo.description}</p>
                    </div>
                </div>
            </div>

            <c:forEach items="${planDetails}" var="item">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">${item.getKey()}</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <c:forEach items="${item.value}" var="value">
                        <tbody class="text-color-lighter">
                        <tr class="d-flex">
                            <td class="col-2">${value.mealName}</td>
                            <td class="col-7">${value.recipeName}</td>
                            <td class="col-1 center">
                                <a href='<c:url value="/app/plan/recipe/delete?id=${value.id}"/>' class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href='<c:url value="/app/recipe/details?id=${value.recipeId}"/>' class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </c:forEach>
        </div>
    </div>
</div>
</div>
</section>

<%@ include file="/appFooter.jsp" %>

