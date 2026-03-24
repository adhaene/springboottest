package com.bezkoder.spring.jpa.h2.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/*
not working
 */
public class WordCount {

    public static void main(String[] args) throws Exception {
        // 1. Create the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 2. Input source
        // Option A: Read from a text file
        // DataStream<String> text = env.readTextFile("input.txt");

        // Option B: Read from a socket (for streaming test)
        // Run: nc -lk 9999  (in terminal) and type text
        DataStream<String> text = env.socketTextStream("localhost", 9999);

        // 3. Transformations: Split lines into words and count
        DataStream<Tuple2<String, Integer>> counts = text
                .flatMap((String line, Collector<Tuple2<String, Integer>> out) -> {
                    for (String word : line.split("\\s+")) {
                        if (!word.isEmpty()) {
                            out.collect(new Tuple2<>(word.toLowerCase(), 1));
                        }
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT)) // Type hint for Flink
                .keyBy(t -> t.f0) // Group by the word
                .sum(1); // Sum the counts

        // 4. Output sink
        counts.print();

        // 5. Execute the Flink job
        env.execute("Flink Word Count Example");
    }
}
