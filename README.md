# Kafka Proof of Concept

Kafka is a distributed streaming platform or a distributed commit log.

## Terminologies:
1. Distributed: Kafka works as a cluster of one or more nodes that can live in different data centers, we can distribute data/load across different nodes in the kafka cluster, and it is inherently scalable, available and fault-tolerant.

2. Streaming platform: Kafka stores data as a stream of continuous records which can be processed in different methods.

3. Commit log: What happens when you push data to a kafka service is that it appends it to a stream of records, like appending log to a log file. This log can be replayed and read from any point at any time.

## Uses of Kafka:
1. Message Queue: Kafka can act as a message queue.
2. FIFO Queue
3. Pub/Sub Messaging system
4. A real-time streaming platform: This is the concept around the transfer of data between systems in continuous way, and building event-driven systems.

## Kafka Core Concepts

1. Message: The message is the atomic unit of data for kafka. When you push data to kafka, kafka saves this data as a byte array and that byte array is a message for kafka. Messages may have an associated key which is nothing but some metadata, which is used to figure out the destination partition for a message.
2. Topics: These are logical categories of messages in kafka, that is, a stream of the same type of data.
3. Partitions: This is similar to a shard in database and is the core concept of scalability in kafka. When we split data belonging to a topic into multiple streams, those smaller streams are called partitions. A partition is a fundamental unit of data storage and organization within a topic.
- Purposes:
  Scalability: With partitions, we can distribute topic's data across multiple nodes in a cluster. This enables horizontal scaling and high message throughput.
  Parallelism: Subscribers can subscribe to partitions within a topic, allowing for "concurrent" consuming of message. This improves performance by allowing concurrent processing of multiple streams.
  Ordering: Messages delivered within a particular partition are guaranteed to be the same order it was inserted.
- Points:
  Number of partitions: You typically define the number of partitions you want when creating a topic in kafka, the more the partitions the more the performance in terms of parallel processing and scalability, but with some management cost.
  Replication factor: Each partition is replicated across multiple brokers, this ensures fault tolerance in a broker fails, another broker can take over to prevent data loss.
  Offset: This is a unique identifier that tracts the position of a consumer within a partition of a topic. It essentially tells the user where it left of reading messages from the partition of that topic.

4. Producer: A producer in kafka is responsible for publishing streams of data to a topic in a kafka cluster. A core responsibility of a producer is to decide the partition to publish the data to. Depending on the configuration, the producer can decide the destination partition.
- No key is specified: When no key is specified, the kafka service would try to randomly select a partition that would balance the messages in all partitions.
- Key Specified
- Partition specified
- Custom partition logic

5. Consumer: A consumer reads messages from partitions in an ordered manner. A consumer in kafka context is responsible for reading and processing messages published to kafka topics. Consumer subscribe to topics indicating there interest in receiving messages from those topics. Consumers are responsible for periodically commiting offsets to kafka. This allows kafka to persist the last processed message by the consumer, so that the consumer can resume incase of a failures.

6. Consumer Group: This is basically a logical group of consumers that collaborate to receive messages from a single topic. When consumer group subscribes to a topic, kafka partitions the data amongst the consumers in the group. This ensures that only one partition is consumed by one consumer at a time thus avoiding duplicate processing. Benefits include that multiple consumers can consume messages from a single topic at a time, If a consumer fails to consume messages, another consumer takes over thus enabling continuous message processing, You can also add and remove consumers from a consumer group.

7. A Broker: This is a simple kafka server process that plays a crucial role in managing the distributed storage and delivery of messages. It acts as the backbone of a kafka cluster, it helps in coordinating communication between a producer and consumer. They are responsible for storing messages published by producers. Brokers manage topics within the cluster and also manage partitions within a topic. For each partition, a broker is elected as the leader and is responsible for replicating data across other replicas and handling read requests from consumers. They also manage consumer communication with the cluster.

8. Cluster: This is a group of nodes working together to provide availability, fault tolerance and scalability of the event streaming system.

9. Zookeeper: This works as a central configuration and consensus management system for kafka. It tracks the brokers, topics, partition assignment and eveyother meta data in the kafka cluster.
Kafka relies on zookeeper for distributed coordination of the kafka cluster.
  Functions:
- Cluster management: Zookeeper acts as a centralized registry for kafka. It stores information about all the brokers (servers) in the cluster.
- Configuration management: Zookeeper stores kafka's configuration params, such as number of topics for topics and retention policies.
- Topic management: Topics are created and managed in zookeeper. This includes tracking the number of partitions within a topic and their current leaders.
- Consumer Groups Management: Consumer groups (consumers working together) are coordinated through ZooKeeper. It keeps track of which consumers belong to a specific group and their offsets (positions they've read from in topics).
- Dynamic Updates: When the Kafka cluster configuration changes (e.g., adding a broker), ZooKeeper facilitates these updates by notifying all other brokers about the changes.

  Benefits of Using ZooKeeper:

- Centralized Coordination: ZooKeeper provides a single source of truth for cluster metadata and configuration.
- High Availability: ZooKeeper itself is a highly available service, ensuring Kafka can function even with broker failures.
- Scalability: ZooKeeper can handle large clusters with many brokers and topics.
- Simplified Management: ZooKeeper manages cluster configuration and coordination tasks, reducing the complexity for developers.
