package com.pedro.dev.todolistwidget.portlet.tarefa.command.render;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import com.pedro.dev.todolistwidget.portlet.tarefa.util.UrlLoginUtil;
import com.pedro.dev.todolistwidget.portlet.tarefa.vo.TarefaVo;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_VIEW
        },
        service = MVCRenderCommand.class
)

public class ViewTarefa implements MVCRenderCommand {

    private static final Logger logger = LoggerFactory.getLogger(ViewTarefa.class);

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        try {
            UrlLoginUtil.createUrlLogin(renderRequest);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
        logger.info("Caputrando tarefaId");
        long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");

        TarefaVo tarefaVo = new TarefaVo();
        logger.info("Buscando tarefa no banco");
        Tarefa tarefa = TarefaLocalServiceUtil.fetchTarefa(tarefaId);
        tarefaVo.setTarefa(tarefa);
        List<Tarefa> subTarefas = null;
        logger.info("buscando subTarefas...");
        subTarefas = TarefaLocalServiceUtil.getSubTarefas(tarefa.getTarefaId(), tarefa.getGroupId(), tarefa.getUserId());

        tarefaVo.setSubTarefas(subTarefas);
        renderRequest.setAttribute("tarefaVo", tarefaVo);
        return "/tarefa/view-tarefa.jsp";
    }


}
