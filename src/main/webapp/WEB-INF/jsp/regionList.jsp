<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Regions" nav="regions">
  <c:choose>
    <c:when test="${fn:length(regionList) == 0}">
      <p>No Regions</p>
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
          <c:forEach var="region" items="${regionList}">
            <c:url var="viewUrl" value="/countries.html">
              <c:param name="id" value="${region.id}"/>
            </c:url>
           <tr>
              <td class="number">${region.id}</td>
              <td><a href="${viewUrl}">${fn:escapeXml(region.regionName)}</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</tags:page>