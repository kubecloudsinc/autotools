<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Employee Search" nav="employees">
    <div class="row">
            <form:form commandName="employeeDTO" cssClass="form-inline">
            <div class="col-md-8">
                <c:set var="firstNameError">
                  <form:errors path="firstName"  />
                </c:set>
                <c:set var="lastNameError">
                  <form:errors path="lastName"  />
                </c:set>
                <spring:hasBindErrors name="employeeDTO">
                    <c:choose>
                        <c:when test="${not empty firstNameError }">
                            <div class="alert alert-error">
                              <button type="button" class="close" data-dismiss="alert">&times;</button>
                              <strong>${firstNameError}</strong>
                            </div>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${not empty lastNameError }">
                            <div class="alert alert-error">
                              <button type="button" class="close" data-dismiss="alert">&times;</button>
                              <strong>${lastNameError}</strong>
                            </div>
                        </c:when>
                    </c:choose>
                </spring:hasBindErrors>
                <span class="control-group required">
                    <form:label path="firstName" ><strong>First Name</strong></form:label>
                </span>
                <form:input path="firstName" placeholder="First Name" />
                &nbsp;&nbsp;
                <span class="control-group required">
                    <form:label path="lastName" ><strong>Last Name</strong></form:label>
                </span>
                <form:input path="lastName" placeholder="Last Name"  />
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
            </form:form>
    </div>
    <div class="page-header"></div>
    <c:choose>
     <c:when test="${not empty employeeDTO.id}">
      <div class="page-header">
        <h1>${employeeDTO.firstName} ${employeeDTO.lastName}</h1>
      </div>
      <table>
       <tbody>
         <tr>
           <td><b>Email:</b></td><td></td><td></td><td>${employeeDTO.email}</td>
         </tr>
         <tr>
           <td><b>Phone Number:</b></td><td></td><td></td><td>${employeeDTO.phoneNumber}</td>
         </tr>
         <tr>
           <td><b>Salary:</b></td><td></td><td></td><td><fmt:formatNumber value="${employeeDTO.salary}" type="currency" /></td>
         </tr>
         <tr>
           <td><b>Manager:</b></td><td></td><td></td><td>${employeeDTO.manager.name}</td>
         </tr>
         <tr>
           <td><b>Department:</b></td><td></td><td></td><td>${employeeDTO.department.departmentName}</td>
         </tr>
         <tr>
           <td><b>Job Title:</b></td><td></td><td></td><td>${employeeDTO.job.title}</td>
         </tr>
       </tbody>
      </table>
      <div class="container">
         <div class="page-header"></div>
      </div>
      <c:choose>
         <c:when test="${fn:length(employeeDTO.jobHistory) == 0}">
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
                    <c:forEach var="aJobHistory" items="${employeeDTO.jobHistory}">
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
     </c:when>
    </c:choose>
</tags:page>