<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    ToolBarTarefaDisplay toolbar = (ToolBarTarefaDisplay) request.getAttribute("toolbar");
    String loginUrl = (String) request.getAttribute("loginUrl");

    Long tarefasConcluidas =(Long) request.getAttribute("tarefasConcluidas");
    Long tarefasPendentes = (Long) request.getAttribute("tarefasPendentes");
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
        <liferay-ui:tabs names="Lista de Tarefas,Pendentes,Concluídas" refresh="<%= false %>">
            
            <!-- Aba da Lista -->
            <liferay-ui:section>
                <div class="sheet">
                    <clay:management-toolbar
                        disabled="${entidadeCount eq 0}"
                        displayContext="${toolbar}"
                        searchContainerId="entidadeEntries"
                        selectable="false"
                        itemsTotal="${entidadeCount}" />

                    <liferay-ui:search-container 
                        id="entidadeEntries"
                        iteratorURL="${portletURL}"
                        total="${entidadeCount}"
                        delta="${delta}">

                        <liferay-ui:search-container-results results="${entidades}" />

                        <c:if test="${(entidades.size() == 0)}">
                            <liferay-frontend:empty-result-message />
                        </c:if>

                        <liferay-ui:search-container-row
                            className="com.pedro.dev.todolistwidget.portlet.tarefa.vo.TarefaVo"
                            keyProperty="tarefaId"
                            modelVar="tarefa">

                            <liferay-ui:search-container-column-text
                                name="ID"
                                value="<%= String.valueOf(tarefa.getTarefaId()) %>" />

                            <liferay-ui:search-container-column-image
                                name="Imagem"
                                src="<%= tarefa.getTarefa().getUrlImagem() %>" />

                            <liferay-ui:search-container-column-text
                                name="Título"
                                value="<%= tarefa.getTarefa().getTitulo() %>" />

                            <liferay-ui:search-container-column-text
                                name="Descrição"
                                value="<%= tarefa.getTarefa().getDescricao() %>" />

                            <liferay-ui:search-container-column-text
                                name="Status"
                                value="<%= TarefaStatus.fromCodigo(tarefa.getTarefa().getStatus()).getDescricao() %>" />
                            <liferay-ui:search-container-column-text
                                name="Total Sub. Concluídas"
                                value="<%= String.valueOf(tarefa.getGetTotalSubTarefaConcluida()) %>" />
                            <liferay-ui:search-container-column-text
                                name="Total Sub. Pendentes"
                                value="<%= String.valueOf(tarefa.getTotalSubTarefaPendente()) %>" />

                            <liferay-portlet:renderURL var="visualizarTarefaURL">
                                <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_VIEW %>" />
                                <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                            </liferay-portlet:renderURL>

                            <liferay-portlet:renderURL var="editTarefaURL">
                                <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_FORM %>" />
                                <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                            </liferay-portlet:renderURL>

                            <liferay-portlet:actionURL var="removeTarefaURL" name="<%= MVCComandKeys.TAREFA_DELETE %>">
                                <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                            </liferay-portlet:actionURL>

                            <liferay-portlet:actionURL var="concluiTarefaURL" name="<%= MVCComandKeys.TAREFA_CONCLUIR %>">
                                <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                            </liferay-portlet:actionURL>

                            <liferay-portlet:actionURL var="pendentTarefaURL" name="<%= MVCComandKeys.TAREFA_PENDING %>">
                                <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                            </liferay-portlet:actionURL>

                            <liferay-portlet:renderURL var="subTarefaURL">
                                <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TAREFA_SUB_LISTAR %>" />
                                <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                            </liferay-portlet:renderURL>

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
                                            message="Concluída"
                                            url="<%= concluiTarefaURL %>"
                                            target="icon-check" />

                                        <liferay-ui:icon 
                                            message="Pendente"
                                            url="<%= pendentTarefaURL %>"
                                            target="icon-check" />

                                        <liferay-ui:icon 
                                            message="Subtarefas"
                                            url="<%= subTarefaURL %>"
                                            target="icon-new" />
                                    </liferay-ui:icon-menu>
                                </liferay-ui:search-container-column-text>
                            </c:if>

                        </liferay-ui:search-container-row>

                        <liferay-ui:search-iterator 
                            displayStyle="${toolbar.getDisplayStyle()}"
                            markupView="lexicon" />
                    </liferay-ui:search-container>
                </div>
            </liferay-ui:section>

            <!-- Aba da Pendentes count -->
            <liferay-ui:section>
                <div class="sheet">
                    <div class="card text-white bg-warning mb-3" style="max-width: 18rem;">
                        <div class="card-header">Resumo</div>
                        <div class="card-body">
                            <h5 class="card-title">Tarefas Pendentes</h5>
                            <p class="card-text">
                                Você tem <strong>${tarefasPendentes}</strong> tarefas pendentes.
                            </p>
                        </div>
                    </div>
                </div>
            </liferay-ui:section>

            <!-- Aba da Concluido count -->
            <liferay-ui:section>
                <div class="sheet">
                    <div class="card text-white bg-success mb-3" style="max-width: 18rem;">
                        <div class="card-header">Resumo</div>
                        <div class="card-body">
                            <h5 class="card-title">Tarefas Concluídas</h5>
                            <p class="card-text">
                                Você tem <strong>${tarefasConcluidas}</strong> tarefas concluídas.
                            </p>
                        </div>
                    </div>
                </div>
            </liferay-ui:section>
        </liferay-ui:tabs>
    </div>
</c:if>

<c:if test="${!themeDisplay.isSignedIn()}">
    <div class="container-fluid">
        <div class="sheet">
            <a href="<%= loginUrl %>" class="sheet-title">Faça login para continuar</a>
        </div>
    </div>
</c:if>
