//package com.example.demo;
//
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
////import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import java.net.InetAddress;
//
//@Configuration
////@EnableElasticsearchRepositories(basePackages = {"com.example.demo"})
//public class ElasticSearchConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
//
//
////    @Value("${spring.elasticsearch.hosts}")
////    private String hostName;
////
////    @Value("${spring.elasticsearch.port}")
////    private String transport;
////
////    @Value("${spring.elasticsearch.clustername}")
////    private String clusterName;
////
////
////    @Bean
////    public TransportClient transportClient() {
////
////        logger.info("初始化开始。。。。。");
////        logger.info(hostName);
////        logger.info(transport);
////        logger.info(clusterName);
////        TransportClient client = null;
////        try {
////            TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName(hostName),
////                    Integer.valueOf(transport));
////
////            // 配置信息
////            Settings esSetting = Settings.builder().put("cluster.name", clusterName).build();
////            //配置信息Settings自定义
////            client = new PreBuiltTransportClient(esSetting);
////
////            client.addTransportAddresses(transportAddress);
////        } catch (Exception e) {
////            logger.error("elasticsearch TransportClient create error!!!", e);
////        }
////        return client;
////
////
////    }
//
//    @Bean
//    public TransportClient transportClient() {
//
//        logger.info("初始化开始。。。。。");
//        TransportClient client = null;
//        try {
//            TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),
//                    Integer.valueOf(9300));
//
//            // 配置信息
//            Settings esSetting = Settings.builder() .put("cluster.name","elasticsearch") .build();
//            //配置信息Settings自定义
//            client = new PreBuiltTransportClient(esSetting);
//
//            client.addTransportAddresses(transportAddress);
//        } catch (Exception e) {
//            logger.error("elasticsearch TransportClient create error!!!", e);
//        }
//        return client;
//
//
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchTemplate(transportClient());
////        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
//    }
//}