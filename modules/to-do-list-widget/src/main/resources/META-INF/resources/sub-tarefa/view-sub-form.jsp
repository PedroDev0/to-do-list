<%@ include file="/init.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Verifica se a tarefa foi definida na requisição
        Tarefa tarefa = (Tarefa) request.getAttribute("subTarefa");
        String loginUrl = (String) request.getAttribute("loginUrl");
        Long tarefaId = (Long) request.getAttribute("tarefaId");
%>

<!-- Action URL com o tarefaId -->
<liferay-portlet:actionURL var="addTarefaURL" name="<%= MVCComandKeys.TAREFA_SUB_INCLUIR_OU_ATUALIZAR %>">
    <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId)  %>" />
    <liferay-portlet:param name="subTarefaId" value="<%= tarefa != null ? String.valueOf(tarefa.getTarefaId()) : "" %>" />
</liferay-portlet:actionURL>

<div class="container">
    <div class="sheet sheet-lg">
        <div class="sheet-title">
            <h2>Sub. Tarefa</h2>
        </div>

        <div class="sheet-section">
            <aui:form action="${addTarefaURL}" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <aui:input name="titulo" label="Título" type="text" required="true"
                               value="<%= tarefa != null ? tarefa.getTitulo() : "" %>" />
                </div>

                <div class="form-group">
                    <aui:input name="descricao" label="Descrição" type="textarea" rows="5" required="true"
                               value="<%= tarefa != null ? tarefa.getDescricao() : "" %>" />
                </div>

                <div class="form-group">
                    <img
                        src="<%= tarefa != null ? tarefa.getUrlImagem() : "" %>"
                        alt="Descrição da Imagem"
                        width="100%"
                        height="250"
                    />
                </div>

                <div class="form-group">
                    <aui:input name="file" label="Imagem" type="file" accept="image/*" required="<%= tarefa == null %>" />
                </div>

                <div class="sheet-footer d-flex justify-content-end">
                    <aui:button type="submit" value="Salvar" cssClass="btn btn-primary mr-2" />
                    <liferay-portlet:renderURL var="cancelURL">
                        <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_SUB_LISTAR %>"/>
                        <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId)  %>" />
                    </liferay-portlet:renderURL>
                    <aui:button type="button" value="Cancelar" cssClass="btn btn-secondary"
                                onClick="window.location.href='${cancelURL}'" />
                </div>

            </aui:form>
        </div>
    </div>
</div>

<c:if test="${!themeDisplay.isSignedIn()}">
    <div class="container-fluid">
        <div class="sheet">
            <a href="<%= loginUrl %>" class="sheet-title">Faça login para continuar</a>
        </div>
    </div>
</c:if>
