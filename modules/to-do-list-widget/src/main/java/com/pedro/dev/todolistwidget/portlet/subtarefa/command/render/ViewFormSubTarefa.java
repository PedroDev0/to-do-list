package com.pedro.dev.todolistwidget.portlet.subtarefa.command.render;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import com.pedro.dev.todolistwidget.portlet.tarefa.util.UrlLoginUtil;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_SUB_FORM
        },
        service = MVCRenderCommand.class
)

public class ViewFormSubTarefa implements MVCRenderCommand {

    private static final Logger logger = LoggerFactory.getLogger(ViewFormSubTarefa.class);


    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        try {
            UrlLoginUtil.createUrlLogin(renderRequest);
        } catch (PortalException e) {
        }
        logger.info("Caputrando tarefaId");
        long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");


        long subTarefaId = ParamUtil.getLong(renderRequest, "subTarefaId");
        Tarefa tarefa = null;
        if (subTarefaId > 0) {
            logger.info("Buscando tarefa no banco");
            tarefa = TarefaLocalServiceUtil.fetchTarefa(subTarefaId);
        }

        renderRequest.setAttribute("tarefaId", tarefaId);
        renderRequest.setAttribute("subTarefa", tarefa);
        return "/sub-tarefa/view-sub-form.jsp";
    }


}
