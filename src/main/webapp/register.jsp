<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="${pageContext.request.contextPath}/header.jsp"/>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->
                <form method="post" action="/register" class="padding-small text-center">
                    <h1 class="text-color-darker">Rejestracja</h1>
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" placeholder="podaj imię" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="surname" name="surname" placeholder="podaj nazwisko" required>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" id="email" name="email" placeholder="podaj email" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="podaj hasło" required>
                    </div>
<%--                    <div class="form-group">--%>
<%--                        <input type="password" class="form-control" id="repassword" name="password" placeholder="powtórz hasło">--%>
<%--                    </div>--%>
                    <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
