<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/headerLogged.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">
        <div class="dashboard-menu">
            <div class="menu-item border-dashed">
                <a href='<c:url value="/app/recipe/add"/>'>
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href='<c:url value="/app/plan/add"/>'>
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj plan</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href='<c:url value="/app/recipe/plan/add"/>'>
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis do planu</span>
                </a>
            </div>
        </div>

        <div class="dashboard-alerts">
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">Liczba przepisów: ${recipes}</span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">Liczba planów: ${plans}</span>
            </div>
        </div>
    </div>
    <div class="m-4 p-4 border-dashed">
        <h2 class="dashboard-content-title">
            <span>Ostatnio dodany plan:</span> ${lastNamePlan}
        </h2>
        <c:forEach items="${lastPlan}" var="item">
            <table class="table">
                <thead>
                <tr class="d-flex">
                    <th class="col-2">${item.getKey()}</th>
                    <th class="col-8"></th>
                    <th class="col-2"></th>
                </tr>
                </thead>
                <c:forEach items="${item.value}" var="value">
                    <tbody>
                    <tr class="d-flex">
                        <td class="col-2">${value.mealName}</td>
                        <td class="col-8">${value.recipeName}</td>
                        <td class="col-2">
                            <form method="post">
                                <input hidden name="recipeId" value="${value.recipeId}">
                                <button type="submit" class="btn btn-primary rounded-0">Szczegóły</button>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
        </c:forEach>
    </div>
</div>
</div>
</section>

<%@ include file="/appFooter.jsp" %>
