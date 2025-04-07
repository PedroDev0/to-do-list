<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>

<%@ page import="com.pedro.dev.todolistwidget.portlet.tarefa.toolbar.ToolBarTarefaDisplay" %>
<%@ page import="com.pedro.dev.todolistwidget.portlet.tarefa.toolbar.ToolBarSubTarefaDisplay" %>
<%@ page import="com.pedro.dev.tarefa.model.Tarefa" %>
<%@ page import="com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys" %>
<%@ page import="com.pedro.dev.tarefa.service.constants.TarefaStatus" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>


<liferay-theme:defineObjects />

<portlet:defineObjects />