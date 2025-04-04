package com.pedro.dev.todolistwidget.portlet.tarefa.command.action;


import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_INCLUIR_OU_ATUALIZAR
        },
        service = MVCActionCommand.class
)
public class ProcessSaveTarefa extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

    }
}
