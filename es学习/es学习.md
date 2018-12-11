# Getting Started（入门指南） #
Elasticsearch 是一个高度可扩展且开源的全文检索和分析引擎。它可以让您快速且**近实时**地存储，检索以及分析海量数据。它通常用作那些具有复杂搜索功能和需求的应用的底层引擎或者技术。

## 基本概念 ##
- 1.Near Realtime（NRT 近实时）
>  Elasticsearch 是一个近实时的搜索平台。这意味着从您索引一个文档开始直到它可以被查询时会有轻微的延迟时间（通常为一秒）。

- 2.Cluster（集群）

> cluster（集群）是一个或者多个节点的集合，它们一起保存数据并且提供所有节点联合索引以及搜索功能。集群存在一个唯一的名字身份且默认为 “elasticsearch”。这个名字非常重要，因为如果节点安装时通过它自己的名字加入到集群中的话，那么一个节点只能是一个集群中的一部分。

- 3.Node（节点）
> node（节点）是一个单独的服务器，它是集群的一部分，存储数据，参与集群中的索引和搜索功能。像一个集群一样，一个节点通过一个在它启动时默认分配的一个随机的 UUID（通用唯一标识符）名称来识别。如果您不想使用默认名称您也可自定义任何节点名称。

- 4.Index（索引）
> index（索引）是具有稍微类似特征文档的集合。例如，您有一个消费者数据的索引，一个产品目录的索引，和另一个是订单数据的索引。一个索引通过名字（**必须全部是小写**）来标识，并且该名字在对 document（文档）执行 indexing（索引），search（搜索），update（更新）和 delete（删除）操作时会涉及到。

- 5.Type（类型）
> document（文档）是索引信息的基本单位。例如，您有一存储 customer（客户）数据的文档，另一个是存储 product（产品）数据的文档，还有一个是存储 order（订单）数据的文档。该文档可以使用 JSON 来表示，它是一种无处不在的互联网数据交换格式。
> 在索引/类型中，您可以存储许多文档。注意，尽管一个文档物理的存在于索引中，实际上一个文档必须被 索引/分配 给索引内的类型。

- 6.Shards & Replicas（分片 & 副本）

> Elasticsearch 提供了把 Index（索引）拆分到多个 Shard（分片）中的能力。在创建索引时，您可以简单的定义 Shard（分片）的数量。每个 Shard 本身就是一个 fully-functional（全功能的）和独立的 “Index（索引）”，（Shard）它可以存储在集群中的任何节点上。

> **Sharding（分片）非常重要两个理由是 :**
> 
-  水平的拆分/扩展。
-  分布式和并行跨 Shard 操作（可能在多个节点），从而提高了性能/吞吐量。

> 在 网络/云 环境中可能随时会故障，无论出于何种原因，在 shard/node 不知何故会离线或者消失的情况下强烈建议设置故障转移是非常有效的。为了达到这个目的，Elasticsearch 可以让您设置一个或多个索引的 Shard 副本到所谓的副本分片，或者副本中去。
> 
> **副本非常重要的两个理由是 :**
> 
-  在 shard/node 故障的情况下提供了高可用性。***为了达到这个目的，需要注意的是在原始的/主 Shard 被复制时副本的 Shard 不会被分配到相同的节点上。***
-  它可以让你水平扩展搜索量/吞吐量，因为搜索可以在所有的副本上并行执行。

- 6.Exploring Your Cluster（探索集群）





使用门槛低，开发周期短，上线快。

性能好，查询快，实时展现结果

扩容方便，快速支撑增长迅猛的数据


Elastic Stack : 
Kibana:数据探索与可视化分析
Elasticsearch：数据存储、查询与分析
Beats，Logstash：数据收集与处理

ELK：Kibana,Elasticsearch,Logstash


Search is something that any application should have !


#  Elasticsearch篇  #
- 什么是倒排索引？它的组成部分是什么？
- 常见的相关性算分方法有哪些？
- 为什么查询语句没有返回预期的文档？
- 常见的数据类型有哪些？text和keyword的区别是什么？
- 集群是如何搭建起来的？是如何实现故障转移的？
- shard具体由什么组成的？

## 常见术语 ##
- 文档Document
   
> 用户存储在es中的数据文档

- 索引Index
   
> 由具有相同字段的文档列表组成

- 节点Node
   
> 一个Elasticsearch的运行实例，是集群的构成单元

- 集群Cluster
   
> 有一个或者多个节点组成，对外提供服务

## Document ##
Json Object，由字段（Field）组成，常见数据类型如下：

- 字符串：text，keyword
- 数值型：long,integer,short,byte,double,float,half_float,scaled_float
- 布尔：boolean
- 日期：date
- 二进制：binary
- 范围类型：integer_range,float_range,long_range,double_range,date_range


每个文档有唯一的id标识

- 自行定义
- es自动生成

## Document MetaData ##
元数据，用于标注文档的相关信息

- _index:文档所在的索引名
- _type:文档所在的类型
- _id:文档唯一id
- _uid:组合id，由_type和_id组成(6.x _type不再起作用，同_id一样)
- _source:文档的原始Json数据，可以从这里获取每个字段的内容
- _all:整合所有字段内容到该字段，默认禁用

## Index ##
索引中存储具有相同结构的文档(Document)

每个索引都有自己的mapping定义，用于定义字段名和类型

一个集群可以有多个索引，比如：

  nginx日志存储的时候可以按照日期每天生成一个索引来存储

-      nginx-log-2018-01-01
-      nginx-log-2018-01-02
-      nginx-log-2018-01-03 

## Rest API ##
Elastisearch集群对外提供RESTful API

- REST:REpresentational State Transfer
- URI指定资源，如Index、Document等
- Http Method指明资源操作类型，如GET,POST,PUT,DELETE等

常用两种交互方式

- Curl命令行

    curl -XPUT 'http://localhost:9200/employment/doc/1' -i -H "Content-Type:application/json" -d'

    {
      "username":"rockybean",
      "job":"software engineer"
    },
- Kibana DevTools

## 索引API ##
es有专门的Index API，用于创建、更新、删除索引配置等
  
###  创建索引api如下： ###

	  **request:**
	
	 PUT /test_index(索引名)

	  **response：**
	
	 {
	  "acknowledge":true,
	  "shards_acknowledged":true,
	  "index":"test_index"
	 }

###  查看现有索引 ###

  	GET _cat/indices

- 删除索引api如下：

    **request:**
    
    DELETE /test_index(索引名)

    **response:**
    
    {
     "acknowledged":true
    }

###  创建文档 ###

    **指定id创建文档api如下：**
    
    **request:**

    PUT /test_index/doc/1
    {
     "username":"alfred",
     "age":1
    }
  
    **创建文档时，如果索引不存在，es会自动创建对应的index和type**

    **response:**

    {
     "_index":"test_index",
     "_type":"doc",
     "_id":"1",
     "_version":1,
     "result":"created",
     "_shards":{
          "total":2,
          "successful":1,
          "failed":0
        },
     "_seq_no":0,
     "_primary_term":1
    }

    **不指定id创建文档api如下：**

    **request:**

    POST /test_index/doc/

    {
     "username":"tom",
     "age":20
    }

    **response:**

    {
      "_index":"test_index",
      "_type":"doc",
      "_id":"Mj-Hg2ABSmWv7ZHR80a3",
      "_version":1,
     "result":"created",
     "_shards":{
          "total":2,
          "successful":1,
          "failed":0
        },
     "_seq_no":0,
     "_primary_term":1
    }


###  查询文档 ###

    1.指定要查询的文档id
    
    **request:**

    GET /test_index/doc/1

    **200 response:**
    
    {
     "_index":"test_index",
     "_type":"doc",
     "_id":"1",
     "version":"1",
     "found":true,
     "_source":{
          "username":"alfred",
          "age":1
        }
    }

    **400 response:**

    {
     "_index":"test_index",
     "_type":"doc",
     "_id":"2",
     "found":false
    }

    2.搜索所有文档，用到_search,如下：
   
    **request:**

    GET /test_index/doc/_search
    
    {
     "query":{
           "term":{
                   "_id":"1"
                  }
        }
    }
  
    **查询语句，Json格式，放在http body中发送到es**

    **response:**
     
    {
     --查询耗时，单位ms
     "took":0,  
     "time_out":false,
     "_shard":{
               "total":5,
               "successful":5,
                "skipped":0,
                "faild":0
              },
     "hits":{
             "total":1,    `--符合条件的总文档数`
             "max_score":1,
             "hits":{      --
                     "_index":"test_index",
                     "_type":"doc",
                     "_id":"1",
                     "_score":1,
                     "_source":{
                                "username":"alfred",
                                "age":1
                               }
                    }
            }
    }

###  批量创建文档API ###
es允许一次创建多个文档，从而减少网络传输开销，提升写入速率。    

endpoint为_bulk,如下：

    request
    POST _bulk
    {
     "index":{
              "_index":"test_index","_type":"doc","_id":"3"
             }
    }
    {
     "username":"alfred",
     "age":10
    }
    {
     "delete":{
               "_index":"test_index",
               "_type":"doc",
               "_id":"1"
              }
    }
    {
     "update":{"_id":"2","_index":"test_index","_type":"doc"}
    }
    {
     "doc":{"age":"20"}
    }


### 批量查询文档API ###
es允许一次查询多个文档

endpoint为_mget,如下：

    request
    GET /_mget
    {
     "docs":[
         {
          "_index":"test_index","_type":"doc","_id":"1"
         },
         {
          "_index":"test_index","_type":"doc","_id":"2"
         }
        ]
    }
  
# 第3章 Elasticsearch 篇之倒排索引与分词 #

## 搜索引擎 ##
    正排索引
       文档Id到文档内容、单词的关联关系
    倒排索引
       单词到文档Id的关联关系

### 倒排索引-查询流程 ###
查询包含"搜索引擎"的文档

- 通过倒排索引获得""对应的文档Id有1和3
- 通过正排索引查询1和3的完整内容
- 返回用户最终结果

### 倒排索引的组成 ###
倒排索引是搜索引擎的核心部分，主要包含两部分:

- 单词词典(Term Dictionary)
- 倒排列表(Posting List)

### 倒排索引-单词词典 ###
单词词典(Term Dictionary)是倒排索引的重要组成 

- 记录所有文档的单词，一般都比较大
- 记录单词到倒排列表的关联信息 

单词字典的实现一般是用B+Tree

### 倒排索引-倒排列表 ###
倒排列表(Posting List)记录了单词对应的文档集合，由倒排索引(Posting)项组成

倒排索引项(Posting)主要包含如下信息：

- 文档Id,用于获取原始信息
- 单词频率(TF,Term Frequency)，记录该单词在文档中的出现次数，用于后续相关性算分
- 位置(Position)，记录单词在文档中的分词位置(多个)，用于做词语搜索
- 偏移(Offset)，记录单词在文档的开始和结束位置，用于做高亮显示。

es存储的是一个json格式的文档，其中包含多个字段，每个字段会有自己的倒排索引。

### 分词 ###
分词是指将文本转换成一系列单词的过程，也可以叫做文本分析，在es里面称为Analysis,如下所示：

    文本：   elasticsearch是最流行的搜索引擎
    分词结果：elasticsearch   流行   搜索引擎

### 分词器 ###
分词器是es中专门处理分词的组件，英文为Analyzer,它的组成如下：

- Character Filters

    针对原始文本进行处理，比如去掉html特殊标记符
- Tokenizer

    将原始文本按照一定规则切分为单词
- Token Filters

    针对tokenizer处理的单词就行再加工，比如转小写、删除或新增等处理

### 分词器-调用顺序 ###
    Character Filters --> Tokenizer  --> Token Filters

### Analyze API ###
es提供了一个测试分词的api接口，方便验证分词效果，endpoint是_analyze

- 可以直接指定analyzer进行测试
- 可以直接指定索引中的字段进行测试
- 也可以自定义分词器进行测试

**直接指定analyzer进行测试，接口如下：**

    request
    POST _analyze
    {
     "analyzer":"standard",//分词器
     "text":"hello world!" //测试文本 
    }

    response
    {
     "tokens":[
         {
          "token":"hello",//分词结果
          "start_offset":0,//起始偏移
          "end_offset":5,//结束偏移
          "type":"<ALPHANUM>",
          "position":0    //分词位置
         },
         {
          "token":"world",//分词结果
          "start_offset":6,//起始偏移
          "end_offset":11,//结束偏移
          "type":"<ALPHANUM>",
          "position":1    //分词位置
         }      
      ]    
    }

**直接指定索引中的字段进行测试，接口如下：**

     request
     POST test_index/_analyze
     {
      "field":"username",    //测试字段
      "text":"hello world!" //测试文本
     } 
     
    response
    {
     "tokens":[
         {
          "token":"hello",//分词结果
          "start_offset":0,//起始偏移
          "end_offset":5,//结束偏移
          "type":"<ALPHANUM>",
          "position":0    //分词位置
         },
         {
          "token":"world",//分词结果
          "start_offset":6,//起始偏移
          "end_offset":11,//结束偏移
          "type":"<ALPHANUM>",
          "position":1    //分词位置
         }      
      ]    
    }

**自定义分词器进行测试，接口如下：**

     request
     POST _analyze
     {
      "tokenizer":"standard",
      "filter":["lowercase"],
      "field":"username",    //测试字段
      "text":"hello world!" //测试文本
     } 

### 预定义的分词器 ###
es自带如下分词器

- Standard
- Simple
- Whitespace
- Stop
- Keyword
- Pattern
- Language

#### Standard Analyzer ####
默认分词器

其组成：

    Tokenizer:Standar
    Token Filters:Standard,Lower case,Stop(disabled by default)

其特性：

    按词切分，支持多语言
    小写处理

#### Simple Analyzer ####
其组成：
    Tokenizer:Lower Case

其特性：

    按照非字母切分
    小写处理


#### Whitespace Analyzer ####
其组成：
    Tokenizer:Whitespace

其特性：

    按照空格切分

#### Stop Analyzer ####
Stop Word指的是语气助词等修饰性的词语，比如the、an、的、这等等

其组成：

    Tokenizer:Lower Case
    Token Filters: Stop

其特性：

    相比Simple Analyzer多了Stop Word处理

#### Keyword Analyzer ####
其组成：

    Tokenizer:Keyword

其特性：

    不分词，直接将输入作为一个单词输出

#### Pattern Analyzer ####
其组成：

    Tokenizer:Keyword
    Token Filters:Lower case,Stop(disable by default)

其特性：

    通过正则表达式自定义分割符
    默认是\W+，即非字词的符号作为分隔符

#### language Analyzer ####
提供了30+常见语言的分词器

#### 中文分词 ####
难点

    中文分词指的是将一个汉字序列切分成一个一个单独的词。在英文中，单词之间是以空格作为自然分界符，汉语中词没有一个形式上的分界符。
   
    上下文不同，分词结果迥异，比如交叉歧义问题，比如下面两种分词都合理：
    乒乓球拍/卖/完了
    乒乓球/拍卖/完了

常见分词系统

    IK：
    实现中英文单词的切分，支持ik_smart、ik_maxword等模式
    可以自定义词库，支持热更新分词词典

    jieba:
    python中最流行的分词系统，支持分词和词性标注
    支持繁体字分词、自定义词典，并行分词等

基于自然语言处理的分词系统

    Hanlp:
    由一系列模型与算法组成的Java工具包，目标是普及自然语言处理在生产环境中的应用

    THULAC：
    THU Lexical Analyzer for Chinese,由清华大学自然语言处理与社会人文计算实验室研制推出的一套中文词法分析工具包，具体中文分词和词性标注功能。

### 自定义分词 ###
自带的分词无法满足需求时，可以自定义分词通过自定义Character Filters、Tokenizer和Token Filter实现

#### Character Filters ####
    -在Tokenizer之前对原始文本进行处理，比如增加、删除或替换字符等
    -自带的如下：
       HTML Strip去除html标签和转换html实体
       Mapping进行字符替换操作
       Patter Replace进行正则匹配替代
    -会影响后续tokenizer解析的position 和 offset 信息
     
    Character Filters测试时可以采用如下api:
    request:
    POST _analyze
    {
     "tokenizer":"keyword",//keyword类型的tokenizer，可以直接看到输出结果
      "char_filter":["html_strip"],//指明要使用的char_filter
      "text":"<p>I&apos;m so <b> happy</b>!</p>"
    }
    
    response:
    {
     "tokens":[
        {
         "koken":***
                   I'm so happy!
                 ***,
         "start_offset":0,
         "end_offset":32,
         "type":"word",
         "position":0
        }
      ]
    }

#### Tokenizer ####
    -将原始文本按照一定规则切分为单词(term or token)
    -自带的如下：
        -standard按照单词进行分割
        -letter按照非字符类进行分割
        -whitespace按照空格进行分割
        -UAX URL Email按照standard分割，但不会分割邮箱和url
        -NGram 和 Edge NGram连词分割
        -Path Hierarchy按照文件路径进行切割

     Tokenizer测试时可以采用如下api:
     **request:**
     POST _analyzer
     {
      "tokenizer":"path_hierarchy",
      "text":"/one/two/three"
     }

     response:
     {
      "tokens":[
         {
          "token":"/one",
          "start_offset":0,
          "end_offset":4,
          "type":"word",
          "position":0
         },
         {
          "token":"/one/teo",
          "start_offset":0,
          "end_offset":8,
          "type":"word",
          "position":0
         },
         {
          "token":"/one/two/three",
          "start_offset":0,
          "end_offset":14,
          "type":"word",
          "position":0
         }
      ]
     }

#### Token Filters ####   
     -对于tokenizer输出的单词(term)进行增加、删除、修改等操作
     -自带如下:
         -lowercase将所有term转化为小写
         -stop删除stop words
         -NGram 和 Edge NGram连词分割

     Filters测试时可以采用如下api：

     **request：**
     POST _analyze
     {
       "text":"a Hello,word!",
       "tokenizer":"standard",
       "filter":[
            "stop", 
            "lowercase",
            {
              "type":"ngram", 
              "min_gram":4,
              "max_gram":4
            }
          ]
     }

     **response**
     {
       "tokens":[
          {
            "token":"hell",
            "start_offset":2,
            "end_offset":7,
            "type":"<ALPHANUM>",
            "position":1
          },
          {
            "token":"ello",
            "start_offset":2,
            "end_offset":7,
            "type":"<ALPHANUM>",
            "position":1
          },
          {
            "token":"worl",
            "start_offset":8,
            "end_offset":13,
            "type":"<ALPHANUM>",
            "position":2
          },
          {
            "token":"hell",
            "start_offset":8,
            "end_offset":13,
            "type":"<ALPHANUM>",
            "position":2
          }
       ]
     }
      
#### 自定义分词api ####
自定义分词需要在索引的配置中设定，如下所示：

     PUT test_index
     {
       "settings":{
            "analysis":{
                 "char_filter":{},
                 "tokenizer":{},
                 "filter":{},
                 "analyzer":{}

               }
          
          }
     }
     分词配置，可以自定义：char_filter，tokenizer，filter，analyzer

我们自定义如下的分词器

Custom Analyzer:

    Character Filter:Custom Mapping
    Tokenizer:Custom Pattern
    Token Filters:Lower case,Custom Stop

    PUT test_index2
    {
	   "settings": {
	    "analysis": {
	      "analyzer": {
	        "my_custom_analyzer": {
	          "type": "custom",
	          "char_filter": [
	            "emoticons" 
	          ],
	          "tokenizer": "punctuation", 
	          "filter": [
	            "lowercase",
	            "english_stop" 
	          ]
	        }
	      },
	      "tokenizer": {
	        "punctuation": { 
	          "type": "pattern",
	          "pattern": "[ .,!?]"
	        }
	      },
	      "char_filter": {
	        "emoticons": { 
	          "type": "mapping",
	          "mappings": [
	            ":) => _happy_",
	            ":( => _sad_"
	          ]
	        }
	      },
	      "filter": {
	        "english_stop": { 
	          "type": "stop",
	          "stopwords": "_english_"
	        }
	      }
	    }
	  }
	}

自定义分词验证
    
    POST test_index_2/_analyze
	{
	  "analyzer": "my_custom_analyzer",
	  "text":     "I'm a :) person, and you?"
	}
    

### 分词使用说明 ###
分词会在如下两个时机使用

    -创建或更新文档时(Index Time),会对相应的文档进行分词处理
    -查询时(Search Time)，会对查询语句进行分词  

#### 索引时分词 ####
索引时分词是通过配置Index Mapping中每个字段的analyzer属性实现的，如下：

    不指定分词时，使用默认standard
    PUT test_index
    {
      "mappings":{
           "doc":{ 
                "properties":{
                     "title":{
	                    "type":"text",
	                    "analyzer":"whitespace"//指定分词器
                        }
                   }
              }
         }
    }  

#### 查询时分词 ####
查询时分词的指定方式如下几种：

    查询的时候通过analyzer指定分词器
    POST test_index/_search
    {
      "query":{
          "match":{
               "message":{
                     "query":"hello",
                     "analyzer":"standard"
                  }
             }
         }
    }

    通过index mapping设置search_analyzer实现
    PUT test_index
    {
      "mappings":{
           "doc":{ 
                "properties":{
                     "title":{
	                    "type":"text",
	                    "analyzer":"whitespace",//指定分词器
                         "search_analyzer":"standard"
                        }
                   }
              }
         }
    }

ps：一般不需要特别指定特定查询时分词器，直接使用索引时分词器即可，否则会出现无法匹配的情况   

### 分词的使用意见 ###
- 明确字段是否需要分词，不需要分词的字段就将type设置为keyword，可以节省空间和提高写性能
- 善用_analyzer API，查看文档的具体分词结果
- 动手测试

# 第4章 Elasticsearch 篇之Mapping 设置 #
## Mapping ##
类似数据库中的表结构定义，主要作用如下:

- 定义Index下的字段名(Field Name)
- 定义字段的类型，比如数值型、字符串型、布尔型等
- 定义倒排索引相关配置，比如是否索引、记录position等

## 自定义Mapping ##
自定义Mapping的api如下所示：
    
    **request**
    PUT my_index
    {
     "mappings":{//mapping关键词
          "doc":{//type名称
               "properties":{
                    "title":{//字段名称
                         "type":"text"  //字段类型
                       },
                    "name":{
                         "type":"keyword"
                       },
                    "age":{
                         "type":"integer"
                       },
                  }
             }

        }
    }

Mapping中的字段类型一旦设定后，禁止直接修改，原因如下：
>     Lucene实现的倒排索引生成后不允许修改

重新建立新的索引，然后做reindex操作。
    

允许新增字段
通过dynamic参数来控制字段的新增

> true()允许自动新增字段

> false不允许自动新增字段，但文档可以正常写入，但无法对字段进行查询等操作。

> strict文档不能写入，报错

    **request**
	PUT my_index
	{
	  "mappings": {
	    "doc": {
	      "dynamic": false,
	      "properties": {
	        "name": {
	          "type": "text"
	        },
	        "profile": {
	          "dynamic": true,
	          "properties": {}
	        },
	        "works": {
	          "dynamic": "strict",
	          "properties": {
	            "name": {
	              "type": "text"
	            }
	          }
	        }
	      }
	    }
	  }
	}

### copy_to ###
> 将该字段的值复制到目标字段，实现类似_all的作用

> 不会出现在_source中，只用来搜索

    PUT my_index
	{
	  "mappings": {
	    "doc": {
	      "properties": {
	        "first_name": {
	          "type": "text",
	          "copy_to": "full_name" 
	        },
	        "last_name": {
	          "type": "text",
	          "copy_to": "full_name" 
	        },
	        "full_name": {
	          "type": "text"
	        }
	      }
	    }
	  }
	}

	PUT my_index/doc/1
	{
	  "first_name": "John",
	  "last_name": "Smith"
	}
	
	GET my_index/_search
	{
	  "query": {
	    "match": {
	      "full_name": { 
	        "query": "John Smith",
	        "operator": "and"
	      }
	    }
	  }
	}

### index ###
控制当前字段是否索引，默认为true，即记录索引，false不记录，即不可搜索

	PUT my_index
	{
	  "mappings": {
	    "doc": {
	      "properties": {
	        "cookie": {
	          "type": "text",
	          "index": false
	        }
	      }
	    }
	  }
	}
	
	PUT my_index/doc/1
	{
	  "cookie":"name=alfred"
	}
	
	GET my_index/_search
	{
	  "query":{
	    "match": {
	      "cookie": "name"
	    }
	  }
	}

    response
    {
     "error"{
         "":[
     
           "index":"my_index",
           "reason":"Cannot search on field[cookies] since it is not indexed."
            ]
       }
    }
    
### index_options ###  
 index_options用于控制倒排索引记录的内容，有如下4种配置

-    docs记录doc id
-    freqs 记录doc id 和 term frequencies
-    positions记录doc id 、term frequencies 和 term position
-    offset记录doc id 、term frequencies、term position 和 character offsets
  
> text类型默认配置为positions,其他默认为docs

> 记录内容越多，占用空间越大

index_options设定如下所示：

    PUT my_index
    {
      "mappings":{
           "properties":{
                 "cookies":{
                             "type":"text",
                             "index_options":"offsets"
                           }
              }
         }
    }

### null_value ###
当字段遇到null值时的处理策略，默认为null，即为空值，此时es会忽略该值。可以通过设定该值设定字段的默认值。

    PUT my_index
	{
	  "mappings": {
	    "my_type": {
	      "properties": {
	        "status_code": {
	          "type":       "keyword",
	          "null_value": "NULL" 
	        }
	      }
	    }
	  }
	}

### 数据类型 ###
核心数据类型

- 字符串型：text、keyword
- 数值型：long、integer、short、byte、double、float、half_float、scaled_float
- 日期类型：date
- 布尔类型：boolean
- 二进制类型：binary
- 范围类型：integer_range、float_range、double_range、date_range

复杂数据类型

- 数组类型：array
- 对象类型：object
- 嵌套类型：nested object

地理位置类型

- geo_point
- geo_shape

专用类型

- 记录ip地址ip
- 实现自动补全completion
- 记录分词数token_count
- 记录字符串hash值murmur3
- percolator
- join

多字段特性multi-fields
> 允许对同一个字段采用不同的配置，比如分词，常见例子如对人名实现拼音搜索，只需要在人名中新增一个子字段为pinyin即可

    {
     "test_index":{
           "mappings":{
                "doc":{
                     "properties":{
                         "username":{
                              "type":"text",
                              "fields":{
                                  "pinyin":{
                                      "type":"text",
                                      "anlyzer":"pinyin"
                                   }
                                }
                          }
                       }
                   }
              }
   
        }
    }

    GET test_index/_search
    {
      "query":{
         "match":{
              "username.pinyin":"hanhan"
            }
      }    
    }

### Dynamic Mapping ###
#### es可以自动识别文档字段类型，从而降低用户使用成本，如下所示： ####

    **request**
    PUT /test_index/doc/1
    {
      "username":"alfred",
      "age":1
    }
  
    **response**
    GET /test_index/_mapping
    {
      "test_index":{
            "mapping":{
                "doc":{
                   "age":{
                      "type":"long"
                    },
                    "username":{
                      "type":"text",
                      "fields":{
                         "keyword":{
                            "type":"keyword",
                            "ignore_above":256
                         }
                       }
                    }
                }
             }
   
         }
    }
    es自动识别age类型为long,username为text类型。

#### es是依靠JSON文档的字段类型来实现自动识别字段类型，支持的类型如下 ####

    JSON类型       es类型
      null          忽略
      boolean       boolean
      浮点类型       float
      整数           long
      object         object
      array        由第一个非null值的类型决定
      string       匹配日期则设为date类型(默认开启),匹配为数字的话设为float或long类型(默认关闭)，设为text类型，并给附带keyword的子字段

#### 日期的自动识别可以自行配置日期格式，以满足各种需求 ####

- 默认是["strict_date_optional_time","yyyy/MM/dd HH:mm:ss Z||yyyy/MM/dd Z"]
- strict_date_optional_time是ISO datetime的格式，完整格式类似下面：YYYY-MM-DDThh:mm:ssTZD(eg 1997-07-16T19:20:30+01:00)
- dynamic_date_formats可以自定义日期类型
- date_detection可以关闭日期自动识别的机制

#### 字符串是数字时，默认不会自动识别为整型，因为字符串中出现数字是完全合理的 ####
numeric_detection可以开启字符串中数字的自动识别，如下所示：

    **request**
    PUT my_index
    {
      "mappings":{
        "my_type":{
          "numeric_detection":true
        }
      }
    }

    PUT my_index/my_type/1
    {
      "my_float":"1.0",
      "my_integer":"1"
    }

    GET my_index/_mapping

    **response**
    {
      "my_index1":{
         "mappings":{
            "my_type":{
              "numeric_detection":true,
              "properties":{
                 "my_float":{
                    "type":"float"
                  },
                  "my_integer":{
                    "type":"long"
                  },
               }
            }
          }

      }
    }

## Dynamic Templates ##
允许根据es自动识别的整数类型、字段名等来动态设定字段类型，可以实现如下效果：

- 所有字符串类型都设定为keyword类型，即默认不分词。
- 所有以message开头的字段都设定为text类型，即分词
- 所有以long_开头的字段都设定为long类型
- 所有自动匹配为double类型的都设定为float类型，以节省空间。

## Dynamic Templates API ##
### API如下所示： ###
   
    PUT test_index
    {
      "mappings":{
        "doc":{
          "dynamic_template"[//数组，可指定过个匹配规则
            {
              "strings":{//template的名称
                "match_mapping_type":"string",//匹配规则
                "mapping":{//设置mapping信息
                  "type":"keyword"
                }
              }
            }
          ] 
        }
      }
    }

### 匹配规则一般有如下几个参数 ###

- match_mapping_type匹配es自动识别的字段类型，如boolean,long,string等
- match,unmatch匹配字段名
- path_match,path_unmath匹配路径

### 字符串默认使用keyword类型 ###
es默认会为字符串设置为text类型，并增加一个keyword的子字段
   
    PUT test_index 
    {
      "mappings":{
         "doc":{
           "dynamic_templates":[
             "string_as_keywords":{
               "match_mapping_type":"string",
               "mapping":{
                  "type":"keyword"
               }
             }
           ]
         }
       }
    }

### 以message开头的字段都设置为text类型 ###
   
    PUT test_index
    {
      "mappings":{
         "doc":{
           "dynamic_templates":[
             "string_as_keywords":{
               "match_mapping_type":"string",
               "match":"message*"
               "mapping":{
                  "type":"text"
               }
             }
           ]
         }
       }
    }

### 自定义Mapping的建议 ###
自定义Mapping的操作步骤如下：

- 1.写入一条文档到es的临时索引中，获取es自动生成mapping
- 2.修改步骤1得到的mapping，自定义相关配置
- 3.使用步骤2的mapping创建实际所需索引

## 索引模版 ##
索引模版，英文为Index Template,主要用于在新建索引时自动应用预先设定的配置，简化索引创建的操作步骤

- 可以设定索引的配置和mapping
- 可以有多个模版，根据order设置，order大的覆盖小的配置

### 索引模版API ###
索引模版API，endpoint为_template,如下所示：
   
    PUT _template/test_template //template名称
    {
      "index_patterns":["te*","bar*"],//匹配的索引名称
      "order":0,//order顺序配置
      "settings":{//索引的配置
         "number_of_shards":1
       }
      "mappings":{
        "doc":{
          "_source":{
            "enabled":false
          },
          "properties":{
             "name":{
               "type":"keyword"
             }
           }
        }
      }
    }

    PUT _template/test_template1 //template名称
    {
      "index_patterns":["test*"],//匹配的索引名称
      "order":1,//order顺序配置
      "settings":{//索引的配置
         "number_of_shards":1
       }
      "mappings":{
        "doc":{
          "_source":{
            "enabled":true
          }
        }
      }
    }

获取与删除的API如下：

- GET _template
- GET _template/test_template
- DELETE _template/test_template