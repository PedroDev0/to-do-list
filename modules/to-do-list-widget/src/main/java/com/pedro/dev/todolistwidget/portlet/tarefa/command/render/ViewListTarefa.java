package com.pedro.dev.todolistwidget.portlet.tarefa.command.render;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.model.TarefaTable;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import com.pedro.dev.todolistwidget.portlet.tarefa.toolbar.ToolBarTarefaDisplay;
import com.pedro.dev.todolistwidget.portlet.tarefa.util.UrlLoginUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

@Component(immediate = true, property = {"javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET, "mvc.command.name=" + MVCComandKeys.TAREFA_LISTAR, "mvc.command.name=/"}, service = MVCRenderCommand.class)

public class ViewListTarefa implements MVCRenderCommand {

    @Reference
    private Portal portal;

    private static final Logger logger = LoggerFactory.getLogger(ViewListTarefa.class);

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        try {
            UrlLoginUtil.createUrlLogin(renderRequest);
            criaToolbar(renderRequest, renderResponse);
            getEntitys(renderRequest);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }

        return "/view.jsp";
    }

    private void criaToolbar(RenderRequest renderRequest, RenderResponse renderResponse) {

        LiferayPortletRequest liferayPortletRequest = portal.getLiferayPortletRequest(renderRequest);

        LiferayPortletResponse liferayPortletResponse = portal.getLiferayPortletResponse(renderResponse);

        logger.info("Iniciando toolbar");
        ToolBarTarefaDisplay toolbar = new ToolBarTarefaDisplay(liferayPortletRequest, liferayPortletResponse, portal.getHttpServletRequest(renderRequest));

        renderRequest.setAttribute("toolbar", toolbar);
    }


    private void getEntitys(RenderRequest renderRequest) {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        int currentPage = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
                SearchContainer.DEFAULT_CUR);

        int delta = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
                SearchContainer.DEFAULT_DELTA);

        int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
        int end = start + delta;


        String keywords = ParamUtil.getString(renderRequest, "keywords");


        long groupId = themeDisplay.getScopeGroupId();
        long userId = themeDisplay.getUserId();

        // efetua a busca ordenada das tarefas
        List<Tarefa> tarefas = null;

        long count = TarefaLocalServiceUtil.getSubTarefas(0l, groupId, userId).size();
        try {
            tarefas = TarefaLocalServiceUtil.getTarefasPaiByKeywords(groupId, keywords, start, end, userId, getOrderBy(renderRequest));

        } catch (Exception e) {
            tarefas = new ArrayList<>();
        }

        logger.info("Buscando entidades e passando para jsp");
        renderRequest.setAttribute("entidades", tarefas);
        renderRequest.setAttribute("entidadeCount", count);
    }

    private OrderByComparator<Tarefa> getOrderBy(RenderRequest renderRequest) {

        String orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "titulo");
        String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");

        // true = ASC | false = DESC
        OrderByComparator<Tarefa> orderByTitulo = OrderByComparatorFactoryUtil.create(
                TarefaTable.INSTANCE.getTableName(), // Nome da tabela/entidade
                orderByCol,
                orderByType.equalsIgnoreCase("asc")
        );

        return orderByTitulo;
    }
}
