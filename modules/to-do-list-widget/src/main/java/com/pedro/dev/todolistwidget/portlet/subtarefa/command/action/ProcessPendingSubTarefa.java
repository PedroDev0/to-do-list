package com.pedro.dev.todolistwidget.portlet.subtarefa.command.action;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.tarefa.service.constants.TarefaStatus;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_SUB_PENDING
        },
        service = MVCActionCommand.class
)
public class ProcessPendingSubTarefa extends BaseMVCActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(ProcessPendingSubTarefa.class);

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        logger.debug("Buscando tarefaId");
        Long tarefaId = ParamUtil.getLong(actionRequest, "subTarefaId");
        try {
            Tarefa tarefa = TarefaLocalServiceUtil.getTarefa(tarefaId);
            logger.debug("Seta status concluido na tarefa");
            tarefa.setStatus(TarefaStatus.PENDENTE.getCodigo());
            TarefaLocalServiceUtil.updateTarefa(tarefa);

            // Redireciona para a página de visualização da lista sub de tarefas
            actionResponse.setRenderParameter("mvcRenderCommandName", MVCComandKeys.TAREFA_SUB_LISTAR);
            actionResponse.setRenderParameter("tarefaId", tarefa.getTarefaPaiId() + "");
            SessionMessages.add(actionRequest, "concluiTarefaSucess");
        } catch (PortalException e) {
            SessionMessages.add(actionRequest, "concluiTarefaErr");
        }
    }


}
