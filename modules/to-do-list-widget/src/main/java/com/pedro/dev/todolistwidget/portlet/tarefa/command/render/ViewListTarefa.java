package com.pedro.dev.todolistwidget.portlet.tarefa.command.render;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import com.pedro.dev.todolistwidget.portlet.tarefa.toolbar.ToolBarTarefaDisplay;
import com.pedro.dev.todolistwidget.portlet.tarefa.util.UrlLoginUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;

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

        logger.info("Buscando entidades e passando para jsp");
        renderRequest.setAttribute("entidades", TarefaLocalServiceUtil.getTarefas(-1, -1));
        renderRequest.setAttribute("entidadeCount", TarefaLocalServiceUtil.getTarefas(-1, -1).size());

    }



}
