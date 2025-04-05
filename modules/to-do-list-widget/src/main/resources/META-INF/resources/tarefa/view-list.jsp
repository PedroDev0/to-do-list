<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ToolBarTarefaDisplay toolbar =  (ToolBarTarefaDisplay) request.getAttribute("toolbar");
%>
<c:if test="${not themeDisplay.getUser().isGuestUser()}">
   <!-- Mensagens de criação -->
    <liferay-ui:success key="addTarefaSucess" message="Tarefa cadastrada com sucesso!" />
    <liferay-ui:success key="updateTarefaSucess" message="Tarefa alterada com sucesso!" />

    <liferay-ui:error key="addTarefaErr" message="Falha ao cadastrar tarefa!" />
    <liferay-ui:error key="updateTarefaErr" message="Falha ao alterar tarefa!" />

   <div class="container-fluid">
    <div class="sheet">
    <h2 class="sheet-title">To Do List</h2>
            <clay:management-toolbar
               disabled="${entidadeCount eq 0}"
		       displayContext="${toolbar}"
		       searchContainerId="entidadeEntries"
		       selectable="false"
		       itemsTotal="${entidadeCount}"
              />

        <liferay-ui:search-container 
            id="entidadeEntries"
            iteratorURL="${portletURL}"
            total="${entidadeCount}"
            delta="${delta}" >

            <liferay-ui:search-container-results  results="${entidades}" />
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

                <liferay-ui:search-container-column-image name="Imagem"
                    src="<%= tarefa.getUrlImagem() %>"
                />
                <liferay-ui:search-container-column-text
                    name="Título"
                    value="<%= tarefa.getTitulo() %>" />
                <liferay-ui:search-container-column-text
                    name="Descrição"
                    value="<%= tarefa.getDescricao() %>" />

              <liferay-portlet:renderURL var="visualizarTarefaURL">
                    <%-- <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.TarefaVIEW %>" /> --%>
                    <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                </liferay-portlet:renderURL>
                <liferay-portlet:renderURL var="editTarefaURL">
                    <%-- <liferay-portlet:param name="mvcRenderCommandName" value="<%= MVCComandKeys.EDIT_TAREFA %>"   /> --%>
                    <liferay-portlet:param name="tarefaId" value="<%= String.valueOf(tarefa.getTarefaId()) %>" />
                </liferay-portlet:renderURL>

        <c:if test="${not themeDisplay.getUser().isGuestUser()}">
            <liferay-ui:search-container-column-text name="Detalhes">
                <liferay-ui:icon-menu
                            direction="left-side"
                            markupView="lexicon"
                            showWhenSingleIcon="true">
                            <liferay-ui:icon cssClass="item-remove last"
                                message="Visualizar"
                                url="<%= visualizarTarefaURL %>"
                                target="icon-penciled" />
                                
                            <liferay-ui:icon cssClass="item-remove last"
                                message="Editar"
                                url="<%= editTarefaURL %>"
                                target="icon-gift" />
                            <liferay-ui:icon cssClass="item-remove last"
                                message="Deletar"
                                url="<%= editTarefaURL %>"
                                target="icon-gift" />
                            <liferay-ui:icon cssClass="item-remove last"
                                message="Criar Sub. Tarefa"
                                url="<%= editTarefaURL %>"
                                target="icon-gift" />
                            <liferay-ui:icon cssClass="item-remove last"
                                message="Concluir Tarefa"
                                url="<%= editTarefaURL %>"
                                target="icon-gift" />
                        </liferay-ui:icon-menu>
            </liferay-ui:search-container-column-text>
            </c:if>
                  
            </liferay-ui:search-container-row>
            <liferay-ui:search-iterator displayStyle="${toolbar.getDisplayStyle()}" markupView="lexicon"  />
        </liferay-ui:search-container>
    </div>
    </div>
</c:if>