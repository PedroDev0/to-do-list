package com.pedro.dev.todolistwidget.portlet.subtarefa.command.action;


import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_SUB_DELETE
        },
        service = MVCActionCommand.class
)
public class ProcessDeleteSubTarefa extends BaseMVCActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(ProcessDeleteSubTarefa.class);

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        logger.debug("Buscando tarefaId");
        Long subTarefaId = ParamUtil.getLong(actionRequest, "subTarefaId");
        try {
            logger.debug("Removendo sub tarefa do banco");
            Tarefa tarefa = TarefaLocalServiceUtil.getTarefa(subTarefaId);
            // remove File
            DLAppLocalServiceUtil.deleteFileEntry(tarefa.getFileEntryId());
            logger.debug("Removendo tarefa do banco");
            TarefaLocalServiceUtil.deleteTarefa(tarefa);
            // Redireciona para a página de visualização da lista de tarefas
            actionResponse.setRenderParameter("mvcRenderCommandName", MVCComandKeys.TAREFA_SUB_LISTAR);
            actionResponse.setRenderParameter("tarefaId", tarefa.getTarefaPaiId() + "");
            SessionMessages.add(actionRequest, "removeTarefaSucess");
        } catch (PortalException e) {
            SessionMessages.add(actionRequest, "removeTarefaErr");
        }
    }

}
