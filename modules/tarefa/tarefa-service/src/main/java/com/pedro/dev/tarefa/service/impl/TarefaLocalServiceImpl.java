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
                            ServiceContext serviceContext) throws PortalException {
        //grupo do usuario
        Group group = GroupLocalServiceUtil.getGroup(groupId);
        //captura o usuario
        User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

        long id = counterLocalService.increment(Tarefa.class.getName());

        Tarefa tarefa = createTarefa(id);
        tarefa.setCompanyId(group.getCompanyId());
        tarefa.setCreateDate(serviceContext.getCreateDate(new Date()));
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setUrlImagem(urlImagem);
        tarefa.setTarefaPaiId(idPai);
        tarefa.setFileEntryId(fileEntryId);

        tarefa.setModifiedDate(serviceContext.getCreateDate(new Date()));
        tarefa.setUserId(user.getUserId());
        tarefa.setUserName(user.getScreenName());


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

    public List<Tarefa> getTarefasByGroupId(long groupId) {
        return tarefaPersistence.findBygroupId(groupId);
    }

    public List<Tarefa> getTarefasGroupPaginator(long groupId, int start, int end) {
        return tarefaPersistence.findBygroupId(groupId, start, end);
    }

    public List<Tarefa> getTarefasByGroupIdOrdenado(long groupId, int start, int end,
                                                    OrderByComparator<Tarefa> comparator) {
        return tarefaPersistence.findBygroupId(groupId, start, end, comparator);
    }

    public List<Tarefa> getTarefasByKeywords(long groupId, String keywords, int start, int end,
                                             OrderByComparator<Tarefa> comparator) {
        return tarefaLocalService.dynamicQuery(getKeywordDynamicQuery(groupId, keywords), start, end, comparator);
    }

    private DynamicQuery getKeywordDynamicQuery(long groupId, String keywords) {

        DynamicQuery dynamicQuery = dynamicQuery()
                .add(RestrictionsFactoryUtil.eq(
                        "groupId", groupId
                ));
        if (Validator.isNotNull(keywords)) {
            Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
            disjunctionQuery.add(RestrictionsFactoryUtil.like("titulo", "%" + keywords + "%"));
            disjunctionQuery.add(RestrictionsFactoryUtil.like("descricao", "%" + keywords + "%"));
            dynamicQuery.add(disjunctionQuery);
        }
        return dynamicQuery;
    }

    public long getCountTarefasByStatus(long groupId, int status) {
        return tarefaLocalService.dynamicQueryCount(getDynamicPend(groupId, status));
    }

    private DynamicQuery getDynamicPend(long groupId, int status) {

        return dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("groupId", groupId))
                .add(RestrictionsFactoryUtil.eq("status", status));
    }

    public List<Tarefa> getPromocoesAtivasDynamicQuery(String keywords, int start, int end) {
        return tarefaLocalService.dynamicQuery(getPromocoesAtivasDynamicQuery(keywords), start, end);
    }

    private DynamicQuery getPromocoesAtivasDynamicQuery(String keywords) {

        DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.ge("dataTermino", new Date()));

        dynamicQuery.add(RestrictionsFactoryUtil.le("dataInicio", new Date()));

        if (Validator.isNotNull(keywords)) {
            Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
            disjunctionQuery.add(RestrictionsFactoryUtil.like("imagem", "%" + keywords + "%"));
            disjunctionQuery.add(RestrictionsFactoryUtil.like("nome", "%" + keywords + "%"));
            disjunctionQuery.add(RestrictionsFactoryUtil.like("descricao", "%" + keywords + "%"));
            dynamicQuery.add(disjunctionQuery);
        }
        return dynamicQuery;
    }

}