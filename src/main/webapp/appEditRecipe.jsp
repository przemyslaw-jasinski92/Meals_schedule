<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/headerLogged.jsp" %>


<div class="m-4 p-3 width-medium text-color-darker">
    <%--    <div class="dashboard-alerts alert-light">Coś poszło nie tak</div>--%>
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">
            <!-- fix action, method -->
            <!-- add name attribute for all inputs -->
            <form method="post">
                <div class="row border-bottom border-3">
                    <div class="col"><h3 class="color-header text-uppercase">Edycja przepisu</h3></div>
                    <div class="col d-flex justify-content-end mb-2">
                        <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                    </div>
                </div>

                <table class="table borderless">
                    <tbody>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Nazwa Przepisu</th>
                        <td class="col-7">
                            <input type="hidden" name="id" value="${editRecipe.id}">
                            <input class="w-100 p-1" name="name" value="${editRecipe.name}" required>
                        </td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Opis przepisu</th>
                        <td class="col-7"><textarea name="description" class="w-100 p-1" rows="5"
                                                    required>${editRecipe.description}</textarea>
                        </td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                        <td class="col-3">
                            <input class="p-1" type="number" name="preparation_time"
                                   value="${editRecipe.preparationTime}" min="1" required>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div class="row d-flex">
                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                    <div class="col-2"></div>
                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                </div>
                <div class="row d-flex">
                    <div class="col-5 p-4">
                        <textarea class="w-100 p-1" name="preparation" rows="10"
                                  required>${editRecipe.preparation}</textarea>
                    </div>
                    <div class="col-2"></div>

                    <div class="col-5 p-4">
                        <textarea name="ingredients" class="w-100 p-1" rows="10">${editRecipe.ingredients}</textarea>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
</div>
</section>

<%@ include file="/appFooter.jsp" %>

