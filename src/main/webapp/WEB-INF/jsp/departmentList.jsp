<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Deprtments at ${location.postalCode}, ${location.city}  " nav="regions">
  <c:choose>
    <c:when test="${fn:length(departmentList) == 0}">
      <p>No Departments</p>
    </c:when>
    <c:otherwise>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th class="number">Id</th>
            <th>Department Name</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="department" items="${departmentList}">
           <tr>
              <td class="number">${department.id}</td>
              <td>${fn:escapeXml(department.departmentName)}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</tags:page>