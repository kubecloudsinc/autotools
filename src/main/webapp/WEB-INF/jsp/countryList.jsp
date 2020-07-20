<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Countries in ${region.regionName}" nav="regions">
  <c:choose>
    <c:when test="${fn:length(countryList) == 0}">
      <p>No Countries</p>
    </c:when>
    <c:otherwise>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th class="number">Id</th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="country" items="${countryList}">
            <c:url var="viewUrl" value="/locations.html">
              <c:param name="id" value="${country.countryId}"/>
            </c:url>
           <tr>
              <td class="number">${country.countryId}</td>
              <td><a href="${viewUrl}">${fn:escapeXml(country.countryName)}</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</tags:page>