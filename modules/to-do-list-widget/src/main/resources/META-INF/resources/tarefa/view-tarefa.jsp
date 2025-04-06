<%@ include file="/init.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Verifica se a tarefa foi definida na requisição
    TarefaVo tarefaVo = (TarefaVo) request.getAttribute("tarefaVo");
    Tarefa tarefa = (Tarefa) tarefaVo.getTarefa();
    List<Tarefa> subtarefas = (List<Tarefa>) tarefaVo.getSubTarefas();
    String loginUrl = (String) request.getAttribute("loginUrl");
    if (subtarefas == null) {
        subtarefas = new ArrayList<>();
    }
%>

<!-- Action URL com o tarefaId -->
<liferay-portlet:actionURL var="addTarefaURL" name="<%= MVCComandKeys.TAREFA_INCLUIR_OU_ATUALIZAR %>">
    <liferay-portlet:param name="tarefaId" value="<%= tarefa != null ? String.valueOf(tarefa.getTarefaId()) : "" %>" />
</liferay-portlet:actionURL>

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
                                <aui:input name="id" readonly="true" label="Título" type="text"
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

                    <!-- Exibindo as subtarefas -->
                    <div class="form-group">
                        <h3>Subtarefas</h3>

                        <liferay-ui:search-container id="entidadeEntries" total="<%= subtarefas.size() %>">
                            <liferay-ui:search-container-results results="<%= subtarefas %>" />

                            <c:if test="<%= subtarefas.size() == 0 %>">
                                <!-- Exibe a mensagem de "nenhum resultado encontrado" -->
                                <liferay-frontend:empty-result-message />
                            </c:if>

                            <liferay-ui:search-container-row
                                className="com.pedro.dev.tarefa.model.Tarefa"
                                keyProperty="tarefaId"
                                modelVar="subTarefa">

                                <liferay-ui:search-container-column-text
                                    name="ID"
                                    value="<%= String.valueOf(subTarefa.getTarefaId()) %>" />

                                <liferay-ui:search-container-column-image
                                    name="Imagem"
                                    src="<%= subTarefa.getUrlImagem() %>" />

                                <liferay-ui:search-container-column-text
                                    name="Título"
                                    value="<%= subTarefa.getTitulo() %>" />

                                <liferay-ui:search-container-column-text
                                    name="Descrição"
                                    value="<%= subTarefa.getDescricao() %>" />

                                <liferay-portlet:renderURL var="visualizarSubTarefaURL">
                                    <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(subTarefa.getTarefaId()) %>" />
                                </liferay-portlet:renderURL>

                                <c:if test="${not themeDisplay.getUser().isGuestUser()}">
                                    <liferay-ui:search-container-column-text name="Detalhes">
                                        <liferay-ui:icon-menu
                                            direction="left-side"
                                            markupView="lexicon"
                                            showWhenSingleIcon="true">
                                            <!-- Ícone para Editar -->
                                            <liferay-ui:icon cssClass="item-remove last"
                                                message="Visualizar"
                                                url="<%= visualizarSubTarefaURL %>"
                                                target="icon-penciled" />
                                        </liferay-ui:icon-menu>
                                    </liferay-ui:search-container-column-text>
                                </c:if>
                            </liferay-ui:search-container-row>
                        </liferay-ui:search-container>
                    </div>

                    <div class="sheet-footer d-flex justify-content-end">
                        <liferay-portlet:renderURL var="cancelURL">
                            <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_LISTAR %>" />
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
