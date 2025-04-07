<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Verifica se a tarefa foi definida na requisição
    Tarefa tarefa = (Tarefa) request.getAttribute("tarefa");
    String loginUrl = (String) request.getAttribute("loginUrl");
%>


<c:if test="${themeDisplay.isSignedIn()}">
    <div class="container">
        <div class="sheet sheet-lg">
            <div class="sheet-title">
                <h2>Tarefa</h2>
            </div>

            <div class="sheet-section">
                <aui:form enctype="multipart/form-data">
                    <div class="form-group">
                        <aui:row>
                            <aui:col width="50">
                                <aui:input name="id" readonly="true" label="Id" type="text"
                                    value="<%= tarefa != null ? String.valueOf(tarefa.getTarefaId()) : "" %>" />
                            </aui:col>
                            <aui:col width="50">
                                <aui:input name="status" readonly="true" label="status" type="text"
                                    value="<%= tarefa != null ? TarefaStatus.fromCodigo(tarefa.getStatus()).getDescricao() : "" %>" />
                            </aui:col>
                        </aui:row>
                    </div>

                    <div class="form-group">
                        <aui:input name="titulo" readonly="true" label="Título" type="text"
                            value="<%= tarefa != null ? tarefa.getTitulo() : "" %>" />
                    </div>

                    <div class="form-group">
                        <aui:input readonly="true" name="descricao" label="Descrição" type="textarea" rows="5"
                            value="<%= tarefa != null ? tarefa.getDescricao() : "" %>" />
                    </div>

                    <div class="form-group">
                        <img src="<%= tarefa != null ? tarefa.getUrlImagem() : "" %>"
                            alt="Descrição da Imagem" width="100%" height="250" />
                    </div>


                    <div class="sheet-footer d-flex justify-content-end">
                        <liferay-portlet:renderURL var="cancelURL">
                            <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_SUB_LISTAR %>" />
                            <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaPaiId()) %>" />
                        </liferay-portlet:renderURL>
                        <aui:button type="button" value="Voltar" cssClass="btn btn-secondary"
                            onClick="window.location.href='${cancelURL}'" />
                    </div>
                </aui:form>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${!themeDisplay.isSignedIn()}">
    <div class="container-fluid">
        <div class="sheet">
            <a href="<%= loginUrl %>" class="sheet-title">Faça login para continuar</a>
        </div>
    </div>
</c:if>
