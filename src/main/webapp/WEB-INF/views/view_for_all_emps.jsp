<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<body>
<h3>Info 4 all Employees</h3>
<br><br>


<security:authorize access="hasRole('HR')">
<input type="button" value="Salary"
onclick="window.location.href = 'hr_info'">
Only 4HR
</security:authorize>
<br><br>

<security:authorize access="hasRole('MANAGER')">
<input type="button" value="Performance"
onclick="window.location.href = 'manager_info'">
Only 4 Managers
</security:authorize>

</body>
</html>
