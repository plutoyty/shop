package com.yty.service.impl;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ElasticSearchService
 * @Description: TODO
 * @author: yty
 * @Date: 2022/1/27 15:04
 * @Version: 1.0
 */

@Service
public class ElasticSearchService {
    private static final Logger log = LoggerFactory.getLogger(ElasticSearchService.class);

    @Autowired
    private RestHighLevelClient client;

    public String search() throws IOException {
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("fields.pk_id", "319");//这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
//         RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("fields_timestamp"); //新建range条件
//         rangeQueryBuilder.gte("2019-03-21T08:24:37.873Z"); //开始时间
//         rangeQueryBuilder.lte("2019-03-21T08:24:37.873Z"); //结束时间
//         boolBuilder.must(rangeQueryBuilder);
        boolBuilder.must(matchQueryBuilder);
        sourceBuilder.query(boolBuilder); //设置查询，可以是任何类型的QueryBuilder。
        sourceBuilder.from(0); //设置确定结果要从哪个索引开始搜索的from选项，默认为0
        sourceBuilder.size(100); //设置确定搜素命中返回数的size选项，默认为10
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS)); //设置一个可选的超时，控制允许搜索的时间。
        sourceBuilder.fetchSource(new String[] {"fields.pk_id","fields.idx_name","fields.idx_subtitle"}, new String[] {}); //第一个是获取字段，第二个是过滤的字段，默认获取全部
        SearchRequest searchRequest = new SearchRequest("test"); //索引
        searchRequest.types("doc"); //类型
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();  //SearchHits提供有关所有匹配的全局信息，例如总命中数或最高分数：
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            log.info("search -> {}",hit.getSourceAsString());
        }
        return Arrays.toString(searchHits);
    }
}
