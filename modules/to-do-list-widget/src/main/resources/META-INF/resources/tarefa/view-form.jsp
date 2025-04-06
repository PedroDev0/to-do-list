<%@ include file="/init.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Verifica se a tarefa foi definida na requisição
    TarefaVo tarefaVo = (TarefaVo) request.getAttribute("tarefaVo");
    Tarefa tarefa = (Tarefa) tarefaVo.getTarefa();
    List<Tarefa> subtarefas = (List<Tarefa>) tarefaVo.getSubTarefas();

    if (subtarefas == null) {
        subtarefas = new ArrayList<>();
    }
%>

<!-- Action URL com o tarefaId -->
<liferay-portlet:actionURL var="addTarefaURL" name="<%= MVCComandKeys.TAREFA_INCLUIR_OU_ATUALIZAR %>">
    <liferay-portlet:param name="tarefaId" value="<%= tarefa != null ? String.valueOf(tarefa.getTarefaId()) : "" %>" />
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

                <!-- Exibindo as subtarefas -->
                <div class="form-group">
                    <h3>Subtarefas</h3>

                    <liferay-ui:search-container 
                        id="entidadeEntries"
                        total="<%= subtarefas.size() %>">
                        
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

                            <liferay-portlet:renderURL var="removerTarefaURL">
                                <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(subTarefa.getTarefaId()) %>" />
                            </liferay-portlet:renderURL>

                            <liferay-portlet:renderURL var="editSubTarefaURL">
                                <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(subTarefa.getTarefaId()) %>" />
                            </liferay-portlet:renderURL>

                            <c:if test="${not themeDisplay.getUser().isGuestUser()}">
                                <liferay-ui:search-container-column-text name="Detalhes">
                                    <liferay-ui:icon-menu
                                        direction="left-side"
                                        markupView="lexicon"
                                        showWhenSingleIcon="true">
                                        
                                        <!-- Ícone para Remover -->
                                        <liferay-ui:icon cssClass="item-remove last"
                                            message="Remover"
                                            url="<%= removerTarefaURL %>"
                                            target="icon-penciled" />

                                        <!-- Ícone para Editar -->
                                        <liferay-ui:icon cssClass="item-remove last"
                                            message="Editar"
                                            url="<%= editSubTarefaURL %>"
                                            target="icon-gift" />
                                    </liferay-ui:icon-menu>
                                </liferay-ui:search-container-column-text>
                            </c:if>

                        </liferay-ui:search-container-row>
                    </liferay-ui:search-container>
                </div>

                <div class="sheet-footer d-flex justify-content-end">
                    <aui:button type="submit" value="Salvar" cssClass="btn btn-primary mr-2" />
                    <liferay-portlet:renderURL var="cancelURL">
                        <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_LISTAR %>"/>
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
