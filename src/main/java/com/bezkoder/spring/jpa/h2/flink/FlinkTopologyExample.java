package com.bezkoder.spring.jpa.h2.flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/*
not working
 */
public class FlinkTopologyExample {
    public static void main(String[] args) throws Exception {
        // 1. Create execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 2. Define a source (socket text stream for example)
        //DataStream<String> textStream = env.socketTextStream("localhost", 9999);
        DataStream<String> textStream = env.fromElements(
                "Apache Flink is a powerful framework",
                "Flink processes data efficiently",
                "Flink is scalable and fast"
        );
        // 3. Apply transformations
        DataStream<String> upperCaseStream = textStream
                .filter(line -> !line.trim().isEmpty()) // remove empty lines
                .map((MapFunction<String, String>) String::toUpperCase);

        // 4. Define a sink (print to console)
        upperCaseStream.print();

        // 5. Execute the topology
        env.execute("Simple Flink Topology Example");
    }
}
