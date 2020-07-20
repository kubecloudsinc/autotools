<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Employees" nav="employees">
  <c:choose>
    <c:when test="${fn:length(employeeList) == 0}">
      <p>No Employees</p>
    </c:when>
    <c:otherwise>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th class="number">Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="employee" items="${employeeList}">
            <c:url var="viewUrl" value="/employee.html">
              <c:param name="id" value="${employee.id}"/>
            </c:url>
           <tr>
              <td class="number"><a href="${viewUrl}">${employee.id}</a></td>
              <td>${fn:escapeXml(employee.firstName)}</a></td>
              <td>${fn:escapeXml(f:trimToLength(employee.lastName, 30))}</td>
              <td>
                  ${fn:escapeXml(f:trimToLength(employee.email, 30))}
              </td>
              <td>
                 ${employee.phoneNumber}
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</tags:page>