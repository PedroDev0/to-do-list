<%@ include file="/init.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Verifica se a tarefa foi definida na requisição
    Tarefa tarefaForm = (Tarefa) request.getAttribute("tarefa");
    List<Tarefa> subTarefas = (List<Tarefa>) request.getAttribute("subTarefas");
    String loginUrl = (String) request.getAttribute("loginUrl");
%>

<!-- Action URL com o tarefaId -->
<liferay-portlet:actionURL var="addTarefaURL" name="<%= MVCComandKeys.TAREFA_INCLUIR_OU_ATUALIZAR %>">
    <liferay-portlet:param name="tarefaId" value="<%= tarefaForm != null ? String.valueOf(tarefaForm.getTarefaId()) : "" %>" />
</liferay-portlet:actionURL>

<div class="container">
    <div class="sheet sheet-lg">
        <div class="sheet-title">
            <h2>Tarefa</h2>
        </div>

        <div class="sheet-section">
            <aui:form action="${addTarefaURL}" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <aui:input name="titulo" label="Título" type="text" required="true"
                        value="<%= tarefaForm != null ? tarefaForm.getTitulo() : "" %>" />
                </div>

                <div class="form-group">
                    <aui:input name="descricao" label="Descrição" type="textarea" rows="5" required="true"
                        value="<%= tarefaForm != null ? tarefaForm.getDescricao() : "" %>" />
                </div>

                <div class="form-group">
                    <img
                        src="<%= tarefaForm != null ? tarefaForm.getUrlImagem() : "" %>"
                        alt="Descrição da Imagem"
                        width="100%"
                        height="250"
                    />
                </div>

                <div class="form-group">
                    <aui:input name="file" label="Imagem" type="file" accept="image/*" required="<%= tarefaForm == null %>" />
                </div>
                <div class="sheet-footer d-flex justify-content-end">
                    <aui:button type="submit" value="Salvar" cssClass="btn btn-primary mr-2" />
                    <liferay-portlet:renderURL var="cancelURL">
                        <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_LISTAR %>" />
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
