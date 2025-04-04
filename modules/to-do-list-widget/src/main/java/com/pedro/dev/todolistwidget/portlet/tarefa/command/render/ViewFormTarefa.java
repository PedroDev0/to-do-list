package com.pedro.dev.todolistwidget.portlet.tarefa.command.render;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import com.pedro.dev.todolistwidget.portlet.tarefa.toolbar.ToolBarTarefaDisplay;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.InputStream;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_CRIAR,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)

public class ViewFormTarefa implements MVCRenderCommand {

    private static final Logger logger = LoggerFactory.getLogger(ViewListTarefa.class);
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {


        logger.info("Caputrando tarefaId");

        long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");

        if (tarefaId > 0) {
            // Recupera a promoção usando o serviço
            logger.info("Buscando tarefa no banco");
            Tarefa tarefa = TarefaLocalServiceUtil.fetchTarefa(tarefaId);

            if (tarefa!= null){
                logger.info("passando tarefa para jsp");
                renderRequest.setAttribute("tarefa", tarefa);
            }
        }
        return "/tarefa/view-form.jsp";
    }

}
