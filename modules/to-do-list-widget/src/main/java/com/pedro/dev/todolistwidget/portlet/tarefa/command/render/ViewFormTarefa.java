package com.pedro.dev.todolistwidget.portlet.tarefa.command.render;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
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
                "mvc.command.name=" + MVCComandKeys.TAREFA_CRIAR
        },
        service = MVCRenderCommand.class
)

public class ViewFormTarefa implements MVCRenderCommand {

    private static final Logger logger = LoggerFactory.getLogger(ViewFormTarefa.class);

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();
        long userId = themeDisplay.getUserId();

        logger.info("Caputrando tarefaId");
        long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");

        TarefaVo tarefaVo = new TarefaVo();

        if (tarefaId > 0) {
            logger.info("Buscando tarefa no banco");
            Tarefa tarefa = TarefaLocalServiceUtil.fetchTarefa(tarefaId);
            tarefaVo.setTarefa(tarefa);

            logger.info("buscando subTarefas...");
            List<Tarefa> subTarefas = TarefaLocalServiceUtil.getSubTarefasByKeywords(
                    groupId,
                    "", -1, -1, userId,
                    tarefa.getTarefaPaiId(), getComparetor());

            tarefaVo.setSubTarefas(subTarefas);
        }
        renderRequest.setAttribute("tarefaVo", tarefaVo);
        return "/tarefa/view-form.jsp";
    }

    private OrderByComparator<Tarefa> getComparetor() {
        return new OrderByComparator<Tarefa>() {
            @Override
            public int compare(Tarefa o1, Tarefa o2) {
                return 0;
            }
        };
    }

}
