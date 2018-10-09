<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



<s:if test="hasActionErrors()">
    <div class="errors">
       <h1> <s:actionerror/></h1>
    </div>
</s:if><s:else>
    <h1>Không có xung đột!</h1>
</s:else>