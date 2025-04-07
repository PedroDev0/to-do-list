package com.pedro.dev.todolistwidget.portlet.subtarefa.command.action;


import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.Folder;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.File;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
                "mvc.command.name=" + MVCComandKeys.TAREFA_SUB_INCLUIR_OU_ATUALIZAR
        },
        service = MVCActionCommand.class
)
public class ProcessSaveSubTarefa extends BaseMVCActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(ProcessSaveSubTarefa.class);

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        logger.debug("Buscando tarefaId");
        // captura o theme display
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        // Processa requisição do forumalrio de tarefa
        logger.debug("Capturando tarefaId (Id do Pai)");
        Long tarefaId = ParamUtil.getLong(actionRequest, "tarefaId");

        logger.debug("Capturando subTarefaId (Id da sub tarefa)");
        Long subTarefaId = ParamUtil.getLong(actionRequest, "subTarefaId");

        // se o id for null cria uma nova tarefa
        if (subTarefaId == null || subTarefaId <= 0) {
            logger.debug("Criando nova tarefa");
            processNewTarefa(tarefaId, themeDisplay, actionRequest, actionResponse);
            return;
        }

        processUpdateTarefa(subTarefaId, actionRequest, actionResponse);

    }


    private void processNewTarefa(long tarefaPaiId, ThemeDisplay themeDisplay, ActionRequest actionRequest, ActionResponse actionResponse) {

        try {
            long groupId = themeDisplay.getScopeGroupId();
            long userId = themeDisplay.getUserId();
            logger.debug("Captrando titulo");
            String titulo = ParamUtil.getString(actionRequest, "titulo");

            logger.debug("Captrando descrição");
            String descricao = ParamUtil.getString(actionRequest, "descricao");

            logger.debug("Captrando arquivo");
            UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
            File file = uploadPortletRequest.getFile("file");
            String originalFileName = uploadPortletRequest.getFileName("file");

            // Gerando um nome único para o arquivo
            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
            ServiceContext serviceContext = getFileServiceContext(actionRequest);

            long folderId = getFolderId(groupId, Folder.FOLDER_NAME_TAREFA, userId, actionRequest);
            // Criando o FileEntry
            FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                    userId, groupId, folderId, uniqueFileName, "image/jpeg", uniqueFileName, "", "", file, serviceContext);

            // Gerando o URL da imagem para exibição
            String imagem = DLUtil.getPreviewURL(
                    DLAppServiceUtil.getFileEntry(fileEntry.getFileEntryId()),
                    DLAppServiceUtil.getFileEntry(fileEntry.getFileEntryId()).getFileVersion(),
                    serviceContext.getThemeDisplay(), "");

            // Adicionando a tarefa no banco de dados


            logger.debug("Adcionando tarefa no banco");
            Tarefa tarefa = TarefaLocalServiceUtil.addTarefa(
                    groupId, titulo, descricao, imagem, 0l, fileEntry.getFileEntryId(), userId, serviceContext);


            // efetua a ligação com a tarefa pai
            tarefa.setTarefaPaiId(tarefaPaiId);
            TarefaLocalServiceUtil.updateTarefa(tarefa);
            // Mensagem de sucesso
            SessionMessages.add(actionRequest, "addTarefaSucess");

            // Redireciona para a página de visualização da lista de tarefas
            actionResponse.setRenderParameter("mvcRenderCommandName", MVCComandKeys.TAREFA_SUB_LISTAR);
            actionResponse.setRenderParameter("tarefaId", tarefa.getTarefaPaiId() + "");

        } catch (
                Exception e) {
            SessionErrors.add(actionRequest, "addTarefaErr");
            e.printStackTrace();
        }


    }

    private long getFolderId(long groupId, String folderName, long userId, ActionRequest actionRequest) throws PortalException {

        DLFolder dlFolder = null;
        ServiceContext dlServiceCtx = ServiceContextFactory.getInstance(DLFolder.class.getName(), actionRequest);
        try {
            // Tentar obter a pasta existente
            dlFolder = DLFolderLocalServiceUtil.getFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, folderName);
        } catch (Exception e) {
            // Caso a pasta não exista, criamos uma nova
            dlFolder = DLFolderLocalServiceUtil.addFolder(
                    null,      // Código de referência externa (pode ser nulo)
                    userId,                     // ID do usuário que está criando a pasta
                    groupId,                    // ID do grupo
                    DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,               // ID do repositório (se aplicável)
                    false,
                    DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, // ID da pasta pai
                    folderName,                 // Nome da pasta
                    folderName,                 // Descrição da pasta
                    false,                       // Se a pasta é oculta
                    dlServiceCtx // Contexto de serviço
            );
        }

        return dlFolder.getFolderId();
    }

    private void processUpdateTarefa(Long tarefaId, ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {

        logger.debug("Buscando Sub Tarefa no Banco");
        Tarefa tarefa = TarefaLocalServiceUtil.getTarefa(tarefaId);

        logger.debug("Captrando titulo");
        String titulo = ParamUtil.getString(actionRequest, "titulo");

        logger.debug("Captrando descrição");
        String descricao = ParamUtil.getString(actionRequest, "descricao");

        logger.debug("Captrando arquivo");
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);

        File file = uploadPortletRequest.getFile("file");
        String originalFileName = uploadPortletRequest.getFileName("file");

        // valida se tem arquivo
        if (originalFileName != null && !originalFileName.isBlank()) {
            // Gerando um nome único para o arquivo
            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

            ServiceContext serviceContext = getFileServiceContext(actionRequest);

            long folderId = getFolderId(tarefa.getGroupId(), Folder.FOLDER_NAME_TAREFA, tarefa.getUserId(), actionRequest);

            // remove File antigo
            DLAppLocalServiceUtil.deleteFileEntry(tarefa.getFileEntryId());

            // Alterando o file
            FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                    tarefa.getUserId(), tarefa.getGroupId(),
                    folderId,
                    uniqueFileName,
                    "image/jpeg",
                    uniqueFileName, "",
                    "", file, serviceContext);

            // Gerando o URL da imagem para exibição
            String imagem = DLUtil.getPreviewURL(
                    DLAppServiceUtil.getFileEntry(fileEntry.getFileEntryId()),
                    DLAppServiceUtil.getFileEntry(fileEntry.getFileEntryId()).getFileVersion(),
                    serviceContext.getThemeDisplay(), "");

            tarefa.setUrlImagem(imagem);
            tarefa.setFileEntryId(fileEntry.getFileEntryId());
        }


        // Atualizando  a tarefa
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        logger.debug("Adcionando tarefa no banco");
        TarefaLocalServiceUtil.updateTarefa(tarefa);
        // Redireciona para a página de visualização da lista de tarefas
        actionResponse.setRenderParameter("mvcRenderCommandName", MVCComandKeys.TAREFA_SUB_LISTAR);
        actionResponse.setRenderParameter("tarefaId", tarefa.getTarefaPaiId() + "");

    }


    private ServiceContext getFileServiceContext(ActionRequest actionRequest) throws PortalException {

        ServiceContext serviceContext = ServiceContextFactory.getInstance(FileEntry.class.getName(), actionRequest);
        serviceContext.setAddGroupPermissions(true);
        serviceContext.setAddGuestPermissions(true);
        return serviceContext;
    }

}
