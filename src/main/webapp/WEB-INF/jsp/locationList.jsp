<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Locations in ${country.countryName}" nav="regions">
  <c:choose>
    <c:when test="${fn:length(locationList) == 0}">
      <p>No Locations</p>
    </c:when>
    <c:otherwise>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th class="number">Id</th>
            <th>Street Address</th>
            <th>Postal Code</th>
            <th>City</th>
            <th>State or Province</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="location" items="${locationList}">
            <c:url var="viewUrl" value="/departments.html">
              <c:param name="id" value="${location.id}"/>
            </c:url>
           <tr>
              <td class="number"><a href="${viewUrl}">${location.id}</a></td>
              <td>${fn:escapeXml(location.streetAddress)}</td>
              <td>${fn:escapeXml(location.postalCode)}</td>
              <td>${fn:escapeXml(location.city)}</td>
              <td>${fn:escapeXml(location.stateOrProvince)}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</tags:page>