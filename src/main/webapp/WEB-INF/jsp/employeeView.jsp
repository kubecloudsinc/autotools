<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="${fn:escapeXml(employee.name)}" nav="employees">
 <table>
  <tbody>
    <tr>
      <td><b>Email:</b></td><td></td><td></td><td>${employee.email}</td>
    </tr>
    <tr>
      <td><b>Phone Number:</b></td><td></td><td></td><td>${employee.phoneNumber}</td>
    </tr>
    <tr>
      <td><b>Salary:</b></td><td></td><td></td><td><fmt:formatNumber value="${employee.salary}" type="currency" /></td>
    </tr>
    <tr>
      <td><b>Manager:</b></td><td></td><td></td><td>${employee.manager.name}</td>
    </tr>
    <tr>
      <td><b>Department:</b></td><td></td><td></td><td>${employee.department.departmentName}</td>
    </tr>
    <tr>
      <td><b>Job Title:</b></td><td></td><td></td><td>${employee.job.title}</td>
    </tr>
  </tbody>
 </table>
 <div class="container">
    <div class="page-header"></div>
 </div>
 <c:choose>
    <c:when test="${fn:length(employee.jobHistory) == 0}">
        <table class="table table-striped table-hover">
            <thead>
                <th>Job History:</th>
            </thead>
            <tbody>
                <tr><td>This employee has no job history</td></tr>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
         <table class="table table-striped table-hover">
             <thead>
                <th>Job History:</th>
                <tr>
                   <th>Job Title</th>
                   <th>Start Date</th>
                   <th>End Date</th>
                   <th>Department</th>
                </tr>
             </thead>
             <tbody>
               <c:forEach var="aJobHistory" items="${employee.jobHistory}">
                    <tr>
                       <td>${fn:escapeXml(aJobHistory.job.title)}</a></td>
                       <td><fmt:formatDate value="${aJobHistory.startDate}" pattern="MMMMM d, yyyy hh:mma" /></td>
                       <td><fmt:formatDate value="${aJobHistory.endDate}" pattern="MMMMM d, yyyy hh:mma" /></td>
                       <td>${aJobHistory.department.departmentName}</td>
                    </tr>
               </c:forEach>
             </tbody>
         </table>
    </c:otherwise>
 </c:choose>
</tags:page>