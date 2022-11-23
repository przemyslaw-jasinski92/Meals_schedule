<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/headerLogged.jsp" %>

<div class="m-4 p-3 width-medium">
  <div class="dashboard-content border-dashed p-3 m-4 view-height">
    <form method="post">
    <div class="row border-bottom border-3 p-1 m-1">
      <div class="col noPadding">
        <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
      </div>
      <div class="col d-flex justify-content-end mb-2 noPadding">
        <button type="submit" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
      </div>
    </div>

    <div class="schedules-content">
        <div class="form-group row">
          <label for="choosePlan" class="col-sm-2 label-size col-form-label">
            Wybierz plan
          </label>
          <div class="col-sm-3">
            <select class="form-control" id="choosePlan" name="plan_name">
              <c:forEach items="${plans}" var="plan">
                <option>${plan.name}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="form-group row">
          <label for="name" class="col-sm-2 label-size col-form-label">
            Nazwa posiłku
          </label>
          <div class="col-sm-10">
            <input type="text" class="form-control" name="meal_name" id="name" placeholder="Nazwa posiłku" required>
          </div>
        </div>
        <div class="form-group row">
          <label for="number" class="col-sm-2 label-size col-form-label">
            Numer posiłku
          </label>
          <div class="col-sm-2">
            <input type="number" class="form-control" name="meal_number" min="1" id="number" placeholder="Numer posiłku">
          </div>
        </div>
        <div class="form-group row">
          <label for="recipie" class="col-sm-2 label-size col-form-label">
            Przepis
          </label>
          <div class="col-sm-4">
            <select name="recipes" class="form-control" id="recipie">
              <c:forEach items="${recipes}" var="recipe">
                <option>${recipe.name}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="form-group row">
          <label for="day" class="col-sm-2 label-size col-form-label">
            Dzień
          </label>
          <div class="col-sm-2">
            <select name="days" class="form-control" id="day">
              <c:forEach items="${days}" var="day">
                <option>${day.name}</option>
              </c:forEach>
            </select>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</div>
</section>

<%@ include file="/appFooter.jsp" %>

