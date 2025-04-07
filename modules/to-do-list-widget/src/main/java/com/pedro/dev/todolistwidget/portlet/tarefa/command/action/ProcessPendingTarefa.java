package com.pedro.dev.todolistwidget.portlet.tarefa.command.action;


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
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_PENDING
        },
        service = MVCActionCommand.class
)
public class ProcessPendingTarefa extends BaseMVCActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(ProcessPendingTarefa.class);

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        logger.debug("Buscando tarefaId");
        Long tarefaId = ParamUtil.getLong(actionRequest, "tarefaId");
        try {
            Tarefa tarefa = TarefaLocalServiceUtil.getTarefa(tarefaId);
            logger.debug("Seta status PENDENTE na tarefa");
            tarefa.setStatus(TarefaStatus.PENDENTE.getCodigo());

            logger.debug("Seta status PENDENTE nos filhos");
            concluiFilhos(tarefa);
            TarefaLocalServiceUtil.updateTarefa(tarefa);

            logger.debug("Removendo tarefa do banco pelo tarefaId");
            SessionMessages.add(actionRequest, "concluiTarefaSucess");
        } catch (PortalException e) {
            SessionMessages.add(actionRequest, "concluiTarefaErr");
        }
    }

    private void concluiFilhos(Tarefa tarefa) {
        try {
            List<Tarefa> tarefas = TarefaLocalServiceUtil.getSubTarefas(tarefa.getTarefaId(), tarefa.getGroupId(), tarefa.getUserId());
            tarefas.forEach(e -> {
                e.setStatus(TarefaStatus.PENDENTE.getCodigo());
                TarefaLocalServiceUtil.updateTarefa(e);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
