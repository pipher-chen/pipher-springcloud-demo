package com.example.demo.service.impl;

import com.example.demo.dao.ItemRepo;
import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public <T> boolean createIndex(Class<T> clazz) {

        // 创建索引，会根据Item类的@Document注解信息来创建
         return elasticsearchTemplate.createIndex(clazz);
    }

    @Override
    public <T> boolean createMapping(Class<T> clazz) {
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        return elasticsearchTemplate.putMapping(clazz);
    }


    /**多条件查询
     * @param item
     * @return
     */
    public List<Item> MutilQuery(Item item){
        //过滤条件构造器
        BoolQueryBuilder filterQueryBuilder = QueryBuilders.boolQuery();

        filterQueryBuilder.must(QueryBuilders.matchQuery("title", item.getTitle()));
        filterQueryBuilder.must(QueryBuilders.matchQuery("brand", item.getBrand()));
        // 初始化分页参数
        int page = 0;
        int size = 3;

        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(filterQueryBuilder);
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        // 执行查询
        Page<Item> items = this.itemRepo.search(queryBuilder.build());
        return items.getContent();
    }

}
