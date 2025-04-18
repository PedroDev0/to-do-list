package com.pedro.dev.todolistwidget.portlet.tarefa.command.render;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
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

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_FORM
        },
        service = MVCRenderCommand.class
)

public class ViewFormTarefa implements MVCRenderCommand {

    private static final Logger logger = LoggerFactory.getLogger(ViewFormTarefa.class);
    @Reference
    private Portal portal;

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        try {
            UrlLoginUtil.createUrlLogin(renderRequest);
        } catch (PortalException e) {
        }

        logger.info("Caputrando tarefaId");
        long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");

        Tarefa tarefa = null;
        List<Tarefa> subTarefas = new ArrayList<>();
        if (tarefaId > 0) {
            logger.info("Buscando tarefa no banco");
            tarefa = TarefaLocalServiceUtil.fetchTarefa(tarefaId);
            logger.info("buscando subTarefas...");
            subTarefas = TarefaLocalServiceUtil.getSubTarefas(tarefa.getTarefaId(), tarefa.getGroupId(), tarefa.getUserId());

        }
        renderRequest.setAttribute("tarefa", tarefa);
        renderRequest.setAttribute("subTarefas", subTarefas);
        return "/tarefa/view-form.jsp";
    }

}
