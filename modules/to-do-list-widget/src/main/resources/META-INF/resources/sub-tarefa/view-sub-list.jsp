<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    ToolBarSubTarefaDisplay toolbarSubTarefa =  (ToolBarSubTarefaDisplay) request.getAttribute("toolbarSubTarefa");
    String loginUrl = (String) request.getAttribute("loginUrl");
%>

<c:if test="${themeDisplay.isSignedIn()}">
    <!-- Mensagens de sucesso -->
    <liferay-ui:success key="addTarefaSucess" message="Tarefa cadastrada com sucesso!" />
    <liferay-ui:success key="updateTarefaSucess" message="Tarefa alterada com sucesso!" />
    <liferay-ui:error key="removeTarefaSucess" message="Tarefa deletada com sucesso!!" />
    <liferay-ui:error key="concluiTarefaSucess" message="Tarefa concluída com sucesso!!" />

    <!-- Mensagens de erro -->
    <liferay-ui:error key="addTarefaErr" message="Falha ao cadastrar tarefa!" />
    <liferay-ui:error key="updateTarefaErr" message="Falha ao alterar tarefa!" />
    <liferay-ui:error key="removeTarefaErr" message="Falha ao deletar tarefa!" />
    <liferay-ui:error key="concluiTarefaErr" message="Falha ao deletar tarefa!" />

    <div class="container-fluid">
        <div class="sheet">
            <h2 class="sheet-title">Sub. Tarefas List</h2>

            <clay:management-toolbar
                disabled="${entidadeCount eq 0}"
                displayContext="${toolbarSubTarefa}"
                searchContainerId="entidadeEntries"
                selectable="false"
                itemsTotal="${entidadeCount}"
            />

            <liferay-ui:search-container 
                id="entidadeEntries"
                iteratorURL="${portletSubTarefaURL}"
                total="${entidadeSubCount}"
                delta="${delta}">

                <liferay-ui:search-container-results results="${entidadesSub}" />

                <c:if test="${(entidades.size() == 0)}">
                    <!-- Exibe a mensagem de "nenhum resultado encontrado" -->
                    <liferay-frontend:empty-result-message />
                </c:if>

                <liferay-ui:search-container-row
                    className="com.pedro.dev.tarefa.model.Tarefa"
                    keyProperty="tarefaId"
                    modelVar="tarefa">

                    <liferay-ui:search-container-column-text
                        name="ID"
                        value="<%= String.valueOf(tarefa.getTarefaId()) %>" />

                    <liferay-ui:search-container-column-image
                        name="Imagem"
                        src="<%= tarefa.getUrlImagem() %>" />

                    <liferay-ui:search-container-column-text
                        name="Título"
                        value="<%= tarefa.getTitulo() %>" />

                    <liferay-ui:search-container-column-text
                        name="Descrição"
                        value="<%= tarefa.getDescricao() %>" />

                    <liferay-ui:search-container-column-text
                        name="Status"
                        value="<%= TarefaStatus.fromCodigo(tarefa.getStatus()).getDescricao() %>" />

                    <liferay-portlet:renderURL var="visualizarTarefaURL">
                        <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_SUB_VIEW %>" /> 
                        <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                    </liferay-portlet:renderURL>

                    <liferay-portlet:renderURL var="editTarefaURL">
                        <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_SUB_FORM %>" />
                          <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaPaiId()) %>" />
                        <liferay-portlet:param name="subTarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                    </liferay-portlet:renderURL>

                    <liferay-portlet:actionURL var="removeTarefaURL" name="<%= MVCComandKeys.TAREFA_SUB_DELETE %>">
                        <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                    </liferay-portlet:actionURL>

                    <liferay-portlet:actionURL var="concluiTarefaURL" name="<%= MVCComandKeys.TAREFA_SUB_CONCLUIR %>">
                        <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                    </liferay-portlet:actionURL>

                    <c:if test="${themeDisplay.isSignedIn()}">
                        <liferay-ui:search-container-column-text name="Detalhes">
                            <liferay-ui:icon-menu
                                direction="left-side"
                                markupView="lexicon"
                                showWhenSingleIcon="true">

                                <liferay-ui:icon 
                                    message="Visualizar"
                                    url="<%= visualizarTarefaURL %>"
                                    target="icon-view" />

                                <liferay-ui:icon 
                                    message="Editar"
                                    url="<%= editTarefaURL %>"
                                    target="icon-penciled" />

                                <liferay-ui:icon-delete
                                    cssClass="icon-remove-sign"
                                    message="Deletar"
                                    url="<%= removeTarefaURL %>" />

                                <liferay-ui:icon 
                                    message="Concluir Tarefa"
                                    url="<%= concluiTarefaURL %>"
                                    target="icon-check" />
                            </liferay-ui:icon-menu>
                        </liferay-ui:search-container-column-text>
                    </c:if>

                </liferay-ui:search-container-row>

                <liferay-ui:search-iterator 
                    displayStyle="${toolbar.getDisplayStyle()}"
                    markupView="lexicon" />
            </liferay-ui:search-container>
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
