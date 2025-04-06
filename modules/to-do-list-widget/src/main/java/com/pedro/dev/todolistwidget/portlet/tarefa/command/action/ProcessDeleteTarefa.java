package com.pedro.dev.todolistwidget.portlet.tarefa.command.action;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
                "mvc.command.name=" + MVCComandKeys.TAREFA_DELETE
        },
        service = MVCActionCommand.class
)
public class ProcessDeleteTarefa extends BaseMVCActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(ProcessDeleteTarefa.class);

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        logger.debug("Buscando tarefaId");
        Long tarefaId = ParamUtil.getLong(actionRequest, "tarefaId");
        try {
            Tarefa tarefa = TarefaLocalServiceUtil.getTarefa(tarefaId);
            logger.debug("Removendo sub tarefa do banco");
            deletaFilhos(tarefa);
            logger.debug("Removendo tarefa do banco pelo tarefaId");
            TarefaLocalServiceUtil.deleteTarefa(tarefa);
            SessionMessages.add(actionRequest, "removeTarefaSucess");
        } catch (PortalException e) {
            SessionMessages.add(actionRequest, "removeTarefaErr");
        }
    }

    private void deletaFilhos(Tarefa tarefa) {
        try {
            List<Tarefa> tarefas = TarefaLocalServiceUtil.getSubTarefas(tarefa.getTarefaId());
            tarefas.forEach(e -> {
                TarefaLocalServiceUtil.deleteTarefa(e);
            });
        } catch (Exception e) {
        }
    }

}
