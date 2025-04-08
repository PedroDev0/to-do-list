/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.base.TarefaLocalServiceBaseImpl;
import com.pedro.dev.tarefa.service.constants.TarefaStatus;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
        property = "model.class.name=com.pedro.dev.tarefa.model.Tarefa",
        service = AopService.class
)
public class TarefaLocalServiceImpl extends TarefaLocalServiceBaseImpl {

    @Indexable(type = IndexableType.REINDEX)
    public Tarefa addTarefa(long groupId,
                            String titulo,
                            String descricao,
                            String urlImagem,
                            long idPai,
                            long fileEntryId,
                            long userId,
                            ServiceContext serviceContext) throws PortalException {
        //grupo do usuario
        Group group = GroupLocalServiceUtil.getGroup(groupId);
        //captura o usuario
        User user = UserLocalServiceUtil.getUser(userId);

        long id = counterLocalService.increment(Tarefa.class.getName());

        Tarefa tarefa = createTarefa(id);
        tarefa.setCompanyId(group.getCompanyId());
        tarefa.setGroupId(group.getGroupId());
        tarefa.setCreateDate(serviceContext.getCreateDate(new Date()));
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setUrlImagem(urlImagem);
        tarefa.setTarefaPaiId(idPai);
        tarefa.setFileEntryId(fileEntryId);

        tarefa.setModifiedDate(serviceContext.getCreateDate(new Date()));
        tarefa.setUserId(user.getUserId());
        tarefa.setUserName(user.getScreenName());
        tarefa.setStatus(TarefaStatus.PENDENTE.getCodigo());

        tarefa = tarefaLocalService.addTarefa(tarefa);

        return tarefa;
    }


    @Indexable(type = IndexableType.REINDEX)
    public Tarefa updateTarefa(long id,
                               String titulo,
                               String descricao,
                               String urlImagem,
                               long fileEntryId,
                               ServiceContext serviceContext) throws PortalException {

        Tarefa tarefa = getTarefa(id);
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setUrlImagem(urlImagem);
        tarefa.setFileEntryId(fileEntryId);
        tarefa.setModifiedDate(serviceContext.getCreateDate(new Date()));
        return super.updateTarefa(tarefa);
    }

    public List<Tarefa> getTarefasByGroupId(long groupId, long userId) {
        return tarefaPersistence.findBygroupIdUser(userId, groupId);
    }

    public List<Tarefa> getTarefasGroupPaginator(long groupId, long userId, int start, int end) {
        return tarefaPersistence.findBygroupIdUser(userId, groupId, start, end);
    }

    public List<Tarefa> getTarefasByGroupIdOrdenado(long groupId, long userId, int start, int end,
                                                    OrderByComparator<Tarefa> comparator) {
        return tarefaPersistence.findBygroupIdUser(userId, groupId, start, end, comparator);
    }

    public List<Tarefa> getTarefasByKeywords(long groupId, String keywords, int start, int end, long userId,
                                             long tarefaPai,
                                             OrderByComparator<Tarefa> comparator) {
        return tarefaLocalService.dynamicQuery(getKeywordDynamicQuery(groupId, keywords, userId, tarefaPai), start, end, comparator);
    }


    private DynamicQuery getKeywordDynamicQuery(long groupId, String keywords, long userId, long tarefaPai) {

        DynamicQuery dynamicQuery = dynamicQuery();

        dynamicQuery.add(RestrictionsFactoryUtil.eq("userId", userId));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
        // (tarefaPaiId = 0 OR tarefaPaiId IS NULL)
        dynamicQuery.add(RestrictionsFactoryUtil.or(
                RestrictionsFactoryUtil.eq("tarefaPaiId", tarefaPai),
                RestrictionsFactoryUtil.isNull("tarefaPaiId")
        ));
        if (Validator.isNotNull(keywords) && !keywords.isBlank()) {
            Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
            disjunctionQuery.add(RestrictionsFactoryUtil.like("titulo", "%" + keywords + "%"));
            disjunctionQuery.add(RestrictionsFactoryUtil.like("descricao", "%" + keywords + "%"));
            dynamicQuery.add(disjunctionQuery);
        }
        return dynamicQuery;
    }

    public List<Tarefa> getSubTarefasByKeywords(long groupId, int start, int end, long userId, long tarefaPaiId,
                                                OrderByComparator<Tarefa> comparator) {
        return tarefaLocalService.dynamicQuery(getKeywordSubTarefaDynamicQuery(groupId, userId, tarefaPaiId), start, end, comparator);
    }

    private DynamicQuery getKeywordSubTarefaDynamicQuery(long groupId, long userId, long tarefaPaiId) {

        DynamicQuery dynamicQuery = dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("groupId", groupId))
                .add(RestrictionsFactoryUtil.eq("userId", userId))
                .add(RestrictionsFactoryUtil.eq("tarefaPaiId", tarefaPaiId)
                );
        return dynamicQuery;
    }

    public List<Tarefa> getSubTarefas(long tarefaPaiId, long groupId, long userId) {
        return tarefaPersistence.findBychildren(userId, groupId, tarefaPaiId);
    }

    public List<Tarefa> findBybyStatus(long userId, long groupId, int status) {
        return tarefaPersistence.findBybyStatus(userId, groupId, status);
    }
    public List<Tarefa> findSubTarefasByStatus(long userId, long groupId, int status,long tarefaPaiId) {
        return tarefaPersistence.findBysubTarefasByStatus(userId, groupId, status,tarefaPaiId);
    }

}